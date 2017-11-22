package com.example.osvaldoairon.suasnotas;

/**
 * Created by osvaldoairon on 26/10/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MaskEditText {

    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(###)####-#####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";

    public static final String FORMAT_DOUBLE = "#.##";


    //Mascara para disciplina do aluno (somente 15 digitos);
    public static final String FORMAT_DISCIPLINA = "###############";

    // Mascara para matricula do aluno (somente 8 digitos);
    public static final String FORMAT_MATRICULA = "########";

    /**
     * No livro do android encontra-se esse codigo de Mask;
     * pag 125 ]] 130
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @param mask
     * @return
     */
    /**

     Exemplo de recuperacao de valor da MaskEditText ;]
     vai = (Button)findViewById(R.id.vai);

     cpf = (EditText)findViewById(R.id.cpf);
     cpf.addTextChangedListener(MaskEditText.mask(cpf,MaskEditText.FORMAT_CPF));


     //cpf1 = Integer.parseInt(cpf.getText().toString());




     vai.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
    cpf1 = cpf.getText().toString();

    Toast.makeText(MainActivity.this, "CPF: "+ cpf1, Toast.LENGTH_SHORT).show();
    if(cpf1.isEmpty()){
    Toast.makeText(MainActivity.this, "Vazio", Toast.LENGTH_SHORT).show();
    }

    }
    });


     */
    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditText.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[)]", "");
    }

}
