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
    data class NumberKey(var i:Int,var x:Float,var y:Float,var r:Float) {
        fun draw(canvas:Canvas,paint:Paint) {
            canvas.save()
            canvas.translate(x,y)
            paint.color = Color.parseColor("#212121")
            canvas.drawCircle(0f,0f,r,paint)
            paint.color = Color.WHITE
            paint.textSize = r/3
            val text = "$i"
            val tw = paint.measureText(text)
            canvas.drawText("$i",-tw/2,r/2,paint)
            canvas.restore()
        }
        fun handleTap(x:Float,y:Float) = x>=this.x-r && x<=this.x+r && y>=this.y - r && y<=this.y+r
        fun update(stopcb:(Int)->Unit) {

        }
        fun startUpdating(startcb:()->Unit) {

        }
    }
}