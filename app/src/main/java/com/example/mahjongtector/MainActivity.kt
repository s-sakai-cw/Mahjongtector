package com.example.mahjongtector

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.provider.MediaStore
import android.util.Log
import android.view.View;
import android.widget.*


class MainActivity : AppCompatActivity() {

    val RESULT_CAMERA = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //カメラ起動
        cameraButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, RESULT_CAMERA)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_CAMERA) {
            val bitmap: Bitmap?
            // cancelしたケースも含む
            if (data!!.getExtras() == null) {
                Log.d("debug", "cancel ?")
                return
            } else {
                bitmap = data!!.getExtras()!!.get("data") as Bitmap
                if (bitmap != null) {
                    // 画像サイズを計測
                    val bmpWidth = bitmap!!.getWidth()
                    val bmpHeight = bitmap!!.getHeight()
                    Log.d("debug", String.format("w= %d", bmpWidth))
                    Log.d("debug", String.format("h= %d", bmpHeight))
                }
            }

            //sample画像
//            val bmp = BitmapFactory.decodeResource(resources, R.drawable.tehaisample)

            //カメラで撮った画像
            val bmp = bitmap


            //リサイズ（縮小）　大きいと処理できなかったため
            val bmpRsz = resizedBitmap(bmp)

            // SSDに入力するため正方形に拡張
            val bmpSquared = createSquaredBitmap(bmpRsz)

            textview.setText("麻雀牌認識中...")

            // リクエスト
            val task = AsyncHttpRequest(this)
            task.execute(bmpSquared)


            //コールバック時
            task.setOnCallBack(object : AsyncHttpRequest.CallBackTask() {

                override fun CallBack(result: String) {
                    super.CallBack(result)
                    // ※１
                    // resultにはdoInBackgroundの返り値が入ります。
                    // ここからAsyncTask処理後の処理を記述します。
                    Log.i("AsyncTaskCallback", "非同期処理が終了しました。")

                    // Asyncから受け取った値を表示
//                    val tv = findViewById(R.id.textview) as TextView




                    //コンマ区切りで配列に
                    val resultArr =
                        result.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    //Int配列に
                    val tehai = IntArray(resultArr.size)
                    for (i in resultArr.indices) {
                        tehai[i] = Integer.parseInt(resultArr[i]) // throws NumberFormatException
                    }

                    //Intentのインスタンス作成　(元, 呼び出したいclass)
                    val intent = Intent(applicationContext, resultYakuActivity::class.java)
                    //handlistと変更牌番号を渡す
                    intent.putExtra("tehaiPicture", tehai)
                    val requestCode = 1000
                    startActivityForResult(intent, requestCode)


                    //                tv.setText(String.valueOf(tehai[1]));

                }

            })





        }
    }

    // Bitmapリサイズメソッド
    private fun resizedBitmap(srcBmp: Bitmap): Bitmap {
        val width = srcBmp.width
        val height = srcBmp.height
        val widthResized = 800
        val heightResized = widthResized * height / width

        val rszBmp: Bitmap
        rszBmp = Bitmap.createScaledBitmap(srcBmp, widthResized, heightResized, false)

        return rszBmp

    }

    // Bitmap正方形に拡張メソッド
    private fun createSquaredBitmap(srcBmp: Bitmap): Bitmap {
        val dim = Math.max(srcBmp.width, srcBmp.height)
        val dstBmp = Bitmap.createBitmap(dim, dim, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(dstBmp)
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(
            srcBmp,
            ((dim - srcBmp.width) / 2).toFloat(),
            ((dim - srcBmp.height) / 2).toFloat(),
            null
        )

        return dstBmp
    }



}