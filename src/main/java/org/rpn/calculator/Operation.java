package org.rpn.calculator;

public class Operation {

    private String operator;
    private Double firstOperand;
    private Double secondOperand;

    public Operation(String operator, Double firstOperand, Double secondOperand) {
        this.operator = operator;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getFirstOperand() {
        return firstOperand;
    }

    public void setFirstOperand(Double firstOperand) {
        this.firstOperand = firstOperand;
    }

    public Double getSecondOperand() {
        return secondOperand;
    }

    public void setSecondOperand(Double secondOperand) {
        this.secondOperand = secondOperand;
    }
}
