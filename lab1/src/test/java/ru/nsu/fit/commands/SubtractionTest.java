package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class SubtractionTest {
    @Test
    public void additionTest() throws StackSizeException {
        Context ctx = new Context();
        var sub = new Subtraction();
        ctx.getStack().push(1.0);
        ctx.getStack().push(2.0);
        sub.exec(null, ctx);
        assertEquals(1.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var sub = new Subtraction();
        assertThrows(StackSizeException.class, () -> sub.exec(null, ctx));

        ctx.getStack().push(1.0);
        assertThrows(StackSizeException.class, () -> sub.exec(null, ctx));
    }
}
