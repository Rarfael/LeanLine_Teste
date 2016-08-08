package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {

    //Atributos para login
    private EditText editLoginUser;
    private EditText editSenhaUser;
    private Button buttonLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Recupera informações de login da tela
        editLoginUser = (EditText) findViewById(R.id.edit_loginUserId);
        editSenhaUser = (EditText) findViewById(R.id.edit_senhaUserId);
        buttonLogar = (Button) findViewById(R.id.buttonLogarId);

        //ParseUser.logOut();


        //Verificar se o usuário já esta logado
        verifyUserOn();

        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editLoginUser.getText().toString();
                String senha = editSenhaUser.getText().toString();

                verificarLogin(usuario, senha);
            }
        });
    }

    /*Metodo para verificar dados de login do usuário*/
    private void verificarLogin(String usuario, String senha){
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e == null){
                    Toast.makeText(LoginActivity.this, "Login realizado!", Toast.LENGTH_LONG).show();
                    abrirMainActivity();
                }else{
                    Toast.makeText(LoginActivity.this, "Error ao fazer login, " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /*botao cadastrar usuario*/
    public void cadastrarUsuario(View view){
        //levar usuario para activity de cadastro
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity( intent );

    }

    //metodo para verificar usuário logado
    private void verifyUserOn(){
        //verifica se o usuário esta logado e evia para tela principal
        if(ParseUser.getCurrentUser() != null){
            abrirMainActivity();
        }
    }
     //metodo para abrir area principal
    private void abrirMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity( intent );
        finish();
    }
}
