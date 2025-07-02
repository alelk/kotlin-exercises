plugins {
  alias(libs.plugins.springBoot) apply true
  alias(libs.plugins.spring.dependencyManagement) apply true
  alias(libs.plugins.kotlin.spring) apply true
  alias(libs.plugins.kotlin.jvm)
}

dependencies {

  /* Spring */
  implementation("org.springframework.boot:spring-boot-starter-web")

  /* Api docs */
  implementation(libs.springdoc.openapi.starter.webmvc.ui)
  implementation(libs.springdoc.openapi.starter.webmvc.api)

  /* Http Client */
  implementation(libs.httpclient)

  /* Serialization */
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}
