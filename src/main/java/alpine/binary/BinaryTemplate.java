package alpine.binary;

import io.netty.buffer.ByteBuf;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author mudkip
 */
public interface BinaryTemplate {
    @FunctionalInterface
    interface F1<P1, R> {
        R apply(P1 p1);
    }

    @FunctionalInterface
    interface F2<P1, P2, R> {
        R apply(P1 p1, P2 p2);
    }

    @FunctionalInterface
    interface F3<P1, P2, P3, R> {
        R apply(P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    interface F4<P1, P2, P3, P4, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    interface F5<P1, P2, P3, P4, P5, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    @FunctionalInterface
    interface F6<P1, P2, P3, P4, P5, P6, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    @FunctionalInterface
    interface F7<P1, P2, P3, P4, P5, P6, P7, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }

    @FunctionalInterface
    interface F8<P1, P2, P3, P4, P5, P6, P7, P8, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8);
    }

    @FunctionalInterface
    interface F9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9);
    }

    @FunctionalInterface
    interface F10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10);
    }

    @FunctionalInterface
    interface F11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11);
    }

    @FunctionalInterface
    interface F12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12);
    }

    @FunctionalInterface
    interface F13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13);
    }

    @FunctionalInterface
    interface F14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14);
    }

    @FunctionalInterface
    interface F15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15);
    }

    @FunctionalInterface
    interface F16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15, P16 p16);
    }

    @FunctionalInterface
    interface F17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15, P16 p16, P17 p17);
    }

    @FunctionalInterface
    interface F18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15, P16 p16, P17 p17, P18 p18);
    }

    @FunctionalInterface
    interface F19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15, P16 p16, P17 p17, P18 p18, P19 p19);
    }

    @FunctionalInterface
    interface F20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> {
        R apply(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7, P8 p8, P9 p9, P10 p10, P11 p11, P12 p12, P13 p13, P14 p14, P15 p15, P16 p16, P17 p17, P18 p18, P19 p19, P20 p20);
    }

    static <R> BinaryCodec<R> of(Supplier<R> reader) {
        return BinaryCodec.unit(reader);
    }

    static <P1, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            F1<P1, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(p1.read(buffer));
            }
        };
    }

    static <P1, P2, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            F2<P1, P2, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer));
            }
        };
    }

    static <P1, P2, P3, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            F3<P1, P2, P3, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            F4<P1, P2, P3, P4, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            F5<P1, P2, P3, P4, P5, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            F6<P1, P2, P3, P4, P5, P6, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            F7<P1, P2, P3, P4, P5, P6, P7, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            F8<P1, P2, P3, P4, P5, P6, P7, P8, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            F9<P1, P2, P3, P4, P5, P6, P7, P8, P9, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            F10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            F11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            F12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            F13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            F14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            F15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer), p2.read(buffer),
                        p3.read(buffer), p4.read(buffer),
                        p5.read(buffer), p6.read(buffer),
                        p7.read(buffer), p8.read(buffer),
                        p9.read(buffer), p10.read(buffer),
                        p11.read(buffer), p12.read(buffer),
                        p13.read(buffer), p14.read(buffer),
                        p15.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            BinaryCodec<P16> p16, Function<R, P16> g16,
            F16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
                p16.write(buffer, g16.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer),
                        p15.read(buffer),
                        p16.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            BinaryCodec<P16> p16, Function<R, P16> g16,
            BinaryCodec<P17> p17, Function<R, P17> g17,
            F17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
                p16.write(buffer, g16.apply(value));
                p17.write(buffer, g17.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer),
                        p15.read(buffer),
                        p16.read(buffer),
                        p17.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            BinaryCodec<P16> p16, Function<R, P16> g16,
            BinaryCodec<P17> p17, Function<R, P17> g17,
            BinaryCodec<P18> p18, Function<R, P18> g18,
            F18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
                p16.write(buffer, g16.apply(value));
                p17.write(buffer, g17.apply(value));
                p18.write(buffer, g18.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer),
                        p15.read(buffer),
                        p16.read(buffer),
                        p17.read(buffer),
                        p18.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            BinaryCodec<P16> p16, Function<R, P16> g16,
            BinaryCodec<P17> p17, Function<R, P17> g17,
            BinaryCodec<P18> p18, Function<R, P18> g18,
            BinaryCodec<P19> p19, Function<R, P19> g19,
            F19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
                p16.write(buffer, g16.apply(value));
                p17.write(buffer, g17.apply(value));
                p18.write(buffer, g18.apply(value));
                p19.write(buffer, g19.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer),
                        p15.read(buffer),
                        p16.read(buffer),
                        p17.read(buffer),
                        p18.read(buffer),
                        p19.read(buffer));
            }
        };
    }

    static <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> BinaryCodec<R> of(
            BinaryCodec<P1> p1, Function<R, P1> g1,
            BinaryCodec<P2> p2, Function<R, P2> g2,
            BinaryCodec<P3> p3, Function<R, P3> g3,
            BinaryCodec<P4> p4, Function<R, P4> g4,
            BinaryCodec<P5> p5, Function<R, P5> g5,
            BinaryCodec<P6> p6, Function<R, P6> g6,
            BinaryCodec<P7> p7, Function<R, P7> g7,
            BinaryCodec<P8> p8, Function<R, P8> g8,
            BinaryCodec<P9> p9, Function<R, P9> g9,
            BinaryCodec<P10> p10, Function<R, P10> g10,
            BinaryCodec<P11> p11, Function<R, P11> g11,
            BinaryCodec<P12> p12, Function<R, P12> g12,
            BinaryCodec<P13> p13, Function<R, P13> g13,
            BinaryCodec<P14> p14, Function<R, P14> g14,
            BinaryCodec<P15> p15, Function<R, P15> g15,
            BinaryCodec<P16> p16, Function<R, P16> g16,
            BinaryCodec<P17> p17, Function<R, P17> g17,
            BinaryCodec<P18> p18, Function<R, P18> g18,
            BinaryCodec<P19> p19, Function<R, P19> g19,
            BinaryCodec<P20> p20, Function<R, P20> g20,
            F20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, R> reader
    ) {
        return new BinaryCodec<>() {
            @Override
            public void write(ByteBuf buffer, R value) {
                p1.write(buffer, g1.apply(value));
                p2.write(buffer, g2.apply(value));
                p3.write(buffer, g3.apply(value));
                p4.write(buffer, g4.apply(value));
                p5.write(buffer, g5.apply(value));
                p6.write(buffer, g6.apply(value));
                p7.write(buffer, g7.apply(value));
                p8.write(buffer, g8.apply(value));
                p9.write(buffer, g9.apply(value));
                p10.write(buffer, g10.apply(value));
                p11.write(buffer, g11.apply(value));
                p12.write(buffer, g12.apply(value));
                p13.write(buffer, g13.apply(value));
                p14.write(buffer, g14.apply(value));
                p15.write(buffer, g15.apply(value));
                p16.write(buffer, g16.apply(value));
                p17.write(buffer, g17.apply(value));
                p18.write(buffer, g18.apply(value));
                p19.write(buffer, g19.apply(value));
                p20.write(buffer, g20.apply(value));
            }

            @Override
            public R read(ByteBuf buffer) {
                return reader.apply(
                        p1.read(buffer),
                        p2.read(buffer),
                        p3.read(buffer),
                        p4.read(buffer),
                        p5.read(buffer),
                        p6.read(buffer),
                        p7.read(buffer),
                        p8.read(buffer),
                        p9.read(buffer),
                        p10.read(buffer),
                        p11.read(buffer),
                        p12.read(buffer),
                        p13.read(buffer),
                        p14.read(buffer),
                        p15.read(buffer),
                        p16.read(buffer),
                        p17.read(buffer),
                        p18.read(buffer),
                        p19.read(buffer),
                        p20.read(buffer));
            }
        };
    }
}
