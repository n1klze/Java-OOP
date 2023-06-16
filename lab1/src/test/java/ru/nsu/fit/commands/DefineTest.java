package ru.nsu.fit.commands;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.Context;
import ru.nsu.fit.exceptions.ArgumentFormatException;
import ru.nsu.fit.exceptions.ArgumentSizeException;

import static org.junit.jupiter.api.Assertions.*;

public class DefineTest {
    @Test
    public void defineTest() throws ArgumentFormatException, ArgumentSizeException {
        Context ctx = new Context();
        var def = new Define();
        String[] args = {"DEFINE", "PI", "3.14"};
        def.exec(args, ctx);
        assertEquals(3.14, ctx.getNamedParametersDictionary().get("PI"));
    }

    @Test
    public void throwTest() {
        Context ctx = new Context();
        var def = new Define();
        String[] args = {"DEFINE"};
        assertThrows(ArgumentSizeException.class, () -> def.exec(args, ctx));
        String[] args2 = {"DEFINE", "3.14", "PI"};
        assertThrows(ArgumentFormatException.class, () -> def.exec(args2, ctx));
    }
}
