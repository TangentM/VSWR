package com.mike.vswr;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Math;
import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button butCalc = findViewById(R.id.bCalc);
        butCalc.setOnClickListener(this);
        Button butClear = findViewById(R.id.bClear);
        butClear.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            hideSystemUI();
        }
    }

    private void hideSystemUI()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


    @Override
    public void onClick(View view)
    {

        EditText txtFwd = findViewById(R.id.txtFwd);
        EditText txtRef = findViewById(R.id.txtRef);
        TextView lblVswr = findViewById(R.id.lblVswr);

        if (view.getId() == R.id.bCalc)
        {
            double dFwd;
            double dRef;
            double dVswr;
            DecimalFormat df = new DecimalFormat("0.00");
            dFwd = Double.parseDouble(txtFwd.getText().toString());
            dRef = Double.parseDouble(txtRef.getText().toString());
            dVswr = (1 + Math.sqrt(dRef / dFwd)) / (1 - Math.sqrt(dRef / dFwd));
            if (dVswr <= 1.5)
            {
                lblVswr.setTextColor(Color.GREEN);
            }
            else
            {
                lblVswr.setTextColor(Color.RED);
            }
            lblVswr.setText(df.format(dVswr));
        }
        else if (view.getId() == R.id.bClear)
        {
            lblVswr.setText("");
            txtFwd.setText("");
            txtRef.setText("");
        }

    }
}