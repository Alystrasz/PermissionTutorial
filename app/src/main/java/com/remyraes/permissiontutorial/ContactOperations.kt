package com.remyraes.permissiontutorial

import android.content.ContentResolver
import android.provider.ContactsContract
import android.support.v7.app.AppCompatActivity

/**
 * Classe permettant de lister les contacts du téléphone.
 * https://medium.com/@manuaravindpta/fetching-contacts-from-device-using-kotlin-6c6d3e76574f
 */
class ContactOperations: AppCompatActivity() {
    companion object {
        fun loadContacts(contentResolver: ContentResolver): StringBuilder{
            val builder = StringBuilder()
            val resolver: ContentResolver = contentResolver
            val cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneNumber = (cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))).toInt()

                    if (phoneNumber > 0) {
                        val cursorPhone = contentResolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)

                        if(cursorPhone.count > 0) {
                            while (cursorPhone.moveToNext()) {
                                val phoneNumValue = cursorPhone.getString(
                                        cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                                builder.append(name).append(" (").append(phoneNumValue).append(")\n")
                            }
                        }
                        cursorPhone.close()
                    }
                }
            }

            cursor.close()
            return builder
        }
    }
}