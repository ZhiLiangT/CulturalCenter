package com.bjb.cultural.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SataSQLiteOpenHelper extends BaseSQLiteOpenHelper {
	
	public static final String dbName = "/storage/emulated/0/db/box_sata.db";
	public static final int version =2;
	
	public SataSQLiteOpenHelper(Context context) {
		super(context, dbName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("debug", "SataSQLiteOpenHelper > init by sql.");

//		excuteSqlFromFile(db, "db/cre_sata.sql");
//		excuteSqlFromFile(db, "db/data_sata.sql");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("debug", "SataSQLiteOpenHelper > onUpgrade by sql.");
		excuteSqlFromFile(db, "db/cre_sata.sql");
		excuteSqlFromFile(db, "db/data_sata.sql");
	}

}
