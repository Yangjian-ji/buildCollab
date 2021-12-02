package com.example.buildcollab.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.buildcollab.R;

public class HomeFragment extends Fragment {
    Button c;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View InputFragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        c = InputFragmentView.findViewById(R.id.test);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clique!", Toast.LENGTH_LONG).show();
            }
        });
        return InputFragmentView;

    }


}
