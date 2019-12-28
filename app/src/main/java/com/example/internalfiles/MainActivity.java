package com.example.internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText text;
    TextView paste;
    String str,str1;

    /**
     * @author Shaked Dromi
     * @since 28/12/2019
     * @version alpha
     * text- the edit text object
     * paste- the text view object
     * str- a temporary String value
     * str1- the Strings collection
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text =(EditText) findViewById(R.id.text);
        paste =(TextView) findViewById(R.id.paste);

        try {
            FileInputStream fis= openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            str1 = br.readLine();
            paste.setText(str1);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set the options menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * here you move to credits activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     * this method adds the changes to the 'collection' and saves them.
     */
    public void savebtn(View view) throws IOException {
        str= text.getText().toString();
        if (!str.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(str1!=null){
                    str1= str1+str;}
                else{str1=str;}
                bw.write(str1);
                bw.close();
                paste.setText(str1);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }}

    }

    /**
     * this method resets all the collection and settings
     */
    public void reset(View view) {
        try {
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            str1="";
            bw.write(str1);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        text.setText("");
        paste.setText("");
    }

    /**
     * this method saves the data that was entered (in files) and exits the app.
     */
    public void exit(View view) {
        str= text.getText().toString();
        if(!str.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(str1!=null){
                    str1= str1+str;}
                else{str1=str;}
                bw.write(str1);
                bw.close();
                paste.setText(str1);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finish();}
    }
}