package com.example.withdrowapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView appListRecyclerView;
        List<AppLIst_Model> DemoListData = new ArrayList<>();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DemoListData.add(new AppLIst_Model("Quizzy",R.drawable.logo));


        appListRecyclerView = view.findViewById(R.id.AppRecyclerList);

        appListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        appListRecyclerView.setAdapter(new RecyclerAdapter(getContext(), DemoListData));


        return view;
    }
}