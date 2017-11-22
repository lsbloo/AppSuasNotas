package com.example.osvaldoairon.suasnotas.model;

/**
 * Created by osvaldoAiron on 01/11/2017.
 */

public class Aluno extends Pessoa{

  //  private String nomeAluno;
    private String matricula;
    private String disciplina;
    private Double notaUm;
    private Double notaDois;
    private Double notaTres;
    private Double notaFinal;
    private long id;



   // public void setNomeAluno(String nomeAluno){
   //     this.nomeAluno = nomeAluno;
   // }
   // public String getNomeAluno(){
     //   return nomeAluno;
   // }

    public void setMatricula(String matricula){
        this.matricula=matricula;
    }
    public String getMatricula(){ return matricula;}
    public void setDisciplina(String disp){
        this.disciplina = disp;
    }
    public String getDisciplina(){
        return disciplina;
    }
    public void setNotaUm(Double notaUm1){
        this.notaUm=notaUm1;
    }
    public Double getNotaUm(){
        return notaUm;
    }
    public void setNotaDois(Double notaDois){
        this.notaDois=notaDois;
    }
    public Double getNotaDois(){return  notaDois;}
    public void setNotaTres(Double nota3){this.notaTres=nota3;}
    public Double getNotaTres(){
        return notaTres;
    }
    public void setNotaFinal(Double notafinal){
        this.notaFinal=notafinal;
    }
    public Double getNotaFinal(){return notaFinal;}
    public void setId(long id){
        this.id=id;

    }

    public Aluno(String nome , String matricula , String disp , Double n1, Double n2 , Double n3 , Double notaFinal){

        setNome(nome);
        setMatricula(matricula);
        setDisciplina(disp);
        setNotaUm(n1);
        setNotaDois(n2);
        setNotaTres(n3);
        setNotaFinal(notaFinal);


    }


}
