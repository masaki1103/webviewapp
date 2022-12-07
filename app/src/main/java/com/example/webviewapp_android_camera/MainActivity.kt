package com.example.webviewapp_android_camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            println("permission granted")
//        } else {
//            requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
//        }

        val WebView: WebView = findViewById(R.id.webview)
        WebView.settings.javaScriptEnabled = true
        WebView.settings.domStorageEnabled = true
        WebView.setWebChromeClient(object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        })
        WebView.loadUrl("https://https://masaki1103.github.io/camera_demo/")
     }
}