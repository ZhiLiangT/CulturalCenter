package com.bjb.cultural.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.bjb.cultural.dao.BoxGroup;
import com.bjb.cultural.dao.BoxSubMenu;
import com.bjb.cultural.dao.BoxRes;
import com.bjb.cultural.dao.BoxMainMenu;

import com.bjb.cultural.dao.BOX_GROUP;
import com.bjb.cultural.dao.BOX_SUB_MENU;
import com.bjb.cultural.dao.BOX_RES;
import com.bjb.cultural.dao.BOX_MAIN_MENU;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bOX_GROUPConfig;
    private final DaoConfig bOX_SUB_MENUConfig;
    private final DaoConfig bOX_RESConfig;
    private final DaoConfig bOX_MAIN_MENUConfig;

    private final BOX_GROUP bOX_GROUP;
    private final BOX_SUB_MENU bOX_SUB_MENU;
    private final BOX_RES bOX_RES;
    private final BOX_MAIN_MENU bOX_MAIN_MENU;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bOX_GROUPConfig = daoConfigMap.get(BOX_GROUP.class).clone();
        bOX_GROUPConfig.initIdentityScope(type);

        bOX_SUB_MENUConfig = daoConfigMap.get(BOX_SUB_MENU.class).clone();
        bOX_SUB_MENUConfig.initIdentityScope(type);

        bOX_RESConfig = daoConfigMap.get(BOX_RES.class).clone();
        bOX_RESConfig.initIdentityScope(type);

        bOX_MAIN_MENUConfig = daoConfigMap.get(BOX_MAIN_MENU.class).clone();
        bOX_MAIN_MENUConfig.initIdentityScope(type);

        bOX_GROUP = new BOX_GROUP(bOX_GROUPConfig, this);
        bOX_SUB_MENU = new BOX_SUB_MENU(bOX_SUB_MENUConfig, this);
        bOX_RES = new BOX_RES(bOX_RESConfig, this);
        bOX_MAIN_MENU = new BOX_MAIN_MENU(bOX_MAIN_MENUConfig, this);

        registerDao(BoxGroup.class, bOX_GROUP);
        registerDao(BoxSubMenu.class, bOX_SUB_MENU);
        registerDao(BoxRes.class, bOX_RES);
        registerDao(BoxMainMenu.class, bOX_MAIN_MENU);
    }
    
    public void clear() {
        bOX_GROUPConfig.getIdentityScope().clear();
        bOX_SUB_MENUConfig.getIdentityScope().clear();
        bOX_RESConfig.getIdentityScope().clear();
        bOX_MAIN_MENUConfig.getIdentityScope().clear();
    }

    public BOX_GROUP getBOX_GROUP() {
        return bOX_GROUP;
    }

    public BOX_SUB_MENU getBOX_SUB_MENU() {
        return bOX_SUB_MENU;
    }

    public BOX_RES getBOX_RES() {
        return bOX_RES;
    }

    public BOX_MAIN_MENU getBOX_MAIN_MENU() {
        return bOX_MAIN_MENU;
    }

}
