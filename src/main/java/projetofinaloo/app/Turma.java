package projetofinaloo.app;

import java.util.ArrayList;
import java.util.Collections;

public class Turma {
    private String codTurma,
                   codDisciplina,
                   codProfessor;
    private int vagas;
    private ArrayList<Aluno> alunos;

    public Turma(String codTurma, String codDisciplina, String codProfessor, int vagas) {
        this.codTurma = codTurma;
        this.codDisciplina = codDisciplina;
        this.codProfessor = codProfessor;
        this.vagas = vagas;
        alunos = new ArrayList<>();
    }

    public String getCodTurma() {
        return codTurma;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public String getCodProfessor() {
        return codProfessor;
    }

    public int getVagas() {
        return vagas;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public boolean matricularAluno(Aluno aluno) {
        if (alunos.size() >= vagas) {
            return false;
        }

        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getMatricula().equals(aluno.getMatricula())) {
                return false;
            }
        }
        
        alunos.add(aluno);
        Collections.sort(alunos);
        return true;
    }

    public boolean removerAluno(String matricula) {
        boolean removido = false;

        for (int i = 0; i < alunos.size(); i++) {
            if (matricula.equals(alunos.get(i).getMatricula())) {
                alunos.remove(i);
                removido = true;
            }            
        }
        
        return removido;
    }
}
