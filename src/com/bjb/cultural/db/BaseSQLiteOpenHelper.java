package com.bjb.cultural.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseSQLiteOpenHelper extends SQLiteOpenHelper {

	Context context;
	
	public BaseSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	public Context getContext() {
		return this.context;
	}
	
	@Override
	public abstract void onCreate(SQLiteDatabase db);
	
	@Override
	public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
	
	/*^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*/
	
	public long insertRecode(String table, ContentValues cv){
		SQLiteDatabase db = getWritableDatabase();

		return db.insert(table, null, cv);
	}
	
	public Cursor selectRecode(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursorr = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		return cursorr;
	}
	
	public Cursor selectRecode(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursorr = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		return cursorr;
	}
	
	public Cursor select(String sql, String[] selectionArgs){
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursorr = db.rawQuery(sql, selectionArgs);
		
		return cursorr;
	}

	public int deleteRecode(String table, String where, String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		int result = db.delete(table, where, whereArgs);
		db.close();
		return result;
	}
	
	public int deleteAllRecode(String table){
		SQLiteDatabase db = getWritableDatabase();
		int result = db.delete(table, null, null);
		return result;
	}
	
	public int updateRecode(String table, ContentValues cv, String where, String[] whereArgs){
		SQLiteDatabase db = getWritableDatabase();
		int result = db.update(table, cv, where, whereArgs);
		return result;
	}

	/*
	 * 
	 */
	public void excuteSqlFromFile(SQLiteDatabase db, String FileName) {
		
		InputStream is = null;
		InputStreamReader is_reader = null;
		BufferedReader buffer_reader = null;
		
		try {
			is = context.getAssets().open(FileName);
			is_reader = new InputStreamReader(is);
			
			buffer_reader = new BufferedReader(is_reader);
			
			String lineSql = null;
			
			while((lineSql = buffer_reader.readLine()) != null) {
				db.execSQL(lineSql);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(buffer_reader != null) {
					buffer_reader.close();
				}
				
				if(is_reader != null) {
					is_reader.close();
				}
				
				if(is != null) {
					is.close();
				}
				
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
	
}
