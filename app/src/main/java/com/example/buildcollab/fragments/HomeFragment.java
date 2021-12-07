package com.example.buildcollab.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildcollab.R;

public class HomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = InputFragmentView.findViewById(R.id.reclycleview);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(InputFragmentView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Falta a leitura dos dados
        //   mAdapter = new MyAdapter(LoadData);
        mRecyclerView.setAdapter(mAdapter);


        return InputFragmentView;

    }


}
