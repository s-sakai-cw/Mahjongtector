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

        paiNum = intent.getIntExtra("oldPai", 99)


        //ボタン押された時の処理
        aPaiButton.setOnClickListener { onaPaiButtonTapped(it, 0) }
        aPaiButton2.setOnClickListener { onaPaiButtonTapped(it,1) }
        aPaiButton3.setOnClickListener { onaPaiButtonTapped(it,2) }
        aPaiButton4.setOnClickListener { onaPaiButtonTapped(it,3) }
        aPaiButton5.setOnClickListener { onaPaiButtonTapped(it,4) }
        aPaiButton6.setOnClickListener { onaPaiButtonTapped(it,34) }
        aPaiButton7.setOnClickListener { onaPaiButtonTapped(it,5) }
        aPaiButton8.setOnClickListener { onaPaiButtonTapped(it,6) }
        aPaiButton9.setOnClickListener { onaPaiButtonTapped(it,7) }
        aPaiButton10.setOnClickListener { onaPaiButtonTapped(it,8)}
        aPaiButton11.setOnClickListener { onaPaiButtonTapped(it, 9) }
        aPaiButton12.setOnClickListener { onaPaiButtonTapped(it,10) }
        aPaiButton13.setOnClickListener { onaPaiButtonTapped(it,11) }
        aPaiButton14.setOnClickListener { onaPaiButtonTapped(it,12) }
        aPaiButton15.setOnClickListener { onaPaiButtonTapped(it,13) }
        aPaiButton16.setOnClickListener { onaPaiButtonTapped(it,35) }
        aPaiButton17.setOnClickListener { onaPaiButtonTapped(it,14) }
        aPaiButton18.setOnClickListener { onaPaiButtonTapped(it,15) }
        aPaiButton19.setOnClickListener { onaPaiButtonTapped(it,16) }
        aPaiButton20.setOnClickListener { onaPaiButtonTapped(it,17) }
        aPaiButton21.setOnClickListener { onaPaiButtonTapped(it, 18) }
        aPaiButton22.setOnClickListener { onaPaiButtonTapped(it,19) }
        aPaiButton23.setOnClickListener { onaPaiButtonTapped(it,20) }
        aPaiButton24.setOnClickListener { onaPaiButtonTapped(it,21) }
        aPaiButton25.setOnClickListener { onaPaiButtonTapped(it,22) }
        aPaiButton26.setOnClickListener { onaPaiButtonTapped(it,36) }
        aPaiButton27.setOnClickListener { onaPaiButtonTapped(it,23) }
        aPaiButton28.setOnClickListener { onaPaiButtonTapped(it,24) }
        aPaiButton29.setOnClickListener { onaPaiButtonTapped(it,25) }
        aPaiButton30.setOnClickListener { onaPaiButtonTapped(it,26) }
        aPaiButton31.setOnClickListener { onaPaiButtonTapped(it, 27) }
        aPaiButton32.setOnClickListener { onaPaiButtonTapped(it,28) }
        aPaiButton33.setOnClickListener { onaPaiButtonTapped(it,29) }
        aPaiButton34.setOnClickListener { onaPaiButtonTapped(it,30) }
        aPaiButton35.setOnClickListener { onaPaiButtonTapped(it,31) }
        aPaiButton36.setOnClickListener { onaPaiButtonTapped(it,32) }
        aPaiButton37.setOnClickListener { onaPaiButtonTapped(it,33) }


    }
    fun onaPaiButtonTapped(view: View?, pai: Int){
        //Intentのインスタンス作成　(元, 呼び出したいclass)
//        val intent = Intent(this, selectPaiActivity::class.java)

        val intentSub = Intent(this, resultYakuActivity::class.java)
//            Intent()

        var newHandlist = handlist.copyOf()
        newHandlist.set(paiNum, pai)
        //選択した牌を変更したのを渡す
        intentSub.putExtra("newHandlist", newHandlist)
        intentSub.putExtra("changePai", pai)


        setResult(Activity.RESULT_OK, intentSub)
        finish()


//        startActivity(intentSub)

    }
}
