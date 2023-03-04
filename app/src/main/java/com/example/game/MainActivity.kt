package com.example.game

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.game.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    private var ElementGame = linkedMapOf<Int,String>(0 to "Stone",1 to "Paper",2 to "Scissors",3 to "Spok",4 to "Lizard",5 to "All")
    private var ElementsGame = linkedMapOf<String,Int>("StoneStone" to 0,"PaperPaper" to 1,"ScissorsScissors" to 2, "SpokSpok" to 3, "LizardLizard" to 4,
        "PaperStone" to 5,"StonePaper" to 6,
        "PaperScissors" to 7, "ScissorsPaper" to 8,
        "PaperLizard" to 9, "LizardPaper" to 10,
        "PaperSpok" to 11, "SpokPaper" to 12,
        "ScissorsSpok" to 13,"SpokScissors" to 14,
        "SpokStone" to 15,"StoneSpok" to 16,
        "ScissorsLizard" to 17, "LizardScissors" to 18,
        "SpokLizard" to 19, "LizardSpok" to 20,
        "ScissorsStone" to 21, "StoneScissors" to 22,
        "LizardStone" to 23, "StoneLizard" to 24,
         "AllAll" to 25)
    private var TrueGames:Int=0
    private var FalseGames:Int=0
    private var DrawGames:Int=0
    private var Timer = System.currentTimeMillis()
    private var rnd = Random


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.isVisible=false
        SetAllImage(ElementGame.getValue(5),ElementGame.getValue(5))

        binding.StartButton.setOnClickListener()
        {
            binding.StopButton.isEnabled=true
            binding.PlayButton.isEnabled=true
            binding.StartButton.isEnabled=false
            binding.CheckedButton.isEnabled=true
            Timer=System.currentTimeMillis()
            binding.TextLabel.setTextColor(getColor(R.color.black))
        }

        binding.CheckedButton.setOnClickListener()
        {
            if(binding.textView.isVisible)
            {
                VisibilityFalse()
                binding.PlayButton.isEnabled=true
            }
            else {
                VisibilityTrue()
            }
        }

        binding.StopButton.setOnClickListener()
        {
            VisibilityFalse()
            TrueGames=0;
            FalseGames=0;
            DrawGames=0;
            binding.StopButton.isEnabled=false
            binding.PlayButton.isEnabled=false
            binding.StartButton.isEnabled=true
            binding.CheckedButton.isEnabled=false
            binding.TextLabel.text=""
            SetAllImage(ElementGame.getValue(5),ElementGame.getValue(5))
        }

        binding.PlayButton.setOnClickListener()
        {
            val IdButton = GetIdButton(binding.radioGroup.checkedRadioButtonId)
            val IdComputer =rnd.nextInt(0,5)

            when(CheckedGame(IdButton,IdComputer))
            {
                true->{
                    GetInformation(getColor(R.color.green),ElementGame.getValue(IdButton),ElementGame.getValue(IdComputer),"Вы победили!")
                    ++TrueGames
                }
                false->{
                    GetInformation(getColor(R.color.red),ElementGame.getValue(IdButton),ElementGame.getValue(IdComputer),"Вы проиграли")
                    ++FalseGames
                }
                null->{
                    GetInformation(getColor(R.color.black),ElementGame.getValue(IdButton),ElementGame.getValue(IdComputer),"Ничья")
                    ++DrawGames
                }
            }
        }
    }

    fun CheckedGame(Player:Int,Computer:Int):Boolean?
    {
        return when(Player-Computer)
        {
            1,3,-2,-4->true
            2,4,-1,-3->false
            0->null
            else->null
        }
    }

    fun GetIdButton(Id:Int):Int
    {
        return when(Id)
        {
            binding.Zero.id->0
            binding.PaperButton.id->1
            binding.ScissorsButton.id->2
            binding.SpokButton.id->3
            binding.LizardButton.id->4
            else->0
        }
    }

    fun SetImage(Box:ImageView,picture:String)
    {
        when(picture)
        {
            "Stone"->Box.setImageResource(R.drawable.stone)
            "Paper"->Box.setImageResource(R.drawable.paper)
            "Lizard"->Box.setImageResource(R.drawable.lizard)
            "Scissors"->Box.setImageResource(R.drawable.scissors)
            "Spok"->Box.setImageResource(R.drawable.spok)
            "All"->Box.setImageResource(R.drawable.all)
        }
    }

    fun SetImageResult(All:String)
    {
        when(ElementsGame.getValue(All))
        {
            0,1,2,3,4->binding.AllImage.setImageResource(R.drawable.draw)
            5,6->binding.AllImage.setImageResource(R.drawable.paperstone)
            7,8->binding.AllImage.setImageResource(R.drawable.scperper)
            9,10->binding.AllImage.setImageResource(R.drawable.lizardpaper)
            11,12->binding.AllImage.setImageResource(R.drawable.paperspoke)
            13,14->binding.AllImage.setImageResource(R.drawable.spokesc)
            15,16->binding.AllImage.setImageResource(R.drawable.spokestone)
            17,18->binding.AllImage.setImageResource(R.drawable.sclizard)
            19,20->binding.AllImage.setImageResource(R.drawable.lizardspoke)
            21,22->binding.AllImage.setImageResource(R.drawable.stonesc)
            23,24->binding.AllImage.setImageResource(R.drawable.stonelizard)
            25->binding.AllImage.setImageResource(R.drawable.all)
        }

    }

    fun SetAllImage(ElementUser:String,ElementComp: String)
    {
        SetImage(binding.MyImage,ElementUser)
        SetImage(binding.CompImage,ElementComp)
        SetImageResult(ElementUser+ElementComp)
    }

    fun GetInformation(Color:Int,ElementUser: String,ElementComp: String,Text:String)
    {
        binding.TextLabel.setTextColor(Color)
        binding.TextLabel.text=Text
        SetAllImage(ElementUser,ElementComp)
    }

    fun VisibilityFalse()
    {
            binding.textView.isVisible=false
            binding.radioGroup.isVisible=true
            binding.CheckedButton.text="Показать счет"
    }

    fun VisibilityTrue()
    {
            binding.textView.isVisible=true
            binding.radioGroup.isVisible=false
            binding.PlayButton.isEnabled = false
            binding.CheckedButton.text="Скрыть счет"
            binding.textView.text = "Побед: ${TrueGames}\nПоражений: ${FalseGames}\nНичьих: ${DrawGames}\n" +
                "\nВремя игры\n${GetTime(System.currentTimeMillis() - Timer).first} час(-ов)   " +
                "${GetTime(System.currentTimeMillis() - Timer).second} мин.   " +
                "${GetTime(System.currentTimeMillis() - Timer).third} сек."
    }

    fun GetTime (Mill:Long):Triple<Long,Long,Long>
    {
        var Hour:Long = Mill/3600000
        var Min:Long = (Mill-(Hour*3600000))/60000
        var Sec = (Mill-Hour*3600000-Min*60000)/1000
        return Triple(first = Hour, second = Min, third = Sec)
    }

}
