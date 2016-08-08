package com.parse.starter.util;

import java.util.HashMap;

/**
 * Created by Rafael on 07/08/2016.
 *
 * Classe para criação de erros customizáveis
 */
public class ParseErrors {

    //Atributo para recuperar código de erro
    private HashMap<Integer,String> erros;

    //cataloga alguns códigos de erro
    public ParseErrors() {
        this.erros = new HashMap<>();
        this.erros.put(201, "Preencha a senha!");
        this.erros.put(202,"Usuário já existe!");
    }

    public String getErro(int codErro){
        return this.erros.get(codErro);
    }

}
