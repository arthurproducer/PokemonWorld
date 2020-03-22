package br.com.arthursales.pokemonworld.view.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.util.Keys.PREFERENCES_USER_ID
import br.com.arthursales.pokemonworld.view.login.LoginActivity
import br.com.arthursales.pokemonworld.view.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val preferences: SharedPreferences by inject()
    private val WAIT_TIME = 3500L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val saveID = preferences.getLong(PREFERENCES_USER_ID,0)
        if(saveID.toInt() == 0){
            showSplash()
        }else{
            showMain()
        }
    }

    private fun showSplash() {
        Handler().postDelayed({
            showLogin()
        }, WAIT_TIME)
    }

    private fun showLogin() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
