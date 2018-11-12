package org.rpn.calculator;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author Martin Xue
 * <p>
 * RPN Calculator.
 * Java 7+ is required to run this program.
 */
public class RPNCalculator {

    private Stack<Double> stack = new Stack<>();

    //For nudo
    private Stack<Operation> history = new Stack<>();


    public void calculate(String rpn) throws InsufficientParameterException {

        StringBuilder tokenBuf = new StringBuilder();
        for (int i = 0; i < rpn.length(); i++) {
            char c = rpn.charAt(i);
            if (c != ' ') {
                tokenBuf.append(c);
            }

            if ((c == ' ' || i == rpn.length() - 1) && tokenBuf.length() > 0) {
                String token = tokenBuf.toString();
                tokenBuf = new StringBuilder();
                Double operand = tryParseDouble(token);

                if (operand != null) {
                    stack.push(operand);
                    history.push(new Operation(Operator.NOOP, operand, null));
                    continue;
                }

                Double second;
                Double first;

                switch (token) {
                    case Operator.ADD:
                        if (stack.size() < 2) {
                            throw new InsufficientParameterException(Operator.ADD, i);
                        }
                        second = stack.pop();
                        first = stack.pop();
                        stack.push(first + second);
                        history.push(new Operation(Operator.ADD, first, second));
                        break;
                    case Operator.SUB:
                        if (stack.size() < 2) {
                            throw new InsufficientParameterException(Operator.SUB, i);
                        }
                        second = stack.pop();
                        first = stack.pop();
                        stack.push(first - second);
                        history.push(new Operation(Operator.SUB, first, second));
                        break;
                    case Operator.MULTI:
                        if (stack.size() < 2) {
                            throw new InsufficientParameterException(Operator.MULTI, i);
                        }
                        second = stack.pop();
                        first = stack.pop();
                        stack.push(first * second);
                        history.push(new Operation(Operator.MULTI, first, second));
                        break;
                    case Operator.DEVIDE:
                        if (stack.size() < 2) {
                            throw new InsufficientParameterException(Operator.DEVIDE, i);
                        }
                        second = stack.pop();
                        if (second.intValue() == 0) {
                            throw new InsufficientParameterException(Operator.DEVIDE, i);
                        }
                        first = stack.pop();
                        stack.push(first / second);
                        history.push(new Operation(Operator.DEVIDE, first, second));
                        break;
                    case Operator.SQRT:
                        if (stack.size() < 1) {
                            throw new InsufficientParameterException(Operator.SQRT, i);
                        }
                        first = stack.pop();
                        stack.push(Math.sqrt(first));
                        history.push(new Operation(Operator.SQRT, first, null));
                        break;
                    case Operator.UNDO:
                        if (history.size() < 1) {
                            throw new InsufficientParameterException(Operator.UNDO, i);
                        }
                        Operation op = history.pop();
                        if (!Operator.NOOP.equals(op.getOperator())) {
                            stack.pop();
                            stack.push(op.getFirstOperand());
                            if (op.getSecondOperand() != null) {
                                stack.push(op.getSecondOperand());
                            }
                        } else {
                            stack.pop();
                        }
                        break;
                    case Operator.CLEAR:
                        stack.clear();
                        history.clear();
                        break;
                    default:
                        throw new InsufficientParameterException(token, i);
                }
            }
        }
    }

    private Double tryParseDouble(String token) {
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }

    public void displayStack() {
        DecimalFormat fmt = new DecimalFormat("0.##########");
        System.out.print("stack: ");
        for (Double value : stack) {
            System.out.print(fmt.format(value));
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    public String toString() {
        if (stack.isEmpty()) {
            return "";
        }
        DecimalFormat fmt = new DecimalFormat("0.##########");
        StringBuilder buf = new StringBuilder();
        for (Double value : stack) {
            buf.append(fmt.format(value));
            buf.append(",");
        }
        buf.delete(buf.length() - 1, buf.length());
        return buf.toString();
    }

    public static void main(String[] args) {

        RPNCalculator calculator = new RPNCalculator();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String rpn = scanner.nextLine().trim();
            if ("".equals(rpn)) {
                continue;
            }
            try {
                calculator.calculate(rpn);
                calculator.displayStack();
            } catch (InsufficientParameterException e) {
                System.out.println(e.getMessage());
                calculator.displayStack();
                System.exit(-1);
            }
        }
    }

}
