package com.example.kiven.test302

import android.app.Activity
import com.example.kiven.test302.entity.User
import com.kiven.kutils.tools.KContext
import org.xutils.DbManager
import org.xutils.x

/**
 *
 * Created by kiven on 2017/5/26.
 */
class AppContext : KContext() {

    // 静态变量
    companion object{
        var dbManager: DbManager ?= null;
    }

    override fun init() {
        super.init()

        dbManager = x.getDb(DbManager.DaoConfig().setDbVersion(1).setDbUpgradeListener(DbManager.DbUpgradeListener { db, oldVersion, newVersion ->
            db.delete(User::class.java)
        }))
    }

    override fun onActivityCreate(activity: Activity?) {
        super.onActivityCreate(activity)
    }
}