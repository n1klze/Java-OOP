package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplicationTest {
    @Test
    public void divisionTest() throws StackSizeException {
        Context ctx = new Context();
        var mul = new Multiplication();
        ctx.getStack().push(100.0);
        ctx.getStack().push(2.0);
        mul.exec(null, ctx);
        assertEquals(200.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var mul = new Multiplication();
        assertThrows(StackSizeException.class, () -> mul.exec(null, ctx));

        ctx.getStack().push(1.0);
        assertThrows(StackSizeException.class, () -> mul.exec(null, ctx));
    }
}
