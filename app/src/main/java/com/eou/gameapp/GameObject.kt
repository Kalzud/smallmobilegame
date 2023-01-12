package com.eou.gameapp

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
/**
 * Author: Emmanuel O. Uduma
 * This is the parent class of all Game objects
 * It handles teh parameters needed to be passed into the game class and the
 * movement of game objects on the canvas of the application
 */
open class GameObject(var x:Int, var y:Int, var dx:Int, var dy:Int, var image: Drawable) {
    var width:Int = 200
    var height:Int = 200
    var paint= Paint()
    var age:Int = 0
    //this collider is used for the collision detection of game objects
    var collider: Rect = Rect(x.toInt(), y.toInt(), x+width.toInt(), y+height.toInt())

    /**
     * This function is made for game objects
     * whose motion would be controlled by sensors on the phone
     * e.g the accelerometer sensor
     */
    open fun move(x1:Int, y1:Int){
        dx = x1
        dy = y1
        x+=dx
        y+=dy
    }

    /**
     * To make game object show on canvas
     */
    open fun appear(canvas: Canvas){
        if(x>(canvas.width-width) )
            x = (canvas.width-width)
        if(y>(canvas.height-height))
            y = canvas.height-height
        if (x < 0)
            x = 0
        if (y < 0)
            y = 0
        //set collider x, y, width and height property to that of object
        collider.set(x.toInt(), y.toInt(), x+width.toInt(), y+height.toInt())
        image.setBounds(x, y,x+width,y+height)
        image.draw(canvas)
    }

    /**
     * Used for control of game objects need to move just up and down with
     * a certain height limit
     */
    open fun moveUpDown (canvas: Canvas){
        y+=dy
        if(y>(canvas.height-height) || y<1200)
            dy = -dy
        image.setBounds(x, y,x+width,y+height)
        image.draw(canvas)
    }

    /**
     * Used to control game objects nedd to move around the screen
     * at random
     */
    open fun moveAround(canvas: Canvas){
        age++
        x+=dx
        y+=dy
        if(x>(canvas.width-width) || x<0)
            dx = -dx
        if(y>(canvas.height-height) || y<0)
            dy = -dy
        //set collider x, y, width and height rpopety to that of object
        collider.set(x.toInt(), y.toInt(), x+width.toInt(), y+height.toInt())
        image.setBounds(x, y,x+width,y+height)
        image.draw(canvas)
    }


}