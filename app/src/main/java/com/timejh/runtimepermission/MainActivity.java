package com.timejh.runtimepermission;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            loadData();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String permArr[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permArr, REQ_CODE);
        } else {
            loadData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQ_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadData();
            } else {
                Toast.makeText(this, "권한을 허용하지 않으면 프로그램을 실행 할 수 없습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void loadData() {
        Toast.makeText(this, "프로그램 로드완료", Toast.LENGTH_SHORT).show();
    }
}
