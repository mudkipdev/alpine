package alpine.json;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

final class ExternalTest {
    @SuppressWarnings("resource")
    static Stream<Arguments> arguments() {
        try {
            var url = Objects.requireNonNull(ExternalTest.class.getResource("/data"));

            return Files.walk(Path.of(url.toURI()), 1)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .map(path -> {
                        try {
                            var name = path.getFileName().toString();
                            var behavior = Behavior.from(path);
                            var string = Files.readString(path);
                            return Arguments.of(name, behavior, string);
                        } catch (MalformedInputException e) {
                            return null;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(Objects::nonNull);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("arguments")
    void test(String name, Behavior behavior, String string) {
        try {
            Json.read(string);
        } catch (ParsingException e) {
            if (behavior == Behavior.SHOULD_PASS) {
                throw new AssertionError(name + " should have passed!", e);
            }
        } catch (StackOverflowError ignored) {

        }
    }

    enum Behavior {
        SHOULD_PASS,
        SHOULD_FAIL,
        ANY;

        static Behavior from(Path path) {
            return switch (path.getFileName().toString().charAt(0)) {
                case 'y' -> SHOULD_PASS;
                case 'n' -> SHOULD_FAIL;
                case 'i' -> ANY;
                default -> throw new IllegalStateException("Unexpected value!");
            };
        }
    }
}
