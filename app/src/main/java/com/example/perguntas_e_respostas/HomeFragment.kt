package com.example.perguntas_e_respostas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.perguntas_e_respostas.databinding.FragmentGameBinding
import com.example.perguntas_e_respostas.databinding.FragmentHomeBinding


class HomeFragment : Fragment(),View.OnClickListener {

    private  var _binding: FragmentHomeBinding?= null
    private val binding get() = _binding!!
    var imageButton: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton =  activity?.findViewById(R.id.image_button_voltar)
        imageButton?.visibility = View.GONE
        setClicksEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        val id = v?.id

        when(id){
            R.id.button_start ->{
                val direction = HomeFragmentDirections.actionHomeFragmentToGameFragment(1,0)
                findNavController().navigate(direction)
            }
        }
    }

    private fun setClicksEvents(){
        binding.buttonStart?.setOnClickListener(this)
    }

}