package com.hensan.calculator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class advanced extends ActionBarActivity implements View.OnClickListener {
    Button sin, cos, tan, natlog, log, pi, exp, percent, factor, sqrt, pwr, clears, deletes;
    TextView display;
    String ds = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advanced);
		
		ds = getIntent().getStringExtra("string");

        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);
        natlog = (Button)findViewById(R.id.ln);
        log = (Button)findViewById(R.id.log);
        pi = (Button)findViewById(R.id.pi);
        exp = (Button)findViewById(R.id.exp);
        percent = (Button)findViewById(R.id.percent);
        factor = (Button)findViewById(R.id.factor);
        sqrt = (Button)findViewById(R.id.sqrt);
        pwr = (Button)findViewById(R.id.pwr);
        display = (TextView) findViewById(R.id.display);
        clears = (Button) findViewById(R.id.clear);
        deletes = (Button) findViewById(R.id.delete);
		
		display.setText(ds);

        try{
            sin.setOnClickListener(this);
            cos.setOnClickListener(this);
            tan.setOnClickListener(this);
            natlog.setOnClickListener(this);
            log.setOnClickListener(this);
            pi.setOnClickListener(this);
            exp.setOnClickListener(this);
            percent.setOnClickListener(this);
            factor.setOnClickListener(this);
            sqrt.setOnClickListener(this);
            pwr.setOnClickListener(this);
            clears.setOnClickListener(this);
            deletes.setOnClickListener(this);
        }
        catch(Exception e){}
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.clear || v.getId() != R.id.delete)
			ds = ds.concat(((Button) v).getText().toString());
        else
            ;

        Intent intent = new Intent(advanced.this, MainActivity.class);
        intent.putExtra("string", ds);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void deleteNum(){
        String os = display.getText().toString();
        String ns = "";

        if(os.length() > 0)
            ns = os.substring(0, os.length() - 1);

        display.setText(ns);
    }

    public void clearDisplay(){
        /*val1 = 0.0;
        val2 = 0.0;
        temp = 0.0;
        operand = "";*/
        ds = "";
        //clear = true;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

