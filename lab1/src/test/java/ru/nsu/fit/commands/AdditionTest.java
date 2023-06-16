package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {
    @Test
    public void additionTest() throws StackSizeException {
        Context ctx = new Context();
        var plus = new Addition();
        ctx.getStack().push(1.0);
        ctx.getStack().push(2.0);
        plus.exec(null, ctx);
        assertEquals(3.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var plus = new Addition();
        assertThrows(StackSizeException.class, () -> plus.exec(null, ctx));

        ctx.getStack().push(1.0);
        assertThrows(StackSizeException.class, () -> plus.exec(null, ctx));
    }
}
