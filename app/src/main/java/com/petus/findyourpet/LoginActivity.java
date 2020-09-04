package com.petus.findyourpet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private View.OnClickListener button_login_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences sharedPreferences = getSharedPreferences(CDictionary.LOGIN,MODE_PRIVATE);
            sharedPreferences.edit().putBoolean(CDictionary.LOGON,true).commit();
            Bundle bundle = new Bundle();
            bundle.putString(CDictionary.USER_ACCOUNT,editText_login_account.getText().toString());
            bundle.putString(CDictionary.USER_PASSWORD,editText_login_password.getText().toString());
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
    private View.OnClickListener button_register_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitialComponent();

        button_login.setOnClickListener(button_login_click);
        button_register.setOnClickListener(button_register_click);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ConfirmExit();//按返回鍵，則執行退出確認
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void ConfirmExit(){//退出確認
        AlertDialog.Builder ad=new AlertDialog.Builder(LoginActivity.this);
        ad.setTitle("離開");
        ad.setMessage("確定要離開PETUS寵物定位嗎?");
        ad.setPositiveButton("PETUS覺得是", new DialogInterface.OnClickListener() {//退出按鈕
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                finishAffinity();
            }
        });
        ad.setNegativeButton("PETUS覺得否",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //不退出不用執行任何操作
            }
        });
        ad.show();//顯示對話框
    }
    public void onTextViewForgetPassword() {

    }


    private void InitialComponent() {
        editText_login_account = findViewById(R.id.editText_login_account);
        editText_login_password = findViewById(R.id.editText_login_password);
        checkBox_remember_password = findViewById(R.id.checkbox_remember_password);
        button_login = findViewById(R.id.button_login);
        button_register = findViewById(R.id.button_register);
    }
    EditText editText_login_account;
    EditText editText_login_password;
    CheckBox checkBox_remember_password;
    Button button_login;
    Button button_register;
}
