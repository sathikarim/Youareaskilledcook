package com.sthiddov.youareaskilledcook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.sthiddov.youareaskilledcook.modils.Crtag;
import com.sthiddov.youareaskilledcook.modils.Howcook;
import com.sthiddov.youareaskilledcook.modils.Wsfat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class Datacook extends SQLiteOpenHelper {
    private static String DB_NAME = "datacook.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public Datacook(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
//انشاء ملف database
       if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }
//تحديث الملف
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }
//تأكد من ملف يوجد داخل المجلد
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }
//نسخ المعلومات
    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
//مشخ المعلومات
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
//فتح ملف الداتا
    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    //غلق ملف الداتا
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
    //عمل arrayllist الخاص بأنواع الوصفات
    public ArrayList<Crtag> getallCrtag(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Crtag> crtagList = new ArrayList<>();
        Log.v("karim","SELECT * FROM datacratg");
        //تحديد الجدول الذي يأخذ منه المعلومات
        Cursor cursor = db.rawQuery("SELECT * FROM datacratg",null);

        while (cursor.moveToNext()){
            Crtag crtag = new Crtag();
            crtag.setId(cursor.getInt(cursor.getColumnIndex("id")));
            crtag.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            byte blob[]=cursor.getBlob(cursor.getColumnIndex("idimg"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            crtag.setBitmap(bitmap);
            crtagList.add(crtag);
        }
        return  crtagList;
    }
    public ArrayList<Wsfat> getallWsfat(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Wsfat> wsfatList = new ArrayList<>();
        //تحديد الجدول الذي يأخذ منه المعلومات
        //تحديد نوع الوصف ايضا
        //عبر المقارنة بين id نوع الوصفة مع filter
        Cursor cursor = db.rawQuery("SELECT * FROM wsfat WHERE filter =" +i,null);

        while (cursor.moveToNext()){
            Wsfat wsfat = new Wsfat();
            wsfat.setId(cursor.getInt(cursor.getColumnIndex("id")));
            wsfat.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            wsfat.setTime(cursor.getString(cursor.getColumnIndex("fulltime")));
            byte blob[]=cursor.getBlob(cursor.getColumnIndex("img"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            wsfat.setImagewsf(bitmap);
            wsfatList.add(wsfat);
        }
        return  wsfatList;
    }

    public ArrayList<Howcook> gethowcook(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Howcook> howcookList = new ArrayList<>();
        ArrayList<String> shopList=new ArrayList<>();
        //تحديد الجدول الذي يأخذ منه المعلومات
        //تحديد نوع الوصف ايضا
//        ياأتي بمعلومة التى تساوي قيمة id
        Cursor cursor = db.rawQuery("SELECT * FROM wsfat WHERE id =" +i,null);
        while (cursor.moveToNext()){
            int k=0;
            for(int j=0;j<21;j++) {
                String mokn="m"+(j+1);
                String test=cursor.getString(cursor.getColumnIndex(mokn));
                if (test!= null){
                    shopList.add(test);
                    k++;
                }
            }
            byte blob[]=cursor.getBlob(cursor.getColumnIndex("img"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            boolean value;
            if (cursor.getInt(cursor.getColumnIndex("frv")) > 0){value=true;}else {value=false;}

            Howcook howcook = new Howcook(cursor.getInt(cursor.getColumnIndex("id")),cursor.getInt(cursor.getColumnIndex("timecook")),cursor.getInt(cursor.getColumnIndex("timefire")),cursor.getInt(cursor.getColumnIndex("number")),k,value,cursor.getString(cursor.getColumnIndex("dec")),cursor.getString(cursor.getColumnIndex("title")),bitmap,shopList);
            howcookList.add(howcook);

        }
        return  howcookList;
    }
//تحدسث منطقية الوصفة
    //مفصلة ام لا
    public int updatefrv(int i,Boolean check) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int k;
        //تحويل من مفضلة الى لا
        if (check) {
            cv.put("frv", 0);
            db.update("wsfat", cv, "id" + "= " + i, null);
            k=0;
//تحويل من لا الى مفضلة
        } else {
            cv.put("frv", 1);
            k = db.update("wsfat", cv, "id" + "=" + i, null);
            k=1;
        }

return k;
    }
    //قائمة المفضلة
    public ArrayList<Wsfat> getallfrv(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Wsfat> wsfatList = new ArrayList<>();
        //تحديد الجدول الذي يأخذ منه المعلومات
        //تحديد نوع الوصف ايضا
        //عبر المقارنة بقسية frv
        //1=true
        //0=false
        Cursor cursor = db.rawQuery("SELECT * FROM wsfat WHERE frv = 1",null);

        while (cursor.moveToNext()){
            Wsfat wsfat = new Wsfat();
            wsfat.setId(cursor.getInt(cursor.getColumnIndex("id")));
            wsfat.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            wsfat.setTime(cursor.getString(cursor.getColumnIndex("fulltime")));
            byte blob[]=cursor.getBlob(cursor.getColumnIndex("img"));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            wsfat.setImagewsf(bitmap);
            wsfatList.add(wsfat);
        }
        return  wsfatList;
    }
}