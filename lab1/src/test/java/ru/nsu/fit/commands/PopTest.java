package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentSizeException;
import ru.nsu.fit.exceptions.StackSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class PopTest {
    @Test
    public void popTest() throws ArgumentSizeException, StackSizeException {
        Context ctx = new Context();
        var pop = new Pop();
        ctx.getStack().push(1.0);
        ctx.getStack().push(2.0);
        String[] args = {"POP"};
        pop.exec(args, ctx);
        assertEquals(1.0, ctx.getStack().pop());
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var pop = new Pop();
        String[] args = {"POP", "SOMETHING"};
        assertThrows(ArgumentSizeException.class, () -> pop.exec(args, ctx));

        String[] args2 = {"POP"};
        assertThrows(StackSizeException.class, () -> pop.exec(args2, ctx));
    }
}
