package function;

import java.util.Arrays;

public class FormulaEditor
{
    private String formula;
    private String[] signs = {"+", "–", "×", "÷", "^"};

    public FormulaEditor(String formula)
    {
        this.formula = formula;
    }

    public String typeFormula(String value)
    {
        return this.formula += value;
    }

    public String typeSign(String sign)
    {
        if (formula.isEmpty() || Arrays.stream(signs).anyMatch(String.valueOf(formula.charAt(formula.length() - 1))::contains))
        {
            return formula;
        }

        return formula += sign;
    }

    public String typeDecimalPoint()
    {
        if (formula.isEmpty())
        {
            return formula;
        }

        int index = this.getIndexOfNumber();
        String number = formula.substring(index);

        if (number.contains(".") || !number.matches("-?\\d+"))
        {
            return formula;
        }

        return formula += ".";
    }

    public String negate()
    {
        if (formula.isEmpty())
        {
            return formula;
        }

        StringBuilder builder = new StringBuilder(formula);
        int index = this.getIndexOfNumber();
        String number = formula.substring(index);

        if (number.contains("-"))
        {
            return formula = builder.replace(index, builder.length(), number.substring(1)).toString();
        }

        if (!number.matches("-?\\d+\\.?\\d*"))
        {
            return formula;
        }

        return formula = builder.replace(index, builder.length(), "-" + number).toString();
    }

    private int getIndexOfNumber()
    {
        /*
           Regex:
           * at least one digit occurred [before decimal point]
           * zero or more digit occurred [after decimal point]
           * zero or more non-digit occurred.
           Determine an index of last number (either negated or not 'and' either decimal number or not)
         */
        return formula.replaceFirst( "-?\\d+\\.?\\d*\\D*$", " ").length() - 1;
    }

    public String getFormula()
    {
        return formula;
    }

    public String delete()
    {
        if (formula.isEmpty())
        {
            return "";
        }

        return formula = formula.substring(0, formula.length() - 1);
    }

    public String allClear()
    {
        return formula = "";
    }
}
