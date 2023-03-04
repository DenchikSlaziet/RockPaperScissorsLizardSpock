package com.example.firstprogram

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val StartButton: Button = findViewById(R.id.StartButton)
        val ChangeButton: Button = findViewById(R.id.SearchButton)
        var FirstLabel: TextView = findViewById(R.id.FirstLabel)
        var CanLabel: TextView = findViewById(R.id.CanLabel)
        var SecondLabel: TextView = findViewById(R.id.SecondLabel)
        val Result: TextView = findViewById(R.id.ResultLabel)
        var OkLabel: TextView = findViewById(R.id.OkLabel)
        var BadLabel:TextView = findViewById(R.id.BedLabel)
        var AllLabel:TextView = findViewById(R.id.AllLabel)
        var ProcentLabel:TextView = findViewById(R.id.ProcentLabel)
        var rnd = Random
        var OkInt = 0
        var BadInt = 0
        val Can:List<String> = listOf("+","-","/","*")
        ChangeButton.isEnabled=false

        fun Rand()
        {
            Result.text=""
            CanLabel.text = Can.elementAt(rnd.nextInt(0,4))
            var First=rnd.nextInt(10,100)
            var Second = rnd.nextInt(10,100)
            if(CanLabel.text=="/")
            {
                while (First.toDouble()%Second.toDouble()!=0.0)
                {
                   First = rnd.nextInt(10,100)
                   Second = rnd.nextInt(10,100)
                }
            }
            if(CanLabel.text=="*")
            {
                First=rnd.nextInt(1,10)
                Second=rnd.nextInt(1,10)
            }
            FirstLabel.text = First.toString()
            SecondLabel.text = Second.toString()
        }

        fun Proverka():Boolean
        {
            return when(CanLabel.text)
            {
                "+"->{
                    if((FirstLabel.text.toString().toInt() + SecondLabel.text.toString().toInt()) == Result.text.toString().toInt()){
                        true
                    }
                    else {
                        false
                    }
                }

                "-"->{
                    if((FirstLabel.text.toString().toInt() - SecondLabel.text.toString().toInt()) == Result.text.toString().toInt())
                    {
                        true
                    }
                    else
                    {
                        false
                    }
                }
                "/"->{
                    if((FirstLabel.text.toString().toInt() / SecondLabel.text.toString().toInt()) == Result.text.toString().toInt())
                    {
                        true
                    }
                    else
                    {
                        false
                    }
                }
                "*"->{
                    if((FirstLabel.text.toString().toInt() * SecondLabel.text.toString().toInt()) == Result.text.toString().toInt())
                    {
                        true
                    }
                    else
                    {
                        false
                    }
                }
                else->true
            }
        }

        fun ProverkaVvoda(value:String):Boolean
        {
            return if(value.toIntOrNull()!=null)
            {
                true
            }
            else
            {
                false
            }
        }

        StartButton.setOnClickListener()
        {
            StartButton.isEnabled = false
            FirstLabel.text = rnd.nextInt(10,100).toString()
            SecondLabel.text = rnd.nextInt(10,100).toString()
            ChangeButton.isEnabled=true
            Result.text=""
        }

        Result.addTextChangedListener()
        {
            ChangeButton.isEnabled = !Result.text.isNullOrBlank()
        }

        ChangeButton.setOnClickListener()
        {
            if(ProverkaVvoda(Result.text.toString())) {
                if (Proverka()) {
                    ++OkInt
                    OkLabel.text = OkInt.toString()
                } else {
                    ++BadInt
                    BadLabel.text = BadInt.toString()
                }
                AllLabel.text = (OkInt + BadInt).toString()
                ProcentLabel.text = ((((OkInt.toDouble() / (AllLabel.text.toString()
                    .toDouble() / 100.0)) * 100).roundToInt().toDouble() / 100).toString() + "%")
                Rand()
            }
            else
            {
                Result.text="Число!"
            }
        }

    }
}