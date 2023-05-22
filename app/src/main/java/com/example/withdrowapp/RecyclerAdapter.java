package com.example.withdrowapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecyclerAdapter extends RecyclerView.Adapter<AppRecyclerViewHolderClass> {

    Context context;
    List<AppLIst_Model> appList = new ArrayList<>();

    public RecyclerAdapter(Context context, List<AppLIst_Model> appList) {
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public AppRecyclerViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AppRecyclerViewHolderClass(LayoutInflater.from(context).inflate(R.layout.single_app_item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AppRecyclerViewHolderClass holder, int position) {

        AppLIst_Model lIst_model = appList.get(position);

        holder.appName.setText(lIst_model.getAppName());
        holder.appLogo.setImageResource(lIst_model.getImageId());
        holder.installButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadAndInstallApk();
            }

            private void downloadAndInstallApk() {

                FirebaseInstances firebaseInstances = new FirebaseInstances();

                StorageReference storageReference = firebaseInstances.storage.getReference().child("app/app-release.apk");

                File apkDirectory = new File(context.getExternalFilesDir(null), "withdraw");
                apkDirectory.mkdirs();
                File apkFile = new File(apkDirectory, ".my_apk_file.apk");

                storageReference.getFile(apkFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(context, "Successfully Downloaded.", Toast.LENGTH_SHORT).show();
                        try{
                            installApk(apkFile);
                        }catch (Exception e){
                            Toast.makeText(context, "Error in installation: "+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error: "+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    private void installApk(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
        Uri apkUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID+".provider", apkFile);
        intent.setData(apkUri);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "No package installer found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }
}
