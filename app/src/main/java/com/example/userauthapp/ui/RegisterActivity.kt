package com.example.userauthapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.userauthapp.data.UserDbHelper
import com.example.userauthapp.databinding.ActivityRegisterBinding
import com.example.userauthapp.data.UserContract

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: UserDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = UserDbHelper(this)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.writableDatabase
            val values = android.content.ContentValues().apply {
                put(UserContract.UserEntry.COL_NAME, name)
                put(UserContract.UserEntry.COL_EMAIL, email)
                put(UserContract.UserEntry.COL_PASS, pass)
            }

            val newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
            if (newRowId == -1L) {
                Toast.makeText(this, "Erro ao cadastrar — e-mail já existe?", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()  // volta para tela de Login
            }
        }
    }
}
