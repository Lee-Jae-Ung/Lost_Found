package com.example.term;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{


    ArrayList<String> list11 = ((MainActivity)MainActivity.context_main).list1;
    ArrayList<String> list22 = ((MainActivity)MainActivity.context_main).list2;
    ArrayList<String> list33 = ((MainActivity)MainActivity.context_main).list3;
    ArrayList<String> list44 = ((MainActivity)MainActivity.context_main).list4;
    ArrayList<String> list55 = ((MainActivity)MainActivity.context_main).list5;

    ListView listt;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listintent);


        CustomList adapter = new CustomList(ListActivity.this);

        listt=(ListView)findViewById(R.id.list2);
        listt.setAdapter(adapter);

    }
    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context ) {
            super(context, R.layout.item_list, list11);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.item_list, null, true);

            TextView title = (TextView) rowView.findViewById(R.id.textview);
            TextView rating = (TextView) rowView.findViewById(R.id.textview2);
            TextView genre = (TextView) rowView.findViewById(R.id.textview3);
            TextView year = (TextView) rowView.findViewById(R.id.textview4);
            TextView ayear = (TextView) rowView.findViewById(R.id.textview5);

            title.setText(list11.get(position));

            rating.setText(list22.get(position));
            genre.setText(list33.get(position));
            year.setText(list44.get(position));
            ayear.setText(list55.get(position));
            return rowView;
        }
    }
}
