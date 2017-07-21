package com.example.kiven.test302

import com.example.kiven.test302.entity.User
import com.kiven.kutils.logHelper.KLog
import kotlin.properties.Delegates

/**
 * kotlin测试文件
 * Created by kiven on 2017/7/19.
 */
fun test() {
    // todo apply
    val user = User("18780296428").apply {
        pw = "123456"
    }
    with(user) { name = "kiven"}
    user.sex = true

    KLog.i(user.toString())

    // todo
    Tool().test()
}

interface CallInterface{
    fun say()
}
class Tool {
    val lazyValue: String by lazy {
        KLog.i("computed!")
        "Hello"
    }

    var observablePro:String by Delegates.observable("") {
        property, oldValue, newValue -> KLog.i("$oldValue -> $newValue")
    }

    fun test() {
        // 委托和懒加载
        KLog.i(lazyValue)
        KLog.i(lazyValue)

        // todo 代理
        doSomething(object : CallInterface {
            override fun say() {
                KLog.i("Hello")
            }
        })
        // todo 可观察属性 Observable
        // 如果你想能够截获一个赋值并“否决”它，就使用 vetoable() 取代 observable()。 在属性被赋新值生效之前会调用传递给 vetoable 的处理程序
        observablePro = "jack"
    }

    fun doSomething(call: CallInterface) = call.say()
}