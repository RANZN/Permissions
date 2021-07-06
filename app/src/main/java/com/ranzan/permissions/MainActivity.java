package com.ranzan.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private Button permissionButton;
    private final static int REQ_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionButton = findViewById(R.id.btnRequestPermission);
        permissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(MainActivity.this, permission, REQ_CODE);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            showToast("Camera and Storage Permission granted");
        else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED)
            showToast("Camera granted but storage denied");
        else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED)
            showToast("Camera is denied but storage granted");
        else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
            showToast("Both permissions are denied");
//            String[] permission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
//            ActivityCompat.requestPermissions(MainActivity.this, permission, REQ_CODE);
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
