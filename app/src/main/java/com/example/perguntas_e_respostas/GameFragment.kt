package com.example.perguntas_e_respostas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.perguntas_e_respostas.databinding.FragmentGameBinding


class GameFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var qtd_perguntas: Int? = null
    private var qtd_acertos: Int = 0
    private var correctResp: Int? = null
    private  var idCorrectResp: Int = 0
    private val resp: Boolean? = null
    private var questions = IntArray(6)

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
        generateQuestions()
        setAlteratives()
        setClicksEvents()
        getArgs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //marcar a alteranativa em azul
            R.id.radio_button_resp_1 -> {
                binding.buttonResp.isEnabled = true
            }
            R.id.radio_button_resp_2 -> {
                binding.buttonResp.isEnabled = true
            }
            R.id.radio_button_resp_3 -> {
                binding.buttonResp.isEnabled = true
            }
            R.id.radio_button_resp_4 -> {
                binding.buttonResp.isEnabled = true
            }

            R.id.button_resp -> {
                if (binding.buttonResp.text.equals("Responder")) {
                    binding.buttonResp.text = "Próxima pergunta"
                    //TODO Verificar se é a resposta correta e alterar a variável resp para true
                    desativeRadio()
                    vefifyAlternative()

                } else {
                    nextFragment()
                }
            }

        }
    }

    //Eventos de Click
    private fun setClicksEvents() {
        binding.buttonResp?.setOnClickListener(this)
        binding.radioButtonResp1?.setOnClickListener(this)
        binding.radioButtonResp2?.setOnClickListener(this)
        binding.radioButtonResp3?.setOnClickListener(this)
        binding.radioButtonResp4?.setOnClickListener(this)
    }

    //Desativando os radio buttons para evitar de alterar a resposta após responder
    private fun desativeRadio() {
        binding.radioButtonResp1.isEnabled = false
        binding.radioButtonResp2.isEnabled = false
        binding.radioButtonResp3.isEnabled = false
        binding.radioButtonResp4.isEnabled = false
    }

    //Varificando para qual fragment deve ir
    private fun nextFragment() {
        if (qtd_perguntas!! < 10) {
            if (resp == true) {
                qtd_acertos = qtd_acertos!! + 1
            }
            qtd_perguntas = qtd_perguntas!! + 1
            val direction =
                GameFragmentDirections.actionGameFragmentSelf(qtd_perguntas!!, qtd_acertos!!)
            findNavController().navigate(direction)

        } else {
            val direction = GameFragmentDirections.actionGameFragmentToResultFragment(qtd_acertos!!)
            findNavController().navigate(direction)
        }
    }

    //Pegando argumentos da tela anterior
    private fun getArgs() {
        val args: GameFragmentArgs by navArgs()
        qtd_perguntas = args.quantidadePerguntas
        binding.textViewPergunta?.text =
            "Pergunta nº" + qtd_perguntas.toString() + ": qual a soma de " + questions[0].toString() + "  " + questions[1].toString()
    }

    //Gerando a pergunta e as alternativas de forma automática
    private fun generateQuestions() {
        questions[0] = (1..100).random()
        questions[1] = (1..100).random()
        questions[2] = (1..100).random()
        questions[3] = (1..100).random()
        questions[4] = (1..100).random()
        questions[5] = questions[0] + questions[1]
    }

    //Definindo qual radio ira conter a alternativa correta
    private fun setAlteratives() {
        correctResp = (1..4).random()
        when (correctResp) {
            1 -> {
                idCorrectResp = binding.radioButtonResp1.id
                binding.radioButtonResp1.text = questions[5].toString()
                binding.radioButtonResp2.text = questions[2].toString()
                binding.radioButtonResp3.text = questions[3].toString()
                binding.radioButtonResp4.text = questions[4].toString()
            }
            2 -> {
                idCorrectResp = binding.radioButtonResp2.id
                binding.radioButtonResp2.text = questions[5].toString()
                binding.radioButtonResp1.text = questions[2].toString()
                binding.radioButtonResp3.text = questions[3].toString()
                binding.radioButtonResp4.text = questions[4].toString()
            }
            3 -> {
                idCorrectResp = binding.radioButtonResp3.id
                binding.radioButtonResp3.text = questions[5].toString()
                binding.radioButtonResp1.text = questions[2].toString()
                binding.radioButtonResp2.text = questions[3].toString()
                binding.radioButtonResp4.text = questions[4].toString()
            }
            4 -> {
                idCorrectResp = binding.radioButtonResp4.id
                binding.radioButtonResp4.text = questions[5].toString()
                binding.radioButtonResp1.text = questions[2].toString()
                binding.radioButtonResp2.text = questions[3].toString()
                binding.radioButtonResp3.text = questions[4].toString()
            }
        }
    }

    //Verifica se o usuário acertou
    private fun vefifyAlternative() {
         var radio_button_selected: RadioButton? = null
         var radio_button_correct: RadioButton? = null


        radio_button_selected = view?.findViewById(binding.radioGroupRespostas.checkedRadioButtonId)
        radio_button_correct = view?.findViewById(idCorrectResp)


        if(binding.radioGroupRespostas.checkedRadioButtonId == idCorrectResp){
            qtd_acertos = qtd_acertos + 1
            radio_button_selected?.setBackgroundResource(R.drawable.card_options_correct)
            radio_button_selected?.setTextColor(ContextCompat.getColor(context!!,R.color.verde_text_correct))
        }else{
            radio_button_selected?.setBackgroundResource(R.drawable.card_options_error)
            radio_button_selected?.setTextColor(ContextCompat.getColor(context!!,R.color.vermelho_text_error))
            radio_button_correct?.setBackgroundResource(R.drawable.card_options_correct)
            radio_button_correct?.setTextColor(ContextCompat.getColor(context!!,R.color.verde_text_correct))
        }
    }

}