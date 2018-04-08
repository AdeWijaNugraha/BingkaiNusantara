package com.awn.app.bingkainusantara.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.awn.app.bingkainusantara.DetailJelajahActivity;
import com.awn.app.bingkainusantara.ItemClickSupport;
import com.awn.app.bingkainusantara.JelajahActivity;
import com.awn.app.bingkainusantara.R;
import com.awn.app.bingkainusantara.adapter.KulinerListAdapter;
import com.awn.app.bingkainusantara.data.KulinerItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KulinerFragment extends Fragment {
    private RecyclerView rvKuliner;
    private ArrayList<KulinerItems> kulinerList;

    public KulinerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kuliner, container, false);

        rvKuliner = (RecyclerView) view.findViewById(R.id.rv_kuliner);
        rvKuliner.setHasFixedSize(true);

        kulinerList = new ArrayList<KulinerItems>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("kuliner");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    KulinerItems kulinerItems = noteSnapshot.getValue(KulinerItems.class);
                    kulinerList.add(kulinerItems);
                }

                showRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Database Error", "loadNote:onCancelled", databaseError.toException());
            }
        };

        myRef.orderByKey().addValueEventListener(valueEventListener);

        return view;
    }

    private void showRecyclerView() {
        rvKuliner.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        KulinerListAdapter kulinerListAdapter = new KulinerListAdapter(this.getActivity());
        kulinerListAdapter.setListData(kulinerList);
        rvKuliner.setAdapter(kulinerListAdapter);

        ItemClickSupport.addTo(rvKuliner).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMovies(kulinerList.get(position));
            }
        });
    }

    private void showSelectedMovies(KulinerItems kuliner) {
        Intent intent = new Intent(this.getActivity(), DetailJelajahActivity.class);
        intent.putExtra(DetailJelajahActivity.TYPE, "kuliner");
        intent.putExtra(DetailJelajahActivity.KEY, kuliner);
        startActivity(intent);
    }

}
