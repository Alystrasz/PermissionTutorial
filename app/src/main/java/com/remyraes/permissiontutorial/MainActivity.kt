package com.remyraes.permissiontutorial

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionSwitch.setOnClickListener {
            val checked = permissionSwitch.isChecked

            if(checked) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        10)
            } else {
                //TODO enlever la permission
            }
        }

        contactsBtn.setOnClickListener {
            Toast.makeText(this.applicationContext, "click", Toast.LENGTH_SHORT).show()
        }

        checkBtn.setOnClickListener {
            Toast.makeText(applicationContext, getPermissionStatus(), Toast.LENGTH_SHORT).show()
        }
    }

    /*
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
    */

    fun getPermissionStatus() : String {
        val res = ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.READ_CONTACTS)
        if(res == PackageManager.PERMISSION_GRANTED)
            return "Permission accordée"
        if(res == PackageManager.PERMISSION_DENIED)
            return "Permission refusée"
        return ""
    }
}
