package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines

import com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines.Workout.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

data class ActivitySummary(val steps: Int, val calories: Int, val distance: Double)

enum class FitnessGoal { BUILD_MUSCLE_STRENGTH, INCREASE_ENDURANCE }

enum class Workout { RUNNING, CYCLING, SWIMMING, WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING }

val enduranceExercises = listOf(RUNNING, CYCLING, SWIMMING)
val strengthExercises = listOf(WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING)

@RestController
@SpringBootApplication
class WorkoutSuggestionServiceWebflux(val webClient: WebClient) {

  fun fetchActivitySummary(userId: Long): Mono<ActivitySummary> =
    webClient
      .get().uri("/users/$userId/activity-summary").retrieve()
      .bodyToMono<ActivitySummary>()

  fun fetchFitnessGoals(userId: Long): Flux<FitnessGoal> =
    webClient
      .get().uri("/users/$userId/fitness-goals", HttpMethod.GET).retrieve()
      .bodyToFlux<FitnessGoal>()

  fun suggestWorkout(activitySummary: ActivitySummary, fitnessGoals: List<FitnessGoal>): Flux<Workout> =
    Flux.fromIterable(
      if (fitnessGoals.contains(FitnessGoal.INCREASE_ENDURANCE)) enduranceExercises.shuffled().take(2)
      else if (fitnessGoals.contains(FitnessGoal.BUILD_MUSCLE_STRENGTH)) strengthExercises.shuffled().take(2)
      else emptyList()
    )

  @GetMapping("/users/{userId}/workout-suggestions")
  fun getUserWorkoutSuggestions(@PathVariable userId: Long): Flux<Workout> {
    val activitySummaryMono: Mono<ActivitySummary> = fetchActivitySummary(userId)
    val fitnessGoalsMono: Mono<List<FitnessGoal>> = fetchFitnessGoals(userId).collectList()

    return Flux.zip(activitySummaryMono, fitnessGoalsMono).flatMap { r ->
      val activitySummary = r.t1
      val fitnessGoals = r.t2
      suggestWorkout(activitySummary, fitnessGoals)
    }
  }

}

fun main(args: Array<String>) {
  runApplication<WorkoutSuggestionServiceWebflux>(*args)
}