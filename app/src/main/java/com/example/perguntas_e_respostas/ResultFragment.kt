package com.example.perguntas_e_respostas

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.perguntas_e_respostas.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private var qtd_acertos: Int? = null
    var imageButton: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        setComponents()
    }

    private fun getArgs() {
        val args: ResultFragmentArgs by navArgs()
        qtd_acertos = args.totalAcertos
    }

    private fun setComponents() {
        binding.textViewResult.text =
            String.format(getString(R.string.pontuacao, qtd_acertos.toString()))
        val direction = ResultFragmentDirections.actionResultFragmentToHomeFragment()
        binding.textViewTentarNovamente.setOnClickListener {
            findNavController().navigate(direction)
        }
        imageButton = activity?.findViewById(R.id.image_button_voltar)
        imageButton?.visibility = View.VISIBLE
        imageButton?.setOnClickListener{
            findNavController().navigate(direction)
        }
    }

}