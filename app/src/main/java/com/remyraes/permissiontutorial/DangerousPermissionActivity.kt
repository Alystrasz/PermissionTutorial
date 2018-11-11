package com.remyraes.permissiontutorial

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.v7.app.AlertDialog


/**
 * Tutoriel réalisé grâce aux ressources disponibles à l'adresse suivante :
 * https://developer.android.com/guide/topics/permissions
 */

class DangerousPermissionActivity : AppCompatActivity() {
    private val permission = Manifest.permission.READ_CONTACTS

    override fun onResume() {
        super.onResume()
        permissionSwitch.isChecked = hasPermission()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -----------------------------------------------------------------------------------------
        // Etape 4 : listons les contacts !
        // -----------------------------------------------------------------------------------------

        // TODO

        // -----------------------------------------------------------------------------------------
        // Etape 5 : implémentons le code qui permet de demander la permission !
        // -----------------------------------------------------------------------------------------

        // TODO

        // -----------------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------------


        changeActivity_btn.setOnClickListener {
            startActivity(Intent(applicationContext, NormalPermissionActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        checkBtn.setOnClickListener {
            showMessage(getPermissionText(), true)
        }
    }


    /**
     * Fonction callback appelée lorsque l'utilisateur a répondu à la demande de permission.
     */
    // ---------------------------------------------------------------------------------------------
    // Etape 6 : Prenons en compte la réponse de l’utilisateur !
    // ---------------------------------------------------------------------------------------------

    // TODO

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(permission),
                10)
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
