package com.loverian.newslive;


import android.content.Intent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MarathiFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReferemce;
    private ChildEventListener mChildEventListener;

    public MarathiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listview, container, false);

        final ProgressBar simpleProgressbar = (ProgressBar) rootView.findViewById(R.id.simpleProgressBar);

        simpleProgressbar.setVisibility(View.VISIBLE);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReferemce = mFirebaseDatabase.getReferenceFromUrl("https://newslive-8d78f.firebaseio.com/Marathi");

        final ArrayList<Single_view> nsite = new ArrayList<Single_view>();
        final Site_adapter adapter = new Site_adapter(getActivity(), nsite);
        GridView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Single_view mSingleView = dataSnapshot.getValue(Single_view.class);
               adapter.add(mSingleView);
               simpleProgressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReferemce.addChildEventListener(mChildEventListener);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Single_view word = adapter.getItem(position);
                Intent intent = new Intent(getActivity(),Yt.class);
                intent.putExtra("url",word.getUrl());
                intent.putExtra("info",word.getInfo());
                startActivity(intent);
            }
        });

        return rootView;
    }

}
