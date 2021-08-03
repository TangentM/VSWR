package com.mike.vswr;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //Setup buttons
        Button butCalc = findViewById(R.id.bCalc);
        butCalc.setOnClickListener(this);
        Button butClear = findViewById(R.id.bClear);
        butClear.setOnClickListener(this);
    }

    //Make app full screen on focus
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            hideSystemUI();
        }
    }

    //Hide ugly system UI
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
        //Fetch next fields
        EditText txtFwd = findViewById(R.id.txtFwd);
        EditText txtRef = findViewById(R.id.txtRef);
        TextView lblVswr = findViewById(R.id.lblVswr);


        if (view.getId() == R.id.bCalc)
        {
            if ((txtFwd.getText().length() > 0) && (txtRef.getText().length() > 0))
            {
                //Fetch text from input fields
                double dFwd = Double.parseDouble(txtFwd.getText().toString());
                double dRef = Double.parseDouble(txtRef.getText().toString());

                //Calculate VSWR
                double dVswr = (1 + Math.sqrt(dRef / dFwd)) / (1 - Math.sqrt(dRef / dFwd));

                //Red text for bad VSWR, Green text for good VSWR
                if (dVswr <= 1.5) {
                    lblVswr.setTextColor(Color.GREEN);
                } else {
                    lblVswr.setTextColor(Color.RED);
                }

                //Show result rounded to two decimal places
                DecimalFormat df = new DecimalFormat("0.00");
                lblVswr.setText(df.format(dVswr));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Check your inputs!", Toast.LENGTH_SHORT).show();
            }

        }
        else if (view.getId() == R.id.bClear)
        {
            //clear all input fields and result
            lblVswr.setText("");
            txtFwd.setText("");
            txtRef.setText("");
        }

    }
}

