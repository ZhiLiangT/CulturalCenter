package com.bjb.cultural.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.bjb.cultural.dao.BoxRes;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BOX_RES.
*/
public class BOX_RES extends AbstractDao<BoxRes, Void> {

    public static final String TABLENAME = "BOX_RES";

    /**
     * Properties of entity BoxRes.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property RES_ID = new Property(0, String.class, "RES_ID", false, "RES_ID");
        public final static Property SUB_MENU_ID = new Property(1, String.class, "SUB_MENU_ID", false, "SUB_MENU_ID");
        public final static Property CATEGORY_ID = new Property(2, String.class, "CATEGORY_ID", false, "CATEGORY_ID");
        public final static Property FLG = new Property(3, String.class, "FLG", false, "FLG");
        public final static Property TYPE = new Property(4, String.class, "TYPE", false, "TYPE");
        public final static Property MY_FLG = new Property(5, String.class, "MY_FLG", false, "MY_FLG");
        public final static Property IS_TOP = new Property(6, String.class, "IS_TOP", false, "IS_TOP");
        public final static Property IS_REC = new Property(7, String.class, "IS_REC", false, "IS_REC");
        public final static Property RES_NAME = new Property(8, String.class, "RES_NAME", false, "RES_NAME");
        public final static Property RES_SORT_NAME = new Property(9, String.class, "RES_SORT_NAME", false, "RES_SORT_NAME");
        public final static Property PATH = new Property(10, String.class, "PATH", false, "PATH");
        public final static Property IMG = new Property(11, String.class, "IMG", false, "IMG");
        public final static Property TOP_IMG = new Property(12, String.class, "TOP_IMG", false, "TOP_IMG");
        public final static Property ACTOR = new Property(13, String.class, "ACTOR", false, "ACTOR");
        public final static Property CAPTION = new Property(14, String.class, "CAPTION", false, "CAPTION");
        public final static Property NOTICE = new Property(15, String.class, "NOTICE", false, "NOTICE");
        public final static Property UPD_DATE = new Property(16, String.class, "UPD_DATE", false, "UPD_DATE");
        public final static Property CRE_DATE = new Property(17, String.class, "CRE_DATE", false, "CRE_DATE");
    };


    public BOX_RES(DaoConfig config) {
        super(config);
    }
    
    public BOX_RES(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BOX_RES' (" + //
                "'RES_ID' TEXT," + // 0: RES_ID
                "'SUB_MENU_ID' TEXT," + // 1: SUB_MENU_ID
                "'CATEGORY_ID' TEXT," + // 2: CATEGORY_ID
                "'FLG' TEXT," + // 3: FLG
                "'TYPE' TEXT," + // 4: TYPE
                "'MY_FLG' TEXT," + // 5: MY_FLG
                "'IS_TOP' TEXT," + // 6: IS_TOP
                "'IS_REC' TEXT," + // 7: IS_REC
                "'RES_NAME' TEXT," + // 8: RES_NAME
                "'RES_SORT__NAME' TEXT," + // 9: RES_SORT_NAME
                "'PATH' TEXT," + // 10: PATH
                "'IMG' TEXT," + // 11: IMG
                "'TOP_IMG' TEXT," + // 12: TOP_IMG
                "'ACTOR' TEXT," + // 13: ACTOR
                "'CAPTION' TEXT," + // 14: CAPTION
                "'NOTICE' TEXT," + // 15: NOTICE
                "'UPD_DATE' TEXT," + // 16: UPD_DATE
                "'CRE_DATE' TEXT);"); // 17: CRE_DATE
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BOX_RES'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BoxRes entity) {
        stmt.clearBindings();
 
        String RES_ID = entity.getRES_ID();
        if (RES_ID != null) {
            stmt.bindString(1, RES_ID);
        }
 
        String SUB_MENU_ID = entity.getSUB_MENU_ID();
        if (SUB_MENU_ID != null) {
            stmt.bindString(2, SUB_MENU_ID);
        }
 
        String CATEGORY_ID = entity.getCATEGORY_ID();
        if (CATEGORY_ID != null) {
            stmt.bindString(3, CATEGORY_ID);
        }
 
        String FLG = entity.getFLG();
        if (FLG != null) {
            stmt.bindString(4, FLG);
        }
 
        String TYPE = entity.getTYPE();
        if (TYPE != null) {
            stmt.bindString(5, TYPE);
        }
 
        String MY_FLG = entity.getMY_FLG();
        if (MY_FLG != null) {
            stmt.bindString(6, MY_FLG);
        }
 
        String IS_TOP = entity.getIS_TOP();
        if (IS_TOP != null) {
            stmt.bindString(7, IS_TOP);
        }
 
        String IS_REC = entity.getIS_REC();
        if (IS_REC != null) {
            stmt.bindString(8, IS_REC);
        }
 
        String RES_NAME = entity.getRES_NAME();
        if (RES_NAME != null) {
            stmt.bindString(9, RES_NAME);
        }
 
        String RES_SORT_NAME = entity.getRES_SORT_NAME();
        if (RES_SORT_NAME != null) {
            stmt.bindString(10, RES_SORT_NAME);
        }
 
        String PATH = entity.getPATH();
        if (PATH != null) {
            stmt.bindString(11, PATH);
        }
 
        String IMG = entity.getIMG();
        if (IMG != null) {
            stmt.bindString(12, IMG);
        }
 
        String TOP_IMG = entity.getTOP_IMG();
        if (TOP_IMG != null) {
            stmt.bindString(13, TOP_IMG);
        }
 
        String ACTOR = entity.getACTOR();
        if (ACTOR != null) {
            stmt.bindString(14, ACTOR);
        }
 
        String CAPTION = entity.getCAPTION();
        if (CAPTION != null) {
            stmt.bindString(15, CAPTION);
        }
 
        String NOTICE = entity.getNOTICE();
        if (NOTICE != null) {
            stmt.bindString(16, NOTICE);
        }
 
        String UPD_DATE = entity.getUPD_DATE();
        if (UPD_DATE != null) {
            stmt.bindString(17, UPD_DATE);
        }
 
        String CRE_DATE = entity.getCRE_DATE();
        if (CRE_DATE != null) {
            stmt.bindString(18, CRE_DATE);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public BoxRes readEntity(Cursor cursor, int offset) {
        BoxRes entity = new BoxRes( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // RES_ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // SUB_MENU_ID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // CATEGORY_ID
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // FLG
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // TYPE
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // MY_FLG
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // IS_TOP
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // IS_REC
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // RES_NAME
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // RES_SORT_NAME
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // PATH
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // IMG
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // TOP_IMG
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // ACTOR
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // CAPTION
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // NOTICE
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // UPD_DATE
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17) // CRE_DATE
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BoxRes entity, int offset) {
        entity.setRES_ID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSUB_MENU_ID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCATEGORY_ID(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFLG(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTYPE(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMY_FLG(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIS_TOP(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIS_REC(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRES_NAME(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setRES_SORT_NAME(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPATH(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIMG(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTOP_IMG(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setACTOR(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCAPTION(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setNOTICE(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setUPD_DATE(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setCRE_DATE(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(BoxRes entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(BoxRes entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}