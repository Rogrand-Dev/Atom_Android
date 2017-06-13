package com.rogrand.demo.ui.home.webview

import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import com.rogrand.demo.R
import com.rogrand.demo.base.BaseActivity

/**
 * Created by jianhongChen on 2017/6/12.
 *
 */
class WebViewActivity : BaseActivity<WebViewPresenter>() {
    private var mToolbar: Toolbar? = null
    private var mEtUrl: EditText? = null
    private var mBtnGo: Button? = null
    private var mWebView: WebView? = null
    private var mTitle: String? = ""
    var inputMethodService: InputMethodManager? = null

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_web_view
    }

    override fun onViewCreated() {
        super.onViewCreated()
        mToolbar = findViewById(R.id.toolbar) as Toolbar?
        mEtUrl = findViewById(R.id.et_webview_url) as EditText?
        mBtnGo = findViewById(R.id.btn_webview_go) as Button?
        mWebView = findViewById(R.id.wv_webview) as WebView?
        setToolBar(mToolbar, mTitle)
        initWebView()
    }

    fun initWebView() {
        val settings = mWebView?.settings
        settings?.javaScriptEnabled = true
        settings?.domStorageEnabled = true
        mWebView?.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                mEtUrl?.setText(url)
                mEtUrl?.clearFocus()
            }
        })
        mWebView?.setWebChromeClient(object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                mToolbar?.title = view?.title
            }
        })
    }

    override fun initEventAndData() {
        inputMethodService = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        mEtUrl?.setText(getString(R.string.url_rogrand))
        mEtUrl?.setSelection(mEtUrl?.length() ?: 0)

        mEtUrl?.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> mBtnGo?.performClick() ?: false
                else -> false
            }
        }

        mBtnGo?.setOnClickListener {
            mWebView?.loadUrl(mEtUrl?.text.toString())
            inputMethodService?.hideSoftInputFromWindow(mEtUrl?.windowToken, 0)
            mEtUrl?.clearFocus()
        }

        mWebView?.loadUrl(getString(R.string.url_rogrand))
    }

    override fun onBackPressedSupport() {
        if (mWebView?.canGoBack() ?: false) mWebView?.goBack() else super.onBackPressedSupport()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            0 -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, 0, 0, "Close")?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

}
