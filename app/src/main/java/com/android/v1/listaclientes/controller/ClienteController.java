package com.android.v1.listaclientes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.android.v1.listaclientes.core.AppUtil;
import com.android.v1.listaclientes.datamodel.ClienteDataModel;
import com.android.v1.listaclientes.datasource.AppDataBase;
import com.android.v1.listaclientes.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por João Bosco em 03/2021.
 */
public class ClienteController extends AppDataBase implements ICrud<Cliente> {

    ContentValues dadoDoObjeto;

    public ClienteController(Context context) {
        super(context);

        Log.d(AppUtil.TAG, "ClienteController: Conectado");
    }

    @Override
    public boolean incluir(Cliente obj) {

        dadoDoObjeto = new ContentValues();

        // ID é chave primária da tabela cliente
        // é gerada automaticamente pelo SQLite a cada
        // novo registro adicionado
        // SQL ->>> INSERT INTO TABLE (... ... .. ) VALUES (### ### ###)
        dadoDoObjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoObjeto.put(ClienteDataModel.TELEFONE,obj.getTelefone());
        dadoDoObjeto.put(ClienteDataModel.EMAIL,obj.getEmail());
        dadoDoObjeto.put(ClienteDataModel.CEP,obj.getCep());
        dadoDoObjeto.put(ClienteDataModel.LOGRADOURO,obj.getLogradouro());
        dadoDoObjeto.put(ClienteDataModel.NUMERO,obj.getNumero());
        dadoDoObjeto.put(ClienteDataModel.BAIRRO,obj.getBairro());
        dadoDoObjeto.put(ClienteDataModel.CIDADE,obj.getCidade());
        dadoDoObjeto.put(ClienteDataModel.ESTADO,obj.getEstado());
        dadoDoObjeto.put(ClienteDataModel.TERMOS_DE_USO,obj.isTermosDeUso());


        // Enviar os dados (dadoDoObjeto) para a classe AppDatabase
        // utilizando um método capaz de adicionar o OBJ no banco de
        // dados, tabela qualquer uma (Cliente)

        // Retorno sempre será FALSE ou VERDADEIRO
        return insert(ClienteDataModel.TABELA, dadoDoObjeto);
    }

    @Override
    public boolean alterar(Cliente obj) {

        dadoDoObjeto = new ContentValues();

        // Alterar
        // SQL ->>> UPDATE
        dadoDoObjeto.put(ClienteDataModel.ID,obj.getId());
        dadoDoObjeto.put(ClienteDataModel.NOME,obj.getNome());
        dadoDoObjeto.put(ClienteDataModel.EMAIL,obj.getEmail());

        // Enviar os dados (dadoDoObjeto) para a classe AppDatabase
        // utilizando um método capaz de alterar o OBJ no banco de
        // dados, tabela qualquer uma (Cliente), respeitando o ID
        // ou PK (Primary Key)
        return update(ClienteDataModel.TABELA,dadoDoObjeto);
    }

    @Override
    public boolean deletar(int id) {
        return deleteByID(ClienteDataModel.TABELA,id);
    }

    @Override
    public List<Cliente> listar() {
        return getAllClientes(ClienteDataModel.TABELA);
    }

    public List<String> gerarListaDeClientesListView(){

        List<String> clientes = new ArrayList<>();

        for (Cliente obj: listar()) {

            clientes.add(obj.getId()+", "+obj.getNome());

        }

        return clientes;
    }
}
