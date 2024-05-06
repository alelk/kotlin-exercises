plugins {
  alias(libs.plugins.springBoot) apply true
  alias(libs.plugins.spring.dependencyManagement) apply true
  alias(libs.plugins.kotlin.spring) apply true
  alias(libs.plugins.kotlin.jvm)
}

dependencies {
  /* Project modules */
  implementation(project(":core"))

  /* Spring */
  implementation("org.springframework.boot:spring-boot-starter-webflux")

  /* Api docs */
  implementation(libs.springdoc.openapi.starter.webflux.ui)
  implementation(libs.springdoc.openapi.starter.webflux.api)

  /* Serialization */
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

  /* Kotlin support */
  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

  /* Test */
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
}
