package com.example.mahjongtector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;

import com.amazonaws.http.HttpMethodName;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.amazonaws.mobileconnectors.apigateway.ApiRequest;
import com.amazonaws.mobileconnectors.apigateway.ApiResponse;
import com.amazonaws.util.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mahjong.CloudsagemakerapiClient;

public class AsyncHttpRequest extends AsyncTask<Bitmap, Void, String> {

    //元のアクティビティ
    private MainActivity mainActivity;

    //コールバック
    private CallBackTask callbacktask;

    public AsyncHttpRequest(MainActivity activity) {

        this.mainActivity = activity;
    }



    @Override
    protected String doInBackground(Bitmap... bitmaps) {
//        TextView tv = (TextView) mainActivity.findViewById(R.id.textview);
//        tv.setText("麻雀牌認識中...");


        Bitmap bmp = bitmaps[0];



        // Bitmap → jpeg → byte
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] jpgarr = baos.toByteArray();


        CloudsagemakerapiClient client = new ApiClientFactory().build(CloudsagemakerapiClient.class);

        ApiRequest request = new ApiRequest();


        request.withHttpMethod(HttpMethodName.POST);

        request.addHeader("Content-Length", String.valueOf(jpgarr.length));
        request.addHeader("Content-Type", "image/jpeg");

        request.withBody(jpgarr);


        final ApiResponse response = client.execute(request);



        try {
            InputStream content = response.getContent();
            String cntentString = inputStreemToString(content);

            String regex = "\\[(.+)\\]";
            //[]の中だけ取り出す
            String result = extractMatchString(regex, cntentString);


            return  result;


        }catch(IOException e) {
            String result = "IOException";
            return  result;
        }


    }

    // バックグラウンド処理が終了した後にメインスレッドに渡す処理
    @Override
    protected void onPostExecute(String result) {
        callbacktask.CallBack(result);
    }


    public void setOnCallBack(CallBackTask _cbj) {
        callbacktask = _cbj;
    }


    /**
     * コールバック用のstaticなclass
     */
    public static class CallBackTask {
        public void CallBack(String result) {
        }
    }


    //inputStreem→Stringに型変換
    public static String inputStreemToString(InputStream in) throws IOException{

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in, "UTF-8"/* 文字コード指定 */));
        StringBuffer buf = new StringBuffer();
        String str;
        while ((str = reader.readLine()) != null) {
            buf.append(str);
            buf.append("\n");
        }
        return buf.toString();
    }


    //正規表現で特定部分抽出
    public static String extractMatchString(String regex, String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new IllegalStateException("No match found.");
        }
    }


}