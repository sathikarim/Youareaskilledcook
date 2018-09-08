package com.sthiddov.youareaskilledcook.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sthiddov.youareaskilledcook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settoolbar();
    }
    private void settoolbar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        if (id == android.R.id.home
                ) {
            Toast.makeText(this, "pressed1", Toast.LENGTH_SHORT).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void showAbout(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.about, null));
        builder.create().show();
    }
    public void sendEmail(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{ "khhb17@hotmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT,"موضوع المراسلة");
        i.putExtra(Intent.EXTRA_TEXT,"نص المراسلة");
        i.setType("message/rfc822");
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(i, "Send Email"));
        }

    }

    public void gotowsfat(View view) {
        Intent i = new Intent(this,CatrgActivity.class);
        startActivity(i);
        finish();
    }

    public void gotofrv(View view) {
        Intent i = new Intent(this,WsfatActivity.class);
        i.putExtra("key",7);
        startActivity(i);
        finish();
    }
}
