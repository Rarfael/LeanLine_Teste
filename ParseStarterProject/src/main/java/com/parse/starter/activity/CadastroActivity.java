package com.parse.starter.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;
import com.parse.starter.util.ParseErrors;

public class CadastroActivity extends AppCompatActivity {

    //Atributos para cadastro
    private EditText textUser;
    private EditText textUsername; //sobrenome
    private EditText textEmail;
    private EditText textPswd;
    private EditText textPswd2; //confirmação de senha
    private Button buttonCadastro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Recupera Objetos de cadastros da tela
        textUser = (EditText) findViewById(R.id.nomeCadastroId);
        textUsername = (EditText) findViewById(R.id.usernameId);
        textEmail = (EditText) findViewById(R.id.email_cadastroId);
        textPswd = (EditText) findViewById(R.id.senhaCadastroId);
        textPswd2 = (EditText) findViewById(R.id.confirmarSenhaId);
        buttonCadastro = (Button) findViewById(R.id.button_cadastrar);

        //Evento de click para o botão de cadastro, usando metodo OnclickListener
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUser();
            }
        });
    }

    //Metodo para cadastro de usuários
    private void cadastrarUser(){

        //Criar objeto usuario e converter text para String
        ParseUser usuario = new ParseUser();
        usuario.setUsername(textUser.getText().toString());
        usuario.setUsername(textUsername.getText().toString());
        usuario.setEmail(textEmail.getText().toString());
        usuario.setPassword(textPswd.getText().toString());
        usuario.setPassword(textPswd2.getText().toString());

        //Salvar dados
        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if( e == null){
                    //Exibe confirmação de cadastro
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado!", Toast.LENGTH_LONG).show();
                    retornarLogin();
                }else{
                    //Exibe erro ao cadastrar atraves da classe ParseErrors
                    ParseErrors parseErrors = new ParseErrors();
                    String erro = parseErrors.getErro( e.getCode());
                    Toast.makeText(CadastroActivity.this, erro, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Metodo de retorno para tela de login, apos cadastro
    private void retornarLogin(){
        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
