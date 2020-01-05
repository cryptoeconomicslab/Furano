package com.cryptoeconomicslab.demo_wallet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.cryptoeconomicslab.furano_android.FuranoAndroid

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("FURANO-DEBUG", FuranoAndroid.hello())
    }
}
