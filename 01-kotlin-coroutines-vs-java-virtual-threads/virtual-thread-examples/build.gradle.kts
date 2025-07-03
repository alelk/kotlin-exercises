plugins {
  alias(libs.plugins.kotlin.jvm)
}

dependencies {
  implementation(libs.kotlinx.coroutines.core)
}

tasks.withType<JavaCompile> {
  options.compilerArgs.add("--enable-preview")
}

tasks.withType<Test> {
  jvmArgs("--enable-preview")
}

tasks.withType<JavaExec> {
  jvmArgs("--enable-preview")
}