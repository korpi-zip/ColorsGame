package com.example.colors

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.colors.ColorsGame.OnChangeProposedColorListener
import com.example.colors.ColorsGame.OnChangeTargetColorListener
import java.util.*

class MainActivity2 : AppCompatActivity() {

    private var lblTargetColor: TextView? = null
    private var lblProposedColor: TextView? = null
    private var sbrRed: SeekBar? = null
    private var sbrGreen: SeekBar? = null
    private var sbrBlue: SeekBar? = null
    private var lblRedValue: TextView? = null
    private var lblGreenValue: TextView? = null
    private var lblBlueValue: TextView? = null
    private var btnGetScore: Button? = null
    private var btnNewColor: Button? = null
    private var colorsGame: ColorsGame? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        colorsGame = ColorsGame()
        colorsGame!!.onChangeTargetColorListener = OnChangeTargetColorListener { newBackColor: Int, newTextColor: Int ->
            lblTargetColor!!.setBackgroundColor(newBackColor)
            lblTargetColor!!.setTextColor(newTextColor)
        }
        colorsGame!!.onChangeProposedColorListener = OnChangeProposedColorListener { newBackColor: Int, newTextColor: Int ->
            lblProposedColor!!.setBackgroundColor(newBackColor)
            lblProposedColor!!.setTextColor(newTextColor)
            sbrRed!!.progress = Color.red(newBackColor)
            lblRedValue!!.text = Color.red(newBackColor).toString()
            sbrGreen!!.progress = Color.green(newBackColor)
            lblGreenValue!!.text = Color.green(newBackColor).toString()
            sbrBlue!!.progress = Color.blue(newBackColor)
            lblBlueValue!!.text = Color.blue(newBackColor).toString()
        }
        restartGame()
        initEvents()
    }

    private fun initViews() {
        lblTargetColor = findViewById(R.id.lblTargetColor)
        lblProposedColor = findViewById(R.id.lblProposedColor)
        sbrRed = findViewById(R.id.sbrRed)
        sbrGreen = findViewById(R.id.sbrGreen)
        sbrBlue = findViewById(R.id.sbrBlue)
        lblRedValue = findViewById(R.id.lblRedValue)
        lblGreenValue = findViewById(R.id.lblGreenValue)
        lblBlueValue = findViewById(R.id.lblBlueValue)
        btnGetScore = findViewById(R.id.btnGetScore)
        btnNewColor = findViewById(R.id.btnNewColor)
    }

    private fun restartGame() {
        colorsGame!!.restartGame()
    }

    private fun initEvents() {
        val seekBars = arrayOf(sbrRed, sbrGreen, sbrBlue)
        for (seekBar in seekBars) {
            seekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                    updateValues()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
        }
        btnGetScore!!.setOnClickListener { view: View? -> showScore() }
        btnNewColor!!.setOnClickListener { view: View? -> restartGame() }
    }

    fun updateValues() {
        val redValue = sbrRed!!.progress
        val greenValue = sbrGreen!!.progress
        val blueValue = sbrBlue!!.progress
        val newBackColor = Color.rgb(redValue, greenValue, blueValue)
        colorsGame!!.proposedBackColor = newBackColor
    }
    fun showScore() {
        val RED = getString(R.string.Red)
        val GREEN = getString(R.string.Green)
        val BLUE = getString(R.string.Blue)
        val VERY_LOW = getString(R.string.Very_low)
        val LOW = getString(R.string.Low)
        val HIGH = getString(R.string.High)
        val VERY_HIGH = getString(R.string.Very_high)
        val targetColor = colorsGame!!.targetBackColor
        val proposedColor = colorsGame!!.proposedBackColor
        val alert = AlertDialog.Builder(this)
        val text = StringBuilder()
        val tips = StringBuilder()
        val redDiff = Color.red(targetColor) - Color.red(proposedColor)
        val greenDiff = Color.green(targetColor) - Color.green(proposedColor)
        val blueDiff = Color.blue(targetColor) - Color.blue(proposedColor)
        text.append(getString(R.string.Your_score_is, colorsGame!!.score.toString()))

        if (redDiff > 10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (redDiff > 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (redDiff < 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        } else if (redDiff < -10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, RED.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        }

        if (greenDiff > 10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, GREEN.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (greenDiff > 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, GREEN.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (greenDiff < 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, GREEN.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        } else if (greenDiff < -10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, GREEN.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        }

        if (blueDiff > 10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, BLUE.lowercase(Locale.getDefault()), VERY_LOW.lowercase(Locale.getDefault())))
        } else if (blueDiff > 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, BLUE.lowercase(Locale.getDefault()), LOW.lowercase(Locale.getDefault())))
        } else if (blueDiff < 0) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, BLUE.lowercase(Locale.getDefault()), HIGH.lowercase(Locale.getDefault())))
        } else if (blueDiff < -10) {
            tips.append("\n")
            tips.append(getString(R.string.X_is_Y, BLUE.lowercase(Locale.getDefault()), VERY_HIGH.lowercase(Locale.getDefault())))
        }

        if (tips.length > 0){
            text.append("/n/n")
            text.append("Tips")
            text.append(":")
            text.append(tips)
        }

        alert.setMessage(text)
        alert.setPositiveButton(getString(R.string.Close), null)
        alert.show()
    }


}