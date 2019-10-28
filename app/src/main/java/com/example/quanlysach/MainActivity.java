package com.example.quanlysach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnThemtacgia, btnXemdstacgia, btnQuanlysach;
    SQLiteDatabase database = null;
    public static final int OPEN_TACGIA_DIALOG = 1;
    public static final int SEND_DATA_FROM_TACGIA_ACTIVITY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        btnThemtacgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showThemtacgiaDialog();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SEND_DATA_FROM_TACGIA_ACTIVITY){
            Bundle bundle = data.getBundleExtra("DATA_TACGIA");
            String ten= bundle.getString("tentacgia");
            String ho= bundle.getString("hotacgia");
            String ma= bundle.getString("matacgia");
            ContentValues content = new ContentValues();
            content.put("")
            if(database !=null){
                long tacgiaid = database.insert("tblTacgia", null, content);
            }
        }
    }

    public boolean isTableExists(SQLiteDatabase database, String tableName){
        Cursor cursor = database.rawQuery("select DISTINCT tbl_name from sqlite_master " +
                "where tbl_name = '"+tableName+"'", null);
        if (cursor != null){
            if (cursor.getCount()>0){
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    @SuppressLint("WrongConstant")
    public  SQLiteDatabase getDatabase(){
        try {
            database = openOrCreateDatabase("mydata.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            if (database != null) {
                if (isTableExists(database, "tblAuthors"))
                    return database;
                database.setLocale(Locale.getDefault());
                database.setVersion(1);
                database.execSQL("create table tblTacgia(" +
                        "matacgia integer primary key autoincrement, " +
                        "ten text, " +
                        "ho text)");
                database.execSQL("create table tblSach(id integer primary key autoincrement, " +
                        "tuasach text, ngaythem date, " +
                        "matacgia integer not null " +
                        "constraint fk_matacgia references tblTacgia(matacgia) on delete cascade)");
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, "Loi", Toast.LENGTH_SHORT).show();
        }
        return  database;
    }
    public void showThemtacgiaDialog(){
        Intent i = new Intent(MainActivity.this, ThemtacgiaActi.class);
        startActivityForResult(i, OPEN_TACGIA_DIALOG);
    }
    private void anhXa() {
        btnThemtacgia = findViewById(R.id.btnThemtacgia);
        btnXemdstacgia = findViewById(R.id.btnXemdstacgia);
        btnQuanlysach = findViewById(R.id.btnQuanlysach);
    }

}
