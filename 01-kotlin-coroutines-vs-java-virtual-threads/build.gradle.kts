import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.springBoot) apply false
  alias(libs.plugins.spring.dependencyManagement) apply false
  alias(libs.plugins.kotlin.spring) apply false
  alias(libs.plugins.kotlin.jvm)
}

java {
  sourceCompatibility = JavaVersion.VERSION_23
}

allprojects {
  group = "com.github.alelk.template-kotlin-springboot-webflux-r2dbc"
  version = "0.0.1-SNAPSHOT"

  repositories {
    mavenCentral()
  }

  tasks.withType<JavaCompile> {
    options.release.set(23)
  }

  tasks.withType<KotlinCompile> {
    compilerOptions {
      jvmTarget = JvmTarget.JVM_23
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}