package com.sandipbhattacharya.downloadimageinsd;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivShowImage;
    Button btnDownload;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivShowImage = findViewById(R.id.ivShowImage);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(this);
        imageUrl = "http://sandipbhattacharya.com/temp/images/android-game-development-surfaceview.jpg";
    }

    @Override
    public void onClick(View view) {
        //new ImageDownloader().execute("http://sandipbhattacharya.com/temp/images/android-game-development-surfaceview.jpg");
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        ivShowImage.setImageBitmap(resource);
                        saveImage(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }
    /*
    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        HttpURLConnection httpURLConnection;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                ivShowImage.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Download Successful!", Toast.LENGTH_SHORT).show();
                saveImage(bitmap);
            } else{
                Toast.makeText(getApplicationContext(), "Download Error!", Toast.LENGTH_SHORT).show();
            }
        }

        private void saveImage(Bitmap bitmap) {
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File myDirectory = new File(path + "/saved_images");
            if(!myDirectory.exists())
                myDirectory.mkdir();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
            String imageName = timeStamp + ".jpg";
            myDirectory = new File(myDirectory, imageName);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(myDirectory);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
     */

    private void saveImage(Bitmap bitmap) {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDirectory = new File(path + "/saved_images");
        if(!myDirectory.exists())
            myDirectory.mkdir();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());
        String imageName = timeStamp + ".jpg";
        myDirectory = new File(myDirectory, imageName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(myDirectory);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}