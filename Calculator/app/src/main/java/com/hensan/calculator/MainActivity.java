package com.hensan.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button zero, one, two, three, four, five, six, seven, eight, nine, dot,
            add, sub, mul, div, equal, erase, delete;
    TextView display;
    Double val1 = 0.0, val2 = 0.0, temp = 0.0;
    String ds = "", operand = "";
    boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		ds = getIntent().getStringExtra("string");

        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        dot = (Button) findViewById(R.id.dot);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        equal = (Button) findViewById(R.id.equal);
        display = (TextView) findViewById(R.id.display);
        erase = (Button) findViewById(R.id.clear);
        delete = (Button) findViewById(R.id.delete);
		
		display.setText(ds);

        try{
            zero.setOnClickListener(this);
            one.setOnClickListener(this);
            two.setOnClickListener(this);
            three.setOnClickListener(this);
            four.setOnClickListener(this);
            five.setOnClickListener(this);
            six.setOnClickListener(this);
            seven.setOnClickListener(this);
            eight.setOnClickListener(this);
            nine.setOnClickListener(this);
            dot.setOnClickListener(this);
            add.setOnClickListener(this);
            sub.setOnClickListener(this);
            mul.setOnClickListener(this);
            div.setOnClickListener(this);
            equal.setOnClickListener(this);
            erase.setOnClickListener(this);
            delete.setOnClickListener(this);
        }
        catch(Exception e){}
    }

    @Override
    public void onClick(View v) {
        ds = display.getText().toString();

        switch (v.getId()){
            case R.id.zero:
                ds = ds.concat(zero.getText().toString());
                display.setText(ds);
                break;
            case R.id.one:
                ds = ds.concat(one.getText().toString());
                display.setText(ds);
                break;
            case R.id.two:
                ds = ds.concat(two.getText().toString());
                display.setText(ds);
                break;
            case R.id.three:
                ds = ds.concat(three.getText().toString());
                display.setText(ds);
                break;
            case R.id.four:
                ds = ds.concat(four.getText().toString());
                display.setText(ds);
                break;
            case R.id.five:
                ds = ds.concat(five.getText().toString());
                display.setText(ds);
                break;
            case R.id.six:
                ds = ds.concat(six.getText().toString());
                display.setText(ds);
                break;
            case R.id.seven:
                ds = ds.concat(seven.getText().toString());
                display.setText(ds);
                break;
            case R.id.eight:
                ds = ds.concat(eight.getText().toString());
                display.setText(ds);
                break;
            case R.id.nine:
                ds = ds.concat(nine.getText().toString());
                display.setText(ds);
                break;
            case R.id.dot:
                ds = ds.concat(dot.getText().toString());
                display.setText(ds);
                break;
            case R.id.add:
                operand = add.getText().toString();
                ds = "";
                display.setText(ds);
                val1 = temp;
                break;
            case R.id.sub:
                operand = sub.getText().toString();
                ds = "";
                display.setText(ds);
                val1 = temp;
                break;
            case R.id.mul:
                operand = mul.getText().toString();
                ds = "";
                display.setText(ds);
                val1 = temp;
                break;
            case R.id.div:
                operand = div.getText().toString();
                ds = "";
                display.setText(ds);
                val1 = temp;
                break;
            case R.id.equal:
                evaluate(val1, val2, operand);
                break;
            case R.id.clear:
                clearDisplay();
                break;
            case R.id.delete:
                deleteNum();
                break;
        }

        if(!clear){
            if(!ds.equals("")){
                temp = Double.parseDouble(display.getText().toString());
            }
            if(!operand.equals("") && !ds.equals("")){
                val2 = temp;
            }
        }
        else{
            clear = false;
        }
    }

    public void evaluate(Double n1, Double n2, String op){
        Double result = 0.0;

        if(op.equals(add.getText().toString()))
            result = n1 + n2;
        if(op.equals(sub.getText().toString()))
            result = n1 - n2;
        if(op.equals(mul.getText().toString()))
            result = n1 * n2;
        if(op.equals(div.getText().toString())){
            result = n1 / n2;
        }

        display.setText(result.toString());
    }

    public void deleteNum(){
        String os = display.getText().toString();
        String ns = "";

        if(os.length() > 0)
            ns = os.substring(0, os.length() - 1);

        display.setText(ns);
    }

    public void clearDisplay(){
        val1 = 0.0;
        val2 = 0.0;
        temp = 0.0;
        operand = "";
        ds = "";
        clear = true;
        display.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.advanced) {
            Intent intent = new Intent(MainActivity.this, advanced.class);
            intent.putExtra("string", ds);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
