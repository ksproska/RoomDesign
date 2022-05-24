package com.example.roomdesign

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.graphics.drawable.TransitionDrawable


class MainActivity : AppCompatActivity() {
    var currentWallColor: String = "cream"
    var currentLight: String = "outside"
    var currentCarpet: String = "grey"
    lateinit var imageView: ImageView
    private var allWallButtons = mutableListOf<Button>()
    private var allCarpetButtons = mutableListOf<ImageButton>()
    private var allLightButtons = mutableListOf<Button>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        allWallButtons.add(findViewById(R.id.buttonWallBlack))
        allWallButtons.add(findViewById(R.id.buttonWallCream))
        allWallButtons.add(findViewById(R.id.buttonWallRed))
        allCarpetButtons.add(findViewById(R.id.buttonCarpetGrey))
        allCarpetButtons.add(findViewById(R.id.buttonCarpetModern))
        allCarpetButtons.add(findViewById(R.id.buttonCarpetPersian))
        allLightButtons.add(findViewById(R.id.buttonLightInside))
        allLightButtons.add(findViewById(R.id.buttonLightOutside))
        allLightButtons.add(findViewById(R.id.buttonLightRed))

        for (button in allWallButtons) {
            if(currentWallColor == button.tag.toString()) {
                button.isEnabled = false
            }
            button.setOnClickListener {
                currentWallColor = button.tag.toString()
                val newName = "${currentLight}_${currentWallColor}_${currentCarpet}"
                imageView.setImageDrawable(getDrawableByName(newName, this))
                button.isEnabled = false
                for (other in allWallButtons) {
                    if (button != other) {
                        other.isEnabled = true
                    }
                }
            }
        }
        for (button in allLightButtons) {
            if(currentLight == button.text.toString()) {
                button.isEnabled = false
            }
            button.setOnClickListener {
                val prevName = "${currentLight}_${currentWallColor}_${currentCarpet}"
                currentLight = button.text.toString()
                val newName = "${currentLight}_${currentWallColor}_${currentCarpet}"

                changeDrawableSmoothly(imageView,
                    getDrawableByName(prevName, this),
                    getDrawableByName(newName, this),
                    500)

                button.isEnabled = false
                for (other in allLightButtons) {
                    if (button != other) {
                        other.isEnabled = true
                    }
                }
            }
        }
        for (button in allCarpetButtons) {
            if(currentCarpet == button.tag.toString()) {
                button.isEnabled = false
            }
            button.setOnClickListener {
                currentCarpet = button.tag.toString()
                val newName = "${currentLight}_${currentWallColor}_${currentCarpet}"
                imageView.setImageDrawable(getDrawableByName(newName, this))
                button.isEnabled = false
                for (other in allCarpetButtons) {
                    if (button != other) {
                        other.isEnabled = true
                    }
                }
            }
        }
    }

    fun changeDrawableSmoothly(imageView: ImageView, drawableFrom: Drawable, drawableTo: Drawable, durationMillis: Int) {
        val layers = arrayOfNulls<Drawable>(2)
        layers[0] = drawableFrom
        layers[1] = drawableTo

        val transitionDrawable = TransitionDrawable(layers)
        imageView.setImageDrawable(transitionDrawable)
        transitionDrawable.startTransition(durationMillis)
    }

    fun getDrawableByName(name: String, context: Context): Drawable {
        val resourceId: Int = resources.getIdentifier(
            name, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }
}