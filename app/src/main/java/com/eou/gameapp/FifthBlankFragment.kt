package com.eou.gameapp

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eou.gameapp.databinding.FragmentFifthBlankBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Author: Emmanuel O. Uduma
 * This class is the third fragment and it is for the animal sound page
 *
 * A simple [Fragment] subclass.
 * Use the [FifthBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FifthBlankFragment : Fragment() {
    private lateinit var binding: FragmentFifthBlankBinding
    private lateinit var navController: NavController
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    /**
     * access UI features
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_fifth_blank, container, false)
        binding = FragmentFifthBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * sound and navigation
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding.back6.setOnClickListener{
            navController.navigate(R.id.action_fifthBlankFragment_to_firstBlankFragment)
        }

        /**
         * Sound pool sound controls
         */
        val audioAttributes: AudioAttributes = AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(
            AudioAttributes.CONTENT_TYPE_SONIFICATION).build()
        val soundPool = SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(2).build()
        val dog = soundPool.load(requireActivity(), R.raw.dog_growl3,1)
        val goat = soundPool.load(requireActivity(), R.raw.goat,1)
        val pig = soundPool.load(requireActivity(), R.raw.pig,1)
        val frog = soundPool.load(requireActivity(), R.raw.frog,1)

        binding.dog.setOnClickListener {
            soundPool.play(dog, 1.0f, 1.0f, 0,0, 1.0f)
        }
        binding.goat2.setOnClickListener {
            soundPool.play(goat, 1.0f, 1.0f, 0,0, 1.0f)
        }
        binding.pig.setOnClickListener {
            soundPool.play(pig, 1.0f, 1.0f, 0,0, 1.0f)
        }
        binding.frog.setOnClickListener {
            soundPool.play(frog, 1.0f, 1.0f, 0,0, 1.0f)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FifthBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FifthBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}