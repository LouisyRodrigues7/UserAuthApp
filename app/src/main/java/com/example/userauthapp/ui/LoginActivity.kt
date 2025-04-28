package com.example.userauthapp.ui
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userauthapp.MainActivity
import com.example.userauthapp.data.UserDbHelper
import com.example.userauthapp.data.UserContract
import com.example.userauthapp.databinding.ActivityLoginBinding
import com.example.userauthapp.R

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: UserDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDbHelper(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString().trim()
            val pass  = binding.etPasswordLogin.text.toString()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Preencha e-mail e senha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.readableDatabase
            val selection = "${UserContract.UserEntry.COL_EMAIL} = ? AND ${UserContract.UserEntry.COL_PASS} = ?"
            val cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                arrayOf(android.provider.BaseColumns._ID),
                selection,
                arrayOf(email, pass),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                cursor.close()
                // Login OK — iniciar próxima activity (exemplo: MainActivity)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                cursor.close()
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }

        // Link para tela de cadastro
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}