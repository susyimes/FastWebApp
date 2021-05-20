package com.susyimes.fastwebapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_web.*


private const val WEB_URL = "webUrl"



class WebFragment : Fragment() {

    private var webUrl: String? = null
   

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            webUrl = it.getString(WEB_URL)
          
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val settings: WebSettings = web.settings
        settings.javaScriptEnabled = true
        settings.displayZoomControls = false
        //settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setSupportZoom(true)
        //settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.domStorageEnabled = true
        //web.setInitialScale(25)
        web.loadUrl(webUrl.toString())

        web.webViewClient = MyWebClient()


        swipe.setOnRefreshListener {
            web.reload()
        }

    }

   inner class MyWebClient : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            swipe.isRefreshing = false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        web.destroy()
    }

    private var firstTime: Long = 0
    fun backPress(){
        if (web.canGoBack()) {
            web.goBack()
        }else{
            if (System.currentTimeMillis() - firstTime > 2000) {
                toast("再次点击退出")
                firstTime = System.currentTimeMillis()
            } else {
                activity?.finish()
            }
        }
    }



    companion object {

        @JvmStatic
        fun newInstance(webUrl: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(WEB_URL, webUrl)
                   
                }
            }
    }

}