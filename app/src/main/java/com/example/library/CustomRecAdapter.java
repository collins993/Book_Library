package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecAdapter extends RecyclerView.Adapter<CustomRecAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList book_id, book_title, book_author, book_pages;
    Animation translateAnim;


    public CustomRecAdapter(Context context, Activity activity, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.activity = activity;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_view_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bookIdTxt.setText(String.valueOf(book_id.get(position)));
        holder.bookTitleTxt.setText(String.valueOf(book_title.get(position)));
        holder.bookAuthorTxt.setText(String.valueOf(book_author.get(position)));
        holder.bookPagesTxt.setText(String.valueOf(book_pages.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(book_id.get(position)));
                intent.putExtra("title", String.valueOf(book_title.get(position)));
                intent.putExtra("author", String.valueOf(book_author.get(position)));
                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookIdTxt, bookTitleTxt, bookAuthorTxt, bookPagesTxt;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdTxt = itemView.findViewById(R.id.bookId);
            bookTitleTxt = itemView.findViewById(R.id.bookTitle);
            bookAuthorTxt = itemView.findViewById(R.id.bookAuthor);
            bookPagesTxt = itemView.findViewById(R.id.bookPages);
            cardView = itemView.findViewById(R.id.cardView);
            translateAnim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            cardView.setAnimation(translateAnim);
        }
    }
}
