plugins {
    java
    `maven-publish`
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(rootProject)
        compileOnly("org.jetbrains:annotations:26.0.2")
    }

    publishing {
        publications {
            create<MavenPublication>("library") {
                from(components["java"])
            }
        }

        repositories {
            mavenLocal()
        }
    }
}