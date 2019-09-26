package com.example.mahjongtector;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.amazonaws.mobileconnectors.apigateway.ApiRequest;
import com.amazonaws.mobileconnectors.apigateway.ApiResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import mahjong.CloudsagemakerapiClient;

public class AsyncHttpRequest extends AsyncTask<Bitmap, Void, String> {

    private Activity mainActivity;

    public AsyncHttpRequest(Activity activity) {

        this.mainActivity = activity;
    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {
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
            String result = inputStreemToString(content);
            return  result;
        }catch(IOException e) {
            String result = "IOException";
            return  result;
        }


    }


    @Override
    protected void onPostExecute(String result) {
        TextView tv = (TextView) mainActivity.findViewById(R.id.textview);
        tv.setText(result);
    }


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

}