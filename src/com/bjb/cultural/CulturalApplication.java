package com.bjb.cultural;

import com.bjb.cultural.dao.DaoMaster;
import com.bjb.cultural.dao.DaoSession;
import com.bjb.cultural.db.SataSQLiteOpenHelper;
import com.bjb.cultural.service.NetWorkService;
import com.tencent.smtt.sdk.QbSdk;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CulturalApplication extends Application {
	
	private static Context context;
	public static DaoMaster daoMaster;
	public static DaoSession daoSession;
	@Override
	public void onCreate() {
		super.onCreate();
		context=this;
		//startNetService();
		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
			@Override
			public void onViewInitFinished(boolean arg0) {
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				Log.d("app", " onViewInitFinished is " + arg0);
			}
			@Override
			public void onCoreInitFinished() {
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(), cb);
	}
	
	//定时检测网络状态
    public void startNetService(){
        Intent intent=new Intent(context,NetWorkService.class);
        startService(intent);
    }
    /** 
     * 取得DaoMaster 
     *  
     * @param context 
     * @return 
     */  
    public static DaoMaster getDaoMaster() {  
        if (daoMaster == null) {  
        	// TZL 
        	SataSQLiteOpenHelper helper=new SataSQLiteOpenHelper(context);
           // OpenHelper helper = new DaoMaster.DevOpenHelper(context,"News_info_db", null);  
            daoMaster = new DaoMaster(helper.getWritableDatabase());  
        }  
        return daoMaster;  
    }  
      
    /** 
     * 取得DaoSession 
     *  
     * @param context 
     * @return 
     */  
    public static DaoSession getDaoSession() {  
        if (daoSession == null) {  
            if (daoMaster == null) {  
                daoMaster = getDaoMaster();  
            }  
            daoSession = daoMaster.newSession();  
        }  
        return daoSession;  
    }
    
}
