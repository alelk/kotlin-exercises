rootProject.name = "kotlin-coroutines-vs-java-virtual-threads"

include("user-activity-service")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}