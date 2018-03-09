package com.gamma404.pictureapp;

import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_STORAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        } else {
            readThumbnails();
        }
    }

    private void readThumbnails() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readThumbnails();
            } else {
                new AlertDialog.Builder(this)
                        .setMessage("Need get grant before show image.")
                        .setTitle("Permission")
                        .setPositiveButton("OK", null).show();
            }
        }
    }
}
