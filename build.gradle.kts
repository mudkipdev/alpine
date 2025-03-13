plugins {
    java
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(rootProject)
        compileOnly("org.jetbrains:annotations:26.0.2")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.12.0")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.12.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.12.0")
    }

    tasks.test {
        useJUnitPlatform()
    }
}