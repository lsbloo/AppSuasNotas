package com.example.osvaldoairon.suasnotas.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by osvaldoAiron on 01/11/2017.
 */


public class AlunoSQHelper  extends SQLiteOpenHelper{

    public static final String NOME_TABELA = "alunos";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_MATRICULA = "matricula";
    public static final String COLUNA_DISCIPLINA = "disciplina";
    public static final String COLUNA_NOTA1 ="primeiraNota";
    public static final String COLUNA_NOTA2 ="segundaNota";
    public static final String COLUNA_NOTA3 = "terceiraNota";
    public static final String COLUNA_NOTA_FINAL = "notaFinal";
    public static final String COLUNA_ID = "_id";
    public static final String NOME_FICHA = "alunosFicha";
    public static final int VERSAO_BANCO = 1;

    public AlunoSQHelper(Context ctx){
        super(ctx,NOME_FICHA,null,VERSAO_BANCO);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS " +NOME_TABELA+ "("+COLUNA_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT ," +COLUNA_NOME+
                " TEXT NOT NULL, "+ COLUNA_MATRICULA+ " TEXT NOT NULL , "+ COLUNA_DISCIPLINA+ " TEXT NOT NULL, " +COLUNA_NOTA1+ " REAL, "+
        COLUNA_NOTA2 + " REAL, "+COLUNA_NOTA3+ " REAL, "+COLUNA_NOTA_FINAL + " REAL)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*
        N implementado
         */

    }
}
