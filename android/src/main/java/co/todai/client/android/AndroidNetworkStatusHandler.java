package co.todai.client.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.Manifest;
import android.content.pm.PackageManager;

import java.lang.Override;

public class AndroidNetworkStatusHandler {

    private final Context context;

    public AndroidNetworkStatusHandler(Context context) {
        this.context = context;
    }

}
