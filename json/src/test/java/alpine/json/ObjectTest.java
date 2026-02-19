package alpine.json;

import org.junit.jupiter.api.Test;

import static alpine.json.Element.*;
import static org.junit.jupiter.api.Assertions.*;

final class ObjectTest {
    @Test
    void testAccess() {
        assert object().empty();
    }

    @Test
    void testSetting() {
        object().set("key", "value").expect("key", StringElement.class);
        assertThrows(AssertionError.class, () -> object().set("key", "value")
                .expect("key", NullElement.class));
    }

    @Test
    void testContains() {
        assert !object().hasKey("key");
        assert !object().hasValue(false);
        assert object().set("key", "value").hasKey("key");
    }

    @Test
    void testRemoval() {
        assert object().set("key", "value").remove("key").empty();
    }
}
