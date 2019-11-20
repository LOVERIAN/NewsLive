package com.loverian.newslive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter extends ArrayAdapter<Article> {
    private Context mContext;
    private List<Article> articleList = new ArrayList<>();

    public NewsAdapter(@NonNull Context context,ArrayList<Article> list) {
        super(context, 0 , list);
        mContext = context;
        articleList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Article currentArticle = articleList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.img);
        if (currentArticle != null) {
            Picasso.get()
                    .load(currentArticle.getImg())
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .fit()
                    .into(image);
        }

        if(currentArticle!=null) {
            TextView title = (TextView) listItem.findViewById(R.id.title);
            if (currentArticle.getTitle()!=null) {
                title.setText(currentArticle.getTitle());
            }
            TextView dec = (TextView) listItem.findViewById(R.id.dec);
            if (currentArticle.getDec()!=null) {
                dec.setText(currentArticle.getDec());
            }
            TextView time = listItem.findViewById(R.id.time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                long timeAgo = sdf.parse(currentArticle.getTime()).getTime();
                String text = TimeAgo.using(timeAgo);
                time.setText(text);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return listItem;
    }

}
