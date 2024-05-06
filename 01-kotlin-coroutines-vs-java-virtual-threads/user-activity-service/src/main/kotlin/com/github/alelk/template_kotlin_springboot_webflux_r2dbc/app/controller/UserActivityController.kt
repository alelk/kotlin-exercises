package com.github.alelk.template_kotlin_springboot_webflux_r2dbc.app.controller

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

data class ActivitySummary(val steps: Int, val calories: Int, val distance: Double)

enum class FitnessGoal { LOSE_WEIGHT, INCREASE_ENDURANCE }

@RestController
@RequestMapping("/users")
class UserActivityController {

  @GetMapping("/{userId}/activity-summary")
  suspend fun getUserActivitySummary(userId: Long) =
    ActivitySummary(
      steps = Random.nextInt(until = 50_000),
      calories = Random.nextInt(until = 5000),
      distance = Random.nextDouble(until = 100.0)
    ).also { delay(1.seconds) }

  @GetMapping("/{userId}/fitness-goals")
  suspend fun getUserFitnessGoals(userId: Long) =
    List((0..2).random()) { FitnessGoal.entries.random() }.also { delay(1.seconds) }
}