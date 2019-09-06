package com.mob.sqlitetest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.before);

        button.setOnClickListener(this);
        DBHelper helper = new DBHelper(this, "accusation.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT number FROM accusation ORDER BY _id DESC", null);

        while(cursor.moveToNext()) {
            textView.append(cursor.getString(0));
        }
        db.close();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.before) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
