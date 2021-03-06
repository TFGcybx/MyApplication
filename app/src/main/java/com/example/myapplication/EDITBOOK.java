package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EDITBOOK extends AppCompatActivity{
    private EditText editBook_et_name;
    private Button editBook_bt_save;
    private Button editBook_bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editbook);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        editBook_et_name = findViewById(R.id.editBook_et_name);
        editBook_bt_save = findViewById(R.id.editBook_bt_save);
        editBook_bt_cancel = findViewById(R.id.editBook_bt_cancel);

        String name = intent.getStringExtra("name");

        if(null!=name){
            editBook_et_name.setText(name);
        }

        editBook_bt_save.setOnClickListener(view-> {
            Intent backIntent = new Intent();
            backIntent.putExtra("position", position);
            backIntent.putExtra("name", editBook_et_name.getText().toString());
            setResult(Fragment_BOOKLIST.RESULT_CODE_ADD_DATA, backIntent);
            EDITBOOK.this.finish();
        });

        editBook_bt_cancel.setOnClickListener(View -> {
            Toast.makeText(EDITBOOK.this, "Don't cancel :(", Toast.LENGTH_LONG).show();
        });
    }
}
