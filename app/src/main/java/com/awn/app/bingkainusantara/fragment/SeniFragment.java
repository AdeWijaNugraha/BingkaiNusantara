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
import com.awn.app.bingkainusantara.R;
import com.awn.app.bingkainusantara.adapter.KulinerListAdapter;
import com.awn.app.bingkainusantara.adapter.SeniListAdapter;
import com.awn.app.bingkainusantara.data.KulinerItems;
import com.awn.app.bingkainusantara.data.SeniItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeniFragment extends Fragment {
    private RecyclerView rvSeni;
    private ArrayList<SeniItems> seniList;

    public SeniFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seni, container, false);

        rvSeni = (RecyclerView) view.findViewById(R.id.rv_seni);
        rvSeni.setHasFixedSize(true);

        seniList = new ArrayList<SeniItems>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("seni");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    SeniItems seniItems = noteSnapshot.getValue(SeniItems.class);
                    seniList.add(seniItems);
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
        rvSeni.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        SeniListAdapter seniListAdapter = new SeniListAdapter(this.getActivity());
        seniListAdapter.setListData(seniList);
        rvSeni.setAdapter(seniListAdapter);

        ItemClickSupport.addTo(rvSeni).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMovies(seniList.get(position));
            }
        });
    }

    private void showSelectedMovies(SeniItems seni) {
        Intent intent = new Intent(this.getActivity(), DetailJelajahActivity.class);
        intent.putExtra(DetailJelajahActivity.TYPE, "seni");
        intent.putExtra(DetailJelajahActivity.KEY, seni);
        startActivity(intent);
    }

}
