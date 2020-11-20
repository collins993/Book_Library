package com.example.library;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    MyDataBaseHelper myDataBaseHelper;
    ArrayList<String> book_id, book_title, book_author, book_pages;
    CustomRecAdapter customRecAdapter;
    ImageView imageView;
    TextView noData;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteAll) {
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recView);
        fab = findViewById(R.id.floatingActionButton);
        imageView = findViewById(R.id.imageView);
        noData = findViewById(R.id.textView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        myDataBaseHelper = new MyDataBaseHelper(this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        viewAllData();

        customRecAdapter = new CustomRecAdapter(MainActivity.this, MainActivity.this,
                                                book_id, book_title, book_author, book_pages);
        recyclerView.setAdapter(customRecAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void viewAllData() {
        Cursor cursor = myDataBaseHelper.getAllData();
        if (cursor.getCount() == 0) {
            imageView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.VISIBLE);

        }
        else{
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));

                imageView.setVisibility(View.GONE);
                noData.setVisibility(View.GONE);
            }
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All Books ?");
        builder.setMessage("Are you sure you want to delete All Books ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myDataBaseHelper = new MyDataBaseHelper(MainActivity.this);
                myDataBaseHelper.deleteAllData();
                recreate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}