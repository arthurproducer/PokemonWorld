package br.com.arthursales.pokemonworld.view.login

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.model.UserResponse
import br.com.arthursales.pokemonworld.util.Base64Custom.codificarBase64
import br.com.arthursales.pokemonworld.util.Base64Custom.decodificarBase64
import br.com.arthursales.pokemonworld.view.listpokemon.ListPokemonActivity
import br.com.arthursales.pokemonworld.view.main.MainActivity
import kotlinx.android.synthetic.main.custom_login.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val preferences: SharedPreferences by inject()

    lateinit var validUser: UserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail.setOnEditorActionListener { _, i, _ ->
            handleEvent(i)
        }

        btnLogar.setOnClickListener {
            if (btnLogar.text == "Cadastrar Senha") {
                if (!edtPassword.text.isNullOrEmpty()) {
                    loginViewModel.insertSQLLite(
                        validUser,
                        codificarBase64(edtPassword.text.toString()),
                        this
                    )
                    Toast.makeText(this, "Senha salva com sucesso!", Toast.LENGTH_LONG).show()
                    btnLogar.text = getText(R.string.signIn)
                    tilEmail.isErrorEnabled = false
                    tilEmail.isEnabled = true
                    edtPassword.setText("")
                    edtEmail.setText("")
                    btnLogar.isEnabled = false
                }
            } else {
                if (loginViewModel.validateLogin(
                        edtEmail.text.toString(),
                        codificarBase64(edtPassword.text.toString()),
                        this)) {
                    val editor = preferences.edit()
                    editor?.putLong("User_ID",validUser.id)
                    editor?.apply()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this, "Email ou senha invalidos!", Toast.LENGTH_LONG).show()
                }
            }
        }

        loginViewModel.users.observe(this, Observer {
            it.forEach { user ->
                val inputEmail = edtEmail.text
                if (!inputEmail.isNullOrEmpty()) {
                    if (inputEmail.toString() == user.email) {

                        validUser = user

                        if (loginViewModel.validateLogin(inputEmail.toString(), null, this)) {
                            btnLogar.text = getText(R.string.signIn)
                            btnLogar.isEnabled = true
                            tilEmail.isErrorEnabled = false
                            tilEmail.isEnabled = false
                            return@Observer
                        } else {
                            tilEmail.error = "Login sem senha cadastrada! Cadastre uma senha para esse login!"
                            btnLogar.text = "Cadastrar Senha"
                            tvLoginQuestion.text = getText(R.string.what_is_your_password)
                            btnLogar.isEnabled = true
                            tilEmail.isEnabled = false
                            return@Observer
                        }
                    } else {
                        tilEmail.error = "Esse email não está cadastrado para essa aplicação! Tente outro email!"
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!tilEmail.isEnabled) {
            btnLogar.setText(R.string.signIn)
            btnLogar.isEnabled = false
            edtEmail.setText("")
            edtPassword.setText("")
        }
    }

    private fun handleEvent(actionId: Int): Boolean {
        if (EditorInfo.IME_ACTION_NEXT == actionId) {
            loginViewModel.getAllUser()
            edtPassword.requestFocus()
            return true
        }
        return false
    }
}
