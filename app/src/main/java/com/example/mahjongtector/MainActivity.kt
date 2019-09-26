package com.example.mahjongtector

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import android.net.Uri;
import android.provider.MediaStore
import android.util.Log
import android.view.View;
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File


class MainActivity : AppCompatActivity() {


    var task: UploadTask? = null
    var textView: TextView? = null
    var editText: EditText? = null

    val RESULT_CAMERA = 1001
    var imageView:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ダミーカメラボタン　役表示画面へ遷移
        cameraButton.setOnClickListener {
            val intent = Intent(this, resultYakuActivity::class.java)
            startActivity(intent)
        }

        imageView = findViewById(R.id.image_view)

        //カメラ起動
        val cameraButton: Button = findViewById(R.id.camera_button)
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

            imageView!!.setImageBitmap(bitmap)

            //リサイズ（縮小）　大きいと処理できなかったため
            val bmpRsz = resizedBitmap(bitmap)

            // SSDに入力するため正方形に拡張
            val bmpSquared = createSquaredBitmap(bmpRsz)
            AsyncHttpRequest(this).execute(bmpSquared)
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