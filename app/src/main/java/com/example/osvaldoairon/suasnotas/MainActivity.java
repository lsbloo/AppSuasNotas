package com.example.osvaldoairon.suasnotas;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.osvaldoairon.suasnotas.control.AlunoRepositorio;

public class MainActivity extends AppCompatActivity {


    private MediaPlayer player;

    private AlunoRepositorio helper;


   private CheckBox btn_cad;
   private CheckBox btn_limpar;
   private CheckBox btn_listar;
   private CheckBox btn_sigaa;

   private ImageView btn_som;

   private Button btn_inf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new AlunoRepositorio(MainActivity.this);

        player = MediaPlayer.create(MainActivity.this,R.raw.plim);


        btn_som =(ImageView)findViewById(R.id.btn_som);

        btn_cad=(CheckBox)findViewById(R.id.btn_cad);
        btn_limpar=(CheckBox)findViewById(R.id.btn_limpar);
        btn_listar=(CheckBox)findViewById(R.id.btn_listar);
        btn_sigaa=(CheckBox) findViewById(R.id.btn_sigaa);

        btn_inf=(Button)findViewById(R.id.btn_inf);




        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_cad.isChecked()){
                    btn_cad.setChecked(false);
                }

                Intent at = new Intent(MainActivity.this,CadActivity.class);

                startActivity(at);
            }
        });

        btn_listar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(btn_listar.isChecked()){
                    btn_listar.setChecked(false);
                }
                Intent at = new Intent(MainActivity.this,LimpActivity.class);

                startActivity(at);

            }
        });

        btn_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn_limpar.isChecked()){
                    btn_limpar.setChecked(false);
                }

                helper.limparDados();
                helper.resetarAdapter();
                Toast.makeText(MainActivity.this, "Dados Limpos ! ;] ", Toast.LENGTH_SHORT).show();
            }
        });

        btn_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent at = new Intent(MainActivity.this,InfActivity.class);
                startActivity(at);

            }
        });

        btn_sigaa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                if(btn_sigaa.isChecked()){
                    btn_sigaa.setChecked(false);
                }
                Intent t = new Intent(MainActivity.this,SigaaActivity.class);
                startActivity(t);

            }
        });

        btn_som.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                player.stop();
                player.release();

                player = null;


            }
        });


    }
}
