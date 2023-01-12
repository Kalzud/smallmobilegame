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
 * this is what runs the continuous animation in the game
 *
 * THe class contains different array list of game objects which are used in the animation on the fragment
 */
class MyGameplayView(context: Context?, attrs: AttributeSet?) : SurfaceView(context, attrs), Runnable {
    var paint= Paint()
    lateinit var myThread: Thread
    lateinit var myHolder: SurfaceHolder
    var isRunning:Boolean = true
    var myX:Float = 100f
    var myY:Float = 100f
    var meatTimer:Int = 0
    var grassTimer:Int = 101
    var animal = ArrayList<GameObject>()
    var food = ArrayList<GameObject>()
    val goat = context!!.resources.getDrawable(R.drawable.goat,null)
    val grass = context!!.resources.getDrawable(R.drawable.grass,null)
    val meat = context!!.resources.getDrawable(R.drawable.meat,null)
    var ob = GameObject(100, 600, 200, 200, context!!.resources.getDrawable(R.drawable.basket,null));

    /**
     * Constructor extension
     */
    init{
    paint.color= Color.WHITE
        //get different thred from phone UI thread
    myThread = Thread(this)
    myThread.start()
    myHolder = holder
    animal.add(GameObject(0,0,200,200,goat))
    }

    override fun run() {
        /**
         * Continuous loop
         */
        while(isRunning) {
            if(!myHolder.surface.isValid)
                continue
            val canvas: Canvas = myHolder.lockCanvas()
            canvas.drawRect(0f,0f,canvas.width.toFloat(),canvas.height.toFloat(),paint)

            /**
             * Game play animation controls
             */
            ob.appear(canvas)
            for (ani in animal){
                ani.appear(canvas)
            }
            if (grassTimer++>100){
                var xrnds = (0..800).random()
                var yrnds = (0..1500).random()
                food.add(GameObject(xrnds, yrnds,40,40,grass))
            grassTimer =0
            }
            if (meatTimer++>300){
                var xrnds = (0..800).random()
                var yrnds = (0..1500).random()
                food.add(GameObject(xrnds, yrnds,40,40,meat))
                meatTimer =0
            }
            var temp:GameObject? = null
            for (grassMeal in food){
                grassMeal.moveAround(canvas)
                if(ob.collider.intersect(grassMeal.collider)){
                    temp = grassMeal
                }
                if (grassMeal.age>200){
                    temp = grassMeal
                }
            }
            for (meatMeal in food){
                meatMeal.moveAround(canvas)
                if(ob.collider.intersect(meatMeal.collider)){
                    temp = meatMeal

                }
                if (meatMeal.age>200){
                    temp = meatMeal
                }
            }
            if(temp != null){
                food.remove(temp)
            }
            myHolder.unlockCanvasAndPost(canvas)
        }
    }

}