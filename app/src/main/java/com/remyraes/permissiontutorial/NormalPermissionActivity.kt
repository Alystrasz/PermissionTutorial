package com.remyraes.permissiontutorial

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_normal_permission.*

class NormalPermissionActivity : AppCompatActivity() {
    private val wifiManager: WifiManager
        get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_permission)
        
        wifi_btn.isChecked = wifiManager.isWifiEnabled

        wifi_btn.setOnClickListener {
            wifiManager.setWifiEnabled(!wifiManager.isWifiEnabled)
        }

        checkwifi_btn.setOnClickListener {
            val msg = when(wifiManager.isWifiEnabled) {
                true -> "Le wifi est activé"
                false -> "Le wifi est désactivé"
            }
            showMessage(msg, true)
        }

        changeActivity_btn.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    private fun showMessage(message : String, long: Boolean) {
        when(long) {
            true -> Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            false -> Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}
