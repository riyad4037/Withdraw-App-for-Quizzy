package com.example.withdrowapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AppRecyclerViewHolderClass extends RecyclerView.ViewHolder {

    ImageView appLogo;
    TextView appName;
    Button installButton;

    public AppRecyclerViewHolderClass(@NonNull View itemView) {
        super(itemView);

        appLogo = itemView.findViewById(R.id.LogoIcon);
        appName = itemView.findViewById(R.id.AppNameTextView);
        installButton = itemView.findViewById(R.id.InstallButton);
    }
}
