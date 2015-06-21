package com.example.lutfihabiba.layout1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imgbtn1=(ImageButton)findViewById(R.id.imageButton);
        ImageButton imgbtn2=(ImageButton)findViewById(R.id.imageButton1);
        ImageButton imgbtn3=(ImageButton)findViewById(R.id.imageButton2);

        ImageButton imgbtn4=(ImageButton)findViewById(R.id.imageButton3);

        ImageButton imgbtn5=(ImageButton)findViewById(R.id.imageButton4);

        ImageButton imgbtn6=(ImageButton)findViewById(R.id.imageButton6);


        imgbtn5.setOnClickListener(new View.OnClickListener(){


            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Add_Diet_Chart.class);
                /*i.putExtra("Value1", "Android By Javatpoint");
                i.putExtra("Value2", "Simple Tutorial");
                // Set the request code to any code you like, you can identify the
                // callback via this code*/
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
