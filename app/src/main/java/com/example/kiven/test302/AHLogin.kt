package com.example.kiven.test302

import android.os.Bundle
import android.view.View
import com.kiven.kutils.activityHelper.KActivityHelper
import com.kiven.kutils.activityHelper.KHelperActivity

/**
 * Created by kiven on 2017/7/20.
 */
class AHLogin : KActivityHelper() {
    override fun getActivityClas(): Class<*> {
        return KHelperActivity::class.java
    }

    override fun onCreate(activity: KHelperActivity?, savedInstanceState: Bundle?) {
        super.onCreate(activity, savedInstanceState)
        setContentView(R.layout.ah_login)
//        btn_login.text = "登录"
    }

    override fun onClick(view: View) {

    }
}
/*
class AHLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.ah_login)
        btn_login.text = "xxxx"
    }

    fun onClick(view: View) {

    }
}*/
