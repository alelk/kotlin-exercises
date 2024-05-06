plugins {
  alias(libs.plugins.springBoot) apply true
  alias(libs.plugins.spring.dependencyManagement) apply true
  alias(libs.plugins.kotlin.spring) apply true
  alias(libs.plugins.kotlin.jvm)
}

dependencies {

  /* Spring */
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  /* Api docs */
  implementation(libs.springdoc.openapi.starter.webflux.ui)
  implementation(libs.springdoc.openapi.starter.webflux.api)

  /* Serialization */
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  /* Kotlin support */
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

  /* Ktor client */
  implementation(libs.ktor.client.core)
  implementation(libs.ktor.client.cio)
  implementation(libs.ktor.client.content.negotiation)
  implementation(libs.ktor.serialization.jackson)
}
