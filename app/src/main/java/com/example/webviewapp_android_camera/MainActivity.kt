package com.example.webviewapp_android_camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    private val PERMISSIONS_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                println("permission granted")
            } else {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }

        val webView: WebView = findViewById(R.id.webview)
        webView.apply {
            settings.apply {
                javaScriptEnabled = true
                mediaPlaybackRequiresUserGesture = false
                userAgentString = userAgentString + " qo.kenkoappli"
            }
            webChromeClient = object : WebChromeClient() {
                @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
                override fun onPermissionRequest(request: PermissionRequest?) {
                    if (request?.resources!!.contains(PermissionRequest.RESOURCE_VIDEO_CAPTURE)) {
                        request.grant(request.resources)
                    }
                }
            }
            webViewClient = WebViewClient()
        }
//        webView.loadUrl("https://masaki1103.github.io/camera_demo/")
//        webView.loadUrl("https://masaki1103.github.io/qrcode_demo/")
        webView.loadUrl("https://masaki1103.github.io/capture_demo/")
     }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    println("permission granted")
                } else {
/*                   Handler.post(Runnable {
                        RuntimePermissionUtils().showAlertDialog(
                            supportFragmentManager,
                            "オーディオの録音"
                        )
                    })
*/
                }
                return
            }
        }
    }
}