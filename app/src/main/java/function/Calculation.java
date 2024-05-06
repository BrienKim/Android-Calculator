package function;

import java.util.*;

public final class Calculation
{
    /**
     * Takes in formula as String to compute the formula
     * through Shunting Yard Algorithm
     * @param formula Takes a mathematical formula
     * @return a result of the formula
     * @throws ArithmeticException if result is not finite number or format of formula is not appropriate
     */
    public static float calculate(String formula) throws ArithmeticException
    {
        Stack<String> operator = new Stack<>();
        OutputQueue<String> output = new OutputQueue<>();

        String[] tokens = formula.split("");
        String[] operators = {"-", "^", "÷", "×", "+", "–", "(", ")"};
        String[] function = {"sin", "cos", "tan", "arcsin", "arccos", "arctan", "csc", "sec", "cot", "ln"};

        Map<String, Integer> precedence = new Hashtable<>();
        precedence.put("-", 5); // negative sign
        precedence.put("^", 4); // exponent
        precedence.put("÷", 3); // division
        precedence.put("×", 3); // multiplication
        precedence.put("+", 2); // addition
        precedence.put("–", 2); // subtraction
        precedence.put("(", 1); // parenthesis

        precedence.put("sin", 4);
        precedence.put("cos", 4);
        precedence.put("tan", 4);
        precedence.put("arcsin", 4);
        precedence.put("arccos", 4);
        precedence.put("arctan", 4);
        precedence.put("csc", 4);
        precedence.put("sec", 4);
        precedence.put("cot", 4);
        precedence.put("ln", 4);

        try {
            String out = "";
            for (String token : tokens)
            {
                if (Arrays.stream(operators).anyMatch(token::contains))
                {
                    if (!out.isEmpty())
                    {
                        switch (out)
                        {
                            case "sin":
                                operator.push(out);
                                break;
                            case "cos":
                                operator.push(out);
                                break;
                            case "tan":
                                operator.push(out);
                                break;
                            case "arcsin":
                                operator.push(out);
                                break;
                            case "arccos":
                                operator.push(out);
                                break;
                            case "arctan":
                                operator.push(out);
                                break;
                            case "csc":
                                operator.push(out);
                                break;
                            case "sec":
                                operator.push(out);
                                break;
                            case "cot":
                                operator.push(out);
                                break;
                            case "ln":
                                operator.push(out);
                                break;
                            default:
                                output.enqueue(out);
                                break;
                        }

                        out = "";
                    }

                    if (operator.isEmpty())
                    {
                        operator.push(token);
                        continue;
                    }

                    if (token.equals(")"))
                    {
                        while (!operator.peek().equals("("))
                        {
                            output.enqueue(operator.pop());
                        }
                        operator.pop();
                    }
                    else if (token.equals("("))
                    {
                        operator.push(token);
                    }
                    else if (precedence.get(token) > precedence.get(operator.peek()))
                    {
                        operator.push(token);
                    }
                    else if (token.equals(operator.peek()))
                    {
                        operator.push(token);
                    }
                    else
                    {
                        output.enqueue(operator.pop());
                        operator.push(token);
                    }
                }
                else
                {
                    switch (token)
                    {
                        case "e":
                            out += "" + (float)Math.E;
                            break;
                        case "π":
                            out += "" + (float)Math.PI;
                            break;
                        default:
                            out += token; // concatenate all digits of a value
                    }
                }
            }

            if (!out.isEmpty())
            {
                output.enqueue(out);
            }

            while (!operator.isEmpty())
            {
                output.enqueue(operator.pop());
            }

            while (!output.isEmpty())
            {
                String token = output.dequeue();
                if (Arrays.stream(operators).anyMatch(token::contains) || Arrays.stream(function).anyMatch(token::contains))
                {
                    float second_number = Float.parseFloat(operator.pop());
                    float first_number;

                    switch (token) {
                        case "-":
                            operator.push(String.valueOf(-1.0f * second_number));
                            break;
                        case "^":
                            first_number = Float.parseFloat(operator.pop());
                            operator.push(String.valueOf((float) Math.pow(first_number, second_number)));
                            break;
                        case "×":
                            first_number = Float.parseFloat(operator.pop());
                            operator.push(String.valueOf(first_number * second_number));
                            break;
                        case "÷":
                            first_number = Float.parseFloat(operator.pop());
                            if (second_number == 0)
                                throw new ArithmeticException();
                            operator.push(String.valueOf(first_number / second_number));
                            break;
                        case "+":
                            first_number = Float.parseFloat(operator.pop());
                            operator.push(String.valueOf(first_number + second_number));
                            break;
                        case "–":
                            first_number = Float.parseFloat(operator.pop());
                            operator.push(String.valueOf(first_number - second_number));
                            break;

                        case "sin":
                            operator.push(String.valueOf(Math.sin(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;
                        case "cos":
                            operator.push(String.valueOf(Math.cos(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;
                        case "tan":
                            operator.push(String.valueOf(Math.tan(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;

                        case "arcsin":
                            operator.push(String.valueOf((180.0f/Math.PI) * Math.asin(second_number))); // degree mode
                            break;
                        case "arccos":
                            operator.push(String.valueOf((180.0f/Math.PI) * Math.acos(second_number))); // degree mode
                            break;
                        case "arctan":
                            operator.push(String.valueOf((180.0f/Math.PI) * Math.atan(second_number))); // degree mode
                            break;

                        case "csc":
                            operator.push(String.valueOf(1.0f / Math.sin(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;
                        case "sec":
                            operator.push(String.valueOf(1.0f / Math.cos(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;
                        case "cot":
                            operator.push(String.valueOf(1.0f / Math.tan(second_number * (Math.PI / 180.0f)))); // degree mode
                            break;

                        case "ln":
                            operator.push(String.valueOf(Math.log(second_number)));
                            break;

                        default:
                            first_number = Float.parseFloat(operator.pop());
                            operator.push(String.valueOf(first_number * second_number));
                            break;
                    }
                }
                else
                {
                    operator.push(token);
                }
            }

            if (operator.size() > 1 || Double.isNaN(Double.parseDouble(operator.peek())) || Double.isInfinite(Double.parseDouble(operator.peek())))
                throw new ArithmeticException();
        }
        catch (Exception e)
        {
            throw new ArithmeticException();
        }

        return Float.parseFloat(operator.pop());
    }
}
