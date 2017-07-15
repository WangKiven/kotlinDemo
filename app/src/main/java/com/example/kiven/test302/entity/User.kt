package com.example.kiven.test302.entity

import org.xutils.db.annotation.Column
import org.xutils.db.annotation.Table

/**
 *
 * Created by kiven on 2017/5/26.
 */
@Table(name = "user")
class User(@Column(name = "account") var account: String? = null, @Column(name = "pw") var pw: String? = null) {
    @Column(name = "id", isId = true)
    var id: Long? = null

    constructor(account: String) : this()
    constructor(account: String, pw: String, name: String) : this(account, pw)
    constructor(account: String, pw: String, name: String, sex: Boolean) : this(account, pw)
}