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
import com.awn.app.bingkainusantara.adapter.WisataListAdapter;
import com.awn.app.bingkainusantara.adapter.WisataListAdapter;
import com.awn.app.bingkainusantara.data.WisataItems;
import com.awn.app.bingkainusantara.data.WisataItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WisataFragment extends Fragment {
    private RecyclerView rvWisata;
    private ArrayList<WisataItems> wisataList;

    public WisataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wisata, container, false);

        rvWisata = (RecyclerView) view.findViewById(R.id.rv_wisata);
        rvWisata.setHasFixedSize(true);

        wisataList = new ArrayList<WisataItems>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("wisata");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                    WisataItems wisataItems = noteSnapshot.getValue(WisataItems.class);
                    wisataList.add(wisataItems);
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
        rvWisata.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        WisataListAdapter wisataListAdapter = new WisataListAdapter(this.getActivity());
        wisataListAdapter.setListData(wisataList);
        rvWisata.setAdapter(wisataListAdapter);

        ItemClickSupport.addTo(rvWisata).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMovies(wisataList.get(position));
            }
        });
    }

    private void showSelectedMovies(WisataItems wisata) {
        Intent intent = new Intent(this.getActivity(), DetailJelajahActivity.class);
        intent.putExtra(DetailJelajahActivity.TYPE, "wisata");
        intent.putExtra(DetailJelajahActivity.KEY, wisata);
        startActivity(intent);
    }

}
