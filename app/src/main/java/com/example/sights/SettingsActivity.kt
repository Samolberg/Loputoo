package com.example.sights

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


    }

    fun onCheckboxClicked(view: View) {

        val settings: MutableList<String> = mutableListOf()

        val pref = getSharedPreferences("sights.app.settings", Context.MODE_PRIVATE)
        val editor = pref.edit()

        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.historicalCheckbox -> {
                    if (checked) {
                        // add preference
                        settings.add("Historical")

                        editor.putBoolean("historicalSetting", true)
                        editor.apply()
                    }else {
                        settings.remove("Historical")

                        editor.remove("historicalSettings")
                        editor.apply()
                    }
                }
                R.id.modernCheckbox -> {
                    if (checked) {
                        //add preference
                        settings.add("Modern")

                        editor.putBoolean("modernSetting", true)
                        editor.apply()

                    }else {
                        settings.remove("Modern")

                        editor.remove("modernSettings")
                        editor.apply()
                    }
                }
            }
            editor.putString("settings", Gson().toJson(settings))
            editor.apply()
            Log.d("settingstest", pref.getString("settings", "[]").toString())

        }
    }
}


/*   if (view is CheckBox) {
                    val checked: Boolean = view.isChecked
                    editor.putBoolean(view.id.toString(), checked).apply()
                    Log.d("booleantest", pref.getBoolean("historicalSettings", false).toString())
                    Log.d("booleantest", pref.getBoolean("modernSettings", false).toString())

              */