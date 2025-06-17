plugins {
    id("me.champeau.jmh") version "0.7.3"
}

dependencies {
    jmhImplementation(project(":json"))
    jmhImplementation("org.openjdk.jmh:jmh-core:1.37")
    jmhImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmhImplementation("com.google.code.gson:gson:2.13.1")
}

jmh {
    warmupIterations.set(3)
    iterations.set(5)
    fork.set(1)
}