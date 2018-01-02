package ui.anwesome.com.kotlinnumberkeypadview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ui.anwesome.com.numberkeypadview.NumberKeypadView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = NumberKeypadView.create(this)
    }
}
