package com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines

import com.github.alelk.kotlin_exercises.excercise01.workout_suggestion_service.kotlin_coroutines.Workout.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

data class ActivitySummary(val steps: Int, val calories: Int, val distance: Double)

enum class FitnessGoal { BUILD_MUSCLE_STRENGTH, INCREASE_ENDURANCE }

enum class Workout { RUNNING, CYCLING, SWIMMING, WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING }

val enduranceExercises = listOf(RUNNING, CYCLING, SWIMMING)
val strengthExercises = listOf(WEIGHTLIFTING, BODYWEIGHT_EXERCISES, RESISTANCE_TRAINING)

@RestController
@SpringBootApplication
class WorkoutSuggestionServiceKotlinCoroutines(val httpClient: HttpClient) {

  suspend fun fetchActivitySummary(userId: Long): ActivitySummary =
    httpClient.get("/users/$userId/activity-summary") { header("Accept", "application/json") }.body()

  suspend fun fetchFitnessGoals(userId: Long): List<FitnessGoal> =
    httpClient.get("/users/$userId/fitness-goals") { header("Accept", "application/json") }.body()

  suspend fun suggestWorkout(activitySummary: ActivitySummary, fitnessGoals: List<FitnessGoal>): List<Workout> =
    if (fitnessGoals.contains(FitnessGoal.INCREASE_ENDURANCE)) enduranceExercises.shuffled().take(2)
    else if (fitnessGoals.contains(FitnessGoal.BUILD_MUSCLE_STRENGTH)) strengthExercises.shuffled().take(2)
    else emptyList()

  @GetMapping("/users/{userId}/workout-suggestions")
  suspend fun getUserWorkoutSuggestions(@PathVariable userId: Long): List<Workout> =
    coroutineScope {
      val activitySummaryDeferred: Deferred<ActivitySummary> = async { fetchActivitySummary(userId) }
      val fitnessGoalsDeferred: Deferred<List<FitnessGoal>> = async { fetchFitnessGoals(userId) }

      suggestWorkout(activitySummaryDeferred.await(), fitnessGoalsDeferred.await())
    }

}

fun main(args: Array<String>) {
  runApplication<WorkoutSuggestionServiceKotlinCoroutines>(*args)
}