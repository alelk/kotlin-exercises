package com.github.alelk.kotlin_exercises.excercise01.user_activity_service

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.config.EnableWebFlux
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

data class ActivitySummary(val steps: Int, val calories: Int, val distance: Double)

enum class FitnessGoal { BUILD_MUSCLE_STRENGTH, INCREASE_ENDURANCE }

@RestController
@SpringBootApplication
@EnableWebFlux
class UserActivityService {

  @GetMapping("/users/{userId}/activity-summary")
  suspend fun getUserActivitySummary(@PathVariable userId: Long) =
    ActivitySummary(
      steps = Random.nextInt(until = 50_000),
      calories = Random.nextInt(until = 5000),
      distance = Random.nextDouble(until = 100.0)
    ).also { delay(2.seconds) }

  @GetMapping("/users/{userId}/fitness-goals")
  suspend fun getUserFitnessGoals(@PathVariable userId: Long) =
    List((0..2).random()) { FitnessGoal.entries.random() }.also { delay(2.seconds) }
}

fun main(args: Array<String>) {
  runApplication<UserActivityService>(*args)
}
