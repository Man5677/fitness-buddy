package com.app.fitnessbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    TextView mbmidisplay, mbmicategory, mgender;
    Button mgotomain;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;
    String height;
    String weight;
    float intheight, intweight;
    RelativeLayout mbackground;

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        intent = getIntent();
        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicategorydispaly);
        mgotomain = findViewById(R.id.gotomain);

        mimageview = findViewById(R.id.imageview);
        mgender = findViewById(R.id.genderdisplay);
        mbackground = findViewById(R.id.contentlayout);


        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");


        intheight = Float.parseFloat(height);
        intweight = Float.parseFloat(weight);

        intheight = intheight / 100;
        intbmi = intweight / (intheight * intheight);

        Button dietButton = findViewById(R.id.dietButton);
        dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToWebsite(mbmicategory.getText().toString());
            }
        });

        TextView dietDisplay = findViewById(R.id.dietDisplay);

        mbmi = Float.toString(intbmi);
        System.out.println(mbmi);

        if (intbmi < 16) {
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.severe_thinness);

        } else if (intbmi < 16.9 && intbmi > 16) {
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.moderate_thinness);

        } else if (intbmi < 18.4 && intbmi > 17) {
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.mild_thinness);

        } else if (intbmi < 24.9 && intbmi > 18.5) {
            mbmicategory.setText("Normal");
            mimageview.setImageResource(R.drawable.ok);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.normal_diet);
        } else if (intbmi < 29.9 && intbmi > 25) {
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.overweight_diet);

        } else if (intbmi < 34.9 && intbmi > 30) {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(R.color.halfwarn);
            mimageview.setImageResource(R.drawable.warning);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.obese_class_I);
        } else {
            mbmicategory.setText("Obese Class II");
            mbackground.setBackgroundColor(R.color.warn);
            mimageview.setImageResource(R.drawable.crosss);
            dietDisplay.setVisibility(View.VISIBLE);
            dietDisplay.setText(R.string.obese_class_ii);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);


        mgotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), BmicalcActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void redirectToWebsite(String bmiCategory) {
        String url;
        switch (bmiCategory) {
            case "Severe Thinness":
                url = "https://www.healthline.com/nutrition/18-foods-to-gain-weight";
                break;
            case "Moderate Thinness":
                url = "https://apps.who.int/nutrition/landscape/help.aspx?menu=0&helpid=420";
                break;
            case "Mild Thinness":
                url = "https://www.eatingwell.com/article/2060706/healthy-weight-gain-meal-plan/";
                break;
            case "Normal":
                url = "https://healthydietindia.com/";
                break;
            case "Overweight":
                url = "https://www.eatingwell.com/article/2058857/30-day-flat-belly-diet-plan/";
                break;
            case "Obese Class I":
                url = "https://www.lybrate.com/topic/obesity-diet-chart";
                break;
            case "Obese Class II":
                url = "https://www.sugarfit.com/blog/obesity-diet-plan/";
                break;
            default:
                url = "https://indianweightlossdiet.com/";
                break;
        }
        // Open the website in a browser
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}