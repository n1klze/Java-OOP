package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class DivisionTest {
    @Test
    public void divisionTest() throws StackSizeException {
        Context ctx = new Context();
        var div = new Division();
        ctx.getStack().push(100.0);
        ctx.getStack().push(200.0);
        div.exec(null, ctx);
        assertEquals(2.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var div = new Division();
        assertThrows(StackSizeException.class, () -> div.exec(null, ctx));

        ctx.getStack().push(1.0);
        assertThrows(StackSizeException.class, () -> div.exec(null, ctx));
    }
}
