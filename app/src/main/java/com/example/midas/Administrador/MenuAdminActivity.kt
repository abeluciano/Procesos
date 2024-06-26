/**
 * Actividad del menú de administración.
 *
 * Autores:
 * Abel Luciano Aragón Alvaro
 * Josshua David Flores Chumbimuni
 * Rodrigo Ojeda Arce
 *
 * Resumen:
 * La clase MenuAdminActivity es una actividad de Android que sirve como el menú principal
 * para los administradores. Permite la navegación hacia otras actividades como la gestión
 * de usuarios y reportes, y proporciona una opción para cerrar sesión.
 */

package com.example.midas.Administrador

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.midas.BD.DatabaseHelper
import com.example.midas.Login.MainActivity
import com.example.midas.R
import com.example.midas.SignUpActivity
import kotlin.properties.Delegates

class MenuAdminActivity : AppCompatActivity() {
    private var idAmin by Delegates.notNull<Int>()
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_admin)

        dbHelper = DatabaseHelper(this)
        val btnUsers = findViewById<ImageButton>(R.id.btnUsers)
        val btnGesReportes = findViewById<ImageButton>(R.id.btnGesReportes)
        val Logout = findViewById<ImageButton>(R.id.btnLogout)
        val nameAdmin = findViewById<TextView>(R.id.nameAdmin)

        val sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        idAmin = sharedPreferences.getInt("Id_Admin", -1)
        val nameA = dbHelper.getAdminNameById(idAmin.toString())
        nameAdmin.text = nameA

        btnUsers.setOnClickListener(){
            val intent = Intent(this, GestionarUsuariosActivity::class.java)
            startActivity(intent)
        }

        btnGesReportes.setOnClickListener(){
            val intent = Intent(this, GestionarReportesActivity::class.java)
            startActivity(intent)
        }

        Logout.setOnClickListener {
            logout()
        }

    }
    private fun logout() {
        val sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}