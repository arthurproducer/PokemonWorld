package br.com.arthursales.pokemonworld.view.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.arthursales.pokemonworld.R
import br.com.arthursales.pokemonworld.view.login.LoginActivity
import br.com.arthursales.pokemonworld.view.main.MainActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    val splashViewModel: SplashViewModel by viewModel()
    private val preferences: SharedPreferences by inject()

    private val WAIT_TIME = 3500L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        splashViewModel.checkHealthPokemon()
//        splashViewModel.checkHealthUser()

        val saveID = preferences.getLong("User_ID",0)
        if(saveID.toInt() == 0){
            showSplash()
        }else{
            showMain()
        }
//        splashViewModel.messageError.observe(this, Observer {
//            if(it == "") {
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
//            }
//        })
    }

    fun showSplash() {
        Handler().postDelayed({
            showLogin()
        }, WAIT_TIME)
    }

    fun showLogin() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
