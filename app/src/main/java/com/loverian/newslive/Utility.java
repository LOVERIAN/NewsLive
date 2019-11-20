package com.loverian.newslive;
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
import java.util.ArrayList;
import java.util.List;

class Utility {
    static List<Article>  fetchData(String Surl){
        List <Article> data = new ArrayList<>();
        String JSON = "";
        if(Surl == null){
            return null;
        }
        try {
            URL url = new URL(Surl);
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if(httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder output = new StringBuilder();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
                JSON = output.toString();

                JSONObject root = new JSONObject(JSON);
                JSONArray array = root.getJSONArray("articles");
                for(int i=0;i<array.length();i++){
                    JSONObject currentJSON = array.getJSONObject(i);

                    String title = "";
                    if (!currentJSON.isNull("title")){
                        title = currentJSON.getString("title");
                    }

                    String img = currentJSON.getString("urlToImage");
                    String dec = "";
                    if (!currentJSON.isNull("description")){
                        dec = currentJSON.getString("description");
                    }
                    String urlnk = currentJSON.getString("url");
                    String source = currentJSON.getJSONObject("source").getString("name");
                    String time = currentJSON.getString("publishedAt");
                    String content = "";
                    if (!currentJSON.isNull("content")) {
                        content = currentJSON.getString("content");
                    }
                    data.add(new Article(source,title,dec,urlnk,img,content,time));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }
}