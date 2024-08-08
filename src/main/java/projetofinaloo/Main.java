package projetofinaloo;

import projetofinaloo.app.Aluno;
import projetofinaloo.app.Disciplina;
import projetofinaloo.app.Professor;
import projetofinaloo.app.Turma;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.view.MenuAluno;
import projetofinaloo.view.MenuDisciplina;
import projetofinaloo.view.MenuPrincipal;
import projetofinaloo.view.MenuProfessor;
import projetofinaloo.view.MenuTurma;

import javax.swing.JOptionPane;

/**
 * Classe executável do programa
 */
public class Main {

	static Cadastro<Aluno> cadAluno;
	static Cadastro<Professor> cadProfessor;
	static Cadastro<Disciplina> cadDisciplina;
	static Cadastro<Turma> cadTurma;
	
	public static void main(String[] args) {
		cadAluno = new Cadastro<>();
		cadProfessor = new Cadastro<>();
		cadDisciplina = new Cadastro<>();
		cadTurma = new Cadastro<>();
		
		int opcao;
		
		do {
			try {
				opcao = MenuPrincipal.menuOpcoes();
			} catch (NumberFormatException e) {
				opcao = -1;
			}
			
			switch (opcao) {
				case 0:
				break;
				case 1: 
					MenuAluno.menuAluno(cadAluno);
				break;
				case 2:
					MenuProfessor.menuProfessor(cadProfessor);
				break;
				case 3:
					MenuDisciplina.menuDisciplina(cadDisciplina);
				break;
				case 4:
					MenuTurma.menuTurma(cadTurma, cadProfessor, cadDisciplina, cadAluno);
				break;
				default: 
					JOptionPane.showMessageDialog(null, "Opção invalida");
					opcao = -1;
				break;
			}
		} while (opcao != 0);
    }
}
