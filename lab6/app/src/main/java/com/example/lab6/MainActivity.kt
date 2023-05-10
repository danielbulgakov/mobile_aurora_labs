package com.example.lab6

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.lab6.Activities.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switch = findViewById<Switch>(R.id.switch1)
        val prefs = getPreferences(MODE_PRIVATE)
        switch.isChecked = prefs.getBoolean("switch1", false)
        findViewById<TextView>(R.id.editText).text = prefs.getString("text1", "")

        switch.setOnCheckedChangeListener { _, state ->
            val editor = getPreferences(MODE_PRIVATE).edit()
            editor.putBoolean("switch1", state)
            editor.apply()
        }
        findViewById<TextView>(R.id.editText).doAfterTextChanged {
            val editor = getPreferences(MODE_PRIVATE).edit()
            editor.putString("text1", findViewById<TextView>(R.id.editText).text.toString())
            editor.apply()
        }

        val store = SettingsStorage(applicationContext)

        lifecycleScope.launch {
            println(store.getState())
            println(store.getString())
            findViewById<TextView>(R.id.editText2).text = store.getString()
            findViewById<Switch>(R.id.switch1).isChecked = store.getState()
        }

        findViewById<Switch>(R.id.switch2).setOnCheckedChangeListener { _, state ->
            lifecycleScope.launch {
                store.saveState(state)
            }
        }
        findViewById<TextView>(R.id.editText2).doAfterTextChanged {
            lifecycleScope.launch {
                store.saveText(findViewById<TextView>(R.id.editText2).text.toString())
            }
        }

    }

    class SettingsStorage(private val context: Context) {
        companion object {
            private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("prefs")
            private val FIELD_STATE = booleanPreferencesKey("state")
            private val FIELD_STRING = stringPreferencesKey("text")
        }

        suspend fun getState() : Boolean {
            return context.dataStore.data.map { preferences ->
                preferences[FIELD_STATE] ?: false
            }.toList()[0]
        }

        suspend fun getString() : String {
            return context.dataStore.data.map { preferences ->
                preferences[FIELD_STRING] ?: ""
            }.toList()[0]
        }


        suspend fun saveText(text : String) {
            context.dataStore.edit { preferences ->
                preferences[FIELD_STRING] = text
            }
        }

        suspend fun saveState(state : Boolean) {
            context.dataStore.edit { preferences ->
                preferences[FIELD_STATE] = state
            }
        }
    }

    fun onTask1And3(view: View) {
        Intent(this, RectangleListActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask2(view: View) {
        Intent(this, RectangleRemoveActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask4(view: View) {
        Intent(this, MoneyActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask5(view: View) {
        Intent(this, MoneyReqActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask6(view: View) {
        Intent(this, DataBaseActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}