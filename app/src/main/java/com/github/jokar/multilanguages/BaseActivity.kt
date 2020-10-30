package com.github.jokar.multilanguages

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    public override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }
}