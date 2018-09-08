package com.sthiddov.youareaskilledcook.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sthiddov.youareaskilledcook.R;
import com.sthiddov.youareaskilledcook.data.Datacook;
import com.sthiddov.youareaskilledcook.modils.Crtag;
import com.sthiddov.youareaskilledcook.modils.Crtagadabter;
import com.sthiddov.youareaskilledcook.modils.Wsfat;
import com.sthiddov.youareaskilledcook.modils.Wsfatadabter;

import java.io.IOException;
import java.util.ArrayList;

public class WsfatActivity extends AppCompatActivity {
    private Datacook mDBHelper;
    private SQLiteDatabase mDb;
    String toolbartitle;
    int sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wsfat);
        //استقبار رقم نوع الوصف
        sessionId= getIntent().getIntExtra("key",1);
        if (sessionId!=7) {
            toolbartitle = getIntent().getStringExtra("title");
        }else{
          //رقم 7 هو رقم ماين اكتيفيتي
            toolbartitle="المفضلة";
        }
        settoolbar(toolbartitle);

        getdata();
        try{
            if (sessionId!=7){
            final ArrayList<Wsfat> wsfatArrayList = mDBHelper.getallWsfat(sessionId);
            ListView lv = (ListView) findViewById(R.id.listwsfat);
            Wsfatadabter adapter = new Wsfatadabter(this, wsfatArrayList);
            lv.setAdapter(adapter);}else {
                Toast.makeText(this, "this page frv", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
        }

    }
    private void settoolbar(String title){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txt=findViewById(R.id.txttitel);
            txt.setText("وصفات " + title);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            back(sessionId);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        back(sessionId);
    }

    private void back(int j){
        Intent i;
        try {
            if (j==7) {
                i= new Intent(this, MainActivity.class);
            }else {
                i= new Intent(this, CatrgActivity.class);
            }

            startActivity(i);
            finish();
        }catch (Exception e){
            i= new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }

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
