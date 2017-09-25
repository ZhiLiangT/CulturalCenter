package com.bjb.cultural;

import java.util.ArrayList;
import java.util.List;

import com.bjb.cultural.view.ExitDialogFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity {
	private Button bt1,bt2;
	private TextView tvContent;
	private List<String> list;
	private String jsonString;
	private Button btDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		initView();
	}

	private void initView() {
		bt1=(Button) findViewById(R.id.test_bt1);
		bt2=(Button) findViewById(R.id.test_bt2);
		btDialog=(Button) findViewById(R.id.test_bt_dialog);
		tvContent=(TextView) findViewById(R.id.test_tv);
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list=getList();
				Gson gson=new GsonBuilder().create();
				jsonString=gson.toJson(list);
				tvContent.setText(jsonString);
			}
		});
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (jsonString!=null&&!jsonString.equals("")) {
					List<String> strings=listStringToOjectList(String.class,jsonString);
					tvContent.setText(strings.toString());
				}
			}
		}); 
		btDialog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ExitDialogFragment dialog=new ExitDialogFragment();
				dialog.setOnConfirmClickListener(new ExitDialogFragment.OnConfirmClickListener() {
					@Override
					public void onConfirm(String pass) {
						Toast.makeText(TestActivity.this, "密码："+pass, Toast.LENGTH_SHORT).show();
					}
				});
//				dialog.show(getFragmentManager(), "dialog_exit");
				dialog.show(getFragmentManager(), "dialog_exit");
				
			}
		});
	}
	public static <T> List<T> listStringToOjectList(Class<T> type,String listString){
	       List<T>list = new ArrayList<T>();
	       JsonArray array = new JsonParser().parse(listString).getAsJsonArray();
	       for(JsonElement e : array) {
	              list.add(new Gson().fromJson(e, type));
	       }
	       return list;
	}
	public List<String> getList(){
		List<String> list=new ArrayList<String>();
		for (int i = 0; i <10; i++) {
			list.add("数据"+i);
		}
		return list;
	}
}
