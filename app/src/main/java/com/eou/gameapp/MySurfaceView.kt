package com.eou.gameapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Author: Emmanuel O. Uduma
 * This class is a subclass of the surface view class and it implements runnable
 * it implements runnable in order to use its run method
 * this run function contains a loop which is continually running when the view is navigated to
 * this is what is used to run the continuous animation in the view
 *
 * THe class contains different array list of game objects which are used in the animation on the fragment
 */
class MySurfaceView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), Runnable {
    var paint = Paint()
    var isRunning = true
    lateinit var myThread: Thread
    lateinit var myHolder: SurfaceHolder
    var myGameObjects = ArrayList<GameObject>()
    var xs = ArrayList<GameObject>()
    var ticks = ArrayList<GameObject>()

    /**
     * Extended constructor
     */
    init {
        /**
         * Initialise
         */
        paint.color= Color.WHITE
        //get different thred from phone UI thread
        myThread = Thread(this)
        myThread.start()
        myHolder = holder
        /**
         * Get picture and assign to variables
         */
        val basket = context!!.resources.getDrawable(R.drawable.basket,null)
        val tick = context!!.resources.getDrawable(R.drawable.tick,null)
        val x = context!!.resources.getDrawable(R.drawable.x,null)
        /**
         * Add varaibles to array lists
         */
        myGameObjects.add(GameObject(920,1500,10,10,basket))
        ticks.add(GameObject(100,1600,10,10,tick))
        xs.add(GameObject(100,1900,10,10,x))
    }

    /**
     * Run method
     */
    override fun run() {
        /**
         * Continuous running loop
         */
        while(isRunning) {
            //Check if surface is valid or null
            if(!myHolder.surface.isValid)
                continue
            //get canvas
            val canvas: Canvas = myHolder.lockCanvas()
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)
            /**
             * Game object animation controls
             */
            for(gameObject in myGameObjects){
                if (gameObject.y>1500){
                    for (hint in xs){
                        hint.appear(canvas)
                    }
                }
                if (gameObject.y<1500){
                    for (hint in ticks){
                        hint.appear(canvas)
                    }
                }
                    gameObject.moveUpDown(canvas)
            }
            //unlock canvas
            myHolder.unlockCanvasAndPost(canvas)
        }


    }
}