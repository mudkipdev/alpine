package alpine.json.codec;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class CodecBuilder {
    private CodecBuilder() {

    }

    public record Field<F, T>(String key, Codec<F> codec, Function<T, F> getter) {

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
                    F1 f1 = _1.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _1.this.field1.key()));
                    return constructor.construct(f1);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_1.this.field1.key(), _1.this.field1.codec().encode(transcoder, _1.this.field1.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F2> _2<T, F1, F2> with(String key, Codec<F2> codec, Function<T, F2> getter) {
            return new _2<>(this.field1, new Field<>(key, codec, getter));
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
                    F1 f1 = _2.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _2.this.field1.key()));
                    F2 f2 = _2.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _2.this.field2.key()));
                    return constructor.construct(f1, f2);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_2.this.field1.key(), _2.this.field1.codec().encode(transcoder, _2.this.field1.getter().apply(value)));
                    fields.put(_2.this.field2.key(), _2.this.field2.codec().encode(transcoder, _2.this.field2.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F3> _3<T, F1, F2, F3> with(String key, Codec<F3> codec, Function<T, F3> getter) {
            return new _3<>(this.field1, this.field2, new Field<>(key, codec, getter));
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
                    F1 f1 = _3.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _3.this.field1.key()));
                    F2 f2 = _3.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _3.this.field2.key()));
                    F3 f3 = _3.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _3.this.field3.key()));
                    return constructor.construct(f1, f2, f3);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_3.this.field1.key(), _3.this.field1.codec().encode(transcoder, _3.this.field1.getter().apply(value)));
                    fields.put(_3.this.field2.key(), _3.this.field2.codec().encode(transcoder, _3.this.field2.getter().apply(value)));
                    fields.put(_3.this.field3.key(), _3.this.field3.codec().encode(transcoder, _3.this.field3.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F4> _4<T, F1, F2, F3, F4> with(String key, Codec<F4> codec, Function<T, F4> getter) {
            return new _4<>(this.field1, this.field2, this.field3, new Field<>(key, codec, getter));
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
                    F1 f1 = _4.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _4.this.field1.key()));
                    F2 f2 = _4.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _4.this.field2.key()));
                    F3 f3 = _4.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _4.this.field3.key()));
                    F4 f4 = _4.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _4.this.field4.key()));
                    return constructor.construct(f1, f2, f3, f4);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_4.this.field1.key(), _4.this.field1.codec().encode(transcoder, _4.this.field1.getter().apply(value)));
                    fields.put(_4.this.field2.key(), _4.this.field2.codec().encode(transcoder, _4.this.field2.getter().apply(value)));
                    fields.put(_4.this.field3.key(), _4.this.field3.codec().encode(transcoder, _4.this.field3.getter().apply(value)));
                    fields.put(_4.this.field4.key(), _4.this.field4.codec().encode(transcoder, _4.this.field4.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F5> _5<T, F1, F2, F3, F4, F5> with(String key, Codec<F5> codec, Function<T, F5> getter) {
            return new _5<>(this.field1, this.field2, this.field3, this.field4, new Field<>(key, codec, getter));
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
                    F1 f1 = _5.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _5.this.field1.key()));
                    F2 f2 = _5.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _5.this.field2.key()));
                    F3 f3 = _5.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _5.this.field3.key()));
                    F4 f4 = _5.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _5.this.field4.key()));
                    F5 f5 = _5.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _5.this.field5.key()));
                    return constructor.construct(f1, f2, f3, f4, f5);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_5.this.field1.key(), _5.this.field1.codec().encode(transcoder, _5.this.field1.getter().apply(value)));
                    fields.put(_5.this.field2.key(), _5.this.field2.codec().encode(transcoder, _5.this.field2.getter().apply(value)));
                    fields.put(_5.this.field3.key(), _5.this.field3.codec().encode(transcoder, _5.this.field3.getter().apply(value)));
                    fields.put(_5.this.field4.key(), _5.this.field4.codec().encode(transcoder, _5.this.field4.getter().apply(value)));
                    fields.put(_5.this.field5.key(), _5.this.field5.codec().encode(transcoder, _5.this.field5.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F6> _6<T, F1, F2, F3, F4, F5, F6> with(String key, Codec<F6> codec, Function<T, F6> getter) {
            return new _6<>(this.field1, this.field2, this.field3, this.field4, this.field5, new Field<>(key, codec, getter));
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
                    F1 f1 = _6.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field1.key()));
                    F2 f2 = _6.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field2.key()));
                    F3 f3 = _6.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field3.key()));
                    F4 f4 = _6.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field4.key()));
                    F5 f5 = _6.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field5.key()));
                    F6 f6 = _6.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _6.this.field6.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_6.this.field1.key(), _6.this.field1.codec().encode(transcoder, _6.this.field1.getter().apply(value)));
                    fields.put(_6.this.field2.key(), _6.this.field2.codec().encode(transcoder, _6.this.field2.getter().apply(value)));
                    fields.put(_6.this.field3.key(), _6.this.field3.codec().encode(transcoder, _6.this.field3.getter().apply(value)));
                    fields.put(_6.this.field4.key(), _6.this.field4.codec().encode(transcoder, _6.this.field4.getter().apply(value)));
                    fields.put(_6.this.field5.key(), _6.this.field5.codec().encode(transcoder, _6.this.field5.getter().apply(value)));
                    fields.put(_6.this.field6.key(), _6.this.field6.codec().encode(transcoder, _6.this.field6.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F7> _7<T, F1, F2, F3, F4, F5, F6, F7> with(String key, Codec<F7> codec, Function<T, F7> getter) {
            return new _7<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, new Field<>(key, codec, getter));
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
                    F1 f1 = _7.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field1.key()));
                    F2 f2 = _7.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field2.key()));
                    F3 f3 = _7.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field3.key()));
                    F4 f4 = _7.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field4.key()));
                    F5 f5 = _7.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field5.key()));
                    F6 f6 = _7.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field6.key()));
                    F7 f7 = _7.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _7.this.field7.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_7.this.field1.key(), _7.this.field1.codec().encode(transcoder, _7.this.field1.getter().apply(value)));
                    fields.put(_7.this.field2.key(), _7.this.field2.codec().encode(transcoder, _7.this.field2.getter().apply(value)));
                    fields.put(_7.this.field3.key(), _7.this.field3.codec().encode(transcoder, _7.this.field3.getter().apply(value)));
                    fields.put(_7.this.field4.key(), _7.this.field4.codec().encode(transcoder, _7.this.field4.getter().apply(value)));
                    fields.put(_7.this.field5.key(), _7.this.field5.codec().encode(transcoder, _7.this.field5.getter().apply(value)));
                    fields.put(_7.this.field6.key(), _7.this.field6.codec().encode(transcoder, _7.this.field6.getter().apply(value)));
                    fields.put(_7.this.field7.key(), _7.this.field7.codec().encode(transcoder, _7.this.field7.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F8> _8<T, F1, F2, F3, F4, F5, F6, F7, F8> with(String key, Codec<F8> codec, Function<T, F8> getter) {
            return new _8<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, new Field<>(key, codec, getter));
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
                    F1 f1 = _8.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field1.key()));
                    F2 f2 = _8.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field2.key()));
                    F3 f3 = _8.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field3.key()));
                    F4 f4 = _8.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field4.key()));
                    F5 f5 = _8.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field5.key()));
                    F6 f6 = _8.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field6.key()));
                    F7 f7 = _8.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field7.key()));
                    F8 f8 = _8.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _8.this.field8.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_8.this.field1.key(), _8.this.field1.codec().encode(transcoder, _8.this.field1.getter().apply(value)));
                    fields.put(_8.this.field2.key(), _8.this.field2.codec().encode(transcoder, _8.this.field2.getter().apply(value)));
                    fields.put(_8.this.field3.key(), _8.this.field3.codec().encode(transcoder, _8.this.field3.getter().apply(value)));
                    fields.put(_8.this.field4.key(), _8.this.field4.codec().encode(transcoder, _8.this.field4.getter().apply(value)));
                    fields.put(_8.this.field5.key(), _8.this.field5.codec().encode(transcoder, _8.this.field5.getter().apply(value)));
                    fields.put(_8.this.field6.key(), _8.this.field6.codec().encode(transcoder, _8.this.field6.getter().apply(value)));
                    fields.put(_8.this.field7.key(), _8.this.field7.codec().encode(transcoder, _8.this.field7.getter().apply(value)));
                    fields.put(_8.this.field8.key(), _8.this.field8.codec().encode(transcoder, _8.this.field8.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F9> _9<T, F1, F2, F3, F4, F5, F6, F7, F8, F9> with(String key, Codec<F9> codec, Function<T, F9> getter) {
            return new _9<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, new Field<>(key, codec, getter));
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
                    F1 f1 = _9.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field1.key()));
                    F2 f2 = _9.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field2.key()));
                    F3 f3 = _9.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field3.key()));
                    F4 f4 = _9.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field4.key()));
                    F5 f5 = _9.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field5.key()));
                    F6 f6 = _9.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field6.key()));
                    F7 f7 = _9.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field7.key()));
                    F8 f8 = _9.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field8.key()));
                    F9 f9 = _9.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _9.this.field9.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_9.this.field1.key(), _9.this.field1.codec().encode(transcoder, _9.this.field1.getter().apply(value)));
                    fields.put(_9.this.field2.key(), _9.this.field2.codec().encode(transcoder, _9.this.field2.getter().apply(value)));
                    fields.put(_9.this.field3.key(), _9.this.field3.codec().encode(transcoder, _9.this.field3.getter().apply(value)));
                    fields.put(_9.this.field4.key(), _9.this.field4.codec().encode(transcoder, _9.this.field4.getter().apply(value)));
                    fields.put(_9.this.field5.key(), _9.this.field5.codec().encode(transcoder, _9.this.field5.getter().apply(value)));
                    fields.put(_9.this.field6.key(), _9.this.field6.codec().encode(transcoder, _9.this.field6.getter().apply(value)));
                    fields.put(_9.this.field7.key(), _9.this.field7.codec().encode(transcoder, _9.this.field7.getter().apply(value)));
                    fields.put(_9.this.field8.key(), _9.this.field8.codec().encode(transcoder, _9.this.field8.getter().apply(value)));
                    fields.put(_9.this.field9.key(), _9.this.field9.codec().encode(transcoder, _9.this.field9.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F10> _10<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> with(String key, Codec<F10> codec, Function<T, F10> getter) {
            return new _10<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, new Field<>(key, codec, getter));
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
                    F1 f1 = _10.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field1.key()));
                    F2 f2 = _10.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field2.key()));
                    F3 f3 = _10.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field3.key()));
                    F4 f4 = _10.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field4.key()));
                    F5 f5 = _10.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field5.key()));
                    F6 f6 = _10.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field6.key()));
                    F7 f7 = _10.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field7.key()));
                    F8 f8 = _10.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field8.key()));
                    F9 f9 = _10.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field9.key()));
                    F10 f10 = _10.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _10.this.field10.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_10.this.field1.key(), _10.this.field1.codec().encode(transcoder, _10.this.field1.getter().apply(value)));
                    fields.put(_10.this.field2.key(), _10.this.field2.codec().encode(transcoder, _10.this.field2.getter().apply(value)));
                    fields.put(_10.this.field3.key(), _10.this.field3.codec().encode(transcoder, _10.this.field3.getter().apply(value)));
                    fields.put(_10.this.field4.key(), _10.this.field4.codec().encode(transcoder, _10.this.field4.getter().apply(value)));
                    fields.put(_10.this.field5.key(), _10.this.field5.codec().encode(transcoder, _10.this.field5.getter().apply(value)));
                    fields.put(_10.this.field6.key(), _10.this.field6.codec().encode(transcoder, _10.this.field6.getter().apply(value)));
                    fields.put(_10.this.field7.key(), _10.this.field7.codec().encode(transcoder, _10.this.field7.getter().apply(value)));
                    fields.put(_10.this.field8.key(), _10.this.field8.codec().encode(transcoder, _10.this.field8.getter().apply(value)));
                    fields.put(_10.this.field9.key(), _10.this.field9.codec().encode(transcoder, _10.this.field9.getter().apply(value)));
                    fields.put(_10.this.field10.key(), _10.this.field10.codec().encode(transcoder, _10.this.field10.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F11> _11<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11> with(String key, Codec<F11> codec, Function<T, F11> getter) {
            return new _11<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, new Field<>(key, codec, getter));
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
                    F1 f1 = _11.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field1.key()));
                    F2 f2 = _11.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field2.key()));
                    F3 f3 = _11.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field3.key()));
                    F4 f4 = _11.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field4.key()));
                    F5 f5 = _11.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field5.key()));
                    F6 f6 = _11.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field6.key()));
                    F7 f7 = _11.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field7.key()));
                    F8 f8 = _11.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field8.key()));
                    F9 f9 = _11.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field9.key()));
                    F10 f10 = _11.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field10.key()));
                    F11 f11 = _11.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _11.this.field11.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_11.this.field1.key(), _11.this.field1.codec().encode(transcoder, _11.this.field1.getter().apply(value)));
                    fields.put(_11.this.field2.key(), _11.this.field2.codec().encode(transcoder, _11.this.field2.getter().apply(value)));
                    fields.put(_11.this.field3.key(), _11.this.field3.codec().encode(transcoder, _11.this.field3.getter().apply(value)));
                    fields.put(_11.this.field4.key(), _11.this.field4.codec().encode(transcoder, _11.this.field4.getter().apply(value)));
                    fields.put(_11.this.field5.key(), _11.this.field5.codec().encode(transcoder, _11.this.field5.getter().apply(value)));
                    fields.put(_11.this.field6.key(), _11.this.field6.codec().encode(transcoder, _11.this.field6.getter().apply(value)));
                    fields.put(_11.this.field7.key(), _11.this.field7.codec().encode(transcoder, _11.this.field7.getter().apply(value)));
                    fields.put(_11.this.field8.key(), _11.this.field8.codec().encode(transcoder, _11.this.field8.getter().apply(value)));
                    fields.put(_11.this.field9.key(), _11.this.field9.codec().encode(transcoder, _11.this.field9.getter().apply(value)));
                    fields.put(_11.this.field10.key(), _11.this.field10.codec().encode(transcoder, _11.this.field10.getter().apply(value)));
                    fields.put(_11.this.field11.key(), _11.this.field11.codec().encode(transcoder, _11.this.field11.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F12> _12<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12> with(String key, Codec<F12> codec, Function<T, F12> getter) {
            return new _12<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, new Field<>(key, codec, getter));
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
                    F1 f1 = _12.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field1.key()));
                    F2 f2 = _12.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field2.key()));
                    F3 f3 = _12.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field3.key()));
                    F4 f4 = _12.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field4.key()));
                    F5 f5 = _12.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field5.key()));
                    F6 f6 = _12.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field6.key()));
                    F7 f7 = _12.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field7.key()));
                    F8 f8 = _12.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field8.key()));
                    F9 f9 = _12.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field9.key()));
                    F10 f10 = _12.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field10.key()));
                    F11 f11 = _12.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field11.key()));
                    F12 f12 = _12.this.field12.codec().decode(transcoder, transcoder.decodeObjectField(value, _12.this.field12.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_12.this.field1.key(), _12.this.field1.codec().encode(transcoder, _12.this.field1.getter().apply(value)));
                    fields.put(_12.this.field2.key(), _12.this.field2.codec().encode(transcoder, _12.this.field2.getter().apply(value)));
                    fields.put(_12.this.field3.key(), _12.this.field3.codec().encode(transcoder, _12.this.field3.getter().apply(value)));
                    fields.put(_12.this.field4.key(), _12.this.field4.codec().encode(transcoder, _12.this.field4.getter().apply(value)));
                    fields.put(_12.this.field5.key(), _12.this.field5.codec().encode(transcoder, _12.this.field5.getter().apply(value)));
                    fields.put(_12.this.field6.key(), _12.this.field6.codec().encode(transcoder, _12.this.field6.getter().apply(value)));
                    fields.put(_12.this.field7.key(), _12.this.field7.codec().encode(transcoder, _12.this.field7.getter().apply(value)));
                    fields.put(_12.this.field8.key(), _12.this.field8.codec().encode(transcoder, _12.this.field8.getter().apply(value)));
                    fields.put(_12.this.field9.key(), _12.this.field9.codec().encode(transcoder, _12.this.field9.getter().apply(value)));
                    fields.put(_12.this.field10.key(), _12.this.field10.codec().encode(transcoder, _12.this.field10.getter().apply(value)));
                    fields.put(_12.this.field11.key(), _12.this.field11.codec().encode(transcoder, _12.this.field11.getter().apply(value)));
                    fields.put(_12.this.field12.key(), _12.this.field12.codec().encode(transcoder, _12.this.field12.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F13> _13<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13> with(String key, Codec<F13> codec, Function<T, F13> getter) {
            return new _13<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, new Field<>(key, codec, getter));
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
                    F1 f1 = _13.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field1.key()));
                    F2 f2 = _13.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field2.key()));
                    F3 f3 = _13.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field3.key()));
                    F4 f4 = _13.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field4.key()));
                    F5 f5 = _13.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field5.key()));
                    F6 f6 = _13.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field6.key()));
                    F7 f7 = _13.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field7.key()));
                    F8 f8 = _13.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field8.key()));
                    F9 f9 = _13.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field9.key()));
                    F10 f10 = _13.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field10.key()));
                    F11 f11 = _13.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field11.key()));
                    F12 f12 = _13.this.field12.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field12.key()));
                    F13 f13 = _13.this.field13.codec().decode(transcoder, transcoder.decodeObjectField(value, _13.this.field13.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_13.this.field1.key(), _13.this.field1.codec().encode(transcoder, _13.this.field1.getter().apply(value)));
                    fields.put(_13.this.field2.key(), _13.this.field2.codec().encode(transcoder, _13.this.field2.getter().apply(value)));
                    fields.put(_13.this.field3.key(), _13.this.field3.codec().encode(transcoder, _13.this.field3.getter().apply(value)));
                    fields.put(_13.this.field4.key(), _13.this.field4.codec().encode(transcoder, _13.this.field4.getter().apply(value)));
                    fields.put(_13.this.field5.key(), _13.this.field5.codec().encode(transcoder, _13.this.field5.getter().apply(value)));
                    fields.put(_13.this.field6.key(), _13.this.field6.codec().encode(transcoder, _13.this.field6.getter().apply(value)));
                    fields.put(_13.this.field7.key(), _13.this.field7.codec().encode(transcoder, _13.this.field7.getter().apply(value)));
                    fields.put(_13.this.field8.key(), _13.this.field8.codec().encode(transcoder, _13.this.field8.getter().apply(value)));
                    fields.put(_13.this.field9.key(), _13.this.field9.codec().encode(transcoder, _13.this.field9.getter().apply(value)));
                    fields.put(_13.this.field10.key(), _13.this.field10.codec().encode(transcoder, _13.this.field10.getter().apply(value)));
                    fields.put(_13.this.field11.key(), _13.this.field11.codec().encode(transcoder, _13.this.field11.getter().apply(value)));
                    fields.put(_13.this.field12.key(), _13.this.field12.codec().encode(transcoder, _13.this.field12.getter().apply(value)));
                    fields.put(_13.this.field13.key(), _13.this.field13.codec().encode(transcoder, _13.this.field13.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F14> _14<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14> with(String key, Codec<F14> codec, Function<T, F14> getter) {
            return new _14<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, new Field<>(key, codec, getter));
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
                    F1 f1 = _14.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field1.key()));
                    F2 f2 = _14.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field2.key()));
                    F3 f3 = _14.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field3.key()));
                    F4 f4 = _14.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field4.key()));
                    F5 f5 = _14.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field5.key()));
                    F6 f6 = _14.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field6.key()));
                    F7 f7 = _14.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field7.key()));
                    F8 f8 = _14.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field8.key()));
                    F9 f9 = _14.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field9.key()));
                    F10 f10 = _14.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field10.key()));
                    F11 f11 = _14.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field11.key()));
                    F12 f12 = _14.this.field12.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field12.key()));
                    F13 f13 = _14.this.field13.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field13.key()));
                    F14 f14 = _14.this.field14.codec().decode(transcoder, transcoder.decodeObjectField(value, _14.this.field14.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_14.this.field1.key(), _14.this.field1.codec().encode(transcoder, _14.this.field1.getter().apply(value)));
                    fields.put(_14.this.field2.key(), _14.this.field2.codec().encode(transcoder, _14.this.field2.getter().apply(value)));
                    fields.put(_14.this.field3.key(), _14.this.field3.codec().encode(transcoder, _14.this.field3.getter().apply(value)));
                    fields.put(_14.this.field4.key(), _14.this.field4.codec().encode(transcoder, _14.this.field4.getter().apply(value)));
                    fields.put(_14.this.field5.key(), _14.this.field5.codec().encode(transcoder, _14.this.field5.getter().apply(value)));
                    fields.put(_14.this.field6.key(), _14.this.field6.codec().encode(transcoder, _14.this.field6.getter().apply(value)));
                    fields.put(_14.this.field7.key(), _14.this.field7.codec().encode(transcoder, _14.this.field7.getter().apply(value)));
                    fields.put(_14.this.field8.key(), _14.this.field8.codec().encode(transcoder, _14.this.field8.getter().apply(value)));
                    fields.put(_14.this.field9.key(), _14.this.field9.codec().encode(transcoder, _14.this.field9.getter().apply(value)));
                    fields.put(_14.this.field10.key(), _14.this.field10.codec().encode(transcoder, _14.this.field10.getter().apply(value)));
                    fields.put(_14.this.field11.key(), _14.this.field11.codec().encode(transcoder, _14.this.field11.getter().apply(value)));
                    fields.put(_14.this.field12.key(), _14.this.field12.codec().encode(transcoder, _14.this.field12.getter().apply(value)));
                    fields.put(_14.this.field13.key(), _14.this.field13.codec().encode(transcoder, _14.this.field13.getter().apply(value)));
                    fields.put(_14.this.field14.key(), _14.this.field14.codec().encode(transcoder, _14.this.field14.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F15> _15<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15> with(String key, Codec<F15> codec, Function<T, F15> getter) {
            return new _15<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, new Field<>(key, codec, getter));
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
                    F1 f1 = _15.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field1.key()));
                    F2 f2 = _15.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field2.key()));
                    F3 f3 = _15.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field3.key()));
                    F4 f4 = _15.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field4.key()));
                    F5 f5 = _15.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field5.key()));
                    F6 f6 = _15.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field6.key()));
                    F7 f7 = _15.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field7.key()));
                    F8 f8 = _15.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field8.key()));
                    F9 f9 = _15.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field9.key()));
                    F10 f10 = _15.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field10.key()));
                    F11 f11 = _15.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field11.key()));
                    F12 f12 = _15.this.field12.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field12.key()));
                    F13 f13 = _15.this.field13.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field13.key()));
                    F14 f14 = _15.this.field14.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field14.key()));
                    F15 f15 = _15.this.field15.codec().decode(transcoder, transcoder.decodeObjectField(value, _15.this.field15.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_15.this.field1.key(), _15.this.field1.codec().encode(transcoder, _15.this.field1.getter().apply(value)));
                    fields.put(_15.this.field2.key(), _15.this.field2.codec().encode(transcoder, _15.this.field2.getter().apply(value)));
                    fields.put(_15.this.field3.key(), _15.this.field3.codec().encode(transcoder, _15.this.field3.getter().apply(value)));
                    fields.put(_15.this.field4.key(), _15.this.field4.codec().encode(transcoder, _15.this.field4.getter().apply(value)));
                    fields.put(_15.this.field5.key(), _15.this.field5.codec().encode(transcoder, _15.this.field5.getter().apply(value)));
                    fields.put(_15.this.field6.key(), _15.this.field6.codec().encode(transcoder, _15.this.field6.getter().apply(value)));
                    fields.put(_15.this.field7.key(), _15.this.field7.codec().encode(transcoder, _15.this.field7.getter().apply(value)));
                    fields.put(_15.this.field8.key(), _15.this.field8.codec().encode(transcoder, _15.this.field8.getter().apply(value)));
                    fields.put(_15.this.field9.key(), _15.this.field9.codec().encode(transcoder, _15.this.field9.getter().apply(value)));
                    fields.put(_15.this.field10.key(), _15.this.field10.codec().encode(transcoder, _15.this.field10.getter().apply(value)));
                    fields.put(_15.this.field11.key(), _15.this.field11.codec().encode(transcoder, _15.this.field11.getter().apply(value)));
                    fields.put(_15.this.field12.key(), _15.this.field12.codec().encode(transcoder, _15.this.field12.getter().apply(value)));
                    fields.put(_15.this.field13.key(), _15.this.field13.codec().encode(transcoder, _15.this.field13.getter().apply(value)));
                    fields.put(_15.this.field14.key(), _15.this.field14.codec().encode(transcoder, _15.this.field14.getter().apply(value)));
                    fields.put(_15.this.field15.key(), _15.this.field15.codec().encode(transcoder, _15.this.field15.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }

        public <F16> _16<T, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16> with(String key, Codec<F16> codec, Function<T, F16> getter) {
            return new _16<>(this.field1, this.field2, this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10, this.field11, this.field12, this.field13, this.field14, this.field15, new Field<>(key, codec, getter));
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
                    F1 f1 = _16.this.field1.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field1.key()));
                    F2 f2 = _16.this.field2.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field2.key()));
                    F3 f3 = _16.this.field3.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field3.key()));
                    F4 f4 = _16.this.field4.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field4.key()));
                    F5 f5 = _16.this.field5.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field5.key()));
                    F6 f6 = _16.this.field6.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field6.key()));
                    F7 f7 = _16.this.field7.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field7.key()));
                    F8 f8 = _16.this.field8.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field8.key()));
                    F9 f9 = _16.this.field9.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field9.key()));
                    F10 f10 = _16.this.field10.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field10.key()));
                    F11 f11 = _16.this.field11.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field11.key()));
                    F12 f12 = _16.this.field12.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field12.key()));
                    F13 f13 = _16.this.field13.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field13.key()));
                    F14 f14 = _16.this.field14.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field14.key()));
                    F15 f15 = _16.this.field15.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field15.key()));
                    F16 f16 = _16.this.field16.codec().decode(transcoder, transcoder.decodeObjectField(value, _16.this.field16.key()));
                    return constructor.construct(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16);
                }

                @Override
                public <R> R encode(Transcoder<R> transcoder, T value) {
                    var fields = new LinkedHashMap<String, R>();
                    fields.put(_16.this.field1.key(), _16.this.field1.codec().encode(transcoder, _16.this.field1.getter().apply(value)));
                    fields.put(_16.this.field2.key(), _16.this.field2.codec().encode(transcoder, _16.this.field2.getter().apply(value)));
                    fields.put(_16.this.field3.key(), _16.this.field3.codec().encode(transcoder, _16.this.field3.getter().apply(value)));
                    fields.put(_16.this.field4.key(), _16.this.field4.codec().encode(transcoder, _16.this.field4.getter().apply(value)));
                    fields.put(_16.this.field5.key(), _16.this.field5.codec().encode(transcoder, _16.this.field5.getter().apply(value)));
                    fields.put(_16.this.field6.key(), _16.this.field6.codec().encode(transcoder, _16.this.field6.getter().apply(value)));
                    fields.put(_16.this.field7.key(), _16.this.field7.codec().encode(transcoder, _16.this.field7.getter().apply(value)));
                    fields.put(_16.this.field8.key(), _16.this.field8.codec().encode(transcoder, _16.this.field8.getter().apply(value)));
                    fields.put(_16.this.field9.key(), _16.this.field9.codec().encode(transcoder, _16.this.field9.getter().apply(value)));
                    fields.put(_16.this.field10.key(), _16.this.field10.codec().encode(transcoder, _16.this.field10.getter().apply(value)));
                    fields.put(_16.this.field11.key(), _16.this.field11.codec().encode(transcoder, _16.this.field11.getter().apply(value)));
                    fields.put(_16.this.field12.key(), _16.this.field12.codec().encode(transcoder, _16.this.field12.getter().apply(value)));
                    fields.put(_16.this.field13.key(), _16.this.field13.codec().encode(transcoder, _16.this.field13.getter().apply(value)));
                    fields.put(_16.this.field14.key(), _16.this.field14.codec().encode(transcoder, _16.this.field14.getter().apply(value)));
                    fields.put(_16.this.field15.key(), _16.this.field15.codec().encode(transcoder, _16.this.field15.getter().apply(value)));
                    fields.put(_16.this.field16.key(), _16.this.field16.codec().encode(transcoder, _16.this.field16.getter().apply(value)));
                    return transcoder.encodeObject(fields);
                }
            };
        }
    }
}
