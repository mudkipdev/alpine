plugins {
    id("me.champeau.jmh") version "0.7.3"
}

dependencies {
    jmhImplementation(project(":json"))
    jmhImplementation("org.openjdk.jmh:jmh-core:1.37")
    jmhImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    jmhImplementation("com.google.code.gson:gson:2.13.2")
    jmhImplementation("tools.jackson.core:jackson-databind:3.0.4")
    jmhImplementation("com.alibaba.fastjson2:fastjson2:2.0.60")
}

jmh {
    warmupIterations.set(5)
    iterations.set(5)
    fork.set(1)
}