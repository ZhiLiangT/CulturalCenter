package com.bjb.cultural;

import com.bjb.cultural.view.MyLayout;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private WebView webView;
	private WebSettings setting;
	private String url="file:///android_asset/Demo.html";
	private MyLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
		initEvent();
	}

	private void initEvent() {
		
		rl.onLongClikcListener(new MyLayout.OnLongClickListener() {
			
			@Override
			public void onLongClick() {
				 Toast.makeText(MainActivity.this,"延时任务的长按事件",Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initData() {
		setting=webView.getSettings();
        //允许与JS交互
        setting.setJavaScriptEnabled(true);
        //设置编码格式
        setting.setDefaultTextEncodingName("UTF-8");
        webView.setWebViewClient(new MyWebviewCient());  
        webView.loadUrl(url);
        
		
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
    }

	private void initView() {
		webView=(WebView) findViewById(R.id.webView);
		rl=(MyLayout) findViewById(R.id.main);
	}
	
	 public class MyWebviewCient extends WebViewClient{  
	        @SuppressLint("NewApi")
			@Override  
	        public WebResourceResponse shouldInterceptRequest(WebView view,  
	                String url) {  
	            WebResourceResponse response = null;  
	            response = super.shouldInterceptRequest(view, url);  
	            return response;  
	        }  
	  
	        @Override  
	        public void onPageFinished(WebView view, String url) {  
	            super.onPageFinished(view, url);  
	            Log.d("dream", "***on page finished");  
	           webView.loadUrl("javascript:myFunction()");   
	        }  
	          
	    } 

}
