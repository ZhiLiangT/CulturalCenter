package com.bjb.cultural.service;

import com.bjb.cultural.bean.NetInfo;
import com.bjb.cultural.utils.NetUtils;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * Created by tianzl on 2017/9/19.
 */

public class NetWorkService extends Service {

    public TelephonyManager mTelephonyManager;
    public PhoneStatListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取telephonyManager
        mTelephonyManager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //开始监听
        mListener = new PhoneStatListener();
        //监听信号强度
        mTelephonyManager.listen(mListener, PhoneStatListener.LISTEN_SIGNAL_STRENGTHS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class PhoneStatListener extends PhoneStateListener {
        //获取信号强度
        @SuppressLint("NewApi")
		@Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            NetInfo netInfo=new NetInfo();
            //获取0-4的5种信号级别，越大信号越好,但是api23开始才能用
            int level = signalStrength.getLevel();
            netInfo.setLevel(level);
            //获取网络信号强度
            int gsmSignalStrength = signalStrength.getGsmSignalStrength();
            Log.d("","网络信号强度"+gsmSignalStrength);
            netInfo.setStrength(gsmSignalStrength);
            //获取网络类型
            int type= NetUtils.getNetworkState(getApplicationContext());
            netInfo.setType(type);
            Intent intent=new Intent("com.NETWORK_INFO");
            intent.putExtra("NETWORK_INFO",netInfo);
            sendBroadcast(intent);
        }
    }
}
