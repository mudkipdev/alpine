# alpine
A binary and JSON serialization library for Java.

![](https://wakatime.com/badge/github/mudkipdev/alpine.svg)

## Installation
> [!WARNING]
> I'm in the process of setting up the Maven publishing again. JSON is currently not published, and for binary you should use the `0.1.1` version instead of `0.2.0`. This notice is temporary.

### Binary

<details>
<summary>Gradle (Kotlin)</summary>
<br>

```kts
dependencies {
    implementation("dev.mudkip:alpine-binary:0.2.0")
    implementation("io.netty:netty-buffer:4.2.10.Final")
}
```

</details>

<details>
<summary>Gradle (Groovy)</summary>
<br>

```groovy
dependencies {
    implementation 'dev.mudkip:alpine-binary:0.2.0'
    implementation 'io.netty:netty-buffer:4.2.10.Final'
}
```

</details>

<details>
<summary>Maven</summary>
<br>

```xml
<dependency>
    <groupId>dev.mudkip</groupId>
    <artifactId>alpine-binary</artifactId>
    <version>0.2.0</version>
</dependency>

<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-buffer</artifactId>
    <version>4.2.10.Final</version>
</dependency>
```

</details>

### JSON

<details>
<summary>Gradle (Kotlin)</summary>
<br>

```kts
dependencies {
    implementation("dev.mudkip:alpine-json:0.2.0")
}
```

</details>

<details>
<summary>Gradle (Groovy)</summary>
<br>

```groovy
dependencies {
    implementation 'dev.mudkip:alpine-json:0.2.0'
}
```

</details>

<details>
<summary>Maven</summary>
<br>

```xml
<dependency>
    <groupId>dev.mudkip</groupId>
    <artifactId>alpine-json</artifactId>
    <version>0.2.0</version>
</dependency>
```

</details>

## Documentation
- [Binary](./binary.md)
- [JSON](./json.md)