package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class SqrtTest {
    @Test
    public void sqrtTest() throws StackSizeException {
        Context ctx = new Context();
        var sqrt = new Sqrt();
        ctx.getStack().push(4.0);
        sqrt.exec(null, ctx);
        assertEquals(2.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var sqrt = new Sqrt();
        assertThrows(StackSizeException.class, () -> sqrt.exec(null, ctx));
    }
}
