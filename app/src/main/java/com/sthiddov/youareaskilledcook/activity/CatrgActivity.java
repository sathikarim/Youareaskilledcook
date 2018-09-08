package com.sthiddov.youareaskilledcook.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sthiddov.youareaskilledcook.R;
import com.sthiddov.youareaskilledcook.data.Datacook;
import com.sthiddov.youareaskilledcook.modils.Crtag;
import com.sthiddov.youareaskilledcook.modils.Crtagadabter;

import java.io.IOException;
import java.util.ArrayList;

public class CatrgActivity extends AppCompatActivity {
    private Datacook mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catrg);
        //انشاء toolbar
        settoolbar();
        //تحديد الداتا
        getdata();

        //انشاء قائمة الوصفات من الداتا

       final ArrayList<Crtag> crtaglist = mDBHelper.getallCrtag();
        ListView lv = (ListView) findViewById(R.id.listcartg);
        Crtagadabter adapter = new Crtagadabter(this, crtaglist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(CatrgActivity.this,WsfatActivity.class);
               //                ارسال رقم نوع الوصفة
                i.putExtra("key",crtaglist.get(position).getId());
//                ارسال اسم نزع الوصفة
                i.putExtra("title",crtaglist.get(position).getTitle());
                startActivity(i);
                finish();

            }
        });


    }
    //دالة تركيب toolbar
    private void settoolbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.txttitel);
        txt.setText("قائمة الوصفات");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //تحديد عمل السهم
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //تحديد عمل زر الخلف

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
           back();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    //تحديد عمل زؤ الخلف
    public void onBackPressed() {
back();
    }
//دالة عودة الى main activty
    private void back(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void getdata(){
        mDBHelper = new Datacook(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

    }
}
