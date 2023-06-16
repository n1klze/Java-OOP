package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class PushTest {
    @Test
    public void pushTest() throws ArgumentSizeException {
        Context ctx = new Context();
        var push = new Push();
        String[] args = {"PUSH", "1"};
        push.exec(args, ctx);
        assertEquals(1.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var push = new Push();
        String[] args = {"PUSH"};
        assertThrows(ArgumentSizeException.class, () -> push.exec(args, ctx));
    }
}
