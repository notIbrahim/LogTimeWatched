package com.times.logtimewatched

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.annotations.NotNull


class CreateAccount : AppCompatActivity()
{
    private lateinit var mAuth: FirebaseAuth
    private lateinit var conf_pass: EditText
    private lateinit var dataPass: EditText
    private lateinit var dataEmail: EditText
    private lateinit var createBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        conf_pass = findViewById(R.id.confirm_passtext)
        dataEmail = findViewById(R.id.mails)
        dataPass = findViewById(R.id.password)
        createBtn = findViewById(R.id.creates)

        createBtn.setOnClickListener{
            val confirms_pass = conf_pass.text.toString()
            val passwords = dataPass.text.toString()
            val emails = dataEmail.text.toString()
            if (confirms_pass.isNotEmpty() && passwords.isNotEmpty())
            {
                mAuth.createUserWithEmailAndPassword(emails, passwords)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            Toast.makeText(applicationContext, "Successful Register, Redirect...", Toast.LENGTH_SHORT).show()
                            val redirect_time = Intent(this, Timewatch::class.java)
                            redirect_time.putExtra(Timewatch._currUsers, dataEmail.text.toString())
                            startActivity(redirect_time)
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "Uh oh, Something bad, try that again", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}