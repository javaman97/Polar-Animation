package com.example.polarbearanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import app.rive.runtime.kotlin.core.Rive
import com.example.polarbearanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val stateMachineName="Login Machine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Rive.init(this)

        binding.password.setOnFocusChangeListener { _, b ->
            if(b){
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "isHandsUp", true)
            } else {
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "isHandsUp", false)
            }
        }

        binding.email.setOnFocusChangeListener { _, b ->
            if(b){
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "isChecking", true)
            } else {
                binding.loginCharacter.controller.setBooleanState(stateMachineName, "isChecking", false)
            }
        }

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    binding.loginCharacter.controller.setNumberState(
                        stateMachineName,
                        "numLook",
                        s!!.length.toFloat()
                    )
                }catch(_:Exception){

                }
            }

        })

        binding.button.setOnClickListener {

            binding.password.clearFocus()

            Handler(mainLooper).postDelayed({
                if( binding.email.text!!.isNotEmpty()  && binding.password.text!!.isNotEmpty() &&
                    (binding.email.text.toString()=="aurbtao@gmail.com" && binding.password.text.toString()=="123")){
                    binding.loginCharacter.controller.fireState(stateMachineName, "trigSuccess")
                }else {
                    binding.loginCharacter.controller.fireState(stateMachineName, "trigFail")
                }
            },2000)
        }
    }
}