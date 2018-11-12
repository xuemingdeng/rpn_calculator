package org.rpn.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RPNCalculatorTest {
    @Test
    public void testAdd() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 +");
            assertEquals(calculator.toString(), "7");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testSub() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 -");
            assertEquals(calculator.toString(), "3");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testMultiply() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 *");
            assertEquals(calculator.toString(), "10");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testDevide() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 /");
            assertEquals(calculator.toString(), "2.5");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testUndo() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 * undo");
            assertEquals(calculator.toString(), "5,2");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testClear() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 * clear");
            assertEquals(calculator.toString(), "");
        } catch (InsufficientParameterException e) {

        }
    }

    @Test
    public void testInsufficientParameter() {
        RPNCalculator calculator = new RPNCalculator();

        try {
            calculator.calculate("5 2 * *");
        } catch (InsufficientParameterException e) {
            assertEquals(e.getMessage(),"operator * (position: 6): insucient parameters");
        }
    }
}
