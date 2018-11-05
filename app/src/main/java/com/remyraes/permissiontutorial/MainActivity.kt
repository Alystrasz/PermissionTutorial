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

class MainActivity : AppCompatActivity() {
    private val permission = Manifest.permission.READ_CONTACTS

    override fun onResume() {
        super.onResume()
        permissionSwitch.isChecked = hasPermission()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionSwitch.setOnClickListener {
            val checked = permissionSwitch.isChecked

            // demander la permission à l'utilisateur
            if(checked) {
                if(shouldShowRequestPermissionRationale(permission)) {
                    val builder = AlertDialog.Builder(this@MainActivity)
                    builder.setTitle("Encore ?!")
                    builder.setMessage("L'utilisateur a déjà refusé cette permission par le passé...")
                    builder.setOnDismissListener {
                        requestPermission()
                    }
                    builder.create().show()
                } else {
                    requestPermission()
                }
            }
            // lancement d'un intent paramètres, pour amener l'utilisateur à retirer la permission
            else {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
                showMessage( "Veuillez enlever les autorisations de l'application sous le menu \"Autorisations\".", true)
            }
        }

        checkBtn.setOnClickListener {
            showMessage(getPermissionText(), true)
        }

        changeActivity_btn.setOnClickListener {
            startActivity(Intent(applicationContext, NormalPermissionActivity::class.java))
        }
    }


    /**
     * Fonction callback appelée lorsque l'utilisateur a répondu à la demande de permission.
     */
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        // l'utilisateur a accepté d'attribuer la permission à l'application
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showMessage("La permission a été accordée !", true)
        }

        // l'utilisateur a refusé d'attribuer la permission à l'application
        else {
            permissionSwitch.isChecked = false
            showMessage("La permission a été refusée...", true)
        }

    }

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
