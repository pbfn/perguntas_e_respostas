package com.example.perguntas_e_respostas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class GameFragment : Fragment(),View.OnClickListener {

    private var text_view_teste: TextView? = null
    private var button_teste: Button? = null
    private var qtd_perguntas: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents(view)
        setClicksEvents()
        getArgs()
    }

    override fun onClick(v: View?) {
        val id = v?.id

        when(id){
            R.id.button_teste ->{
                if(qtd_perguntas!! < 10){
                    //verificar se acertou e passar no total de acertos +1
                        qtd_perguntas = qtd_perguntas!! +1
                    val direction = GameFragmentDirections.actionGameFragmentSelf(qtd_perguntas!!,0)
                    findNavController().navigate(direction)

                }else{
                    val direction = GameFragmentDirections.actionGameFragmentToResultFragment(0)
                    findNavController().navigate(direction)
                }


            }
        }
    }

    private fun setClicksEvents(){
        button_teste?.setOnClickListener(this)
    }

    private fun initComponents(view:View){
        text_view_teste=view.findViewById(R.id.text_view_teste)
        button_teste = view.findViewById(R.id.button_teste)
    }

    private fun getArgs(){
        val args : GameFragmentArgs by navArgs()
        qtd_perguntas = args.quantidadePerguntas
        text_view_teste?.text = qtd_perguntas.toString()
    }




}