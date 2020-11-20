package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    MyDataBaseHelper myDataBaseHelper;
    EditText editTextTitle,editTextAuthor, editTextPages;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        editTextTitle = findViewById(R.id.editTxtTitle);
        editTextAuthor = findViewById(R.id.editTxtAuthor);
        editTextPages = findViewById(R.id.editTxtPages);
        buttonAdd = findViewById(R.id.btnAdd);

        insertBook();
    }

    public void insertBook() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataBaseHelper = new MyDataBaseHelper(AddBookActivity.this);
                Book book = new Book(editTextTitle.getText().toString(), editTextAuthor.getText().toString(), editTextPages.getText().toString());
                boolean insert = myDataBaseHelper.addData(book);
                if (insert)
                    Toast.makeText(AddBookActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddBookActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

}