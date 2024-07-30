package projetofinaloo.cadastros;

import projetofinaloo.app.Professor;

import java.util.HashMap;

public class CadastroProfessor {
    private HashMap<String, Professor> professores;

    public CadastroProfessor() {
        professores = new HashMap<>();
    }

    public int cadastrarProfessor(Professor p) {
        professores.put(p.getMatriculaFUB(), p);
        return professores.size();
    }

    public Professor pesquisarProfessor(String matricula) {
        Professor resposta = null;
        if (professores.containsKey(matricula)) {
            resposta = professores.get(matricula);
        }
        return resposta;
    }

    public boolean removerProfessor(String matricula) {
        if (professores.containsKey(matricula)) {
            professores.remove(matricula);
            return true;
        }
        return false;
    }

    public boolean atualizarProfessor(String matricula, Professor p) {
        if (professores.containsKey(matricula)) {
            professores.replace(matricula, p);
            return true;
        }
        return false;
    }
}
