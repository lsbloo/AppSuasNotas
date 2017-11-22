package com.example.osvaldoairon.suasnotas;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.osvaldoairon.suasnotas.model.Aluno;
import com.example.osvaldoairon.suasnotas.MaskEditText;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.text.DecimalFormat;

import com.example.osvaldoairon.suasnotas.control.AlunoSQHelper;
import com.example.osvaldoairon.suasnotas.control.AlunoRepositorio;


public class CadActivity extends AppCompatActivity {

    private MediaPlayer player;

    private AlunoRepositorio helper;

    private TextView media_saida;

    private TextView final_saida;


    private EditText txt_nome;
    private EditText txt_matricula;
    private EditText txt_disciplina;
    private EditText nota1;
    private EditText nota2;
    private EditText nota3;

    private RadioButton btn_calcular;
    private RadioButton btn_salvar;


    private Double notaUm;
    private Double notaDois;
    private Double notaTres;
    private static Double resultMedia;
    private static Double precisaFinal;

    private static String saida_media;

    private static Double medias[] = {4.0,4.1,4.2,4.3,4.4,4.5,4.6,4.7,4.8,4.9,5.0,5.1,5.2,5.3,5.4,5.5,5.6,5.7,5.8,5.9,6.0,6.1,6.2,6.3,6.4,6.5,6.6,6.7,6.8,6.9};
    private static Double notaFinal[] = {6.5,6.3,6.2,6.0,5.9,5.7,5.6,5.4,5.3,5.1,5.0,4.8,4.7,4.5,4.4,4.2,4.1,3.9,3.8,3.6,3.5,3.2,3.0,2.9,2.7,2.6,2.4,2.3,2.1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad);

        helper = new AlunoRepositorio(this);

        player = MediaPlayer.create(CadActivity.this,R.raw.plim);


        media_saida = (TextView)findViewById(R.id.txt_media);
        final_saida = (TextView)findViewById(R.id.txt_final);



        txt_nome=(EditText)findViewById(R.id.edt_nome);
        txt_matricula=(EditText)findViewById(R.id.edt_matricula);
        txt_disciplina=(EditText)findViewById(R.id.edt_disp);
        nota1=(EditText)findViewById(R.id.edt_n1);
        nota2=(EditText)findViewById(R.id.edt_n2);
        nota3=(EditText)findViewById(R.id.edt_n3);

        btn_calcular=(RadioButton)findViewById(R.id.btn_calcular);
        btn_salvar=(RadioButton)findViewById(R.id.btn_salvar);

        txt_matricula.addTextChangedListener(MaskEditText.mask(txt_matricula,MaskEditText.FORMAT_MATRICULA));
        txt_disciplina.addTextChangedListener(MaskEditText.mask(txt_disciplina,MaskEditText.FORMAT_DISCIPLINA));

       nota1.addTextChangedListener(MaskEditText.mask(nota1,MaskEditText.FORMAT_DOUBLE));
       nota2.addTextChangedListener(MaskEditText.mask(nota2,MaskEditText.FORMAT_DOUBLE));
       nota3.addTextChangedListener(MaskEditText.mask(nota3,MaskEditText.FORMAT_DOUBLE));



        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tocarMusica();


                if(btn_calcular.isChecked()){
                    btn_calcular.setChecked(false);
                }


                if(txt_nome.getText().toString().isEmpty() || txt_matricula.getText().toString().isEmpty() || txt_disciplina.getText().toString().isEmpty() || nota1.getText().toString().isEmpty() || nota2.getText().toString().isEmpty() || nota3.getText().toString().isEmpty() ){
                    Toast.makeText(CadActivity.this, "Preencha Todos os Campos!!", Toast.LENGTH_SHORT).show();

                }else{

                    notaUm = Double.parseDouble(nota1.getText().toString());
                    notaDois = Double.parseDouble(nota2.getText().toString());
                    notaTres = Double.parseDouble(nota3.getText().toString());

                    resultMedia = (notaUm+notaDois+notaTres)/ 3;
                    if(resultMedia>=7.0){
                        Toast.makeText(CadActivity.this, "Você já esta aprovado!", Toast.LENGTH_SHORT).show();
                    }
                    else if (resultMedia<=4.0){
                        Toast.makeText(CadActivity.this, "REPROVADO !!! ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String  saida_final = String.valueOf(calcularFinal(formatarDouble(resultMedia)));
                        saida_media = String.valueOf(formatarDouble(resultMedia));

                        media_saida.setText("Sua média é: "+ saida_media);

                        final_saida.setText("Você precisa na final: " +saida_final);

                    }

                }




            }
        });
        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tocarMusica();

                if(btn_salvar.isChecked()){
                    btn_salvar.setChecked(false);
                }

                if(txt_nome.getText().toString().isEmpty() || txt_matricula.getText().toString().isEmpty() || txt_disciplina.getText().toString().isEmpty() || nota1.getText().toString().isEmpty() || nota2.getText().toString().isEmpty() || nota3.getText().toString().isEmpty() ){
                    Toast.makeText(CadActivity.this, "Preencha Todos os Campos!!", Toast.LENGTH_SHORT).show();

                }else{

                    try{

                        Aluno aluno = new Aluno(txt_nome.getText().toString(),txt_matricula.getText().toString(),txt_disciplina.getText().toString(),Double.parseDouble(nota1.getText().toString()),Double.parseDouble(nota2.getText().toString()),Double.parseDouble(nota3.getText().toString()),calcularFinal(formatarDoubleUm(resultMedia)));
                        helper.inserir(aluno);

                        Toast.makeText(CadActivity.this, "Dados Salvos!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        //e.printStackTrace();
                        Toast.makeText(CadActivity.this, "Calcule primeiro antes de salvar!", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }
    public static Double calcularFinal(Double media){

        double resultado;

        for(int i = 0 ; i<notaFinal.length;i++){
           // Log.d("MEDIA: ", "MEDIA>"+ media);
           // Log.d("CONTADOR", "calcularFinal: "+ i);
            if(media <= medias[i]){
             //   Log.d("MEDIA VETOR", "Medias[i]"+ medias[i]);

                resultado = notaFinal[i];

                return resultado;
            }

        }
        return null;

    }
    public static Double formatarDouble(Double numero){
        DecimalFormat df = new DecimalFormat("#.##");
        df.format(numero);

        return numero;
    }
    public static Double formatarDoubleUm(Double numero){
        DecimalFormat df = new DecimalFormat("#.#");
        df.format(numero);

        return numero;

    }

    public void tocarMusica(){
        if(player!=null){
            player.start();
        }
    }

    @Override
    protected void onDestroy() {
        if(player!=null){
            player.stop();
            player.release();
            player=null;

        }
        super.onDestroy();
    }
}
