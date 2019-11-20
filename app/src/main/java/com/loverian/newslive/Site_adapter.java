package com.loverian.newslive;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anil on 9/17/2019.
 */


public class Site_adapter extends ArrayAdapter<Single_view> {

    public Site_adapter(Activity context, ArrayList<Single_view> nsite) {
        super(context, 0, nsite);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_item_view, parent, false);
        }

        Single_view currentWord = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.txt);
        nameTextView.setText(currentWord.getName());

        return listItemView;
    }
}