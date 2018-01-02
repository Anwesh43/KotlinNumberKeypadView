package ui.anwesome.com.numberkeypadview

/**
 * Created by anweshmishra on 02/01/18.
 */
import android.content.*
import android.view.*
import android.graphics.*
class NumberKeypadView(ctx:Context):View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas:Canvas) {

    }
    override fun onTouchEvent(event:MotionEvent):Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}