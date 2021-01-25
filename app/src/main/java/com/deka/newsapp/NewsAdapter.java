package com.deka.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, @NonNull ArrayList<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView==null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.activity_news,parent,false);
        }
        News news =getItem(position);

        TextView titleTextView = listView.findViewById(R.id.title_textView);
        titleTextView.setText(news.getTitle());

        TextView descriptionTextView = listView.findViewById(R.id.description_textView);
        descriptionTextView.setText(news.getDescription());

        TextView authorTextView = listView.findViewById(R.id.author_textView);
        if (news.getAuthor()==null||news.getAuthor().equals("null")){
            authorTextView.setText(" ");
        }
        else
            authorTextView.setText(news.getAuthor());

        TextView dateTextView = listView.findViewById(R.id.date_textView);
        dateTextView.setText(news.getDate());

        TextView timeTextView = listView.findViewById(R.id.time_textView);
        timeTextView.setText(news.getTime());

        ImageView imageView = listView.findViewById(R.id.image_view);
        Picasso.get().load(news.getImageUrl()).into(imageView);



        return listView;
    }


}
