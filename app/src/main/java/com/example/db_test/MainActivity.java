package com.example.db_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final MyDatabase db = new MyDatabase(this);
    ArrayList<WebData> arrayData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.list);
        arrayData=db.getAllData();
        final BaseAdapter adapter = new MyAdapter(this, arrayData);
        listView.setAdapter(adapter);

        final EditText text1 = findViewById(R.id.editText1);
        final EditText text2 = findViewById(R.id.editText2);
        final Button button  = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t1=text1.getText().toString();
                String t2=text2.getText().toString();
                WebData wb  = new WebData(t1,"",t2);
                db.AddNewData(wb);
                arrayData=db.getAllData();
                ((MyAdapter) adapter).refresh(arrayData);
                text1.setText("");
                text2.setText("");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                WebData temp = (WebData)adapterView.getItemAtPosition(i);
                db.DeleteData(temp.getName());
                arrayData=db.getAllData();
                ((MyAdapter) adapter).refresh(arrayData);
                return false;
            }
        });

    }
}
