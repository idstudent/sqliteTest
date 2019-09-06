package com.mob.sqlitetest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button button, nextBtn;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.btn);
        nextBtn = findViewById(R.id.next);

        DBHelper helper = new DBHelper(this, "accusation.db");
        db = helper.getWritableDatabase();
        button.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn) {
            Log.e("tag", "여기탐?");
            String text = editText.getText().toString();

            Cursor cursor = db.rawQuery("SELECT number FROM accusation ORDER BY _id desc limit 1", null);

            if(cursor.getCount() == 0){
                Log.e("tag", "널일때");
                db.execSQL("INSERT INTO accusation (number) values (?)", new String[]{text});
            }else {
                Log.e("tag", "널아닐때");
                while (cursor.moveToNext()) {
                    if (text.equals(cursor.getString(0))) {
                        Log.e("tag", "중복");
                        Toast.makeText(this, "중복값", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("tag", "insert");
                        db.execSQL("INSERT INTO accusation (number) values (?)", new String[]{text});
                    }
                }
            }
        }else if(view.getId() == R.id.next) {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
