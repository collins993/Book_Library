package com.example.library;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText id_input, title_input, author_input, pages_input;
    Button btnUpdate, btnDelete;
    String id, title, author, pages;
    MyDataBaseHelper myDataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        id_input = findViewById(R.id.editTxtId);
        title_input = findViewById(R.id.editTxtTitle2);
        author_input = findViewById(R.id.editTxtAuthor2);
        pages_input = findViewById(R.id.editTxtPages2);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        getAndSetIntent();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }

        updateBook();
        deleteBook();
    }

    public void deleteBook() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }


    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDataBaseHelper = new MyDataBaseHelper(UpdateActivity.this);
                Book book = new Book(Integer.parseInt(id_input.getText().toString()),title_input.getText().toString(),author_input.getText().toString(), pages_input.getText().toString());
                int deletedBook = myDataBaseHelper.deleteData(book);
                if (deletedBook != 0 )
                    Toast.makeText(UpdateActivity.this, "Book Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateActivity.this, "Book Not Deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


    public void updateBook() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataBaseHelper = new MyDataBaseHelper(UpdateActivity.this);
                Book book = new Book(Integer.parseInt(id_input.getText().toString()), title_input.getText().toString(), author_input.getText().toString(), pages_input.getText().toString());

                boolean isUpdated = myDataBaseHelper.updateData(book);
                if (isUpdated)
                    Toast.makeText(UpdateActivity.this, "Book Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdateActivity.this, "Book Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getAndSetIntent() {
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("pages")) {

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            id_input.setText(id);
            id_input.setVisibility(View.GONE);
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
        }
        else{
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }

}