package projetofinaloo.view;

import javax.swing.JOptionPane;


/**
 * Menu principal da aplicação, permite o acesso aos outros menus
 */
public class MenuPrincipal {

	/**
	 * Cria uma janela de input que serve como o menu principal da aplicação
	 * @return O número digitado pelo usuário
	 * @throws NumberFormatException Se o usuário não digitar um número
	 */
	public static int menuOpcoes() {
		String opcoes = "1 - Abrir cadastro de alunos\n" +
						"2 - Abrir cadastro de professores\n" +
						"3 - Abrir cadastro de disciplinas\n" +
						"4 - Abrir cadastro de turmas\n" +
						"0 - Sair\n";
	
		String strOpcao = JOptionPane.showInputDialog(opcoes);
		int opcao = Integer.parseInt(strOpcao);
	
		return opcao;
	}

}
