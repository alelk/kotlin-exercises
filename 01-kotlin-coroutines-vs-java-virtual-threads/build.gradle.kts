import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.springBoot) apply false
  alias(libs.plugins.spring.dependencyManagement) apply false
  alias(libs.plugins.kotlin.spring) apply false
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.gatling)
}

java {
  sourceCompatibility = JavaVersion.VERSION_21
}

allprojects {
  group = "com.github.alelk.template-kotlin-springboot-webflux-r2dbc"
  version = "0.0.1-SNAPSHOT"

  repositories {
    mavenCentral()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs += "-Xjsr305=strict"
      jvmTarget = "21"
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}