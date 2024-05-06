package com.example.androidcalculator;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.*;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import database.Formula;
import function.Calculation;
import function.FormulaEditor;
import org.jetbrains.annotations.NotNull;
import java.text.DecimalFormat;

//@SuppressLint("ResourceAsColor")
public class CalculationFragment extends Fragment implements View.OnClickListener
{
    private final FormulaEditor editor = new FormulaEditor("");
    private EditText formula_field;
    private Button sin, cos, tan, csc, sec, cot, division, one, two, three, multiply, four, five,
            six, subtraction, seven, eight, nine, plus, negate, zero, decimal_point, equal, all_clear, open_parenthesis, close_parenthesis, power;
    private ToggleButton alternative;
    private Drawable backspace;
    private boolean secondary_function;


    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        formula_field = view.findViewById(R.id.formulaInput);
        formula_field.setShowSoftInputOnFocus(false);
        formula_field.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        formula_field.setLongClickable(false);
        formula_field.setCursorVisible(true);

        formula_field.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                menu.clear();
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

        formula_field.setOnTouchListener((view1, motionEvent) ->
        {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP)
            {
                final int DRAWABLE_RIGHT = 2;
                if (motionEvent.getRawX() >= formula_field.getRight() - formula_field.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())
                {
                    formula_field.setText(editor.delete());
                    formula_field.setSelection(formula_field.getText().toString().length());
                    return true;
                }
            }
            return false;
        });

        alternative = view.findViewById(R.id.alternative);
        alternative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {

                secondary_function = isChecked;

                if(isChecked)
                {
                    alternative.setTextColor(getResources().getColor(R.color.black));

                    sin.setText(R.string.arcsine);
                    sin.setBackgroundResource(R.drawable.toggled_button_background);
                    sin.setTextColor(getResources().getColor(R.color.black));

                    cos.setText(R.string.arccosine);
                    cos.setBackgroundResource(R.drawable.toggled_button_background);
                    cos.setTextColor(getResources().getColor(R.color.black));

                    tan.setText(R.string.arctangent);
                    tan.setBackgroundResource(R.drawable.toggled_button_background);
                    tan.setTextColor(getResources().getColor(R.color.black));

                    csc.setText(R.string.natural_log);
                    csc.setBackgroundResource(R.drawable.toggled_button_background);
                    csc.setTextColor(getResources().getColor(R.color.black));

                    sec.setText(R.string.e);
                    sec.setBackgroundResource(R.drawable.toggled_button_background);
                    sec.setTextColor(getResources().getColor(R.color.black));

                    cot.setText(R.string.pi);
                    cot.setBackgroundResource(R.drawable.toggled_button_background);
                    cot.setTextColor(getResources().getColor(R.color.black));
                }
                else
                {
                    alternative.setTextColor(getResources().getColor(R.color.white));

                    sin.setText(R.string.sine);
                    sin.setBackgroundResource(R.drawable.button_background);
                    sin.setTextColor(getResources().getColor(R.color.white));

                    cos.setText(R.string.cosine);
                    cos.setBackgroundResource(R.drawable.button_background);
                    cos.setTextColor(getResources().getColor(R.color.white));

                    tan.setText(R.string.tangent);
                    tan.setBackgroundResource(R.drawable.button_background);
                    tan.setTextColor(getResources().getColor(R.color.white));

                    csc.setText(R.string.cosecant);
                    csc.setBackgroundResource(R.drawable.button_background);
                    csc.setTextColor(getResources().getColor(R.color.white));

                    sec.setText(R.string.secant);
                    sec.setBackgroundResource(R.drawable.button_background);
                    sec.setTextColor(getResources().getColor(R.color.white));

                    cot.setText(R.string.cotangent);
                    cot.setBackgroundResource(R.drawable.button_background);
                    cot.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });

        sin = view.findViewById(R.id.sin);
        sin.setOnClickListener(this);
        cos = view.findViewById(R.id.cos);
        cos.setOnClickListener(this);
        tan = view.findViewById(R.id.tan);
        tan.setOnClickListener(this);

        csc = view.findViewById(R.id.csc);
        csc.setOnClickListener(this);
        sec = view.findViewById(R.id.sec);
        sec.setOnClickListener(this);
        cot = view.findViewById(R.id.cot);
        cot.setOnClickListener(this);

        zero = view.findViewById(R.id.zero);
        zero.setOnClickListener(this);
        one = view.findViewById(R.id.one);
        one.setOnClickListener(this);
        two = view.findViewById(R.id.two);
        two.setOnClickListener(this);
        three = view.findViewById(R.id.three);
        three.setOnClickListener(this);
        four = view.findViewById(R.id.four);
        four.setOnClickListener(this);
        five = view.findViewById(R.id.five);
        five.setOnClickListener(this);
        six = view.findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = view.findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = view.findViewById(R.id.eight);
        eight.setOnClickListener(this);
        nine = view.findViewById(R.id.nine);
        nine.setOnClickListener(this);

        division = view.findViewById(R.id.division);
        division.setOnClickListener(this);
        multiply = view.findViewById(R.id.multiply);
        multiply.setOnClickListener(this);
        subtraction = view.findViewById(R.id.subtraction);
        subtraction.setOnClickListener(this);
        plus = view.findViewById(R.id.plus);
        plus.setOnClickListener(this);
        power = view.findViewById(R.id.power);
        power.setOnClickListener(this);

        negate = view.findViewById(R.id.negate);
        negate.setOnClickListener(this);
        decimal_point = view.findViewById(R.id.decimal_point);
        decimal_point.setOnClickListener(this);

        open_parenthesis = view.findViewById(R.id.open_parenthesis);
        open_parenthesis.setOnClickListener(this);
        close_parenthesis = view.findViewById(R.id.close_parenthesis);
        close_parenthesis.setOnClickListener(this);

        all_clear = view.findViewById(R.id.all_clear);
        all_clear.setOnClickListener(this);

        equal = view.findViewById(R.id.equal);
        equal.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.calculation, container, false);
    }

    @Override
    public void onClick(View view)
    {
        if (formula_field.isFocused())
        {
            switch (view.getId())
            {
                // Values
                case R.id.zero:
                    formula_field.setText(editor.typeFormula("0"));
                    break;
                case R.id.one:
                    formula_field.setText(editor.typeFormula("1"));
                    break;
                case R.id.two:
                    formula_field.setText(editor.typeFormula("2"));
                    break;
                case R.id.three:
                    formula_field.setText(editor.typeFormula("3"));
                    break;
                case R.id.four:
                    formula_field.setText(editor.typeFormula("4"));
                    break;
                case R.id.five:
                    formula_field.setText(editor.typeFormula("5"));
                    break;
                case R.id.six:
                    formula_field.setText(editor.typeFormula("6"));
                    break;
                case R.id.seven:
                    formula_field.setText(editor.typeFormula("7"));
                    break;
                case R.id.eight:
                    formula_field.setText(editor.typeFormula("8"));
                    break;
                case R.id.nine:
                    formula_field.setText(editor.typeFormula("9"));
                    break;

                // Signs
                case R.id.plus:
                    formula_field.setText(editor.typeSign("+"));
                    break;
                case R.id.subtraction:
                    formula_field.setText(editor.typeSign("–"));
                    break;
                case R.id.multiply:
                    formula_field.setText(editor.typeSign("×"));
                    break;
                case R.id.division:
                    formula_field.setText(editor.typeSign("÷"));
                    break;
                case R.id.power:
                    formula_field.setText(editor.typeSign("^"));
                    break;

                // Trigonometry
                case R.id.sin:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("sin("));
                    else
                        formula_field.setText(editor.typeFormula("arcsin("));
                    break;
                case R.id.cos:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("cos("));
                    else
                        formula_field.setText(editor.typeFormula("arccos("));
                    break;
                case R.id.tan:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("tan("));
                    else
                        formula_field.setText(editor.typeFormula("arctan("));
                    break;
                case R.id.csc:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("csc("));
                    else
                        formula_field.setText(editor.typeFormula("ln("));
                    break;
                case R.id.sec:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("sec("));
                    else
                        formula_field.setText(editor.typeFormula("e"));
                    break;
                case R.id.cot:
                    if (!secondary_function)
                        formula_field.setText(editor.typeFormula("cot("));
                    else
                        formula_field.setText(editor.typeFormula("π"));
                    break;


                case R.id.open_parenthesis:
                    formula_field.setText(editor.typeFormula("("));
                    break;
                case R.id.close_parenthesis:
                    formula_field.setText(editor.typeFormula(")"));
                    break;

                case R.id.decimal_point:
                    formula_field.setText(editor.typeDecimalPoint());
                    break;

                case R.id.negate:
                    formula_field.setText(editor.negate());
                    break;

                case R.id.all_clear:
                    formula_field.setText(editor.allClear());
                    break;

                case R.id.equal:
                    DecimalFormat df = new DecimalFormat("#,###.###");
                    String f = editor.getFormula();
                    editor.allClear();
                    try
                    {
                        String answer = df.format(Calculation.calculate(formula_field.getText().toString()));
                        formula_field.setText(editor.typeFormula(answer));

                        Formula formula = new Formula();
                        formula.setFormula(f);
                        formula.setAnswer(answer);

                        new HistoryFragment().addHistory(formula);
                    }
                    catch (ArithmeticException e)
                    {
                        formula_field.setText("Error");
                    }
                    finally
                    {
                        formula_field.clearFocus();
                    }
                    break;
            }
        }

        formula_field.setSelection(formula_field.getText().toString().length());
    }
}