package com.deka.newsapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    String stringUrl;
    public NewsLoader(Context context,String stringUrl){
        super(context);
        this.stringUrl = stringUrl;
        Log.v("NEWS LOADER","object created");
    }
    @Nullable
    @Override
    public List<News> loadInBackground() {
        Log.v("NEWS LOADER","load in background");
        ArrayList<News> newsArrayList = QueryUtils.extractNews(stringUrl);
        return newsArrayList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
