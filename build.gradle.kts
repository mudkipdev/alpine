import com.vanniktech.maven.publish.SonatypeHost

plugins {
    java
    signing
    id("com.vanniktech.maven.publish") version "0.31.0"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "signing")
    apply(plugin = "com.vanniktech.maven.publish")

    group = "dev.mudkip"
    version = "0.2.0"
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:26.0.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.1")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.1")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.12.1")
    }

    tasks.test {
        useJUnitPlatform()
    }

    mavenPublishing {
        coordinates(group.toString(), "${rootProject.name}-${project.name}", version.toString())
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
        signAllPublications()

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