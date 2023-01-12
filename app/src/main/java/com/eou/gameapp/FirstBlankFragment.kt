package com.eou.gameapp

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eou.gameapp.databinding.FragmentFirstBlankBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Author: Emmanuel O. Uduma
 * This class is the first fragment and it appears first
 * when the application is open it contains a menu that links to other parts
 * of the application.
 *
 * A simple [Fragment] subclass.
 * Use the [FirstBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstBlankFragment : Fragment() {
    private lateinit var binding: FragmentFirstBlankBinding
    private lateinit var navController: NavController
    var play:Boolean = true

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
     * when view is created it get access of the xml layout and fragment via binding
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first_blank, container, false)
        binding= FragmentFirstBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Controls navigation and sound controls
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        //go to second page
        binding.draw.setOnClickListener{
            navController.navigate(R.id.action_firstBlankFragment_to_secondBlankFragment)
        }
        //go to third page
        binding.play.setOnClickListener{
            navController.navigate(R.id.action_firstBlankFragment_to_thirdBlankFragment)
        }
        //go to fifth page
        binding.sound.setOnClickListener{
            navController.navigate(R.id.action_firstBlankFragment_to_fifthBlankFragment)
        }
        //theme song
        val themeMP = MediaPlayer.create(requireActivity(),R.raw.theme)
            themeMP.start()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}