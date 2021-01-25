package com.deka.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class QueryUtils {

    private QueryUtils(){}

    public static ArrayList<News> extractNews(String stringUrl){
        Log.v(" QUERY UTILS","extract news ");
        URL url = createURL(stringUrl);
        HttpURLConnection httpsURLConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        try {
            httpsURLConnection = (HttpURLConnection) url.openConnection();
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(15000);
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            httpsURLConnection.connect();
            if(httpsURLConnection.getResponseCode()==200){
                Log.v(" QUERY UTILS","https connected");
                inputStream = httpsURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else Log.v(" QUERY UTILS",url+"");
        } catch (Exception e) {
            Log.e("QueryUtils", "Problem retrieving the news results in ExtractNews.", e);
        }finally {
            if(httpsURLConnection!=null){
                httpsURLConnection.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return parseJson(jsonResponse);
    }

    private static ArrayList<News> parseJson(String json){
        ArrayList<News> newsArrayList = new ArrayList<>();
        if (!json.isEmpty()){

            try {
                String title = null;
                String description = null;
                String imageUrl = null;
                String url = null;
                String date = null;
                String time = null;
                String author = null;

                JSONObject sample = new JSONObject(json);
                JSONArray articles = sample.optJSONArray("articles");
                for(int i=0;i<articles.length();i++){
                    JSONObject article = articles.getJSONObject(i);
                    title = article.optString("title");
                    author = article.optString("author");
                    description = article.optString("description");
                    url = article.optString("url");
                    imageUrl = article.optString("urlToImage");
                    date = article.getString("publishedAt");
                    String[] x = date.split("T");
                    date = x[0];
                    time = x[1];
                    newsArrayList.add(new News(title,imageUrl,description,author,date,time,url));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return newsArrayList;

    }

    private static URL createURL(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String readFromStream(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader =new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try{
                String line = bufferedReader.readLine();
                while (line!=null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }
}
