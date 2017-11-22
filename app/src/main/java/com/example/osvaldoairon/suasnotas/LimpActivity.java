package com.example.osvaldoairon.suasnotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.osvaldoairon.suasnotas.control.AlunoRepositorio;
import com.example.osvaldoairon.suasnotas.control.AlunoSQHelper;

import java.util.List;
import android.widget.Button;
public class LimpActivity extends AppCompatActivity {

    private AlunoRepositorio helper;
    private ArrayAdapter adapter;
    private ListView list;

    private Button btn_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limp);

        helper = new AlunoRepositorio(LimpActivity.this);
        list=(ListView)findViewById(R.id.list_id);

         adapter = new ArrayAdapter(LimpActivity.this,android.R.layout.simple_list_item_2,android.R.id.text1,helper.listarDados());
         list.setAdapter(adapter);


         btn_voltar=(Button)findViewById(R.id.btn_back);

         btn_voltar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 finish();
             }
         });



    }
}
