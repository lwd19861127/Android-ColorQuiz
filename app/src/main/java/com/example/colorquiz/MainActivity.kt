package com.example.colorquiz

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var scoreMark: TextView
    lateinit var scoreTitle: TextView
    lateinit var title: TextView
    lateinit var answerLeft: Button
    lateinit var answerRight: Button
    lateinit var question: TextView

    var score = 0
    var questionList = mutableListOf("")
    lateinit var questionChosen: String
    var relatedColors = mutableMapOf<String, String>()
    lateinit var relatedTwoColor: List<String>
    lateinit var rightAnswerSide: String
    var userChoice = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        start()
    }

    private fun initView() {
        scoreMark = findViewById(R.id.scoreMark)
        scoreTitle = findViewById(R.id.scoreTitle)
        title = findViewById(R.id.title)
        answerLeft = findViewById(R.id.answerLeft)
        answerRight = findViewById(R.id.answerRight)
        question = findViewById(R.id.question)

        scoreTitle.text = "Score:"
        scoreMark.text = score.toString()
        title.text = "Select the right color!"
    }

    private fun start() {
        initQuestion()
        questionChosen = getQuestion()
        question.text = questionChosen
        val twoColors = getRelatedTwoColor(questionChosen)
        if(twoColors != null) {
            setTwoColors(twoColors)
            rightAnswerSide = getRightAnswerSide(questionChosen,relatedTwoColor)
        }
    }

    private fun initQuestion() {
        questionList.add("Cyan")
        questionList.add("LightGray")
        questionList.add("RedRust")
        questionList.add("BlueSky")
        questionList.add("WhiteGrey")
        questionList.add("Pink")
        questionList.add("Green")
        questionList.add("Orange")
        questionList.add("Brown")
        questionList.add("Grape")

        relatedColors["Cyan"] = "Cyan:DarkBlue"
        relatedColors["LightGray"] = "DarkGray:LightGray"
        relatedColors["RedRust"] = "RedRust:RedDark"
        relatedColors["BlueSky"] = "BlueIce:BlueSky"
        relatedColors["WhiteGrey"] = "WhiteGrey:White"
        relatedColors["Pink"] = "Ruby:Pink"
        relatedColors["Green"] = "Green:Forest"
        relatedColors["Orange"] = "Gold:Orange"
        relatedColors["Brown"] = "Brown:Cedar"
        relatedColors["Grape"] = "Amethyst:Grape"
    }

    private fun getQuestion(): String {
        var result: String
        val randomInt = Random().nextInt(questionList.size-1) + 1
        result = questionList[randomInt]
        return result
    }
    private fun getRelatedTwoColor(question: String): String? {
        return relatedColors[question]
    }

    private fun setTwoColors(twoColors: String) {

        relatedTwoColor = twoColors.split(":")

        var leftColor = when(relatedTwoColor[0]) {
            "Cyan" -> R.color.colorCyan
            "DarkGray" -> R.color.colorDarkGray
            "RedRust" -> R.color.colorRedRust
            "BlueIce" -> R.color.colorBlueIce
            "WhiteGrey" -> R.color.colorWhiteGrey
            "Ruby" -> R.color.colorRuby
            "Green" -> R.color.colorGreen
            "Gold" -> R.color.colorGold
            "Brown" -> R.color.colorBrown
            "Amethyst" -> R.color.colorAmethyst
            else -> R.color.colorPrimary
        }
        var rightColor = when(relatedTwoColor[1]) {
            "DarkBlue" -> R.color.colorDarkBlue
            "LightGray" -> R.color.colorLightGray
            "RedDark" -> R.color.colorRedDark
            "BlueSky" -> R.color.colorBlueSky
            "White" -> R.color.colorWhite
            "Pink" -> R.color.colorPink
            "Forest" -> R.color.colorForest
            "Orange" -> R.color.colorOrange
            "Cedar" -> R.color.colorCedar
            "Grape" -> R.color.colorGrape
            else -> R.color.colorPrimary
        }
        answerLeft.setBackgroundColor(resources.getColor(leftColor))
        answerRight.setBackgroundColor(resources.getColor(rightColor))
    }

    private fun getRightAnswerSide(question: String, twoColors: List<String>):String {
        when(question) {
            twoColors[0] -> return "Left"
            twoColors[1] -> return "Right"
            else -> return ""
        }
    }

    private fun caculate(userChoice: String) {
        if(userChoice.equals(rightAnswerSide)) {
            score += 1
            Toast.makeText(this,"You win 1 point", Toast.LENGTH_SHORT).show()
        } else {
            score -= 1
            Toast.makeText(this,"You lose 1 point", Toast.LENGTH_SHORT).show()
        }
        scoreMark.text = score.toString()
        start()
    }

    fun clickLeft(view: View) {
        userChoice = "Left"
        caculate(userChoice)
    }

    fun clickRight(view: View) {
        userChoice = "Right"
        caculate(userChoice)
    }
}
