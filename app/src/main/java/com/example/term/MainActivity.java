package com.example.term;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    EditText edit2;

    XmlPullParser xpp;

    String key="fCA7uQTKcs9VuyR7n3nQQwXVcd28lvHLLa85mX0KNodRACBqDbn4h48ibkFbpv1XRjc7lHEfldTzJZKCsKOxyQ%3D%3D";

    ArrayList<String> list1 = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    ArrayList<String> list4 = new ArrayList<>();
    ArrayList<String> list5 = new ArrayList<>();

    ListView list;
    public static Context context_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_main = this;

        edit= (EditText)findViewById(R.id.edit);
        edit2= (EditText)findViewById(R.id.edit2);

    }


    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getXmlData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }).start();
                list1.clear();
                list2.clear();
                list3.clear();
                list4.clear();
                list5.clear();
                break;
            case R.id.button2:
                edit.setText(null);
                edit2.setText(null);
        }
    }


    String Url(){
        String str= edit.getText().toString();
        String str2= edit2.getText().toString();
        String location = URLEncoder.encode(str);
        String name = URLEncoder.encode(str2);
        String queryUrl="http://apis.data.go.kr/1320000/LostGoodsInfoInqireService/getLostGoodsInfoAccTpNmCstdyPlace?serviceKey=" + key  + "&LST_PLACE=" + location + "&LST_PRDT_NM=" + name;
        return queryUrl;
    }


















    void getXmlData(){

        String queryUrl;
        queryUrl = Url();
        try{
            URL url= new URL(queryUrl);
            InputStream is= url.openStream();

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") );

            String tag;
            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        //buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("lstPlace")){
                            xpp.next();
                            list1.add(xpp.getText());
                        }

                        else if(tag.equals("lstPrdtNm")){
                            xpp.next();
                            list2.add(xpp.getText());
                        }

                        else if(tag.equals("lstSbjt")){
                            xpp.next();
                            list3.add(xpp.getText());
                        }

                        else if(tag.equals("lstYmd")){
                            xpp.next();
                            list4.add(xpp.getText());
                        }

                        else if(tag.equals("prdtClNm")){
                            xpp.next();
                            list5.add(xpp.getText());
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기


                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}