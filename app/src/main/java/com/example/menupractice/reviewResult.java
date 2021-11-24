package com.example.menupractice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class reviewResult extends AppCompatActivity {
    TextView txt1, txt2;
    RatingBar ra1;
    ImageView img1;
    Button btn1;
    private static String TAG = "phptest_secondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultreview);

        Intent intent = getIntent();
        String[] listResult = intent.getStringArrayExtra("List");

        Log.d(TAG, "아아아아아아아아아아아아아ㅏ아아앙"+listResult[0]);
        txt1 = (TextView) findViewById(R.id.textBU);
        txt2 = (TextView) findViewById(R.id.textBUBU);
        ra1 = (RatingBar) findViewById(R.id.ratingBU);
        img1 = (ImageView) findViewById(R.id.imageBu);
        btn1 = (Button) findViewById(R.id.btnBU);

        txt1.setText(listResult[0]);
        txt2.setText(listResult[2]);
        ra1.setRating(Float.parseFloat(listResult[1]));
        img1.setImageURI(Uri.parse(listResult[3]));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
