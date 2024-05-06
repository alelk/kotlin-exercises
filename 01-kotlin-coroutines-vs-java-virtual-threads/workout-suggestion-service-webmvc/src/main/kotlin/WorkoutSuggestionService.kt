package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.webmvc

import com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.webmvc.Workout.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient
import org.springframework.web.servlet.config.annotation.EnableWebMvc

data class ActivitySummary(val steps: Int, val calories: Int, val distance: Double)

enum class FitnessGoal { BUILD_MUSCLE_STRENGTH, INCREASE_ENDURANCE }

enum class Workout { RUNNING, CYCLING, SWIMMING, WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING }

val enduranceExercises = listOf(RUNNING, CYCLING, SWIMMING)
val strengthExercises = listOf(WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING)

@RestController
@SpringBootApplication
@EnableWebMvc
class UserActivityServiceWebMvc(val restTemplate: RestClient) {

  fun fetchActivitySummary(userId: Long): ActivitySummary? =
    restTemplate
      .get().uri("users/$userId/activity-summary").accept(MediaType.APPLICATION_JSON)
      .retrieve().toEntity(ActivitySummary::class.java).body

  fun fetchFitnessGoals(userId: Long): List<FitnessGoal>? =
    restTemplate
      .get().uri("users/$userId/fitness-goals", userId).accept(MediaType.APPLICATION_JSON)
      .retrieve().toEntity(object : ParameterizedTypeReference<List<FitnessGoal>>() {}).body

  fun suggestWorkout(activitySummary: ActivitySummary, fitnessGoals: List<FitnessGoal>): List<Workout> =
    if (fitnessGoals.contains(FitnessGoal.INCREASE_ENDURANCE)) enduranceExercises.shuffled().take(2)
    else if (fitnessGoals.contains(FitnessGoal.BUILD_MUSCLE_STRENGTH)) strengthExercises.shuffled().take(2)
    else emptyList()

  @GetMapping("/users/{userId}/workout-suggestions")
  fun getUserWorkoutSuggestions(@PathVariable userId: Long): List<Workout> {
    val activitySummary = fetchActivitySummary(userId) ?: return emptyList()
    val fitnessGoals = fetchFitnessGoals(userId) ?: return emptyList()
    return suggestWorkout(activitySummary, fitnessGoals)
  }

}

fun main(args: Array<String>) {
  runApplication<UserActivityServiceWebMvc>(*args)
}