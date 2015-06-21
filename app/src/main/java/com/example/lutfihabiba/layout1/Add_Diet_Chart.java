package com.example.lutfihabiba.layout1;

/**
 * Created by Lutfi Habiba on 20/06/2015.
 */

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.lutfihabiba.layout1.R.id;

public class Add_Diet_Chart extends Activity implements OnClickListener {
    EditText edtDayName, edtBreakfat, edtLunch;
    EditText edtDinner;
    Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;
    SQLiteDatabase db;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_diet_chart);
       edtDayName=(EditText)findViewById(R.id.editday_name);
        edtBreakfat=(EditText)findViewById(R.id.editbreakfast);
        edtLunch=(EditText)findViewById(R.id.editlunch);
        edtDinner=(EditText)findViewById(R.id.editdINNER);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnModify=(Button)findViewById(R.id.btnModify);
        btnView=(Button)findViewById(R.id.btnView);
        btnViewAll=(Button)findViewById(R.id.btnViewAll);
        btnShowInfo=(Button)findViewById(R.id.btnShowInfo);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShowInfo.setOnClickListener(this);
        db=openOrCreateDatabase("DietChartDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS chart(day_name VARCHAR,breakfast VARCHAR,lunch VARCHAR,dinner VARCHAR);");
    }
    private EditText findViewById(Class<id> class1) {
        // TODO Auto-generated method stub
        return null;
    }
    public void onClick(View view)
    {
        if(view==btnAdd)
        {
            if(edtDayName.getText().toString().trim().length()==0||
                    edtBreakfat.getText().toString().trim().length()==0||
                    edtLunch.getText().toString().trim().length()==0||edtDinner.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }

            db.execSQL("INSERT INTO chart VALUES('"+edtDayName.getText()+"','"+edtBreakfat.getText()+","+edtLunch.getText()+
                    "','"+edtDinner.getText()+"');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view==btnDelete)
        {
            if(edtDayName.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Day Name");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM chart WHERE day_name='"+edtDayName.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM chart WHERE day_name='"+edtDayName.getText()+"'");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Day Name is not Inputed");
            }
            clearText();
        }
        if(view==btnModify)
        {
            if(edtDayName.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please Day Name");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM chart WHERE day_name='"+edtDayName.getText()+"'", null);
            if(c.moveToFirst())
            {
                db.execSQL("UPDATE chart SET breakfast='"+edtBreakfat.getText()+"', lunch='"+edtLunch.getText()+
                        "','"+edtDinner.getText()+"' WHERE day_name='"+edtDayName.getText()+"'");
                showMessage("Success", "Record Modified");
            }
            else
            {
                showMessage("Error", "Day Is Not Inputed");
            }
            clearText();
        }
        if(view==btnView)
        {
            if(edtDayName.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Day Name");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM chart WHERE day_name='"+edtDayName.getText()+"'", null);
            if(c.moveToFirst())
            {
                edtBreakfat.setText(c.getString(1));
                edtLunch.setText(c.getString(2));
                edtDinner.setText(c.getString(3));
            }
            else
            {
                showMessage("Error", "Day is not Inputed");
                clearText();
            }
        }
        if(view==btnViewAll)
        {
            Cursor c=db.rawQuery("SELECT * FROM chart", null);
            if(c.getCount()==0)
            {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Day Name: "+c.getString(0)+"\n");
                buffer.append("Breakfast: "+c.getString(1)+"\n");
                buffer.append("Lunch: "+c.getString(2)+"\n");
                buffer.append("Dinner: "+c.getString(3)+"\n\n");
            }
            showMessage("Diet Chart Plan", buffer.toString());
        }
        if(view==btnShowInfo)
        {
            showMessage("Diet Chart Record", "Developed By Lopa");
        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        edtDayName.setText("");
        edtBreakfat.setText("");
        edtLunch.setText("");
        edtDinner.setText("");
        edtDayName.requestFocus();
    }
    }
