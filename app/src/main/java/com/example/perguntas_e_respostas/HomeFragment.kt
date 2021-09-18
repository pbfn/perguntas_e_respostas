package com.example.perguntas_e_respostas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment(),View.OnClickListener {

    private var button_start: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        setClicksEvents()
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
        button_start?.setOnClickListener(this)
    }

    private fun initComponents(view:View){
        button_start = view.findViewById(R.id.button_start)
    }
}