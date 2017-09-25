package com.bjb.cultural.db;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.database.Cursor;

import com.bjb.cultural.CulturalApplication;
import com.bjb.cultural.dao.BOX_GROUP;
import com.bjb.cultural.dao.BOX_RES;
import com.bjb.cultural.dao.BOX_SUB_MENU;
import com.bjb.cultural.dao.BoxGroup;
import com.bjb.cultural.dao.BoxMainMenu;
import com.bjb.cultural.dao.BoxRes;
import com.bjb.cultural.dao.BoxSubMenu;
import com.bjb.cultural.dao.DaoSession;

public class DBUtils {
	
	private static String sql="SELECT g.GROUP_ID, g.GROUP_NAME, gm.MAIN_MENU_ID, "+
			"m.MAIN_MENU_NAME "+
			"FROM BOX_GROUP g "+
			"LEFT JOIN BOX_GROUP_MENU gm ON g.GROUP_ID = gm.GROUP_ID "+
			"LEFT JOIN BOX_MAIN_MENU m ON m.MAIN_MENU_ID = gm.MAIN_MENU_ID "+
			"WHERE g.GROUP_ID=?";
	private static String slqResList="SELECT m.* "+
			"FROM BOX_MAIN_MENU g "+
			"LEFT JOIN BOX_SUB_MENU  gm ON g.MAIN_MENU_ID = gm.MAIN_MENU_ID "+
			"LEFT JOIN BOX_RES m ON m.SUB_MENU_ID = gm.SUB_MENU_ID "+
			"WHERE g.MAIN_MENU_ID=? LIMIT  ?,? ";
	private static String slqResNum="SELECT COUNT(*) "+
			"FROM BOX_MAIN_MENU g "+
			"LEFT JOIN BOX_SUB_MENU  gm ON g.MAIN_MENU_ID = gm.MAIN_MENU_ID "+
			"LEFT JOIN BOX_RES m ON m.SUB_MENU_ID = gm.SUB_MENU_ID "+
			"WHERE g.MAIN_MENU_ID=? ";


	private static BOX_GROUP box_GROUP;
	private static BOX_SUB_MENU box_SUB_MENU;
	private static BOX_RES box_RES;
	/**
	 * 查询以及分类
	 * @return
	 */
	public static List<BoxGroup> getBoxGroup(){
		List<BoxGroup> temp=new ArrayList<BoxGroup>();
		DaoSession session=CulturalApplication.getDaoSession();
		box_GROUP=session.getBOX_GROUP();
		temp=box_GROUP.queryBuilder().orderDesc(BOX_GROUP.Properties.SHOW_SORT).list();
		return temp;
	}
	public static List<BoxSubMenu> getBoxSubMenu(String msg){
		List<BoxSubMenu> temp=new ArrayList<BoxSubMenu>();
		DaoSession session=CulturalApplication.getDaoSession();
		box_SUB_MENU=session.getBOX_SUB_MENU();
		temp=box_SUB_MENU.queryBuilder().where(BOX_SUB_MENU.Properties.MAIN_MENU_ID.eq(msg)).list();
		return temp;
	}
	/**
	 * 查询资源详情
	 * @param msg
	 * @return
	 */
	public static List<BoxRes> getBoxRes(String msg){
		List<BoxRes> temp=new ArrayList<BoxRes>();
		DaoSession session=CulturalApplication.getDaoSession();
		box_RES=session.getBOX_RES();
		temp=box_RES.queryBuilder().where(BOX_RES.Properties.RES_ID.eq(msg)).list();
		return temp;
	}
	/**
	 * 查询二级分类
	 * @param id
	 * @return
	 */
	public static List<BoxMainMenu> getBoxMainMenu(String id){
		List<BoxMainMenu> temp=new ArrayList<BoxMainMenu>();
		DaoSession session=CulturalApplication.getDaoSession();
		Cursor cursor=session.getDatabase().rawQuery(sql,new String[]{id});
		if(null != cursor && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				BoxMainMenu boxMainMenu=new BoxMainMenu();
				boxMainMenu.setMAIN_MENU_ID(cursor.getString(2));
				boxMainMenu.setMAIN_MENU_NAME(cursor.getString(3));
				temp.add(boxMainMenu);
			}
			cursor.close();
		}
		return temp;
	}
	/**
	 * 查询资源列表
	 * @param id
	 * @return
	 */
	public static List<BoxRes> getResList(String id,String start,String num){
		List<BoxRes> temp=new ArrayList<BoxRes>();
		DaoSession session=CulturalApplication.getDaoSession();
		Cursor cursor=session.getDatabase().rawQuery(slqResList,new String[]{id,start,num});
		if(null != cursor && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				BoxRes boxRes=new BoxRes();
				boxRes.setRES_ID(cursor.getString(0));
				boxRes.setRES_NAME(cursor.getString(8));
				boxRes.setPATH(cursor.getString(10));
				boxRes.setCAPTION(cursor.getString(14));
				boxRes.setNOTICE(cursor.getString(15));
				boxRes.setUPD_DATE(cursor.getString(16));
				boxRes.setCRE_DATE(cursor.getString(17));
				temp.add(boxRes);
			}
			cursor.close();
		}
		return temp;
	}
	/**
	 * 资源列表的资源数量
	 * @return
	 */
	public static int getResNum(String id){
		DaoSession session=CulturalApplication.getDaoSession();
		Cursor cursor=session.getDatabase().rawQuery(slqResNum,new String[]{id});
		int num = 0;
		if(null != cursor && cursor.getCount() > 0) {
			cursor.moveToFirst();
			num=cursor.getInt(0);
			cursor.close();
		}
		return  num;
	}

}
