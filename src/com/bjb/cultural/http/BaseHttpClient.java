package com.bjb.cultural.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;




import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.R.integer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class BaseHttpClient {
	// TZL 网络框架的替换
	private static int timeoutConnection = 8000;
	private static int timeoutSocket = 8000;
	
	private static BaseHttpClient okHttp;
	
	private static OkHttpClient client;
	
	private BaseHttpClient(){
		client=new OkHttpClient();
		//超时时间
		client.newBuilder()
		.connectTimeout(timeoutConnection, TimeUnit.MILLISECONDS)
		.readTimeout(timeoutSocket, TimeUnit.MILLISECONDS)
		.writeTimeout(timeoutSocket, TimeUnit.MILLISECONDS);
	}
	public static BaseHttpClient getInstance(){
		if (okHttp==null) {
			okHttp=new BaseHttpClient();
		}
		return okHttp;
	}
	
	
	private BaseHttpClient(int timeoutConnection,int timeoutSocket){
		client=new OkHttpClient();
		//超时时间
		client.newBuilder()
		.connectTimeout(60, TimeUnit.SECONDS)
		.readTimeout(10, TimeUnit.SECONDS)
		.writeTimeout(10, TimeUnit.SECONDS);
	}
	public static BaseHttpClient getInstance(int timeoutConnection,int timeoutSocket){
		if (okHttp==null) {
			okHttp=new BaseHttpClient(timeoutConnection,timeoutSocket);
		}
		return okHttp;
	}
	
	/****对外公布的方法****/
	
	//异步get请求
	public static void getRequest(String url,  HashMap<String, String> params,Callback callback){
		getInstance();
		if (params != null && !params.isEmpty()) {
        	StringBuffer buf = new StringBuffer("?");
        	for(String key : params.keySet()){
        		 buf.append("&").append(key).append("=").append(params.get(key));
        	}
        	buf.deleteCharAt(1);       	
        	url += buf.toString();      	
		}
		Request request=new Request.Builder().url(url).get().build();
		client.newCall(request).enqueue(callback);
	}
	//同步get请求方法
	public static String doGetRequest(String url,HashMap<String, String> params){
		getInstance();
		if (params != null && !params.isEmpty()) {
        	StringBuffer buf = new StringBuffer("?");
        	for(String key : params.keySet()){
        		 buf.append("&").append(key).append("=").append(params.get(key));
        	}
        	buf.deleteCharAt(1);       	
        	url += buf.toString();      	
		}
		Request request=new Request.Builder().url(url).get().build();
		Call call=client.newCall(request);
		try {
			Response response = call.execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//异步post请求
	public static void postRequest(String url,Map<String, String> params,Callback callback){
		getInstance();
		RequestBody requestBody=SetRequestBody(params);
		Request request=new Request.Builder().url(url).post(requestBody).build();
		client.newCall(request).enqueue(callback);
	
	}
	//同步post请求
	public static String doPostRequest(String url,Map<String,String> params){
		getInstance();
		RequestBody requestBody=SetRequestBody(params);
		Request request=new Request.Builder().url(url).post(requestBody).build();
		Call call=client.newCall(request);
		try {
			Response response=call.execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//同步下载
	@SuppressWarnings("resource")
	public static int download(String downloadUrl, final String savePath){
		getInstance();
		if(downloadUrl == null || downloadUrl.length() == 0 ||
				savePath == null || savePath.length() == 0) {
			return -1;
		}
		InputStream is=null;
		FileOutputStream fos=null;
		Request request=new Request.Builder().url(downloadUrl).get().build();
		Call call=client.newCall(request);
		try {
			Response response=call.execute();
			is=response.body().byteStream();
			File file=new File(savePath+"tmp");
			if (file.exists()) {
				file.delete();
			}
			fos=new FileOutputStream(file);
			byte[] buffer = new byte[2048];
			int len = 0;
            while ((len = is.read(buffer)) != -1) {
            	fos.write(buffer, 0, len);
            }
            fos.flush();
            if(file.exists()) {
            	file.renameTo(new File(savePath));
        	}
            return 0;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	//同步下载
	 public static int  downloada(final String downloadUrl, final String savePath, final OnDownloadListener listener) {
		 getInstance();
		 if(downloadUrl == null || downloadUrl.length() == 0 ||
					savePath == null || savePath.length() == 0) {
				return -1;
			}
			InputStream is=null;
			FileOutputStream fos=null;
			Request request=new Request.Builder().url(downloadUrl).get().build();
			Call call=client.newCall(request);
			try {
				Response response=call.execute();
				is=response.body().byteStream();
				File file=new File(savePath+"tmp");
				if (file.exists()) {
					file.delete();
				}
				fos=new FileOutputStream(file);
				byte[] buffer = new byte[2048];
				int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	            	fos.write(buffer, 0, len);
	            }
	            fos.flush();
	            if(file.exists()) {
	            	file.renameTo(new File(savePath));
	        	}
	            return 0;
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return -1;
    }
	 /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }
	
	
	 private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//获取下载数据的大小
	public static long getDownloadSize(String downloadUrl){
		getInstance();
		if(downloadUrl == null || downloadUrl.length() == 0) {
			return -1;
		}
		Request request=new Request.Builder().url(downloadUrl).get().build();
		Call call=client.newCall(request);
		try {
			Response response=call.execute();
			long size=response.body().contentLength();
			return size;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	//获取状态
	public static int getStatus(String urlString, HashMap<String, String> params){
		return getStatus(urlString, params, timeoutConnection, timeoutSocket);
	}
	public static int getStatus(String urlString, HashMap<String, String> params, int timeoutConnection, int timeoutSocket){
		
		if (params != null && !params.isEmpty()) {
        	StringBuffer buf = new StringBuffer("?");
        	for(String key : params.keySet()){
        		 buf.append("&").append(key).append("=").append(params.get(key));
        	}
        	buf.deleteCharAt(1);       	
        	urlString += buf.toString();      	
		}
		Request request=new Request.Builder().url(urlString).get().build();
		Call call=client.newCall(request);
		try {
			Response response=call.execute();
			return response.code();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	static public List<NameValuePair> convertMapToNameValuePair(Map<String, String> params) {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for( String key : params.keySet() ) {
			pairs.add(new BasicNameValuePair(key, params.get(key)));
		}
		return pairs;
	}
	
	//设置post请求参数
	public static RequestBody SetRequestBody(Map<String, String> BodyParams){
		getInstance();
        RequestBody body=null;  
        FormBody.Builder formEncodingBuilder=new FormBody.Builder();  
        if(BodyParams != null){  
            Iterator<String> iterator = BodyParams.keySet().iterator();  
            String key = "";  
            while (iterator.hasNext()) {  
                key = iterator.next().toString();  
                formEncodingBuilder.add(key, BodyParams.get(key));  
                Log.d("post http", "post_Params==="+key+"===="+BodyParams.get(key));  
            }  
        }  
        body=formEncodingBuilder.build();  
        return body;  
    }  
	
}
