package com.example.covidtracker.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covidtracker.MainActivity;
import com.example.covidtracker.R;

public class HelpFragment extends Fragment {

    public static HelpFragment newInstance(){
        HelpFragment fragment = new HelpFragment();
        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideSystemUI();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_help, container, false);
        return root;
    }
}
