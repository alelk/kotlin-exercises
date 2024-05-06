rootProject.name = "template-kotlin-springboot-webflux-r2dbc"

include("core", "app")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}