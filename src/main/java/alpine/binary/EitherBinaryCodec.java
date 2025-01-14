package alpine.binary;

import alpine.Either;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
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
