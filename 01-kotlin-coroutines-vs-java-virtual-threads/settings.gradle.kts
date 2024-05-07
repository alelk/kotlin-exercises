rootProject.name = "kotlin-coroutines-vs-java-virtual-threads"

include(
  "user-activity-service",
  "workout-suggestion-service-webmvc",
  "workout-suggestion-service-webflux",
  "workout-suggestion-service-virtual-threads",
  "workout-suggestion-service-kotlin-coroutines",
  "virtual-thread-examples",
  "kotlin-coroutines-examples",
  "load-test"
)

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}