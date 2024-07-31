package projetofinaloo;

import projetofinaloo.app.Aluno;
import projetofinaloo.app.Professor;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.view.MenuAluno;
import projetofinaloo.view.MenuPrincipal;
import projetofinaloo.view.MenuProfessor;

import javax.swing.JOptionPane;

public class Main {

	static Cadastro<Aluno> cadAluno;
	static Cadastro<Professor> cadProfessor;
	
	public static void main(String[] args) {
		cadAluno = new Cadastro<>();
		cadProfessor = new Cadastro<>();
		
		int opcao;
		
		do {
			opcao = MenuPrincipal.menuOpcoes();
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
					//TODO Cadastro de Disciplinas
					JOptionPane.showMessageDialog(null, "Cadastro de disciplinas a ser implementado");
				break;
				case 4:
					//TODO Cadastro de Turmas
					JOptionPane.showMessageDialog(null, "Cadastro de turmas a ser implementado");
				break;
				default: 
					JOptionPane.showMessageDialog(null, "Opção invalida");
					opcao = -1;
				break;
			}
		} while (opcao != 0);
    }
}
