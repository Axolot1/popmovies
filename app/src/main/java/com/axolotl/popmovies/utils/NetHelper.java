package com.axolotl.popmovies.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


public class NetHelper {

    private static NetHelper instance = null;

    Context context;
    WifiManager mWifiManager;
    WifiInfo mWifiInfo;
    ConnectivityManager mConnectivityManager;
    TelephonyManager telephonyMgr;

    public synchronized static NetHelper getInstance(){
        if (instance == null){
            instance = new NetHelper();
        }
        return instance;
    }

    public NetHelper() {

        mWifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        telephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public NetHelper(Context mContext) {
        this.context = mContext;
        mWifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
    }


    public boolean isWifiEnabled() {
        return mWifiManager.isWifiEnabled();
    }


    public boolean isWifiConnected()
    {
        NetworkInfo mWifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected())
        {
            return true;
        }
        else
            return false;

    }


    public NetworkInfo.State getWifiState() {
        return mConnectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState();
    }

    public NetworkInfo.State getDataState() {
        return mConnectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).getState();
    }


    public boolean isConnected(){
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


}
