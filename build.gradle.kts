plugins {
    java
    `maven-publish`
}

group = "dev.mudkip"
version = "0.1.0"

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.1")
    compileOnly("io.netty:netty-buffer:4.1.116.Final")
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            artifactId = rootProject.name
        }
    }

    repositories {
        mavenLocal()
    }
}