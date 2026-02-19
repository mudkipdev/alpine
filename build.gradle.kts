plugins {
    java
    id("com.vanniktech.maven.publish") version "0.36.0"
}

fun Project.applyPublishingSettings() {
    apply(plugin = "com.vanniktech.maven.publish")

    mavenPublishing {
        coordinates(group.toString(), "${rootProject.name}-${project.name}", version.toString())
        publishToMavenCentral()

        if (!gradle.startParameter.taskNames.any { it.contains("publishToMavenLocal") }) {
            signAllPublications()
        }

        pom {
            name = project.name
            description = project.description
            url = "https://github.com/mudkipdev/alpine"

            licenses {
                license {
                    name = "MIT"
                    url = "https://github.com/mudkipdev/alpine/blob/main/LICENSE"
                }
            }

            developers {
                developer {
                    name = "mudkip"
                    id = "mudkipdev"
                    email = "mudkip@mudkip.dev"
                    url = "https://mudkip.dev"
                }
            }

            scm {
                url = "https://github.com/mudkipdev/alpine"
                connection = "scm:git:git://github.com/mudkipdev/alpine.git"
                developerConnection = "scm:git:ssh://git@github.com/mudkipdev/alpine.git"
            }
        }
    }
}

subprojects {
    apply(plugin = "java")

    group = "dev.mudkip"
    version = "0.2.0"
    java.toolchain.languageVersion = JavaLanguageVersion.of(25)

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.1")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.13.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.13.1")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.13.1")
    }

    tasks.test {
        useJUnitPlatform()
    }

    if (name in setOf("binary", "json")) {
        applyPublishingSettings()
    }
}