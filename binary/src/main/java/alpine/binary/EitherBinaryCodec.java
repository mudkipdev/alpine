package alpine.binary;

import alpine.Either;
import io.netty.buffer.ByteBuf;

/**
 * A binary codec which serializes something that can be represented as one of two values.
 * @param leftCodec The binary codec to serialize a left value with.
 * @param rightCodec The binary codec to serialize a right value with.
 * @param <L> The left value type.
 * @param <R> The right value type.
 * @see Either
 */
record EitherBinaryCodec<L, R>(BinaryCodec<L> leftCodec, BinaryCodec<R> rightCodec) implements BinaryCodec<Either<L, R>> {
    @Override
    public Either<L, R> read(ByteBuf buffer) {
        if (buffer.readBoolean()) {
            return Either.right(this.rightCodec.read(buffer));
        } else {
            return Either.left(this.leftCodec.read(buffer));
        }
    }

    @Override
    public void write(ByteBuf buffer, Either<L, R> value) {
        if (value.isLeft()) {
            this.leftCodec.write(buffer, value.expectLeft());
        } else {
            this.rightCodec.write(buffer, value.expectRight());
        }
    }
}
