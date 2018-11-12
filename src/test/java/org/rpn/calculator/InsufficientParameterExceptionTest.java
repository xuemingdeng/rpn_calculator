package org.rpn.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsufficientParameterExceptionTest {
    @Test
    public void testReverseOneOperandInstruction() {
        InsufficientParameterException e = new InsufficientParameterException("*", 5);
        assertEquals(e.getMessage(), "operator * (position: 5): insucient parameters");
    }

}
