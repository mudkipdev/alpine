package alpine.binary;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Something that can be represented as one of two types.
 * @param <L> The left type.
 * @param <R> The right type.
 * @author mudkip
 */
public final class Either<L, R> {
    enum Type {
        LEFT,
        RIGHT
    }

    private final Type type;
    private final L left;
    private final R right;

    private Either(Type type, L left, R right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(Type.LEFT, left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(Type.RIGHT, null, right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.left, this.right);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Either<?, ?> either
                && this.type == either.type
                && Objects.equals(this.left, either.left)
                && Objects.equals(this.right, either.right);
    }

    @Override
    public String toString() {
        return this.fold(
                left -> String.format("Left(%s)", left),
                right -> String.format("Right(%s)", right));
    }

    public Optional<L> left() {
        return this.isLeft() ? Optional.ofNullable(this.left) : Optional.empty();
    }

    public Optional<R> right() {
        return this.isRight() ? Optional.ofNullable(this.right) : Optional.empty();
    }

    public <A, B> Either<A, B> map(Function<L, A> leftMapper, Function<R, B> rightMapper) {
        return this.fold(
                left -> left(leftMapper.apply(left)),
                right -> right(rightMapper.apply(right)));
    }

    public <A> Either<A, R> mapLeft(Function<L, A> mapper) {
        return this.fold(left -> left(mapper.apply(left)), Either::right);
    }

    public <B> Either<L, B> mapRight(Function<R, B> mapper) {
        return this.fold(Either::left, right -> right(mapper.apply(right)));
    }

    public <T> T fold(Function<L, T> leftFunction, Function<R, T> rightFunction) {
        if (this.isLeft()) {
            return leftFunction.apply(this.left);
        } else {
            return rightFunction.apply(this.right);
        }
    }

    public void consume(Consumer<L> leftConsumer, Consumer<R> rightConsumer) {
        this.ifLeft(leftConsumer);
        this.ifRight(rightConsumer);
    }

    public Either<R, L> swap() {
        return this.isLeft() ? right(this.left) : left(this.right);
    }

    public boolean isLeft() {
        return this.type == Type.LEFT;
    }

    public boolean isRight() {
        return this.type == Type.RIGHT;
    }

    public void ifLeft(Consumer<L> consumer) {
        if (this.isLeft()) {
            consumer.accept(this.left);
        }
    }

    public void ifRight(Consumer<R> consumer) {
        if (this.isRight()) {
            consumer.accept(this.right);
        }
    }

    public L expectLeft() {
        if (this.isLeft()) {
            return this.left;
        } else {
            throw new IllegalStateException("The right value was unexpected!");
        }
    }

    public R expectRight() {
        if (this.isRight()) {
            return this.right;
        } else {
            throw new IllegalStateException("The left value was unexpected!");
        }
    }
}
