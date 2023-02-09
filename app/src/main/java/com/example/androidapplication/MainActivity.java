package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;



public class MainActivity extends AppCompatActivity {
    TextView result, subResult;
    MaterialButton btnDiv, btnMultiply, btnMinus, btnPlus, btnEqual;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAC;
    MaterialButton btnDot, btnOpenBracket, btnCloseBracket;

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick (View view) {
            MaterialButton button = (MaterialButton) view;
            String btnText = button.getText().toString();
            String calcualateStr = subResult.getText().toString();
            if(btnText.equals("AC")) {
                subResult.setText("");
                result.setText("0");
                return;
            }else if(btnText.equals("=")) {
                String finalResult = calcualate(calcualateStr);
                if(!finalResult.equals("Error")) {
                    result.setText(finalResult);
                }
                return;
            } else {
                calcualateStr += btnText;
            }
            subResult.setText(calcualateStr);

        }
    };

    public void assignButton (MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(buttonClick);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignButton(btnDiv, R.id.button_div);
        assignButton(btnMultiply, R.id.button_multiply);
        assignButton(btnMinus, R.id.button_minus);
        assignButton(btnPlus, R.id.button_plus);
        assignButton(btnEqual, R.id.button_equal);
        assignButton(btn0, R.id.button_0);
        assignButton(btn1, R.id.button_1);
        assignButton(btn2, R.id.button_2);
        assignButton(btn3, R.id.button_3);
        assignButton(btn4, R.id.button_4);
        assignButton(btn5, R.id.button_5);
        assignButton(btn6, R.id.button_6);
        assignButton(btn7, R.id.button_7);
        assignButton(btn8, R.id.button_8);
        assignButton(btn9, R.id.button_9);
        assignButton(btnDot, R.id.button_dot);
        assignButton(btnOpenBracket, R.id.button_openBracket);
        assignButton(btnCloseBracket, R.id.button_closeBracket);
        assignButton(btnAC, R.id.button_ac);
        result = findViewById(R.id.result);
        subResult = findViewById(R.id.sub_result);
    }

    String calcualate(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            return result;
        }catch (Exception e) {
            return "Error";
        }
    }
}