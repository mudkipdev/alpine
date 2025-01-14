# alpine
A binary<sup>(JSON soon™)</sup> serialization library for Java.

![](https://wakatime.com/badge/github/mudkipdev/alpine.svg)

## Documentation
The core primitives of Alpine is a codec. A codec is something that can encode and decode an object from a byte buffer.
Netty's `ByteBuf` is used for this, however you don't need to use any other parts of Netty to take advantage of this system.

You can easily create an `Integer` codec like this:
```java
public static final BinaryCodec<Integer> INTEGER = new BinaryCodec<>() {
    @Override
    public Integer read(ByteBuf buffer) {
        return buffer.readInt();
    }

    @Override
    public void write(ByteBuf buffer, Integer value) {
        buffer.writeInt(value);
    }
};
```

### Built-in codecs
There are already many built-in codecs exposed through the `Codec` class, a full list is available below:

| Java Type   | Codec           | Notes                                                                               |
|-------------|-----------------|-------------------------------------------------------------------------------------|
| `boolean`   | `BOOLEAN`       | Encoded as `0` or `1`.                                                              |
| `byte`      | `BYTE`          |                                                                                     |
| `short`     | `SHORT`         |                                                                                     |
| `int`       | `INTEGER`       |                                                                                     |
| `int`       | `VARINT`        | [LEB128](https://en.wikipedia.org/wiki/LEB128) encoded. Uses between 1 and 5 bytes. |
| `long`      | `LONG`          |                                                                                     |
| `float`     | `FLOAT`         |                                                                                     |
| `double`    | `DOUBLE`        |                                                                                     |
| `String`    | `STRING`        | Encoded as UTF-8. Length-prefixed with a varint.                                    |
| `boolean[]` | `BOOLEAN_ARRAY` | Length prefixed with a varint.                                                      |
| `byte[]`    | `BYTE_ARRAY`    | Length prefixed with a varint.                                                      |
| `short[]`   | `SHORT_ARRAY`   | Length prefixed with a varint.                                                      |
| `int[]`     | `INTEGER_ARRAY` | Length prefixed with a varint.                                                      |
| `long[]`    | `LONG_ARRAY`    | Length prefixed with a varint.                                                      |
| `float[]`   | `FLOAT_ARRAY`   | Length prefixed with a varint.                                                      |
| `double[]`  | `DOUBLE_ARRAY`  | Length prefixed with a varint.                                                      |
| `UUID`      | `UUID`          | Encoded as two 64-bit integers.                                                     |

### Templates
Complex composite types can be created using the template syntax:

```java
public record User(String name, Gender gender, int age) {
    public static final BinaryCodec<User> CODEC = BinaryTemplate.of(
            STRING, User::name,
            Gender.CODEC, User::gender,
            INTEGER, User::age,
            // include up to 20 fields (total)
            User::new);
}
```

### Transformations
Use these methods to map a codec to another type.
- `.array()` → `T[]`
- `.list()` → `List<T>`
- `.nullable()` → `@Nullable T`
- `.optional()` → `Optional<T>`
- `.map(Function<T, U>, Function<U, T>)` → `U`

### There's more!
- Use `BinaryCodec.ordinal(Example.class)` to represent an enum.
- Use `BinaryCodec.unit(Example::new)` to represent singleton types.
- Use `BinaryCodec.map(keyCodec, valueCodec)` to represent a hash map.
- Use `BinaryCodec.either(leftCodec, rightCodec)` to represent something which can be one of two types.
- Use `BinaryCodec.of(Function<ByteBuf, T>, BiConsumer<ByteBuf, T>)` for an easier way to create a codec, especially if using the `::` syntax.
