package org.rpn.calculator;

public class InsufficientParameterException extends Exception {
    public InsufficientParameterException(String operator, int pos) {
        super(
                new StringBuilder().append("operator ").append(operator).append(" (position: ").append(pos - operator.length() + 1).append("): insucient parameters").toString()
        );
    }
}
