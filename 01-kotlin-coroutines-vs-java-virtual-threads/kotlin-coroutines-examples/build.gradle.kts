plugins {
  alias(libs.plugins.kotlin.jvm)
}

dependencies {
  implementation(libs.ktor.client.core)
  implementation(libs.ktor.client.cio)
  implementation(libs.ktor.client.content.negotiation)
  implementation(libs.ktor.serialization.jackson)
  implementation("org.slf4j:slf4j-api:2.0.13")
}