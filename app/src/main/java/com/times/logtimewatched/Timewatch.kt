package com.times.logtimewatched

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import kotlin.system.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RestrictTo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import java.text.DecimalFormat


open class Timewatch : AppCompatActivity(), View.OnClickListener
{
    /* I need scrap this if you wanna finish this
    private lateinit var of editbutton -> Password
    private lateinit var of editbutton -> logout (this is gonna use that
     */
    // CountDown -> TextView
    // timesets -> EditText
    // TimeStart -> Button
    // Logout -> Button

    private lateinit var auth: FirebaseAuth

    private lateinit var hours: EditText
    private lateinit var minutes: EditText
    private lateinit var seconds: EditText
    private lateinit var Start: ImageButton
    private lateinit var TextCount: TextView
    private lateinit var info: TextView
    private lateinit var logouts: Button
    /* var temp : Boolean?
    private lateinit var auth: FirebaseAuth
     */
    /*
    private lateinit var pomodoroBtn: Button
    private lateinit var shortBreak: Button
    private lateinit var longBreak: Buttonw
     */
    companion object {
        var _currUsers: String? = "email"
    }

    override fun onStart() {
        super.onStart()
        val getEmails: String? = intent.getStringExtra(_currUsers)
        auth = Firebase.auth
        val auth_mail = auth.currentUser
        if (auth_mail != null)
        {
            info.text = getEmails
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timewatch)


        hours = findViewById(R.id.Hours)
        minutes = findViewById(R.id.Minutes)
        seconds = findViewById(R.id.Second)
        Start = findViewById(R.id.TimeStart)
        TextCount = findViewById(R.id.CountDown)
        logouts = findViewById(R.id.Logout)
        info = findViewById(R.id.InfoUser)
        logouts.setOnClickListener(this)
        Start.setOnClickListener(this)


    }

    override fun onClick(Views: View?)
    {
        if(Views?.id == R.id.TimeStart)
        {
            var handle_hours = hours!!.text.toString().toLong() * 3600000
            var handle_minute = minutes!!.text.toString().toLong() * 60000
            var handle_second = seconds!!.text.toString().toLong() * 1000
            var times = (handle_hours + handle_minute + handle_second)
            setTimePomodoro(times)
        }

        if(Views?.id == R.id.Logout)
        {
            val curr_auth = auth.currentUser
            if (curr_auth != null)
            {
                auth.signOut()
                Log.d(TAG, "Log Out successful return Login")
                startActivity((Intent(this, MainActivity::class.java)))
            }
            else
            {
                startActivity((Intent(this, MainActivity::class.java)))
            }
        }
    }
    /*
    Coroutine that will apply but headache come
    pomodoroBtn.setOnClickListener{
        CoroutineScope(IO).launch {
            onBatch()
        }
    }
     */

    fun setTimePomodoro(timed: Long) // onPressedState:Boolean)
    {
        // var (onStateSetTime) = checkGuard.reverseStated(onPressedState).stated().checkStated()
        // while (true) //onStateSetTime.toString().toBoolean())
        //{
            object : CountDownTimer(timed, 1000) {
                override fun onTick(timeRemaining: Long)
                {
                    val Hour = (timeRemaining / 3600000) % 24
                    val Minutes = (timeRemaining / 60000) % 60
                    val Second = (timeRemaining / 1000) % 60
                    val decimal = DecimalFormat("####,###")
                    TextCount.setText(
                        "${Hour} : ${Minutes} : ${
                            decimal.format(
                                Second
                            )
                        }"
                    )
                }

                override fun onFinish() {
                    TextCount.setText("00:00:00")
                }
            }.start()
        //}
    }
}

open class checkGuard : Timewatch()
{
    data class reverseStated(var Stated: Boolean?): checkGuard()
    {
        inner class stated
        {
            fun checkStated(): reverseStated
            {
                return reverseStated(!Stated!!)
            }
        }
    }
}


