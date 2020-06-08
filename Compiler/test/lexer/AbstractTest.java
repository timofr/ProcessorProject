package lexer;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;

import main.Main;

class AbstractTest {
    @BeforeEach
    void resetError() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
      Field errorOccured = Main.class.getDeclaredField("errorOccured");
      errorOccured.setAccessible(true);
      errorOccured.set(null, false);
    }
}