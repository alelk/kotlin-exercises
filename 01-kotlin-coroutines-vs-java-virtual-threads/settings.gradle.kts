rootProject.name = "kotlin-coroutines-vs-java-virtual-threads"

include(
  "user-activity-service",
  "workout-suggestion-service-webmvc"
)

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}