package com.example.perguntas_e_respostas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.perguntas_e_respostas.databinding.FragmentGameBinding


class GameFragment : Fragment(),View.OnClickListener {

    private  var _binding: FragmentGameBinding?= null
    private val binding get() = _binding!!
    private var qtd_perguntas: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClicksEvents()
        getArgs()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {

        when(v?.id){
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
        binding.buttonTeste?.setOnClickListener(this)
    }


    private fun getArgs(){
        val args : GameFragmentArgs by navArgs()
        qtd_perguntas = args.quantidadePerguntas
        binding.textViewPergunta?.text = "Pergunta nº" + qtd_perguntas.toString()
    }

}