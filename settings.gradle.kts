rootProject.name = "template-kotlin-springboot-webflux-r2dbc"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}