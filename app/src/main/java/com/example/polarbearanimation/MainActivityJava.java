package com.example.polarbearanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.rive.runtime.kotlin.RiveAnimationView;
import app.rive.runtime.kotlin.core.Rive;

public class MainActivityJava extends AppCompatActivity {

    RiveAnimationView riveAnimationView;
    Button button;
    EditText email, password;
    private String stateMachineName = "Login Machine";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    riveAnimationView.setBooleanState(stateMachineName, "isHandsUp", true);
                } else {
                    riveAnimationView.setBooleanState(stateMachineName, "isHandsUp", false);
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    riveAnimationView.setBooleanState(stateMachineName, "isChecking", true);

                } else {
                    riveAnimationView.setBooleanState(stateMachineName, "isChecking", false);

                }
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    riveAnimationView.getController().setNumberState(stateMachineName,
                            "numLook",
                            editable.length()
                    );

                } catch (Exception exception) {
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                password.clearFocus();
                Handler handler = new Handler();

                final Runnable r = new Runnable() {
                    public void run() {
                        if (!email.getText().toString().isEmpty() && email.getText().toString().length() > 0 &&
                                (email.getText().toString() == "aurbtao@gmail.com" && password.getText().toString() == "123")
                        ) {
                            riveAnimationView.getController().fireState(stateMachineName, "trigSuccess");
                        } else {
                            riveAnimationView.getController().fireState(stateMachineName, "trigFail");

                        }
                    }
                };
                handler.postDelayed(r, 1000);
            }
        });
    }

    public void init() {
        Rive.INSTANCE.init(this);
        riveAnimationView = findViewById(R.id.loginCharacter);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);
    }
}