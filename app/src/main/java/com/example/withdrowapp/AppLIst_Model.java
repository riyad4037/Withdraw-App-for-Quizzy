package com.example.withdrowapp;

public class AppLIst_Model {

    String AppName;
    int ImageId;

    public AppLIst_Model(String appName, int imageId) {
        AppName = appName;
        ImageId = imageId;
    }

    public AppLIst_Model() {
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
