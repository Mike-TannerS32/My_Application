package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {


    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setup()
    }

    public fun setup(){

        findViewById<Button>(R.id.btn_auth).setOnClickListener {
            validateCredentialsAndRedirect()
        }
    }

    private fun validateCredentialsAndRedirect() {
        if (areCredentialsValid()) {
            val username = findViewById<TextInputEditText>(R.id.tet_username).text.toString()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_USERNAME,username)

            startActivity(intent)
            finish()
        }
    }

    private fun areCredentialsValid(): Boolean {
        val username = findViewById<TextInputEditText>(R.id.tet_username).text.toString()
        if (username.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_username)
            return false
        }

        val password = findViewById<TextInputEditText>(R.id.tet_password).text.toString()
        if (password.isEmpty()) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_empty_password)
            return false
        }

        val valid = viewModel.areCredentialsValid(username, password)
        if (!valid) {
            findViewById<TextView>(R.id.tv_error).text = getString(R.string.error_credentials_mismatch)
        }

        return valid
    }
}