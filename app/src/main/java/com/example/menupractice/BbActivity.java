package com.example.menupractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BbActivity extends AppCompatActivity {
    private static String TAG = "phptest_secondActivity";

    private static final String TAG_JSON = "webnautes";
    private static final String TAG_TITLE = "name";
    private static final String TAG_REVIEW = "review";
    private static final String TAG_RATING = "rating";
    private static final String TAG_IMAGE = "image";

    ArrayList<HashMap<String, String>> mArrayList2;
    ListView mListView;
    String mJsonString;
    Button btn1, btn2;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubu);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String review = intent.getExtras().getString("review");
        String image = intent.getExtras().getString("photo");
        float number = intent.getExtras().getFloat("rating");
        boolean flag =false;
        flag = intent.getExtras().getBoolean("true");
        btn1 = (Button) findViewById(R.id.button);

        btn2 = (Button) findViewById(R.id.dddd);

        txt1 = (TextView) findViewById(R.id.textView);
        mListView = (ListView) findViewById(R.id.listView_main_list1);
        mArrayList2 = new ArrayList<>();


        if (title != null) {
            Log.d(TAG, title + number + review);
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/insert.php?name='" + title + "'&rate='" + number + "'&text='" + review + "'&image='"+image+"'");
            txt1.setText(title+"맛집에 대한 리뷰가 작성되었습니다");
        }
        else if(flag = true) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/list.php");
            Intent intent1 = new Intent(getApplicationContext(), userfragment.class);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), scrabfragment.class);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userfragment scrab = new userfragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(android.R.id.content, scrab);
                ft.commit();
                String[] numbers = new String[]{"1", "2", "3"};
                Bundle bundle = new Bundle();
                bundle.putStringArray("numbers", numbers);
                scrab.setArguments(bundle);
            }
        });

    }
    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(BbActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "response  - " + result);

            if (result == null){

                //mTextViewResult.setText(errorString);
            }
            else {

                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);


                String title1 = item.getString(TAG_TITLE);
                String review1 = item.getString(TAG_REVIEW);
                String image = item.getString(TAG_IMAGE);
                Double rating1 = item.getDouble(TAG_RATING);


                HashMap<String,String> hashMap2 = new HashMap<>();

                hashMap2.put(TAG_TITLE, title1);
                hashMap2.put(TAG_REVIEW, review1);
                hashMap2.put(TAG_RATING, rating1.toString());

                mArrayList2.add(hashMap2);
            }

            ListAdapter adapter2 = new SimpleAdapter(
                    BbActivity.this, mArrayList2, R.layout.user_list_item,
                    new String[]{TAG_TITLE, TAG_REVIEW},
                    new int[]{R.id.Restaurant, R.id.userReview}
            );

            mListView.setAdapter(adapter2);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}
