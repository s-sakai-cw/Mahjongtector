package com.example.mahjongtector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_select_pai.*

class selectPaiActivity : AppCompatActivity() {

    var paiNum = 99
    var handlist: IntArray = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pai)

        //resultYakuActivityから受け取り
        val intent = getIntent()
//        val handlist = intent.extras.getntArray(resultYakuActivity.oldHandlist)
        handlist = intent.getIntArrayExtra("oldHandlist")
        test.setText(handlist.toString() + "\n")

        paiNum = intent.getIntExtra("oldPai", 99)
        test.append(paiNum.toString())

        //ボタン押された時の処理
        aPaiButton.setOnClickListener { onaPaiButtonTapped(it, 0) }
        aPaiButton2.setOnClickListener { onaPaiButtonTapped(it,1) }
        aPaiButton3.setOnClickListener { onaPaiButtonTapped(it,2) }
        aPaiButton4.setOnClickListener { onaPaiButtonTapped(it,3) }
        aPaiButton5.setOnClickListener { onaPaiButtonTapped(it,4) }
        aPaiButton6.setOnClickListener { onaPaiButtonTapped(it,5) }
        aPaiButton7.setOnClickListener { onaPaiButtonTapped(it,6) }
        aPaiButton8.setOnClickListener { onaPaiButtonTapped(it,7) }
        aPaiButton9.setOnClickListener { onaPaiButtonTapped(it,8) }
        aPaiButton10.setOnClickListener { onaPaiButtonTapped(it,9) }
        aPaiButton11.setOnClickListener { onaPaiButtonTapped(it, 10) }
        aPaiButton12.setOnClickListener { onaPaiButtonTapped(it,11) }
        aPaiButton13.setOnClickListener { onaPaiButtonTapped(it,12) }

    }
    fun onaPaiButtonTapped(view: View?, pai: Int){
        //Intentのインスタンス作成　(元, 呼び出したいclass)
        val intent = Intent(this, selectPaiActivity::class.java)

        var newHandlist = handlist.copyOf()
        newHandlist.set(paiNum, pai)
        //選択した牌を変更したのを渡す
        intent.putExtra("newHandlist", newHandlist)

        setResult(Activity.RESULT_OK, intent)
        finish()

    }
}
