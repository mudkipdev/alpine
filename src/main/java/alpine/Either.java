package alpine;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Something that can be represented as one of two types.
 * @param <L> The left type.
 * @param <R> The right type.
 */
public final class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        if (left == null) {
            throw new IllegalArgumentException("Left value cannot be null!");
        }

        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        if (right == null) {
            throw new IllegalArgumentException("Left value cannot be null!");
        }

        return new Either<>(null, right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.left, this.right);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Either<?, ?> either
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
        return Optional.ofNullable(this.left);
    }

    public Optional<R> right() {
        return Optional.ofNullable(this.right);
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

    public boolean isLeft() {
        return this.left != null;
    }

    public boolean isRight() {
        return this.right != null;
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
