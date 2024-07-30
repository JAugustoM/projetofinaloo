package projetofinaloo.cadastros;

import projetofinaloo.app.Aluno;

import java.util.HashMap;

public class CadastroAluno {
	HashMap<String, Aluno> alunos;
	
	public CadastroAluno() {
		alunos = new HashMap<>();
	}
	
	public int cadastrarAluno(Aluno a) {
		alunos.put(a.getMatricula(), a);
		return alunos.size();
	}
	
	public Aluno pesquisarAluno(String matriculaAluno) {
		Aluno resposta =  null;
		if (alunos.containsKey(matriculaAluno)) {
			resposta = alunos.get(matriculaAluno);
		}
		return resposta;
	}
	
	public boolean removerAluno(String matricula) {
		if (alunos.containsKey(matricula)) {
			alunos.remove(matricula);
			return true;
		}
		return false;
	}
	
	public boolean atualizarAluno(String matricula, Aluno a) {
		if (alunos.containsKey(matricula)) {
			alunos.replace(matricula, a);
			return true;
		}
		return false;
	}
}
