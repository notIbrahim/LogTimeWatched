package com.times.logtimewatched

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener
{
    private lateinit var auth: FirebaseAuth
    private lateinit var emails: EditText
    private lateinit var passwords: EditText
    private lateinit var noLoginbtn: Button
    private lateinit var loginBtn: Button
    private lateinit var createUser: Button

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noLoginbtn = findViewById(R.id.NoLogin)
        noLoginbtn.setOnClickListener{
            val noLog = Intent(this, Timewatch::class.java)
            startActivity(noLog)
        }
        // initialize email and password
        emails = findViewById(R.id.username)
        passwords = findViewById(R.id.password)
        loginBtn = findViewById(R.id.ModalLogin)
        // end initialize

        loginBtn.setOnClickListener(this)
        createUser = findViewById(R.id.Accounts)
        createUser.setOnClickListener{
            val newUser = Intent(this, CreateAccount::class.java)
            startActivity(newUser)
        }
    }

    override fun onClick(Views: View?)
    {
        when(Views?.id)
        {
            R.id.ModalLogin ->
            {
                if(emails.text.toString().isNotEmpty() && passwords.text.toString().isNotEmpty()) /*(/* emails.isNotEmpty() == async.data */)*/
                {
                    auth.signInWithEmailAndPassword(emails.text.toString(), passwords.text.toString())
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful)
                            {
                                val userLogin = Intent(this, Timewatch::class.java)
                                userLogin.putExtra(Timewatch._currUsers, emails.text.toString())
                                startActivity(userLogin)
                            }
                            else
                            {
                                Toast.makeText(applicationContext, "Either your password and Email Incorrectly", Toast.LENGTH_SHORT).show()
                            }

                        }
                }
                else
                {
                    Toast.makeText(applicationContext, "If you want use this without login, Please use Login As Guess", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /*
    fun Login(view: View) {
        TODO("Not Implemented")
    }
    */
}