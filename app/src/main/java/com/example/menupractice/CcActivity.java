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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CcActivity extends AppCompatActivity {


    private static String TAG = "phptest_secondActivity";

    private static final String TAG_JSON="webnautes";
    private static final String TAG_NAME = "name";
    private static final String TAG_KEYWORD1 ="keyword1";
    private static final String TAG_KEYWORD2 = "keyword2";
//    private static final String TAG_TITLE = "name";
//    private static final String TAG_REVIEW = "text";
//    private static final String TAG_RATING = "rate";

    //private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;//, mArrayList2
    ListView mlistView;   //, mListView2
    String mJsonString;
    Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choochun);

        Intent intent = getIntent();
        String kind = intent.getExtras().getString("Kind"); // 값 받아오기
        String keyword = intent.getExtras().getString("keyword");

//        String title = intent.getExtras().getString("title");
//        String review = intent.getExtras().getString("review");
//        float number = intent.getExtras().getFloat("rating");

        boolean flag = intent.getExtras().getBoolean("true");
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.findK1);
        btn5 = (Button) findViewById(R.id.findK2);
        //mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        mlistView = (ListView) findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<>();


//        if (title != null) {
//            Log.d(TAG, title+number+review);
//            GetData task = new GetData();
//            task.execute("http://203.234.62.82:8088/insert.php?name='"+title+"'&rate='"+number+"'&text='"+review+"'");
//        }
//
//        else if(flag == true) {
//            GetData task = new GetData();
//            task.execute("새로운 php 경로(SELECT하는 SQL있는 거)");
//        }



        if(kind.equals("0food")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/korea.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/korea.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> matchList2 = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    while(rr.find())
                        matchList2.add(rr.group());
                    Intent intent2 = new Intent(getApplicationContext(), MapViewActivity.class);
                    intent2.putExtra("foodName", matchList2.get(2));
                    startActivity(intent2);
                    Log.d(TAG, "response  - " + matchList2.get(2));
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    String tu1 = mArrayList.get(0).get("keyword2");
                    String tu2 = mArrayList.get(0).get("name");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, tu);
                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("1food")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/japan.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/japan.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("2food")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/china.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/china.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("3food")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/uro.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/uro.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("4food")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/asia.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/asia.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("0taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/jjanmat.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/jjanmat.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("1taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/sinmat.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/sinmat.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("2taste")) {
            GetData task = new GetData();
            task.execute( "http://203.234.62.82:8088/ice.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/ice.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("3taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/spicy.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/spicy.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("4taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/damback.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/damback.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("5taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/danmat.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/danmat.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("6taste")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/gireum.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/gireum.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("0feel")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/good.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/good.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("1feel")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/bad.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/bad.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    while(rr.find())
                        matchList.add(rr.group());
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }
        else if(kind.equals("맑음")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/margem.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/margem.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("구름많음")||kind.equals("구름조금")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/cloud.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/cloud.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("뇌우/폭우")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/noiu.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/noiu.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("흐림")|kind.equals("안개")|kind.equals("황사")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/herim.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/herim.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("비")|kind.equals("소낙비")|kind.equals("눈비")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/rain.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/rain.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("눈")|kind.equals("소낙눈")) {
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/snow.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/snow.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu = mArrayList.get(0).get("keyword1");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu;
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu);

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tu1 = mArrayList.get(0).get("keyword2");
                    mArrayList.clear();
                    GetData task = new GetData();
                    String key1 = tu1;
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + tu1);

                }
            });
        }
        else if(kind.equals("random")){
            GetData task = new GetData();
            task.execute("http://203.234.62.82:8088/all.php");
            btn1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    mArrayList.clear();
                    GetData task = new GetData();
                    task.execute("http://203.234.62.82:8088/all.php");
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                }
            });
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(0);
                    String phpName="";
                    switch (key1) {
                        case "고기" : phpName = "meat";break;
                        case "닭" : phpName = "chicken";break;
                        case "떡" : phpName = "cake";break;
                        case "면" : phpName = "noodle";break;
                        case "밥" : phpName = "rice";break;
                        case "빵" : phpName = "bread";break;
                        case "야채" : phpName = "ya";break;
                        case "해산물" : phpName = "seafood";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //mArrayList.clear();

                    List<String> matchList = new ArrayList<String>();
                    String a = mArrayList.get(0).toString();
                    Pattern ss = Pattern.compile("[가-힣]+");
                    Matcher rr = ss.matcher(a);
                    mArrayList.clear();
                    while(rr.find())
                        matchList.add(rr.group());
                    GetData task = new GetData();
                    String key1 = matchList.get(1);
                    String phpName="";
                    switch (key1) {
                        case "구이" : phpName = "gu";break;
                        case "날것" : phpName = "nal";break;
                        case "덮밥" : phpName = "du";break;
                        case "복합" : phpName = "bok";break;
                        case "볶음" : phpName = "bbo";break;
                        case "부침" : phpName = "bu";break;
                        case "분식" : phpName = "bunsik";break;
                        case "비빔" : phpName = "bibim";break;
                        case "오븐" : phpName = "oven";break;
                        case "장" : phpName = "ja";break;
                        case "찜" : phpName = "jji";break;
                        case "탕" : phpName = "ta";break;
                        case "튀김" : phpName = "tui";break;
                        case "패스트푸드" : phpName = "fast";break;
                        case "한상" : phpName = "han";break;
                    }
                    task.execute("http://203.234.62.82:8088/"+phpName+".php");
                    Log.d(TAG, "response  - " + matchList.get(0));

                }
            });
        }



    }


    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CcActivity.this,
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

                String name = item.getString(TAG_NAME);
                String keyword1 = item.getString(TAG_KEYWORD1);
                String keyword2 = item.getString(TAG_KEYWORD2);

//                String title1 = item.getString(TAG_TITLE);
//                String review1 = item.getString(TAG_REVIEW);
//                Double rating1 = item.getDouble(TAG_RATING);


                HashMap<String,String> hashMap = new HashMap<>();
                HashMap<String,String> hashMap2 = new HashMap<>();

                hashMap.put(TAG_NAME, name);
                hashMap.put(TAG_KEYWORD1, keyword1);
                hashMap.put(TAG_KEYWORD2, keyword2);

//                hashMap2.put(TAG_TITLE, title1);
//                hashMap2.put(TAG_REVIEW, review1);
//                hashMap2.put(TAG_RATING, rating1.toString());

                mArrayList.add(hashMap);
//                mArrayList2.add(hashMap2);
            }

            ListAdapter adapter = new SimpleAdapter(
                    CcActivity.this, mArrayList, R.layout.item_list,
                    new String[]{TAG_NAME, TAG_KEYWORD1, TAG_KEYWORD2},// TAG_CATEGORY, , TAG_FLAVOR,TAG_MOOD
                    new int[]{R.id.textView_list_id1, R.id.textView_list_id2, R.id.textView_list_id3}//,R.id.textView_list_id3,R.id.textView_list_id5,R.id.textView_list_id7,R.id.textView_list_id9,R.id.textView_list_id11
            );
//            ListAdapter adapter2 = new SimpleAdapter(
//                    CcActivity.this, mArrayList2, R.layout.user_list_item,
//                    new String[]{TAG_TITLE, TAG_REVIEW, TAG_RATING},
//                    new int[]{R.id.Restaurant, R.id.userReview, R.id.userRating}
//            );

            mlistView.setAdapter(adapter);
//            mListView2.setAdapter(adapter2);

//            btn2.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    mArrayList.clear();
//                    mlistView.setAdapter(adapter);
//                }
//            });

        } catch (JSONException e) {




            Log.d(TAG, "showResult : ", e);
        }

    }

}