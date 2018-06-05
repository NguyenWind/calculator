package com.example.nguyen_wind7.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] buttons = new Button[10];
    TextView tv1, tv2;
    EditText edt;
    Button btDot, btPlus, btExcept, btMultip, btDivide, btC, btAC, btResult;
    int i;
    boolean isNumber, lastDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setID();

    }

    private void setID() {

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        edt = (EditText) findViewById(R.id.edt);

        btDot = (Button) findViewById(R.id.btDot);
        btDot.setOnClickListener(this);
        btPlus = (Button) findViewById(R.id.btPlus);
        btPlus.setOnClickListener(this);
        btExcept = (Button) findViewById(R.id.btExcept);
        btExcept.setOnClickListener(this);
        btMultip = (Button) findViewById(R.id.btMultip);
        btMultip.setOnClickListener(this);
        btDivide = (Button) findViewById(R.id.btDivide);
        btDivide.setOnClickListener(this);
        btC = (Button) findViewById(R.id.btC);
        btC.setOnClickListener(this);
        btAC = (Button) findViewById(R.id.btAC);
        btAC.setOnClickListener(this);
        btResult = (Button) findViewById(R.id.btResult);
        btResult.setOnClickListener(this);

        for (i = 0; i < 10; i++) {
            String buttonID = "bt" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt0:
                edt.append("0");
                isNumber = true;
                lastDot = false;
                break;
            case R.id.bt1:
                lastDot = false;
                edt.append("1");
                isNumber = true;
                break;
            case R.id.bt2:
                lastDot = false;
                isNumber = true;
                edt.append("2");
                break;
            case R.id.bt3:
                lastDot = false;
                isNumber = true;
                edt.append("3");
                break;
            case R.id.bt4:
                lastDot = false;
                isNumber = true;
                edt.append("4");
                break;
            case R.id.bt5:
                lastDot = false;
                isNumber = true;
                edt.append("5");
                break;
            case R.id.bt6:
                lastDot = false;
                isNumber = true;
                edt.append("6");
                break;
            case R.id.bt7:
                lastDot = false;
                isNumber = true;
                edt.append("7");
                break;
            case R.id.bt8:
                lastDot = false;
                isNumber = true;
                edt.append("8");
                break;
            case R.id.bt9:
                lastDot = false;
                isNumber = true;
                edt.append("9");
                break;
            case R.id.btDot:
                if (isNumber && !lastDot) {
                    edt.append(".");
                    isNumber = false;
                    lastDot = true;
                } else if (isEmpty()) {
                    edt.append("0.");
                    isNumber = false;
                    lastDot = true;
                }
                break;
            case R.id.btPlus:
                if (endsWithOperation()) {
                    replace("+");
                } else
                    edt.append("+");
                break;
            case R.id.btExcept:
                if (endsWithOperation()) {
                    replace("-");
                } else
                    edt.append("-");
                break;
            case R.id.btMultip:
                if (endsWithOperation()) {
                    replace("x");
                } else
                    edt.append("x");
                break;
            case R.id.btDivide:
                if (endsWithOperation()) {
                    replace("/");
                } else
                    edt.append("/");
                break;
            case R.id.btC:
                BaseInputConnection baseInputConnection = new BaseInputConnection(edt, true);
                baseInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btAC:
                edt.setText("");
                break;
            case R.id.btResult:
                calcule();
                break;
            default:
                break;
        }
    }

    private String getInput() {
        return edt.getText().toString();
    }

    private boolean isEmpty() {
        return getInput().isEmpty();
    }

    private void replace(String str) {
        edt.getText().replace(getInput().length() - 1, getInput().length(), str);
    }

    private boolean endsWithOperation() {
        return getInput().endsWith("+") || getInput().endsWith("-") || getInput().endsWith("x") || getInput().endsWith("/");
    }

    private void calcule() {
        try{
            DecimalFormat df = new DecimalFormat("###.#######");
            double result = 0;
            addOperation(edt.getText().toString());
            addNumber(edt.getText().toString());
            for (int i = 0; i < (arrNumber.size() - 1); i++) {
                switch (arrOperation.get(i)) {
                    case "+":
                        if (i == 0) {
                            result = arrNumber.get(i) + arrNumber.get(i + 1);
                        } else {
                            result = result + arrNumber.get(i + 1);
                        }
                        break;
                    case "-":
                        if (i == 0) {
                            result = arrNumber.get(i) - arrNumber.get(i + 1);
                        } else {
                            result = result - arrNumber.get(i + 1);
                        }
                        break;
                    case "x":
                        if (i == 0) {
                            result = arrNumber.get(i) * arrNumber.get(i + 1);
                        } else {
                            result = result * arrNumber.get(i + 1);
                        }
                        break;
                    case "/":
                        if (i == 0) {
                            result = arrNumber.get(i) / arrNumber.get(i + 1);
                        } else {
                            result = result / arrNumber.get(i + 1);
                        }
                        break;
                    default:
                        break;
                }
            }
            tv2.setText(df.format(result) + "");
        }catch (Exception e){

        }

    }



    //Mảng chứa các phép tính +, - , x, /
    public ArrayList<String> arrOperation;
    //Mảng chứa các số
    public ArrayList<Double> arrNumber;

    //Lấy tất cả các phép tính lưu vào mảng arrOperation
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }

    //Lấy tất cả các số lưu vào mảng arrNumber
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while (matcher.find()) {
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }

}




