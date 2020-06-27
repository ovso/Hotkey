package kr.blogspot.ovsoce.hotkey

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.google.android.gms.ads.MobileAds
import kr.blogspot.ovsoce.hotkey.db.DatabaseHelper
import kr.blogspot.ovsoce.hotkey.framework.SystemUtils
import kr.blogspot.ovsoce.hotkey.framework.TypefaceUtil


class App : Application() {
    lateinit var databaseHelper: DatabaseHelper
        private set

    override fun onCreate() {
        super.onCreate()
        databaseHelper = DatabaseHelper(applicationContext)
        Stetho.initializeWithDefaults(this)
        instance = this
        setFontSize()
        initDebuggable()
        initAdmob()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initAdmob() {
        MobileAds.initialize(applicationContext, Ads.APP_ID)
    }

    private fun initDebuggable() {
        DEBUG = SystemUtils.isDebuggable(this)
    }

    private fun setFontSize() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val fontSize = sharedPreferences.getString("fonts_size", "1.0")!!.toFloat()
        TypefaceUtil.fontsSize(applicationContext, fontSize)
        sharedPreferences.edit().putString("fonts_size", fontSize.toString()).apply()
    }

    companion object {
        var DEBUG = false

        @JvmStatic
        lateinit var instance: App
            private set
    }
}
