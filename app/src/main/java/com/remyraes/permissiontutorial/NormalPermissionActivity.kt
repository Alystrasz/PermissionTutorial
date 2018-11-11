package com.remyraes.permissiontutorial

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_normal_permission.*

class NormalPermissionActivity : AppCompatActivity() {
    private val wifiManager: WifiManager
        get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val permission = Manifest.permission.CHANGE_WIFI_STATE

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_permission)

        if(hasPermission())
            wifi_btn.isChecked = wifiManager.isWifiEnabled

        // -----------------------------------------------------------------------------------------
        // Etape 2 : ajoutons le code permettant de jouer avec le Wi-Fi !
        // -----------------------------------------------------------------------------------------

        // TODO

        // -----------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------

        checkWifiPermissionBtn.setOnClickListener {
            showMessage(getPermissionText(), true)
        }

        changeActivity_btn.setOnClickListener {
            startActivity(Intent(applicationContext, DangerousPermissionActivity::class.java))
        }
    }

    /**
     * Renvoie l'état de la permission.
     */
    private fun getPermissionStatus() : Int {
        return ContextCompat.checkSelfPermission(applicationContext, permission)
    }

    /**
     * Renvoie l'état de la permission sous forme booléenne.
     */
    private fun hasPermission() : Boolean {
        return getPermissionStatus() == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Renvoie un petit texte "human-readable" représentant l'état de la permission.
     */
    private fun getPermissionText() : String {
        when (hasPermission()) {
            true -> return "Permission accordée"
            false -> return "Permission refusée"
        }

    }

    private fun showMessage(message : String, long: Boolean) {
        when(long) {
            true -> Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            false -> Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}
