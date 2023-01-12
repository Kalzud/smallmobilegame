package com.eou.gameapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Author: Emmanuel O. Uduma
 * This class subclasses the view class and uses its features
 * to enable drawing on the canvas of application alongside
 * allowing drawing with the bitmap via touch motion on the screen
 *
 * This class is used byy the layout xml of the second fragment
 */
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var paint= Paint()
    var myX:Float = 100f
    var myY:Float = 100f
    lateinit var myBitmap: Bitmap
    lateinit var myBitmapCanvas: Canvas
    var myWidth:Int = 800
    var myHeight:Int = 800

    /**
     * Constructor extension
     */
    init{
        paint.color= Color.RED
        paint.textSize=80f
    }

    /**
     * enables drawing on a canvas with the bitmap
     */
    override fun onDrawForeground(canvas: Canvas?) {
        super.onDrawForeground(canvas)
        canvas!!.drawText("Hello from canvas", myX, myY, paint)
        myBitmapCanvas.drawCircle(myX, myY,10f,paint)
        canvas!!.drawBitmap(myBitmap,0f,0f,null)
    }
    /**
     * This method enables drawing on the application with the
     * the touch motion
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //return super.onTouchEvent(event)
        if(event!!.action==MotionEvent.ACTION_MOVE) {
            myX = event!!.x
            myY = event!!.y
            invalidate()
        }
        return true
    }
    /**
     * set the width and height of the Bitmap to be used on canvas
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        myWidth = measuredWidth
        myHeight = measuredHeight
        myBitmap = Bitmap.createBitmap(myWidth,myHeight,Bitmap.Config.RGB_565)
        myBitmapCanvas = Canvas(myBitmap)
    }
}