package alpine.json.codec;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public final class CodecBuilder {
    private CodecBuilder() {

    }

    public record Field<F, T>(@Nullable String key, Codec<F> codec, Function<T, F> getter) {
        public static <F, T> Field<F, T> flat(Codec<F> codec, Function<T, F> getter) {
            return new Field<>(null, codec, getter);
        }

        public <R> F decodeFrom(Transcoder<R> transcoder, R object) {
            return key != null
                    ? codec.decode(transcoder, transcoder.decodeObjectField(object, key))
                    : codec.decode(transcoder, object);
        }

        public <R> void encodeTo(Transcoder<R> transcoder, T value, Map<String, R> fields) {
            var encoded = codec.encode(transcoder, getter.apply(value));
            if (key != null) {
                fields.put(key, encoded);
            } else {
                transcoder.decodeObject(encoded).forEach(fields::put);
            }
        }
    }

    public static <T> _0<T> builder() {
        return new _0<>();
    }

    public interface Constructor0<R> {
        R construct();
    }

    public interface Constructor1<R, F1> {
        R construct(F1 f1);
    }

    public interface Constructor2<R, F1, F2> {
        R construct(F1 f1, F2 f2);
    }

    public interface Constructor3<R, F1, F2, F3> {
        R construct(F1 f1, F2 f2, F3 f3);
    }

    public interface Constructor4<R, F1, F2, F3, F4> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4);
    }

    public interface Constructor5<R, F1, F2, F3, F4, F5> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5);
    }

    public interface Constructor6<R, F1, F2, F3, F4, F5, F6> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6);
    }

    public interface Constructor7<R, F1, F2, F3, F4, F5, F6, F7> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7);
    }

    public interface Constructor8<R, F1, F2, F3, F4, F5, F6, F7, F8> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8);
    }

    public interface Constructor9<R, F1, F2, F3, F4, F5, F6, F7, F8, F9> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9);
    }

    public interface Constructor10<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10);
    }

    public interface Constructor11<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11);
    }

    public interface Constructor12<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11, F12 f12);
    }

    public interface Constructor13<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11, F12 f12, F13 f13);
    }

    public interface Constructor14<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11, F12 f12, F13 f13, F14 f14);
    }

    public interface Constructor15<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11, F12 f12, F13 f13, F14 f14, F15 f15);
    }

    public interface Constructor16<R, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> {
        R construct(F1 f1, F2 f2, F3 f3, F4 f4, F5 f5, F6 f6, F7 f7, F8 f8, F9 f9, F10 f10, F11 f11, F12 f12, F13 f13, F14 f14, F15 f15, F16 f16);
    }

    public static final class _0<T> {
        _0() {

        }

        public Codec<T> build(Constructor0<T> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    return constructor.construct();
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    return transcoder.encodeObject(Map.of());
                }
            };
        }

        public <F1> _1<T, F1> with(String key, Codec<F1> codec, Function<T, F1> getter) {
            return new _1<>(new Field<>(key, codec, getter));
        }

        public <F1> _1<T, F1> with(FlatCodec<F1> codec, Function<T, F1> getter) {
            return new _1<>(Field.flat(codec.codec(), getter));
        }
    }

    public static final class _1<T, F1> {
        private final Field<F1, T> field1;

        _1(Field<F1, T> field1) {
            this.field1 = field1;
        }

        public Codec<T> build(Constructor1<T, F1> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _1.this.field1.decodeFrom(transcoder, value);
                    return constructor.construct(f1);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _1.this.field1.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F2> _2<T, F1, F2> with(String key, Codec<F2> codec, Function<T, F2> getter) {
            return new _2<>(this.field1, new Field<>(key, codec, getter));
        }

        public <F2> _2<T, F1, F2> with(FlatCodec<F2> codec, Function<T, F2> getter) {
            return new _2<>(this.field1, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _2<T, F1, F2> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;

        _2(Field<F1, T> field1, Field<F2, T> field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        public Codec<T> build(Constructor2<T, F1, F2> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _2.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _2.this.field2.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _2.this.field1.encodeTo(transcoder, value, fields);
                    _2.this.field2.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F3> _3<T, F1, F2, F3> with(String key, Codec<F3> codec, Function<T, F3> getter) {
            return new _3<>(this.field1, this.field2, new Field<>(key, codec, getter));
        }

        public <F3> _3<T, F1, F2, F3> with(FlatCodec<F3> codec, Function<T, F3> getter) {
            return new _3<>(this.field1, this.field2, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _3<T, F1, F2, F3> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;

        _3(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        public Codec<T> build(Constructor3<T, F1, F2, F3> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _3.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _3.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _3.this.field3.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _3.this.field1.encodeTo(transcoder, value, fields);
                    _3.this.field2.encodeTo(transcoder, value, fields);
                    _3.this.field3.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F4> _4<T, F1, F2, F3, F4> with(String key, Codec<F4> codec, Function<T, F4> getter) {
            return new _4<>(this.field1, this.field2, this.field3, new Field<>(key, codec, getter));
        }

        public <F4> _4<T, F1, F2, F3, F4> with(FlatCodec<F4> codec, Function<T, F4> getter) {
            return new _4<>(this.field1, this.field2, this.field3, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _4<T, F1, F2, F3, F4> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;

        _4(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
        }

        public Codec<T> build(Constructor4<T, F1, F2, F3, F4> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _4.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _4.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _4.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _4.this.field4.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _4.this.field1.encodeTo(transcoder, value, fields);
                    _4.this.field2.encodeTo(transcoder, value, fields);
                    _4.this.field3.encodeTo(transcoder, value, fields);
                    _4.this.field4.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F5> _5<T, F1, F2, F3, F4, F5> with(String key, Codec<F5> codec, Function<T, F5> getter) {
            return new _5<>(this.field1, this.field2, this.field3, this.field4, new Field<>(key, codec, getter));
        }

        public <F5> _5<T, F1, F2, F3, F4, F5> with(FlatCodec<F5> codec, Function<T, F5> getter) {
            return new _5<>(this.field1, this.field2, this.field3, this.field4, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _5<T, F1, F2, F3, F4, F5> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;

        _5(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
        }

        public Codec<T> build(Constructor5<T, F1, F2, F3, F4, F5> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _5.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _5.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _5.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _5.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _5.this.field5.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _5.this.field1.encodeTo(transcoder, value, fields);
                    _5.this.field2.encodeTo(transcoder, value, fields);
                    _5.this.field3.encodeTo(transcoder, value, fields);
                    _5.this.field4.encodeTo(transcoder, value, fields);
                    _5.this.field5.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F6> _6<T, F1, F2, F3, F4, F5, F6> with(String key, Codec<F6> codec, Function<T, F6> getter) {
            return new _6<>(this.field1, this.field2, this.field3, this.field4, this.field5, new Field<>(key, codec, getter));
        }

        public <F6> _6<T, F1, F2, F3, F4, F5, F6> with(FlatCodec<F6> codec, Function<T, F6> getter) {
            return new _6<>(this.field1, this.field2, this.field3, this.field4, this.field5, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _6<T, F1, F2, F3, F4, F5, F6> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;

        _6(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
        }

        public Codec<T> build(Constructor6<T, F1, F2, F3, F4, F5, F6> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _6.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _6.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _6.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _6.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _6.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _6.this.field6.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _6.this.field1.encodeTo(transcoder, value, fields);
                    _6.this.field2.encodeTo(transcoder, value, fields);
                    _6.this.field3.encodeTo(transcoder, value, fields);
                    _6.this.field4.encodeTo(transcoder, value, fields);
                    _6.this.field5.encodeTo(transcoder, value, fields);
                    _6.this.field6.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F7> _7<T, F1, F2, F3, F4, F5, F6, F7> with(String key, Codec<F7> codec, Function<T, F7> getter) {
            return new _7<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, new Field<>(key, codec, getter));
        }

        public <F7> _7<T, F1, F2, F3, F4, F5, F6, F7> with(FlatCodec<F7> codec, Function<T, F7> getter) {
            return new _7<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _7<T, F1, F2, F3, F4, F5, F6, F7> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;

        _7(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
        }

        public Codec<T> build(Constructor7<T, F1, F2, F3, F4, F5, F6, F7> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _7.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _7.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _7.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _7.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _7.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _7.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _7.this.field7.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _7.this.field1.encodeTo(transcoder, value, fields);
                    _7.this.field2.encodeTo(transcoder, value, fields);
                    _7.this.field3.encodeTo(transcoder, value, fields);
                    _7.this.field4.encodeTo(transcoder, value, fields);
                    _7.this.field5.encodeTo(transcoder, value, fields);
                    _7.this.field6.encodeTo(transcoder, value, fields);
                    _7.this.field7.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F8> _8<T, F1, F2, F3, F4, F5, F6, F7, F8> with(String key, Codec<F8> codec, Function<T, F8> getter) {
            return new _8<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, new Field<>(key, codec, getter));
        }

        public <F8> _8<T, F1, F2, F3, F4, F5, F6, F7, F8> with(FlatCodec<F8> codec, Function<T, F8> getter) {
            return new _8<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _8<T, F1, F2, F3, F4, F5, F6, F7, F8> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;

        _8(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
        }

        public Codec<T> build(Constructor8<T, F1, F2, F3, F4, F5, F6, F7, F8> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _8.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _8.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _8.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _8.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _8.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _8.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _8.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _8.this.field8.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _8.this.field1.encodeTo(transcoder, value, fields);
                    _8.this.field2.encodeTo(transcoder, value, fields);
                    _8.this.field3.encodeTo(transcoder, value, fields);
                    _8.this.field4.encodeTo(transcoder, value, fields);
                    _8.this.field5.encodeTo(transcoder, value, fields);
                    _8.this.field6.encodeTo(transcoder, value, fields);
                    _8.this.field7.encodeTo(transcoder, value, fields);
                    _8.this.field8.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F9> _9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> with(String key, Codec<F9> codec, Function<T, F9> getter) {
            return new _9<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, new Field<>(key, codec, getter));
        }

        public <F9> _9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> with(FlatCodec<F9> codec, Function<T, F9> getter) {
            return new _9<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;

        _9(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
        }

        public Codec<T> build(Constructor9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _9.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _9.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _9.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _9.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _9.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _9.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _9.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _9.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _9.this.field9.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _9.this.field1.encodeTo(transcoder, value, fields);
                    _9.this.field2.encodeTo(transcoder, value, fields);
                    _9.this.field3.encodeTo(transcoder, value, fields);
                    _9.this.field4.encodeTo(transcoder, value, fields);
                    _9.this.field5.encodeTo(transcoder, value, fields);
                    _9.this.field6.encodeTo(transcoder, value, fields);
                    _9.this.field7.encodeTo(transcoder, value, fields);
                    _9.this.field8.encodeTo(transcoder, value, fields);
                    _9.this.field9.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F10> _10<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> with(String key, Codec<F10> codec, Function<T, F10> getter) {
            return new _10<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, new Field<>(key, codec, getter));
        }

        public <F10> _10<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> with(FlatCodec<F10> codec, Function<T, F10> getter) {
            return new _10<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _10<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;

        _10(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
        }

        public Codec<T> build(Constructor10<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _10.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _10.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _10.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _10.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _10.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _10.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _10.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _10.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _10.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _10.this.field10.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _10.this.field1.encodeTo(transcoder, value, fields);
                    _10.this.field2.encodeTo(transcoder, value, fields);
                    _10.this.field3.encodeTo(transcoder, value, fields);
                    _10.this.field4.encodeTo(transcoder, value, fields);
                    _10.this.field5.encodeTo(transcoder, value, fields);
                    _10.this.field6.encodeTo(transcoder, value, fields);
                    _10.this.field7.encodeTo(transcoder, value, fields);
                    _10.this.field8.encodeTo(transcoder, value, fields);
                    _10.this.field9.encodeTo(transcoder, value, fields);
                    _10.this.field10.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F11> _11<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> with(String key, Codec<F11> codec, Function<T, F11> getter) {
            return new _11<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, new Field<>(key, codec, getter));
        }

        public <F11> _11<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> with(FlatCodec<F11> codec, Function<T, F11> getter) {
            return new _11<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _11<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;

        _11(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
        }

        public Codec<T> build(Constructor11<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _11.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _11.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _11.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _11.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _11.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _11.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _11.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _11.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _11.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _11.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _11.this.field11.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _11.this.field1.encodeTo(transcoder, value, fields);
                    _11.this.field2.encodeTo(transcoder, value, fields);
                    _11.this.field3.encodeTo(transcoder, value, fields);
                    _11.this.field4.encodeTo(transcoder, value, fields);
                    _11.this.field5.encodeTo(transcoder, value, fields);
                    _11.this.field6.encodeTo(transcoder, value, fields);
                    _11.this.field7.encodeTo(transcoder, value, fields);
                    _11.this.field8.encodeTo(transcoder, value, fields);
                    _11.this.field9.encodeTo(transcoder, value, fields);
                    _11.this.field10.encodeTo(transcoder, value, fields);
                    _11.this.field11.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F12> _12<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> with(String key, Codec<F12> codec, Function<T, F12> getter) {
            return new _12<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, new Field<>(key, codec, getter));
        }

        public <F12> _12<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> with(FlatCodec<F12> codec, Function<T, F12> getter) {
            return new _12<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _12<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;
        private final Field<F12, T> field12;

        _12(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11, Field<F12, T> field12) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
            this.field12 = field12;
        }

        public Codec<T> build(Constructor12<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _12.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _12.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _12.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _12.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _12.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _12.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _12.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _12.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _12.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _12.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _12.this.field11.decodeFrom(transcoder, value);
                    F12 f12 = _12.this.field12.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _12.this.field1.encodeTo(transcoder, value, fields);
                    _12.this.field2.encodeTo(transcoder, value, fields);
                    _12.this.field3.encodeTo(transcoder, value, fields);
                    _12.this.field4.encodeTo(transcoder, value, fields);
                    _12.this.field5.encodeTo(transcoder, value, fields);
                    _12.this.field6.encodeTo(transcoder, value, fields);
                    _12.this.field7.encodeTo(transcoder, value, fields);
                    _12.this.field8.encodeTo(transcoder, value, fields);
                    _12.this.field9.encodeTo(transcoder, value, fields);
                    _12.this.field10.encodeTo(transcoder, value, fields);
                    _12.this.field11.encodeTo(transcoder, value, fields);
                    _12.this.field12.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F13> _13<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> with(String key, Codec<F13> codec, Function<T, F13> getter) {
            return new _13<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, new Field<>(key, codec, getter));
        }

        public <F13> _13<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> with(FlatCodec<F13> codec, Function<T, F13> getter) {
            return new _13<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _13<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;
        private final Field<F12, T> field12;
        private final Field<F13, T> field13;

        _13(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11, Field<F12, T> field12, Field<F13, T> field13) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
            this.field12 = field12;
            this.field13 = field13;
        }

        public Codec<T> build(Constructor13<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _13.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _13.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _13.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _13.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _13.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _13.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _13.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _13.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _13.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _13.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _13.this.field11.decodeFrom(transcoder, value);
                    F12 f12 = _13.this.field12.decodeFrom(transcoder, value);
                    F13 f13 = _13.this.field13.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _13.this.field1.encodeTo(transcoder, value, fields);
                    _13.this.field2.encodeTo(transcoder, value, fields);
                    _13.this.field3.encodeTo(transcoder, value, fields);
                    _13.this.field4.encodeTo(transcoder, value, fields);
                    _13.this.field5.encodeTo(transcoder, value, fields);
                    _13.this.field6.encodeTo(transcoder, value, fields);
                    _13.this.field7.encodeTo(transcoder, value, fields);
                    _13.this.field8.encodeTo(transcoder, value, fields);
                    _13.this.field9.encodeTo(transcoder, value, fields);
                    _13.this.field10.encodeTo(transcoder, value, fields);
                    _13.this.field11.encodeTo(transcoder, value, fields);
                    _13.this.field12.encodeTo(transcoder, value, fields);
                    _13.this.field13.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F14> _14<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> with(String key, Codec<F14> codec, Function<T, F14> getter) {
            return new _14<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, new Field<>(key, codec, getter));
        }

        public <F14> _14<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> with(FlatCodec<F14> codec, Function<T, F14> getter) {
            return new _14<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _14<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;
        private final Field<F12, T> field12;
        private final Field<F13, T> field13;
        private final Field<F14, T> field14;

        _14(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11, Field<F12, T> field12, Field<F13, T> field13, Field<F14, T> field14) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
            this.field12 = field12;
            this.field13 = field13;
            this.field14 = field14;
        }

        public Codec<T> build(Constructor14<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _14.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _14.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _14.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _14.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _14.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _14.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _14.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _14.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _14.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _14.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _14.this.field11.decodeFrom(transcoder, value);
                    F12 f12 = _14.this.field12.decodeFrom(transcoder, value);
                    F13 f13 = _14.this.field13.decodeFrom(transcoder, value);
                    F14 f14 = _14.this.field14.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _14.this.field1.encodeTo(transcoder, value, fields);
                    _14.this.field2.encodeTo(transcoder, value, fields);
                    _14.this.field3.encodeTo(transcoder, value, fields);
                    _14.this.field4.encodeTo(transcoder, value, fields);
                    _14.this.field5.encodeTo(transcoder, value, fields);
                    _14.this.field6.encodeTo(transcoder, value, fields);
                    _14.this.field7.encodeTo(transcoder, value, fields);
                    _14.this.field8.encodeTo(transcoder, value, fields);
                    _14.this.field9.encodeTo(transcoder, value, fields);
                    _14.this.field10.encodeTo(transcoder, value, fields);
                    _14.this.field11.encodeTo(transcoder, value, fields);
                    _14.this.field12.encodeTo(transcoder, value, fields);
                    _14.this.field13.encodeTo(transcoder, value, fields);
                    _14.this.field14.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F15> _15<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> with(String key, Codec<F15> codec, Function<T, F15> getter) {
            return new _15<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, new Field<>(key, codec, getter));
        }

        public <F15> _15<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> with(FlatCodec<F15> codec, Function<T, F15> getter) {
            return new _15<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _15<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;
        private final Field<F12, T> field12;
        private final Field<F13, T> field13;
        private final Field<F14, T> field14;
        private final Field<F15, T> field15;

        _15(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11, Field<F12, T> field12, Field<F13, T> field13, Field<F14, T> field14, Field<F15, T> field15) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
            this.field12 = field12;
            this.field13 = field13;
            this.field14 = field14;
            this.field15 = field15;
        }

        public Codec<T> build(Constructor15<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _15.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _15.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _15.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _15.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _15.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _15.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _15.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _15.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _15.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _15.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _15.this.field11.decodeFrom(transcoder, value);
                    F12 f12 = _15.this.field12.decodeFrom(transcoder, value);
                    F13 f13 = _15.this.field13.decodeFrom(transcoder, value);
                    F14 f14 = _15.this.field14.decodeFrom(transcoder, value);
                    F15 f15 = _15.this.field15.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _15.this.field1.encodeTo(transcoder, value, fields);
                    _15.this.field2.encodeTo(transcoder, value, fields);
                    _15.this.field3.encodeTo(transcoder, value, fields);
                    _15.this.field4.encodeTo(transcoder, value, fields);
                    _15.this.field5.encodeTo(transcoder, value, fields);
                    _15.this.field6.encodeTo(transcoder, value, fields);
                    _15.this.field7.encodeTo(transcoder, value, fields);
                    _15.this.field8.encodeTo(transcoder, value, fields);
                    _15.this.field9.encodeTo(transcoder, value, fields);
                    _15.this.field10.encodeTo(transcoder, value, fields);
                    _15.this.field11.encodeTo(transcoder, value, fields);
                    _15.this.field12.encodeTo(transcoder, value, fields);
                    _15.this.field13.encodeTo(transcoder, value, fields);
                    _15.this.field14.encodeTo(transcoder, value, fields);
                    _15.this.field15.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F16> _16<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> with(String key, Codec<F16> codec, Function<T, F16> getter) {
            return new _16<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, new Field<>(key, codec, getter));
        }

        public <F16> _16<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> with(FlatCodec<F16> codec, Function<T, F16> getter) {
            return new _16<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, Field.flat(codec.codec(), getter));
        }
    }

    public static final class _16<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> {
        private final Field<F1, T> field1;
        private final Field<F2, T> field2;
        private final Field<F3, T> field3;
        private final Field<F4, T> field4;
        private final Field<F5, T> field5;
        private final Field<F6, T> field6;
        private final Field<F7, T> field7;
        private final Field<F8, T> field8;
        private final Field<F9, T> field9;
        private final Field<F10, T> field10;
        private final Field<F11, T> field11;
        private final Field<F12, T> field12;
        private final Field<F13, T> field13;
        private final Field<F14, T> field14;
        private final Field<F15, T> field15;
        private final Field<F16, T> field16;

        _16(Field<F1, T> field1, Field<F2, T> field2, Field<F3, T> field3, Field<F4, T> field4, Field<F5, T> field5, Field<F6, T> field6, Field<F7, T> field7, Field<F8, T> field8, Field<F9, T> field9, Field<F10, T> field10, Field<F11, T> field11, Field<F12, T> field12, Field<F13, T> field13, Field<F14, T> field14, Field<F15, T> field15, Field<F16, T> field16) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
            this.field7 = field7;
            this.field8 = field8;
            this.field9 = field9;
            this.field10 = field10;
            this.field11 = field11;
            this.field12 = field12;
            this.field13 = field13;
            this.field14 = field14;
            this.field15 = field15;
            this.field16 = field16;
        }

        public Codec<T> build(Constructor16<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> constructor) {
            return new Codec<>() {
                @Override
                public <R> T decode(Transcoder<R> transcoder, R value) {
                    F1 f1 = _16.this.field1.decodeFrom(transcoder, value);
                    F2 f2 = _16.this.field2.decodeFrom(transcoder, value);
                    F3 f3 = _16.this.field3.decodeFrom(transcoder, value);
                    F4 f4 = _16.this.field4.decodeFrom(transcoder, value);
                    F5 f5 = _16.this.field5.decodeFrom(transcoder, value);
                    F6 f6 = _16.this.field6.decodeFrom(transcoder, value);
                    F7 f7 = _16.this.field7.decodeFrom(transcoder, value);
                    F8 f8 = _16.this.field8.decodeFrom(transcoder, value);
                    F9 f9 = _16.this.field9.decodeFrom(transcoder, value);
                    F10 f10 = _16.this.field10.decodeFrom(transcoder, value);
                    F11 f11 = _16.this.field11.decodeFrom(transcoder, value);
                    F12 f12 = _16.this.field12.decodeFrom(transcoder, value);
                    F13 f13 = _16.this.field13.decodeFrom(transcoder, value);
                    F14 f14 = _16.this.field14.decodeFrom(transcoder, value);
                    F15 f15 = _16.this.field15.decodeFrom(transcoder, value);
                    F16 f16 = _16.this.field16.decodeFrom(transcoder, value);
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    _16.this.field1.encodeTo(transcoder, value, fields);
                    _16.this.field2.encodeTo(transcoder, value, fields);
                    _16.this.field3.encodeTo(transcoder, value, fields);
                    _16.this.field4.encodeTo(transcoder, value, fields);
                    _16.this.field5.encodeTo(transcoder, value, fields);
                    _16.this.field6.encodeTo(transcoder, value, fields);
                    _16.this.field7.encodeTo(transcoder, value, fields);
                    _16.this.field8.encodeTo(transcoder, value, fields);
                    _16.this.field9.encodeTo(transcoder, value, fields);
                    _16.this.field10.encodeTo(transcoder, value, fields);
                    _16.this.field11.encodeTo(transcoder, value, fields);
                    _16.this.field12.encodeTo(transcoder, value, fields);
                    _16.this.field13.encodeTo(transcoder, value, fields);
                    _16.this.field14.encodeTo(transcoder, value, fields);
                    _16.this.field15.encodeTo(transcoder, value, fields);
                    _16.this.field16.encodeTo(transcoder, value, fields);
                    return transcoder.encodeObject(fields);
                }
            };
        }
    }
}
