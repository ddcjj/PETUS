package com.petus.findyourpet;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitialComponent();

    }

    private void InitialComponent() {
        button_get_verification_code = findViewById(R.id.button_get_verification_code);
        button_register_confirm = findViewById(R.id.button_register_confirm);
    }
    EditText editText_phone_number;
    EditText editText_verification_code;
    Button button_get_verification_code;
    EditText editText_register_password;
    Button button_register_confirm;
}
