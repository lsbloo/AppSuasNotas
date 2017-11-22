package com.example.osvaldoairon.suasnotas.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.osvaldoairon.suasnotas.model.Aluno;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Created by osvaldoAiron on 01/11/2017.
 */

public class AlunoRepositorio  {
    private AlunoSQHelper helper;

    public static ArrayList<String> dados = new ArrayList<String>();



    public AlunoRepositorio(Context ctx){
        helper= new AlunoSQHelper(ctx);
    }

    public long inserir(Aluno aluno){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(AlunoSQHelper.COLUNA_NOME,aluno.getNome());
        cv.put(AlunoSQHelper.COLUNA_MATRICULA,aluno.getMatricula());
        cv.put(AlunoSQHelper.COLUNA_DISCIPLINA,aluno.getDisciplina());
        cv.put(AlunoSQHelper.COLUNA_NOTA1,aluno.getNotaUm());
        cv.put(AlunoSQHelper.COLUNA_NOTA2,aluno.getNotaDois());
        cv.put(AlunoSQHelper.COLUNA_NOTA3,aluno.getNotaTres());
        cv.put(AlunoSQHelper.COLUNA_NOTA_FINAL,aluno.getNotaFinal());

        long id = db.insert(AlunoSQHelper.NOME_TABELA,null,cv);
        if(id != -1){
            aluno.setId(id);
        }

        db.close();

        return id;
    }
    public ArrayList listarDados(){

        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+AlunoSQHelper.NOME_TABELA,null);

        int indiceColunaNome=cursor.getColumnIndex("nome");
        int indiceColunaMatricula=cursor.getColumnIndex("matricula");
        int indiceColunaDisciplina = cursor.getColumnIndex("disciplina");
        int indiceColunaNota1=cursor.getColumnIndex("primeiraNota");
        int indiceColunaNota2=cursor.getColumnIndex("segundaNota");
        int indiceColunaNota3=cursor.getColumnIndex("terceiraNota");
        int indiceColunaNotaFinal=cursor.getColumnIndex("notaFinal");


        cursor.moveToFirst();

        while(cursor.moveToNext()){


            dados.add("Nome: "+cursor.getString(indiceColunaNome));
            dados.add("Matricula: "+cursor.getString(indiceColunaMatricula));
            dados.add("Disciplina: "+cursor.getString(indiceColunaDisciplina));
            dados.add("Primeira Nota: "+cursor.getString(indiceColunaNota1));
            dados.add("Segunda Nota: "+cursor.getString(indiceColunaNota2));
            dados.add("Terceira Nota: "+cursor.getString(indiceColunaNota3));
            dados.add("Precisa tirar na final: "+cursor.getString(indiceColunaNotaFinal));
            dados.add("------------------------------------------------------------------------");


        }
        try{
            if(dados.isEmpty()){
                throw new Exception("ADAPTER VAZIO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dados;

    }

    public void limparDados(){
        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM "+AlunoSQHelper.NOME_TABELA);

        db.close();
    }

    public void resetarAdapter(){
        dados.clear();
    }

}
