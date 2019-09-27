package com.example.mahjongtector

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_result_yaku.*
import java.text.FieldPosition
import java.util.*
import java.util.Collections.min


class resultYakuActivity : AppCompatActivity() {

    //14の手牌
    var handlist = intArrayOf(


//        0, 1, 2, 9, 10, 11, 18, 19, 20, 15, 15, 5, 6
            0, 1, 2, 3, 4, 5, 6, 7, 8, 20, 20, 22, 22

        //九蓮宝燈
//    0,0,0,1,2,3,4,5,6,7,8,8,8

//        0,0,0,1,2,3,4,5,6,7,27,27,27

    )





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_yaku)

        val intent = intent
        handlist = intent.getIntArrayExtra("tehaiPicture")





//        var bakaze : String
//
//        val adapter = ArrayAdapter(applicationContext,
//            android.R.layout.simple_spinner_item, spinnerItems)
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        // spinner に adapter をセット
//        // Kotlin Android Extensions
//        spinner.adapter = adapter
//
//        // リスナーを登録
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            //　アイテムが選択された時
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?, position: Int, id: Long
//            ) {
//                val spinnerParent = parent as Spinner
//                val item = spinnerParent.selectedItem as String
//                // Kotlin Android Extensions
//                textView2.text = item
//            }
//
//            //　アイテムが選択されなかった
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                //
//            }
//        }








        //手牌
        var tiles = IntArray(34, { 0 } )
        for (tile in handlist){
            if(tile<34) {
                tiles[tile] += 1
            }
            else{
                when(tile){
                    34 -> tiles[4] += 1
                    35 -> tiles[13] += 1
                    36 -> tiles[22] += 1


                }

            }
        }




        aimYaku(tiles)






        //ボタン画像セット
        setPaiImage(handlist[0], paiButton1)
        setPaiImage(handlist[1], paiButton2)
        setPaiImage(handlist[2], paiButton3)
        setPaiImage(handlist[3], paiButton4)
        setPaiImage(handlist[4], paiButton5)
        setPaiImage(handlist[5], paiButton6)
        setPaiImage(handlist[6], paiButton7)
        setPaiImage(handlist[7], paiButton8)
        setPaiImage(handlist[8], paiButton9)
        setPaiImage(handlist[9], paiButton10)
        setPaiImage(handlist[10], paiButton11)
        setPaiImage(handlist[11], paiButton12)
        setPaiImage(handlist[12], paiButton13)



        //ボタン押された時の処理
        paiButton1.setOnClickListener { onPaiButtonTapped(it, 0) }
        paiButton2.setOnClickListener { onPaiButtonTapped(it,1) }
        paiButton3.setOnClickListener { onPaiButtonTapped(it,2) }
        paiButton4.setOnClickListener { onPaiButtonTapped(it,3) }
        paiButton5.setOnClickListener { onPaiButtonTapped(it,4) }
        paiButton6.setOnClickListener { onPaiButtonTapped(it,5) }
        paiButton7.setOnClickListener { onPaiButtonTapped(it,6) }
        paiButton8.setOnClickListener { onPaiButtonTapped(it,7) }
        paiButton9.setOnClickListener { onPaiButtonTapped(it,8) }
        paiButton10.setOnClickListener { onPaiButtonTapped(it,9) }
        paiButton11.setOnClickListener { onPaiButtonTapped(it,  10) }
        paiButton12.setOnClickListener { onPaiButtonTapped(it,11) }
        paiButton13.setOnClickListener { onPaiButtonTapped(it,12) }


        //並べ替えボタン
        sortButton.setOnClickListener {
            Arrays.sort(handlist);
            //ボタン画像セット
            setPaiImage(handlist[0], paiButton1)
            setPaiImage(handlist[1], paiButton2)
            setPaiImage(handlist[2], paiButton3)
            setPaiImage(handlist[3], paiButton4)
            setPaiImage(handlist[4], paiButton5)
            setPaiImage(handlist[5], paiButton6)
            setPaiImage(handlist[6], paiButton7)
            setPaiImage(handlist[7], paiButton8)
            setPaiImage(handlist[8], paiButton9)
            setPaiImage(handlist[9], paiButton10)
            setPaiImage(handlist[10], paiButton11)
            setPaiImage(handlist[11], paiButton12)
            setPaiImage(handlist[12], paiButton13)
        }
    }




    fun onPaiButtonTapped(view: View?, pai: Int){
        //Intentのインスタンス作成　(元, 呼び出したいclass)
        val intent = Intent(this, selectPaiActivity::class.java)
//        //インテントにデータを追加　(名前, 値)　画像ボタンのidを渡している
//        intent.putExtra("myHand", view?.id)
//        //アクティビティ起動
//        startActivity(intent)
        //handlistと変更牌番号を渡す
        intent.putExtra("oldHandlist", handlist)
        intent.putExtra("oldPai", pai)
        val requestCode = 1000
        startActivityForResult( intent, requestCode )

    }


    //画面戻ってきたら
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)
//        super.onActivityResult(requestCode, 2000, intent)

        if (resultCode == Activity.RESULT_OK &&
            requestCode == 1000 && intent != null) {




//            val intent = getIntent()
            handlist = intent.getIntArrayExtra("newHandlist")


            //手牌
            var tiles = IntArray(34, { 0 } )
            for (tile in handlist){
                if(tile<34) {
                    tiles[tile] += 1
                }
                else{
                    when(tile){
                        34 -> tiles[4] += 1
                        35 -> tiles[13] += 1
                        36 -> tiles[22] += 1


                    }

                }
            }


            //ボタン画像セット
            setPaiImage(handlist[0], paiButton1)
            setPaiImage(handlist[1], paiButton2)
            setPaiImage(handlist[2], paiButton3)
            setPaiImage(handlist[3], paiButton4)
            setPaiImage(handlist[4], paiButton5)
            setPaiImage(handlist[5], paiButton6)
            setPaiImage(handlist[6], paiButton7)
            setPaiImage(handlist[7], paiButton8)
            setPaiImage(handlist[8], paiButton9)
            setPaiImage(handlist[9], paiButton10)
            setPaiImage(handlist[10], paiButton11)
            setPaiImage(handlist[11], paiButton12)
            setPaiImage(handlist[12], paiButton13)

            aimYaku(tiles)


        }
    }


    //ボタンに牌画像セット
    fun setPaiImage(pai: Int, imageButton: ImageButton){
        when(pai){
            0 -> imageButton.setImageResource(R.drawable.m1)
            1 -> imageButton.setImageResource(R.drawable.m2)
            2 -> imageButton.setImageResource(R.drawable.m3)
            3 -> imageButton.setImageResource(R.drawable.m4)
            4 -> imageButton.setImageResource(R.drawable.m5)
            5 -> imageButton.setImageResource(R.drawable.m6)
            6 -> imageButton.setImageResource(R.drawable.m7)
            7 -> imageButton.setImageResource(R.drawable.m8)
            8 -> imageButton.setImageResource(R.drawable.m9)

            9 -> imageButton.setImageResource(R.drawable.p1)
            10 -> imageButton.setImageResource(R.drawable.p2)
            11 -> imageButton.setImageResource(R.drawable.p3)
            12 -> imageButton.setImageResource(R.drawable.p4)
            13 -> imageButton.setImageResource(R.drawable.p5)
            14 -> imageButton.setImageResource(R.drawable.p6)
            15 -> imageButton.setImageResource(R.drawable.p7)
            16 -> imageButton.setImageResource(R.drawable.p8)
            17 -> imageButton.setImageResource(R.drawable.p9)

            18 -> imageButton.setImageResource(R.drawable.s1)
            19 -> imageButton.setImageResource(R.drawable.s2)
            20 -> imageButton.setImageResource(R.drawable.s3)
            21 -> imageButton.setImageResource(R.drawable.s4)
            22 -> imageButton.setImageResource(R.drawable.s5)
            23 -> imageButton.setImageResource(R.drawable.s6)
            24 -> imageButton.setImageResource(R.drawable.s7)
            25 -> imageButton.setImageResource(R.drawable.s8)
            26 -> imageButton.setImageResource(R.drawable.s9)

            27 -> imageButton.setImageResource(R.drawable.ton)
            28 -> imageButton.setImageResource(R.drawable.nan)
            29 -> imageButton.setImageResource(R.drawable.sha)
            30 -> imageButton.setImageResource(R.drawable.pe)

            31 -> imageButton.setImageResource(R.drawable.haku)
            32 -> imageButton.setImageResource(R.drawable.hatu)
            33 -> imageButton.setImageResource(R.drawable.chun)

            34 -> imageButton.setImageResource(R.drawable.m5r)
            35 -> imageButton.setImageResource(R.drawable.p5r)
            36 -> imageButton.setImageResource(R.drawable.s5r)




        }

    }





    //シャン点数
    fun getSyantenNum(tiles: IntArray) : Pair<Int, MutableList<Tile>> {

        yaku1.append("国士無双シャンテン数" + getKokusiSyantenNum(tiles).first.toString() + "不要牌"+getKokusiSyantenNum(tiles).second[0].toString() + "\n")
        yaku1.append("七対子シャンテン数" + getTiitoituSyantenNum(tiles).first.toString() + "\n")
        yaku1.append("普通のシャンテン数" + getNormalSyantenNum(tiles).first.toString() + "不要牌"+getNormalSyantenNum(tiles).second[0].toString() + "\n")

        val syanten_min = kotlin.math.min(kotlin.math.min(getKokusiSyantenNum(tiles).first, getTiitoituSyantenNum(tiles).first), getNormalSyantenNum(tiles).first)
        if ((syanten_min == getKokusiSyantenNum(tiles).first)) {
            return Pair(syanten_min, getKokusiSyantenNum(tiles).second)
        } else if ((syanten_min == getTiitoituSyantenNum(tiles).first)) {
            return Pair(syanten_min, getTiitoituSyantenNum(tiles).second)
        } else {
            return Pair(syanten_min, getNormalSyantenNum(tiles).second)
        }
    }

    //国士無双のシャンテン数を返す
    fun getKokusiSyantenNum(tiles: IntArray) : Pair<Int, MutableList<Tile>> {

        var toituflag = false
        var syanten_kokusi = 13
        // 作業用変数
        var tmp = tiles.copyOf()
        //老頭牌
        for (i in 0 until 34) {
            if ((Tile.valueOf(i).isYaochu())) {
                if ((tmp[i] > 0)) {
                    syanten_kokusi -= 1
                    tmp[i] -= 1
                }
                if ((tmp[i] >= 1 && !toituflag)) {
                    toituflag = true
                    tmp[i] -= 1
                }
            }
        }
        //
        syanten_kokusi -= if (toituflag) 1 else 0

        //追加や変更したいリストを作りたいときMutableListを使う
        var gomi_kokusi: MutableList<Tile> = mutableListOf()
        for (k in 0 until 34) {
            if ((tmp[k] == 1)) {
                gomi_kokusi.add(Tile.valueOf(k))
            }
        }

        return Pair(syanten_kokusi, gomi_kokusi)
    }


    //チートイツのシャンテン数を返す
    fun getTiitoituSyantenNum(tiles: IntArray) : Pair<Int, MutableList<Tile>> {
        var toitu = 0
        var syanten_tiitoi = 6
        // 作業用変数
        var tmp = tiles.copyOf()
        //トイツ数を数える
        for (i in 0 until 34) {
            if ((tmp[i] >= 2)) {
                toitu += 1
                tmp[i] -= 2
            }
            if ((tmp[i] == 2)) {
                toitu -= 1
            }
        }
        syanten_tiitoi -= toitu

        var gomi_tiitoi: MutableList<Tile> = mutableListOf()
        for (k in 0 until 34) {
            if ((tmp[k] == 1)) {
                gomi_tiitoi.add(Tile.valueOf(k))
            }
        }
        return Pair(syanten_tiitoi, gomi_tiitoi)
    }

    // ------- 通常の和了の形におけるシャンテン数の計算のためのグローバル変数的な ------
    var mentu = 0
    //メンツ数
    var toitu = 0
    //トイツ数
    var kouho = 0
    //ターツ数
    var temp = 0
    //シャンテン数（計算用）
    var syanten_normal: Int = 0
    //シャンテン数（結果用） 普通にシャンテン数考える用
    var gomi_normal: MutableList<Tile> = mutableListOf()

    //普通にシャンテン数考える
    fun getNormalSyantenNum(tiles: IntArray) : Pair<Int, MutableList<Tile>> {
        // 作業用変数
        var tmp = tiles.copyOf()
        mentu = 0
        toitu = 0
        kouho = 0
        temp = 0
        syanten_normal = 8
        for (i in 0 until 34) {


            //頭ありと仮定して計算
            if ((2 <= tmp[i])) {
                toitu += 1
                tmp[i] -= 2
                mentu_cut(tmp, start = 0)
                tmp[i] += 2
                toitu -= 1
            }
            //頭無しと仮定して計算
            mentu_cut(tmp, start = 0)
        }
        return Pair(syanten_normal, gomi_normal)
    }

    //最終的な結果
//メンツ抜き出しの関数//////////////////////////
    fun mentu_cut(tmp: IntArray, start: Int) {
//        yaku1.append("mentu " + mentu.toString() + "\n")
        var j = 0
        //牌が見つかるまで飛ばす
        for (k in start until 34) {
            j = k
            if ((tmp[j] > 0)) {
                break
            }
        }
        //メンツを抜き終わったのでターツ抜きへ
        if ((start > 33)) {
            taatu_cut(tmp, start = 0)
            return
        }
        //コーツ
        if ((tmp[j] >= 3)) {
            mentu += 1
            tmp[j] -= 3
            mentu_cut(tmp, start = j)
            tmp[j] += 3
            mentu -= 1
        }
        //シュンツ
        if ((j != 7 && j != 8 && j != 16 && j != 17 && j < 25)) {
            if ((tmp[j + 1] > 0 && tmp[j + 2] > 0)) {
                mentu += 1
                tmp[j] -= 1
                tmp[j + 1] -= 1
                tmp[j + 2] -= 1
                mentu_cut(tmp, start = j)
                tmp[j] += 1
                tmp[j + 1] += 1
                tmp[j + 2] += 1
                mentu -= 1
            }
        }
        //メンツ無しと仮定
        mentu_cut(tmp, start = j + 1)
    }

    //ターツ抜き出しの関数///////////////////////////
    fun taatu_cut(tmp: IntArray, start: Int) {
        var j = 0
        //牌が見つかるまで飛ばす
        for (k in start until 34) {
            j = k
            if ((tmp[j] > 0)) {
                break
            }
        }
        //抜き出し終了
        if ((start >= 34)) {
            temp = 8 - mentu * 2 - kouho - toitu
            if ((temp < syanten_normal)) {
                syanten_normal = temp
                gomi_normal = mutableListOf()
                for (k in 0 until 34) {
                    if ((tmp[k] == 1)) {
                        gomi_normal.add(Tile.valueOf(k))
                    }
                }

            }
            return
        }
        if ((mentu + kouho < 4)) {
            //トイツ
            if ((tmp[j] == 2)) {
                kouho += 1
                tmp[j] -= 2
                taatu_cut(tmp, start = j)
                tmp[j] += 2
                kouho -= 1
            }
            //ペンチャンかリャンメン
            if ((j != 8 && j != 17 && j < 27)) {
                if ((tmp[j + 1] > 0)) {
                    kouho += 1
                    tmp[j] -= 1
                    tmp[j + 1] -= 1
                    taatu_cut(tmp, start = j)
                    tmp[j] += 1
                    tmp[j + 1] += 1
                    kouho -= 1
                }
            }
            //カンチャン
            if ((j != 7 && j != 8 && j != 16 && j != 17 && j < 27)) {
                if ((tmp[j + 2] > 0)) {
                    kouho += 1
                    tmp[j] -= 1
                    tmp[j + 2] -= 1
                    taatu_cut(tmp, start = j)
                    tmp[j] += 1
                    tmp[j + 2] += 1
                    kouho -= 1
                }
            }
        }
        //ターツなしと仮定
        taatu_cut(tmp, start = j + 1)
    }




    //狙える役/////////////
    fun aimYaku(tiles: IntArray){

        // 作業用変数
        var tmp = tiles.copyOf()
//        var tmp = tiles みたいにするとtilesも変わってしまう
        yaku1.setText("成立している役\n")
        yaku2.setText("狙える役\n")




//        yaku1.append("国士無双シャンテン数" + getKokusiSyantenNum(tiles).first.toString() + "不要牌"+getKokusiSyantenNum(tiles).second[0].toString() + "\n")
//        yaku1.append("七対子シャンテン数" + getTiitoituSyantenNum(tiles).first.toString() + "不要牌"+getTiitoituSyantenNum(tiles).second[0].toString() + "\n")
//        yaku1.append("普通のシャンテン数" + getNormalSyantenNum(tiles).first.toString() + "\n")




        var yakuScoreList = doubleArrayOf(isReach(tmp), isIppatu(tmp), isTumo(tmp), isPinhu(tmp), isTanyao(tmp), isIpeiko(tmp), isHaku(tmp), isHatu(tmp), isChun(tmp), isJikaze(tmp), isBakaze(tmp), isRinsyan(tmp), isTyankan(tmp), isHaitei(tmp), isHoutei(tmp), isDoubleReach(tmp), isTyanta(tmp), isHonroutou(tmp), isSansyokuDoujun(tmp), isIttuu(tmp), isToiToi(tmp), isSansyokuDoukou(tmp), isSanankou(tmp), isSankantu(tmp), isSyousangen(tmp), isTitoitu(tmp), isRyanpeiko(tmp), isJuntyan(tmp), isHonitu(tmp), isTinitu(tmp), isDora(tmp), isSuankou(tmp), isSuankouTanki(tmp), isDaisangen(tmp), isTuiso(tmp), isSyoususi(tmp), isDaisusi(tmp), isRyuisou(tmp), isTyurenPoutou(tmp), isJunseiTyurenpoutou(tmp), isTinroutou(tmp), isSukantu(tmp), isTenhou(tmp), isTihou(tmp), isKokusi(tmp), isKokusi13(tmp))
        var countEst = 0
        var countAim = 0
        for (i in yakuScoreList.indices){
            when(yakuScoreList[i]){
                1.0 -> {
                    if (countEst == 0){
                        yaku1Name.setText("${Yaku.valueOf(i).yakuName}\n    ${Yaku.valueOf(i).yakuExp}\n")
                        countEst += 1
                    }
                    else {
                        yaku1Name.append("${Yaku.valueOf(i).yakuName}\n    ${Yaku.valueOf(i).yakuExp}\n")
                    }
                }

                0.66 -> {
                    if (countAim == 0){
                        yaku2Name.setText("${Yaku.valueOf(i).yakuName}\n    ${Yaku.valueOf(i).yakuExp}\n")
                        countAim += 1
                    }
                    else {
                        yaku2Name.append("${Yaku.valueOf(i).yakuName}\n    ${Yaku.valueOf(i).yakuExp}\n")
                    }
                }
//                0.33 -> yaku3.append("まあ狙える: ${Yaku.valueOf(i).yakuName}\n    ${Yaku.valueOf(i).yakuExp}\n")
            }
        }
    }
//
//    // 狙うべき役を判定するための関数テーブル
//    val yakuFuncList: List<() -> Float>
//        get() = listOf(isReach, isIppatu, isTumo, isPinhu, isTanyao, isIpeiko, isHaku, isHatu, isChun, isJikaze, isBakaze, isRinsyan, isTyankan, isHaitei, isHoutei, isDoubleReach, isTyanta, isHonroutou, isSansyokuDoujun, isIttuu, isToiToi, isSansyokuDoukou, isSanankou, isSankantu, isSyousangen, isTitoitu, isRyanpeiko, isJuntyan, isHonitu, isTinitu, isDora, isSuankou, isSuankouTanki, isDaisangen, isTuiso, isSyoususi, isDaisusi, isRyuisou, isTyurenPoutou, isJunseiTyurenpoutou, isTinroutou, isSukantu, isTenhou, isTihou, isKokusi, isKokusi13)
//


    //狙える役判定関数//////////////

    //平和
    fun isPinhu(tmp: IntArray) : Double {
        // 作業用変数
        var tmpPin = tmp.copyOf()
        // 0.33:
        // 0.66: 頭があって，リャンメん二つ
        // 1.0: テンパイ
        var count = 0

        for (i in 1 .. 7) {
            while (tmpPin[i] > 0 && tmpPin[i - 1] > 0 && tmpPin[i + 1] > 0) {
                tmpPin[i] -= 1
                tmpPin[i - 1] -= 1
                tmpPin[i + 1] -= 1
                count += 1
            }
        }

        for (i in 10 .. 16) {
            while (tmpPin[i] > 0 && tmpPin[i - 1] > 0 && tmpPin[i + 1] > 0) {
                tmpPin[i] -= 1
                tmpPin[i - 1] -= 1
                tmpPin[i + 1] -= 1
                count += 1
            }
        }
        for (i in 19 .. 25) {
            while (tmpPin[i] > 0 && tmpPin[i - 1] > 0 && tmpPin[i + 1] > 0) {
                tmpPin[i] -= 1
                tmpPin[i - 1] -= 1
                tmpPin[i + 1] -= 1
                count += 1
            }
        }


//        if (count == 3) {
//            for (i in 0 .. 30) {
//                if ((Tile.valueOf(i) != perSituation.jikaze && Tile.valueOf(i) != genSituation.bakaze) && tmp[i] == 2) {
//                    count += 1
//                    tmp[i] -= 2
//                }
//            }
//        }

        if (count == 3) {
            for (i in 0 .. 30) {
                if (tmpPin[i] == 2) {
                    count += 1
                    tmpPin[i] -= 2
                }
            }
        }

        if (count == 4) {
            for (i in 0 .. 26) {
                val num = Tile.valueOf(i).getNumber()
                if (num != 1 && num != 8 && num != 9 && num != 0) {
                    if (tmpPin[i] == 1 && tmpPin[i + 1] == 1) {
                        count += 1
                    }
                }
            }
        }
        when (count) {
            1 -> return 0.33
            2, 3, 4 -> return 0.66
            5 -> return 0.66
//            5 -> return 1.0
            else -> return 0.0
        }
    }

    //タンヤオ
    fun isTanyao(tmp: IntArray) : Double {
        var count = 0
        for (i in 0 until tmp.size) {
            if (!(Tile.valueOf(i).isYaochu())) {
                count += tmp[i]
            }
        }
        when (count) {
            10, 11 -> return 0.33
            12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    //一盃口
    fun isIpeiko(tmp: IntArray) : Double {
        var result: Double = 0.0

        for (i in 1 .. 7) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    // 0初期化は二盃口判定のための処理
                    tmp[i] = 0
                    tmp[i - 1] = 0
                    tmp[i + 1] = 0
                    result = 1.0
                } else if (sum >= 5 && result < 0.66) {
                    result = 0.66
                } else if (sum >= 4 && result < 0.33) {
                    result = 0.33
                }
            }
        }
        for (i in 10 .. 16) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    // 0初期化は二盃口判定のための処理
                    tmp[i] = 0
                    tmp[i - 1] = 0
                    tmp[i + 1] = 0
                    result = 1.0
                } else if (sum >= 5 && result < 0.66) {
                    result = 0.66
                } else if (sum >= 4 && result < 0.33) {
                    result = 0.33
                }
            }
        }
        for (i in 19 .. 25) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    // 0初期化は二盃口判定のための処理
                    tmp[i] = 0
                    tmp[i - 1] = 0
                    tmp[i + 1] = 0
                    result = 1.0
                } else if (sum >= 5 && result < 0.66) {
                    result = 0.66
                } else if (sum >= 4 && result < 0.33) {
                    result = 0.33
                }
            }
        }


        return result
    }

    //白
    fun isHaku(tmp: IntArray) : Double {
        if (tmp[Tile.HAK.getCode()] == 2) {
            return 0.66
        } else if (tmp[Tile.HAK.getCode()] == 3) {
            return 1.0
        }
        return 0.0
    }

    //發
    fun isHatu(tmp: IntArray) : Double {
        if (tmp[Tile.HAT.getCode()] == 2) {
            return 0.66
        } else if (tmp[Tile.HAT.getCode()] == 3) {
            return 1.0
        }
        return 0.0
    }

    //中
    fun isChun(tmp: IntArray) : Double {
        if (tmp[Tile.CHN.getCode()] == 2) {
            return 0.66
        } else if (tmp[Tile.CHN.getCode()] == 3) {
            return 1.0
        }
        return 0.0
    }

    //自風グローバル変数　仮
    var jikaze: Tile = Tile.TON
    var bakaze: Tile = Tile.TON

    //自風
    fun isJikaze(tmp: IntArray) : Double {
        if (tmp[jikaze.getCode()] == 2) {
            return 0.66
        } else if (tmp[jikaze.getCode()] == 3) {
            return 1.0
        }
        return 0.0
    }

    //場風
    fun isBakaze(tmp: IntArray) : Double {
        if (tmp[bakaze.getCode()] == 2) {
            return 0.66
        } else if (tmp[bakaze.getCode()] == 3) {
            return 1.0
        }
        return 0.0
    }


    //チャンタ
    fun isTyanta(tmp: IntArray) : Double {
        var count = 0
        var count2 = 0
        for (i in 0 until tmp.size) {
            var suu = Tile.valueOf(i).getNumber()
            if (Tile.valueOf(i).isYaochu()) {
                count2 += 1
            }
            if (suu > 6 || suu < 4) {
                count += tmp[i]
                count2 += 1
            }
        }
        if (count > 11 && count2 > 17) {
            return 0.66
        }
        if (count > 10 && count2 > 16) {
            return 0.33
        }
        return 0.0
    }

    //混老頭
    fun isHonroutou(tmp: IntArray) : Double {
        var count = 0
        for (i in 0 until tmp.size) {
            if (Tile.valueOf(i).isYaochu()) {
                count += tmp[i]
            }
        }
        if (count > 11) {
            return 1.0
        }
        return 0.0
    }

    //三色同順
    fun isSansyokuDoujun(tmp: IntArray) : Double {
        var kazu = IntArray(7, { 0 } )
        var ans = 0
        for (i in 0 until 26) {
            var suu = Tile.valueOf(i).getNumber()
            if ((tmp[i] > 0 && suu < 8)) {
                kazu[suu - 1] += 1
                if ((suu > 1)) {
                    kazu[suu - 2] += 1
                }
                if ((suu > 2)) {
                    kazu[suu - 3] += 1
                }
            }
        }
        for (i in 0 until 7) {
            if ((kazu[i] > ans)) {
                ans = kazu[i]
            }
            when (ans) {
                6 -> return 0.33
                7, 8 -> return 0.66
                9 -> return 1.0
                else -> return 0.0
            }
        }
        return 0.0
    }

    //一通
    fun isIttuu(tmp: IntArray) : Double {
        var count = 0
        var score = 0.0
        for (i in 0 .. 8) {
            if (tmp[i] > 0) {
                count += 1
            }
        }
        when (count) {
            7 -> score += 0.33
            8 -> score += 0.66
            9 -> score += 1.0
        }

        count = 0
        for (i in 9 .. 17) {
            if (tmp[i] > 0) {
                count += 1
            }
        }
        when (count) {
            7 -> score += 0.33
            8 -> score += 0.66
            9 -> score += 1.0
        }

        count = 0
        for (i in 18 .. 26) {
            if (tmp[i] > 0) {
                count += 1
            }
        }
        when (count) {
            7 -> score += 0.33
            8 -> score += 0.66
            9 -> score += 1.0
        }

        return score
    }

    //三色同刻
    fun isSansyokuDoukou(tmp: IntArray) : Double {
        var kazu = IntArray(9, { 0 } )
        var ans = 0
        for (i in 0 until 26) {
            kazu[Tile.valueOf(i).getNumber() - 1] += tmp[i]
        }
        for (i in 0 until 9) {
            if ((kazu[i] > ans)) {
                ans = kazu[i]
            }
            when (ans) {
                6 -> return 0.33
                7, 8 -> return 0.66
                9 -> return 1.0
                else -> return 0.0
            }
        }
        return 0.0
    }

    //三暗刻
    fun isSanankou(tmp: IntArray) : Double {
        var count = 0
        var toituflag = false
        for (i in 0 until 33) {
            if (tmp[i] > 2) {
                count += 1
            }
            if (tmp[i] == 2) {
                toituflag = true
            }
        }
        if (count > 2) {
            return 1.0
        }
        if (count > 1) {
            if (toituflag) {
                return 0.66
            }
            return 0.33
        }
        return 0.0
    }

    //小三元
    fun isSyousangen(tmp: IntArray) : Double {
        var count = 0
        count += tmp[Tile.HAK.getCode()]
        count += tmp[Tile.HAT.getCode()]
        count += tmp[Tile.CHN.getCode()]
        when (count) {
            5 -> return 0.33
            6, 7 -> return 0.66
            8 -> return 1.0
            else -> return 0.0
        }
    }

    //七対子
    fun isTitoitu(tmp: IntArray) : Double {
        if (isRyanpeiko(tmp) == 1.0) {
            return 0.0
        }
        when (getTiitoituSyantenNum(tmp).first) {
            3 -> return 0.33
            2, 1 -> return 0.66
            else -> return 0.0
        }
    }

    fun isRyanpeiko(tmp: IntArray) : Double {
        if (isIpeiko(tmp) < 1.0) {
            return 0.0
        }
        var result: Double = 0.33
        for (i in 1 .. 7) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    result = 1.0
                } else if (sum >= 4 && result < 0.66) {
                    result = 0.66
                }
            }
        }
        for (i in 10 .. 16) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    result = 1.0
                } else if (sum >= 4 && result < 0.66) {
                    result = 0.66
                }
            }
        }
        for (i in 19 .. 25) {
            if (tmp[i] > 0 && tmp[i - 1] > 0 && tmp[i + 1] > 0) {
                //３種類ある
                var sum = tmp[i] + tmp[i - 1] + tmp[i + 1]
                if (tmp[i] >= 2 && tmp[i - 1] >= 2 && tmp[i + 1] >= 2) {
                    result = 1.0
                } else if (sum >= 4 && result < 0.66) {
                    result = 0.66
                }
            }
        }


        return result
    }

    fun isJuntyan(tmp: IntArray) : Double {
        var count = 0
        var count2 = 0
        for (i in 0 until tmp.size) {
            var suu = Tile.valueOf(i).getNumber()
            if (suu == 1 || suu == 9) {
                count2 += 1
            }
            if (suu != 0) {
                if (suu > 6 || suu < 4) {
                    count += tmp[i]
                    count2 += 1
                }
            }
        }
        if (count > 11 && count2 > 17) {
            return 0.66
        }
        if (count > 10 && count2 > 16) {
            return 0.33
        }
        return 0.0
    }

    fun isHonitu(tmp: IntArray) : Double {
        var max = 0
        var count = 0
        for (i in 0 .. 8) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }

        count = 0
        for (i in 9 .. 17) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }

        count = 0
        for (i in 18 .. 26) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }


        for (i in 27 .. 33) {
            max += tmp[i]
        }
        when (max) {
            10 -> return 0.33
            11, 12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    fun isTinitu(tmp: IntArray) : Double {
        var max = 0
        var count = 0
        for (i in 0 .. 8) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }

        count = 0
        for (i in 9 .. 17) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }

        count = 0
        for (i in 18 .. 26) {
            if (tmp[i] > 0) {
                count += tmp[i]
            }
            if (count > max) {
                max = count
            }
        }

        when (max) {
            10 -> return 0.33
            11, 12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    fun isSuankou(tmp: IntArray) : Double {
        var count = 0
        var toituflag = false
        for (i in 0 .. 33) {
            if (tmp[i] > 2) {
                count += 1
            }
            if (tmp[i] == 2) {
                toituflag = true
            }
        }
        if (count > 2) {
            if (toituflag) {
                return 0.66
            }
            return 0.33
        }
        return 0.0
    }

    fun isDaisangen(tmp: IntArray) : Double {
        var count = 0
        count += tmp[Tile.HAK.getCode()]
        count += tmp[Tile.HAT.getCode()]
        count += tmp[Tile.CHN.getCode()]
        when (count) {
            6 -> return 0.33
            7, 8 -> return 0.66
            9 -> return 1.0
            else -> return 0.0
        }
    }

    fun isTuiso(tmp: IntArray) : Double {
        var count = 0
        for (i in 27 .. 33) {
            count += tmp[i]
        }
        if (count > 9) {
            return 1.0
        }
        when (count) {
            10 -> return 0.33
            11, 12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    fun isSyoususi(tmp: IntArray) : Double {
        var count = 0
        for (i in 27 .. 30) {
            count += tmp[i]
        }
        when (count) {
            8 -> return 0.33
            9, 10 -> return 0.66
            11 -> return 1.0
            else -> return 0.0
        }
    }

    fun isDaisusi(tmp: IntArray) : Double {
        var count = 0
        for (i in 27 .. 30) {
            count += tmp[i]
        }
        when (count) {
            10 -> return 0.33
            11 -> return 0.66
            12 -> return 1.0
            else -> return 0.0
        }
    }

    fun isRyuisou(tmp: IntArray) : Double {
        var count = 0
        for (i in 19 until 33) {
            if (i == 19 || i == 20 || i == 21 || i == 23 || i == 25 || i == 32) {
                count += tmp[i]
            }
        }
        when (count) {
            10 -> return 0.33
            11, 12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    fun isTyurenPoutou(tmp: IntArray) : Double {
        var count = 0
        var count2 = 0
        for (i in 0 .. 8) {
            if (tmp[i] > 0) {
                count += tmp[i]
                count2 += 1
            }
        }
        if (count >= 11 && count2 >= 8) {
            return 0.66
        }
        if (count >= 10 && count2 >= 7) {
            return 0.33
        }

        count = 0
        count2 = 0
        for (i in 9 .. 17) {
            if (tmp[i] > 0) {
                count += tmp[i]
                count2 += 1
            }
        }
        if (count >= 11 && count2 >= 8) {
            return 0.66
        }
        if (count >= 10 && count2 >= 7) {
            return 0.33
        }

        count = 0
        count2 = 0
        for (i in 18 .. 26) {
            if (tmp[i] > 0) {
                count += tmp[i]
                count2 += 1
            }
        }
        if (count >= 11 && count2 >= 8) {
            return 0.66
        }
        if (count >= 10 && count2 >= 7) {
            return 0.33
        }



        return 0.0
    }

    fun isTinroutou(tmp: IntArray) : Double {
        var count = 0
        for (i in 0 until tmp.size) {
            var suu = Tile.valueOf(i).getNumber()
            if (suu == 1 || suu == 9) {
                count += tmp[i]
            }
        }
        when (count) {
            10 -> return 0.33
            11, 12, 13, 14 -> return 0.66
            else -> return 0.0
        }
    }

    fun isKokusi(tmp: IntArray) : Double {

        when (getKokusiSyantenNum(tmp).first) {
            3 -> return 0.33
            2, 1 -> return 0.66
            else -> return 0.0
        }


    }


    // ------ 以降，0.0を返すだけの関数 ------
    fun isReach(tmp: IntArray) : Double =
        0.0

    fun isIppatu(tmp: IntArray) : Double =
        0.0

    fun isTumo(tmp: IntArray) : Double =
        0.0

    fun isRinsyan(tmp: IntArray) : Double =
        0.0

    fun isTyankan(tmp: IntArray) : Double =
        0.0

    fun isHaitei(tmp: IntArray) : Double =
        0.0

    fun isHoutei(tmp: IntArray) : Double =
        0.0

    fun isDora(tmp: IntArray) : Double =
        0.0

    fun isToiToi(tmp: IntArray) : Double =
        0.0

    fun isDoubleReach(tmp: IntArray) : Double =
        0.0

    fun isSankantu(tmp: IntArray) : Double =
        0.0

    fun isSukantu(tmp: IntArray) : Double =
        0.0

    fun isJunseiTyurenpoutou(tmp: IntArray) : Double =
        0.0

    fun isSuankouTanki(tmp: IntArray) : Double =
        0.0

    fun isKokusi13(tmp: IntArray) : Double =
        0.0

    fun isTenhou(tmp: IntArray) : Double =
        0.0

    fun isTihou(tmp: IntArray) : Double =
        0.0


}