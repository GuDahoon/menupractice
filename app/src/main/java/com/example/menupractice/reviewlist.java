package com.example.menupractice;


import static android.view.View.inflate;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class reviewlist extends AppCompatActivity {


    private static String TAG = "phptest_secondActivity";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_EPIC = "epicenter";     //진원지 불러오는 TAG

    private static final String TAG_TIME = "name";
    private static final String TAG_SOUND1 = "rate";
    private static final String TAG_SOUND2 = "text";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_LIST = "list";


    //private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviewlist);

        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button1);


        //mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mlistView = (ListView) findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://203.234.62.82:8088/list.php");

//        for(int i = 0; i< mArrayList.size(); i++) {
//            mlistView.getItemIdAtPosition();
//            mArrayList.get(i);
//        }

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mArrayList.clear();
                GetData task = new GetData();
                task.execute("http://203.234.62.82:8088/list.php");
//                String asdf = ;
//                Uri uri = Uri.parse(asdf);
//                Log.d(TAG, "sdfasdf ==== "+ uri);
//                imageView1.setImageURI(uri);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // 확대 activity를 만들어서
                Intent intent2 = new Intent(getApplicationContext(), reviewResult.class);
                String[] List = {mArrayList.get(position).get("name"), mArrayList.get(position).get("rate"),
                        mArrayList.get(position).get("text"), mArrayList.get(position).get("image")};
                intent2.putExtra("List", List);
                startActivity(intent2);
                Log.d(TAG, mArrayList.get(position).get("image"));
                Toast.makeText(getApplicationContext(),mArrayList.get(position).get("rate").toString(),Toast.LENGTH_SHORT).show();

            }
        });
        mlistView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int a = Integer.parseInt(mArrayList.get(position).get("list"));
                GetData task = new GetData();
                task.execute("http://203.234.62.82:8088/delete.php?list1='"+a+"'");
                return true;
            }
        });

    }


    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(reviewlist.this,
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

                String time = item.getString(TAG_TIME);
                String sound1 = item.getString(TAG_SOUND1);
                String sound2 = item.getString(TAG_SOUND2);
                String image = item.getString(TAG_IMAGE);
                String list = item.getString(TAG_LIST);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_TIME, time);
                hashMap.put(TAG_SOUND1, sound1);
                hashMap.put(TAG_SOUND2, sound2);
                hashMap.put(TAG_IMAGE, image);
                hashMap.put(TAG_LIST, list);


                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    reviewlist.this, mArrayList, R.layout.user_list_item,
                    new String[]{TAG_TIME,TAG_SOUND1,TAG_SOUND2},
                    new int[]{R.id.Restaurant,R.id.userRating, R.id.userReview}
            );

            mlistView.setAdapter(adapter);


        } catch (JSONException e) {




            Log.d(TAG, "showResult : ", e);
        }

    }

}