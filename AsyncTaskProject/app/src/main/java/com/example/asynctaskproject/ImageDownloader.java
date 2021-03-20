package com.example.asynctaskproject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
//import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cscharff.
 */

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

    private FirstFragment activity;
    private SecondFragment activity2;

    public ImageDownloader(SecondFragment myActivity){
        activity2 =myActivity;
        activity = null;
    }

    public ImageDownloader(FirstFragment myActivity) {
        activity = myActivity;
        activity2=null;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i("hello1", "hello1");
        publishProgress(1);
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Failed to connect");
            }
            InputStream is = con.getInputStream();
            publishProgress(0);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Image", "Failed to load image", e);
            Log.e("error", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if(activity!=null) {
            TextView tv = (TextView) activity.getActivity().findViewById(R.id.textview_first);
            if (values[0] == 1) {
                tv.setText("Loading...");
            } else {
                tv.setText("");
            }
        }

        if(activity2 !=null){
            TextView tv = (TextView) activity2.getActivity().findViewById(R.id.textview_second);
            if (values[0] == 1) {
                tv.setText("Loading...");
            } else {
                tv.setText("");
            }
        }
    }

    @Override
    protected void onPostExecute(Bitmap img) {
        Log.i("hello4", "hello4");

        if (activity !=null) {
            ImageView iv = (ImageView) activity.getActivity().findViewById(R.id.img);
            Log.i("hello5", "hello5");
            if (iv != null && img != null) {
                Log.i("hello6", "hello6");
                iv.setImageBitmap(img);
            }
        }
        if (activity2 !=null) {
            ImageView iv = (ImageView) activity2.getActivity().findViewById(R.id.img);
            Log.i("hello5", "hello5");
            if (iv != null && img != null) {
                Log.i("hello6", "hello6");
                iv.setImageBitmap(img);
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}