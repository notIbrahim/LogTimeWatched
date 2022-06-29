package com.times.logtimewatched

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import kotlinx.coroutines.*
import kotlin.system.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RestrictTo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import java.text.DecimalFormat


open class Timewatch : AppCompatActivity(), View.OnClickListener {
    /* I need scrap this if you wanna finish this
    private lateinit var of editbutton -> Password
    private lateinit var of editbutton -> logout (this is gonna use that
     */
    // CountDown -> TextView
    // timesets -> EditText
    // TimeStart -> Button
    // Logout -> Button

    private lateinit var inTime: EditText
    private lateinit var Start: ImageButton
    private lateinit var TextCount: TextView
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timewatch)


        inTime = findViewById(R.id.timesets)
        Start = findViewById(R.id.TimeStart)
        TextCount = findViewById(R.id.CountDown)
        Start.setOnClickListener(this)
    }

    override fun onClick(Views: View?)
    {
        if(Views?.id == R.id.TimeStart)
        {
            var badConvert = inTime.text.toString().toLong()
            setTimePomodoro(badConvert)
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

    fun setTimePomodoro(times: Long) // onPressedState:Boolean)
    {
        // var (onStateSetTime) = checkGuard.reverseStated(onPressedState).stated().checkStated()
        // while (true) //onStateSetTime.toString().toBoolean())
        //{
            object : CountDownTimer(times, 1000) {
                override fun onTick(timeRemaining: Long) {
                    val decimal = DecimalFormat("####,###")
                    val Hour = (timeRemaining / 3600000) % 24
                    val Minutes = (timeRemaining / 60000) % 60
                    val Second = (timeRemaining / 1000) % 60
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

        fun setState(){

        }
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