package com.sthiddov.youareaskilledcook.activity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sthiddov.youareaskilledcook.R;
import com.sthiddov.youareaskilledcook.data.Datacook;
import com.sthiddov.youareaskilledcook.modils.Howcook;
import com.sthiddov.youareaskilledcook.modils.Wsfat;
import com.sthiddov.youareaskilledcook.modils.Wsfatadabter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HowcookActivity extends AppCompatActivity {
    private Datacook mDBHelper;
    private SQLiteDatabase mDb;
    int sessionId1,ss;
    String toolbartitle;
    ArrayList<Howcook> howcookList;
    LinearLayout layout;
    TextView txttime1,txttime2,txtpeople,txttitle,txtdec;
    ImageView imagefrv,imagewsf;
    boolean j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howcook);
        sessionId1= getIntent().getIntExtra("key1",1);
        ss= getIntent().getIntExtra("key",1);
        toolbartitle = getIntent().getStringExtra("title");
        getdata();
        howcookList = mDBHelper.gethowcook(sessionId1);
        layout= findViewById(R.id.listshop);
        txttime1=findViewById(R.id.txttimecook);
        txttime2=findViewById(R.id.txttimefire);
        txtpeople=findViewById(R.id.txtpeople);
        txttitle=findViewById(R.id.txtfixe);
        txtdec=findViewById(R.id.txtdec);
        imagefrv=findViewById(R.id.imgcheckfrv);
        imagewsf=findViewById(R.id.imagwsfa);

        txttime1.setText(howcookList.get(0).getTimecook()+"\nدقيقة");
        txttime2.setText(howcookList.get(0).getTimefire()+"\nدقيقة");
        txtpeople.setText(howcookList.get(0).getPepole()+"\nأفراد");
        txtdec.setText(howcookList.get(0).getDec());
        txttitle.setText(howcookList.get(0).getTitle());
        imagewsf.setImageBitmap(howcookList.get(0).getImage());
        j=howcookList.get(0).getCheckfrv();
        if (j){
            imagefrv.setImageResource(R.drawable.iconfrvtrue);
            imagefrv.setBackgroundColor(Color.RED);

        }else{            imagefrv.setImageResource(R.drawable.iconfrvfalse);
            imagefrv.setBackgroundColor(Color.WHITE);

        }

        for (int i=0 ;i<howcookList.get(0).getShop();i++){
            CheckBox cb = new CheckBox(this);
            cb.setText(howcookList.get(0).getListshop().get(i));
            cb.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            cb.setTextSize(16);
            cb.setTypeface(Typeface.DEFAULT_BOLD);
            layout.addView(cb);
        }




    }






    private void back(int j){
                 Intent i= new Intent(this, WsfatActivity.class);
        i.putExtra("key",j);
        i.putExtra("title",toolbartitle);
            startActivity(i);
            finish();
    }
    public void onBackPressed() {
        back(ss);
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

    public void updatefrv(View view) {
        try {
            int k = mDBHelper.updatefrv(sessionId1, j);
            if (k == 0) {
                imagefrv.setImageResource(R.drawable.iconfrvfalse);
                imagefrv.setBackgroundColor(Color.WHITE);

                j=false;
            } else if (k == 1) {
                imagefrv.setImageResource(R.drawable.iconfrvtrue);
                imagefrv.setBackgroundColor(Color.RED);
                j=true;
            }
        } catch (Exception e) {
            Toast.makeText(HowcookActivity.this, "problem", Toast.LENGTH_SHORT).show();
        }
    }}
