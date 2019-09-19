package com.example.mahjongtector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraButton.setOnClickListener {
            val intent = Intent(this, resultYakuActivity::class.java)
            startActivity(intent)
        }

//        // 非同期処理
//        "https://www.casareal.co.jp/".httpGet().response { request, response, result ->
//            when (result) {
//                is Result.Success -> {
//                    // レスポンスボディを表示
//                    println("非同期処理の結果：" + String(response.data))
//                }
//                is Result.Failure -> {
//                    println("通信に失敗しました。")
//                }
//            }
//        }
//
//        // 同期処理
//        val triple = "https://www.casareal.co.jp/".httpGet().response()
//        // レスポンスボディを表示
//        println("同期処理の結果：" + String(triple.second.data))

    }
}

