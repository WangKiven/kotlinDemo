package com.example.kiven.test302

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.kiven.kutils.tools.KAlertDialogHelper
import com.kiven.kutils.tools.KString
import com.kiven.kutils.tools.KToast
import kotlinx.android.synthetic.main.activity_flash.*

/**
 *
 * Created by kiven on 2017/5/21.
 */
class FlashActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flash)

        btn_login.text = "登录"
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_login -> {
                val account = inputLayout_account.editText?.text.toString()
                if (KString.isBlank(account)) {
                    KToast.ToastMessage(this, "请输入账号", Toast.LENGTH_LONG)
                    return
                }

                val pw = inputLayout_pw.editText?.text.toString()
                if (KString.isBlank(pw)) {
                    KAlertDialogHelper.Show1BDialog(this, "请输入密码")
                    return
                }

                startActivity(Intent(MainActivity@ this, MainActivity::class.java))
            }

            btn_register -> {
                startActivityForResult(Intent(this, RegisterActivity::class.java), 779)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            779 -> startActivity(Intent(MainActivity@ this, MainActivity::class.java))
        }
    }
}