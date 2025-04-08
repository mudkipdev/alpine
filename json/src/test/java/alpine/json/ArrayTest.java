package alpine.json;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.*;

final class ArrayTest {
    @Test
    void testAccess() {
        assert array().empty();
        assert array(true).get(0) instanceof BooleanElement;
        assert array(true).expect(0, BooleanElement.class).value();
        assertThrows(IndexOutOfBoundsException.class, () -> array().get(69));
    }

    @Test
    void testInsertion() {
        assert array().append("a").length() == 1;
        assert array().append("b").insert(0, true).first() instanceof BooleanElement;
    }

    @Test
    void testRemoval() {
        assert array(1, 2, 3).clear().empty();
        assert Objects.equals(array("a", "b", "c").removeAt(0), array("b", "c"));
        assert Objects.equals(array("a", "b", "c").remove("b"), array("a", "c"));
        assertThrows(IndexOutOfBoundsException.class, () -> array().removeAt(1));
    }

    @Test
    void testContains() {
        assert !array().has(nil());
        assert array(1, 2, 3).has(number(1));
        assert array("a", "b").has(string("b"));
    }
}
