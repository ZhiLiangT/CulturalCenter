package com.bjb.cultural;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bjb.cultural.dao.BoxGroup;
import com.bjb.cultural.dao.BoxMainMenu;
import com.bjb.cultural.dao.BoxRes;
import com.bjb.cultural.db.DBUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DBTestActivity extends Activity implements View.OnClickListener{
	private Button button;
	private TextView textView;
	private Button btClassify;
	private Button btTwoClassify;
	private Button btResourcesList;
	private Button btResourcesDetails;
	private EditText etClassifyID;
	private EditText etTwoClassifyID;
	private EditText etPageSize;
	private EditText etPageNum;
	private EditText etResID;
	private Gson gson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dbtest);
		initView();
		initData();
	}
	 private void initData() {
		 gson=new GsonBuilder().create();
	}
	private void initView() {
		 button=(Button) findViewById(R.id.dbtest_bt1);
		 button.setOnClickListener(this);
		 btClassify=(Button) findViewById(R.id.dbtest_classify);
		 btClassify.setOnClickListener(this);
		 btTwoClassify=(Button) findViewById(R.id.dbtest_two_classify);
		 btTwoClassify.setOnClickListener(this);
		 btResourcesList=(Button) findViewById(R.id.dbtest_ziyuanliebiao);
		 btResourcesList.setOnClickListener(this);
		 btResourcesDetails=(Button) findViewById(R.id.dbtest_ziyuan_details);
		 btResourcesDetails.setOnClickListener(this);
		 textView=(TextView) findViewById(R.id.dbtest_tv);
		 etClassifyID=(EditText) findViewById(R.id.dbtest_et_da_id);
		 etTwoClassifyID=(EditText) findViewById(R.id.dbtest_et_two_id);
		 etPageSize=(EditText) findViewById(R.id.dbtest_et_page_size);
		 etPageNum=(EditText) findViewById(R.id.dbtest_et_page_num);
		 etResID=(EditText) findViewById(R.id.dbtest_et_ziyuan_id);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dbtest_bt1:
			etResID.setText("20150501000100001");
			break;
		case R.id.dbtest_classify:
			//获取一级分类
			List<BoxGroup> listGroup=DBUtils.getBoxGroup();
			Map<String, Object> map=new HashMap<String, Object>();
			Log.d("DBTestActivity", "listGroup的长度"+listGroup.size());
			if (listGroup.size()==0) {
				map.put("msg", "没有数据");
			}else {
				map.put("msg", "获取成功");
			}
			map.put("status", 1);
			map.put("data", listGroup);
			String jsonGroup=gson.toJson(map);
			textView.setText(jsonGroup);
			break;
		case R.id.dbtest_two_classify:
			//二级分类
			String classifyID=etClassifyID.getText().toString();
			List<BoxMainMenu> menus=DBUtils.getBoxMainMenu(classifyID);
			Map<String, Object> mapSub=new HashMap<String, Object>();
			Log.d("DBTestActivity", "subList的长度"+menus.size());
			if (menus.size()==0) {
				mapSub.put("msg", "没有数据");
			}else {
				mapSub.put("msg", "获取成功");
			}
			mapSub.put("status", 1);
			mapSub.put("data", menus);
			String jsonSub=gson.toJson(mapSub);
			textView.setText(jsonSub);
			break;
		case R.id.dbtest_ziyuanliebiao:
			//资源列表
			String twoIDString=etTwoClassifyID.getText().toString();//二级分类的ID
			String pageSize=etPageSize.getText().toString();	//单页的数量
			String pageNum=etPageNum.getText().toString();		//第页
			int size=Integer.parseInt(pageSize);
			int num=Integer.parseInt(pageNum)*size;
			int totalPage=DBUtils.getResNum(twoIDString);
			List<BoxRes> resList=DBUtils.getResList(twoIDString,num+"",pageSize);
			Map<String, Object> mapResList=new HashMap<String, Object>();
			Log.d("DBTestActivity", "subList的长度"+resList.size());
			if (resList.size()==0||resList.toString().equals("")) {
				mapResList.put("msg", "没有数据");
			}else {
				mapResList.put("msg", "获取成功");
			}
			double sum=(double)(totalPage/size);
			mapResList.put("sum", (int)Math.ceil(sum));
			mapResList.put("status", 1);
			mapResList.put("data", resList);
			String jsonResList=gson.toJson(mapResList);
			textView.setText(jsonResList);
			break;
		case R.id.dbtest_ziyuan_details:
			//资源详情
			String redID=etResID.getText().toString();
			List<BoxRes> res=DBUtils.getBoxRes(redID);
			Map<String, Object> mapRes=new HashMap<String, Object>();
			Log.d("DBTestActivity", "res的长度"+res.size());
			if (res.size()==0) {
				mapRes.put("msg", "没有数据");
			}else {
				mapRes.put("msg", "获取成功");
			}
			mapRes.put("status", 1);
			mapRes.put("data", res);
			String jsonRed=gson.toJson(mapRes);
			textView.setText(jsonRed);
			break;
		default:
			break;
		}
	}  
}
