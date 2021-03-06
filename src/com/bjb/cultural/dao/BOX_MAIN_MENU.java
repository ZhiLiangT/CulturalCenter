package com.bjb.cultural.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.bjb.cultural.dao.BoxMainMenu;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table BOX_MAIN_MENU.
*/
public class BOX_MAIN_MENU extends AbstractDao<BoxMainMenu, Void> {

    public static final String TABLENAME = "BOX_MAIN_MENU";

    /**
     * Properties of entity BoxMainMenu.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property MAIN_MENU_ID = new Property(0, String.class, "MAIN_MENU_ID", false, "MAIN__MENU__ID");
        public final static Property MAIN_MENU_NAME = new Property(1, String.class, "MAIN_MENU_NAME", false, "MAIN__MENU__NAME");
        public final static Property SHOW_SORT = new Property(2, Integer.class, "SHOW_SORT", false, "SHOW__SORT");
        public final static Property TYPE = new Property(3, Integer.class, "TYPE", false, "TYPE");
    };


    public BOX_MAIN_MENU(DaoConfig config) {
        super(config);
    }
    
    public BOX_MAIN_MENU(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'BOX_MAIN_MENU' (" + //
                "'MAIN__MENU__ID' TEXT," + // 0: MAIN_MENU_ID
                "'MAIN__MENU__NAME' TEXT," + // 1: MAIN_MENU_NAME
                "'SHOW__SORT' INTEGER," + // 2: SHOW_SORT
                "'TYPE' INTEGER);"); // 3: TYPE
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'BOX_MAIN_MENU'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, BoxMainMenu entity) {
        stmt.clearBindings();
 
        String MAIN_MENU_ID = entity.getMAIN_MENU_ID();
        if (MAIN_MENU_ID != null) {
            stmt.bindString(1, MAIN_MENU_ID);
        }
 
        String MAIN_MENU_NAME = entity.getMAIN_MENU_NAME();
        if (MAIN_MENU_NAME != null) {
            stmt.bindString(2, MAIN_MENU_NAME);
        }
 
        Integer SHOW_SORT = entity.getSHOW_SORT();
        if (SHOW_SORT != null) {
            stmt.bindLong(3, SHOW_SORT);
        }
 
        Integer TYPE = entity.getTYPE();
        if (TYPE != null) {
            stmt.bindLong(4, TYPE);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public BoxMainMenu readEntity(Cursor cursor, int offset) {
        BoxMainMenu entity = new BoxMainMenu( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // MAIN_MENU_ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // MAIN_MENU_NAME
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // SHOW_SORT
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3) // TYPE
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, BoxMainMenu entity, int offset) {
        entity.setMAIN_MENU_ID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setMAIN_MENU_NAME(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSHOW_SORT(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setTYPE(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(BoxMainMenu entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(BoxMainMenu entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
