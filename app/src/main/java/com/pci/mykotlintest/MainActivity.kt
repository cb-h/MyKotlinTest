package com.pci.mykotlintest


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebViewClient
import com.pci.mykotlintest.databinding.ActivityMainBinding
import com.pci.mylibrary.BaseLibraryActivity
import kotlin.concurrent.thread


class MainActivity : BaseLibraryActivity<ActivityMainBinding>() {
    var go:AgentWeb? = null

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    override fun onCreateV() {
        bind.hello.setOnClickListener {
//            PermissionUtils(this).request(object :PermissionUtils.PermissionListener{
//                override fun allGranted(list: List<String>) {
//                    Log.e("tag","allGranted${list.toString()}")
//                    GetPhotoUtils(this@MainActivity).request(object :GetPhotoUtils.PickPhotoListener{
//                        override fun callBack(file: File, uri: Uri) {
//
//                            Log.e("tag","${uri.path}  ${file.path}")
//
//                        }
//
//                    })
//                }
//                override fun denied(list: List<String>) {
//                    Log.e("tag","denied${list.toString()}")
//                }
//
//                override fun explained(list: List<String>) {
//                    Log.e("tag","explained${list.toString()}")
//                }
//            },Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA)
//            bind.webview.loadUrl("https://v.douyin.com/YHhpXo8/")
            go = AgentWeb.with(this)
                .setAgentWebParent(bind.webview, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebViewClient(MyWebViewClient())
                .addJavascriptInterface("local",InJavaScriptLocalObj())
                .createAgentWeb()
                .ready()
                .go("https://v.douyin.com/YXtQ5L5/")

            thread {
                Log.e(TAG, "onCreateV: ${Thread.currentThread().name}")
                Thread.sleep(5000)
                handler.post {
                    bind.hello.text = Thread.currentThread().name
                    try {


//                        go!!.webCreator.webView.loadUrl("javascript:window.local.getVideoUrl(document.getElementById('root').innerHTML)")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        }
    }

    class InJavaScriptLocalObj{
        @JavascriptInterface
        fun getVideoUrl(url:String){
            Log.e("TAG", "getVideoUrl: $url" )
        }
    }

    class MyWebViewClient:WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view?.loadUrl("javascript:window.local.getVideoUrl(document.querySelector('video').getAttribute('src'))")
        }
    }

    val handler = Handler(Looper.getMainLooper())

}


