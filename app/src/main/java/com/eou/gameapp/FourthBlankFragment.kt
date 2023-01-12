package com.eou.gameapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eou.gameapp.databinding.FragmentFourthBlankBinding


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Author: Emmanuel O. Uduma
 * This class is the third fragment and it is for the game play page
 *
 * A simple [Fragment] subclass.
 * Use the [FourthBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthBlankFragment : Fragment(), SensorEventListener {
    lateinit var sensorManager: SensorManager
    lateinit var accelerometer: Sensor
    private lateinit var binding: FragmentFourthBlankBinding
    private lateinit var navController: NavController

    private var param1: String? = null
    private var param2: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fourth_blank, container, false)
        binding = FragmentFourthBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * sensor and navigation control
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //create sensor manager
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for(s in deviceSensors) {
            //create tag to assist in debugging
            Log.d("MyTAG",s.name)
        }
        //get sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //get sensor data
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_STATUS_ACCURACY_LOW)
        //navigation control
        navController = findNavController()
        binding.button.setOnClickListener{
            navController.navigate(R.id.action_fourthBlankFragment_to_thirdBlankFragment)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FourthBlankFragment.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FourthBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     * event controls for change in sensor data
     */
    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null)
            return
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            var x=event.values[0]
            var y=event.values[1]
            var z=event.values[2]
            var myText = "%.2f".format(x) + " %.2f".format(y) + " %.2f".format(z)
            Log.d("MyTAG",myText)
            var w:Int=0
            var h:Int=0
            //enables sensor control game object movement in game play
            binding.gameplayView.ob.move(x.toInt(), z.toInt())
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}