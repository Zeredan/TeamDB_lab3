pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "teamBd_lab3"
include(":app")
include(":DBSelectionFeature")
include(":DBChangeDataFeature")
include(":core")
include(":InteractionFormFeature")
