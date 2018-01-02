package ui.anwesome.com.numberkeypadview

/**
 * Created by anweshmishra on 02/01/18.
 */
import android.content.*
import android.view.*
import android.graphics.*
import java.util.concurrent.ConcurrentLinkedQueue

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
        val state = State()
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
            canvas.save()
            canvas.scale(1f,1f)
            paint.color = Color.parseColor("#99BDBDBD")
            canvas.drawCircle(0f,0f,r*state.scale,paint)
            canvas.restore()
            canvas.restore()
        }
        fun handleTap(x:Float,y:Float) = x>=this.x-r && x<=this.x+r && y>=this.y - r && y<=this.y+r
        fun update(stopcb:(Int)->Unit) {
            state.update {
                stopcb(i)
            }
        }
        fun startUpdating(startcb:()->Unit) {
            state.startUpdating(startcb)
        }
    }
    data class NumberKeyContainer(var w:Float,var h:Float) {
        var textBuilder:StringBuilder = StringBuilder()
        var updatingKeys:ConcurrentLinkedQueue<NumberKey> = ConcurrentLinkedQueue()
        val numberKeys:ConcurrentLinkedQueue<NumberKey> = ConcurrentLinkedQueue()
        init {
            val x_gap = w/7
            val y_gap = (0.8f*h)/7
            var x = 3*x_gap/2
            var y = 0.2f*h+3*y_gap/2
            for(i in 1..9) {
                numberKeys.add(NumberKey(i,x,y,x_gap/2))
                x += 2*x_gap
                if(i%3 == 0) {
                    x = 3*x_gap/2
                    y += 2*y_gap
                }
            }
        }
        fun draw(canvas:Canvas,paint:Paint) {
            numberKeys.forEach {
                it.draw(canvas,paint)
            }
            paint.color = Color.BLACK
            paint.textSize = h/20
            val text = textBuilder.toString()
            val tw = paint.measureText(text)
            canvas.drawText(text,w/2-tw/2,h/10,paint)
        }
        fun update() {
            updatingKeys.forEach{
                it.update{i ->
                    textBuilder.append(i)
                }
            }
        }
        fun handleTap(x:Float,y:Float,startcb:()->Unit) {
            numberKeys.forEach {
                if(it.handleTap(x,y)) {
                    updatingKeys.add(it)
                    it.startUpdating{
                        if(updatingKeys.size == 1) {
                            startcb()
                        }
                    }

                }
            }
        }
    }
    data class State(var scale:Float = 0f,var deg:Float = 0f,var dir:Int = 0) {
        fun update(stopcb:()->Unit) {
            deg += 18*dir
            scale = Math.sin(deg*Math.PI/180).toFloat()
            if(deg > 180) {
                deg = 0f
                dir = 0
                scale = 0f
                stopcb()
            }
        }
        fun startUpdating(startcb:()->Unit) {
            if(dir == 0) {
                dir = 1
                startcb()
            }
        }
    }
}