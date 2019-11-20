package com.loverian.newslive;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Headlines extends Fragment {

    // Passes Url to async task and retrieves json response
    // Sets listview to the adapter
    private String URL = "https://newsapi.org/v2/top-headlines?country=in&apiKey=ad12484cfda2454fa2021eb30247b3db";

    private NewsAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar simpleProgressbar;


    public Headlines() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);

        simpleProgressbar = (ProgressBar) view.findViewById(R.id.simpleProgressBar1);

        simpleProgressbar.setVisibility(View.VISIBLE);

        final ArrayList<Article> list = new ArrayList<>();
        adapter = new NewsAdapter(getContext(),list);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(adapter);
        final NewsAsync newsAsync = new NewsAsync();
        newsAsync.execute(URL);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                NewsAsync newsAsync1 = new NewsAsync();
                newsAsync1.execute(URL);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),Details.class);
                Article currentArticle = list.get(position);
                intent.putExtra("img",currentArticle.getImg());
                intent.putExtra("content",currentArticle.getContent());
                intent.putExtra("source",currentArticle.getSource());
                intent.putExtra("dec",currentArticle.getDec());
                intent.putExtra("url",currentArticle.getUrl());
                startActivity(intent);
            }

        });
        return view;
    }
    private class NewsAsync extends AsyncTask<String,Void, List<Article>> {
        @Override
        protected List<Article> doInBackground(String... strings) {
            if (strings[0] == null) {
                return null;
            }
            return Utility.fetchData(strings[0]);

        }

        @Override
        protected void onPostExecute(List<Article> strings) {
            super.onPostExecute(strings);

            adapter.clear();
            if(strings!=null){
                adapter.addAll(strings);
            }
            simpleProgressbar.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
