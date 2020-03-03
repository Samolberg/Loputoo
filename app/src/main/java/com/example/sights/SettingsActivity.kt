package com.example.sights

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


    }

    fun onCheckboxClicked(view: View) {

        val pref = getSharedPreferences("sights.app.settings", Context.MODE_PRIVATE)
        val editor = pref.edit()

        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            editor.putBoolean(view.id.toString(), checked).apply()
            Log.d("booleantest", pref.getBoolean("historicalSettings", false).toString())
            Log.d("booleantest", pref.getBoolean("modernSettings", false).toString())
          /*  when (view.id) {
                R.id.historicalCheckbox -> {
                    if (checked) {
                        // add preference
                        editor.putBoolean("historicalSettings", checked)
                        editor.apply()

                    }
                }
                R.id.modernCheckbox -> {
                    if (checked) {
                        //add preference
                        editor.putBoolean("modernSettings", checked)
                        editor.apply()

                    }
                }
            }

           */
        }
    }


}