package projetofinaloo.view;

import projetofinaloo.app.Aluno;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.exceptions.CampoEmBrancoException;

import javax.swing.JOptionPane;

public class MenuAluno extends Menu {

	public static Aluno dadosNovoAluno() {
		try {
			String nome = lerDado("nome", "Informe o nome do aluno: ");
			String cpf = lerDado("cpf", "Informe o CPF do aluno: ");
			String email = lerDado("email", "Informe o email do aluno: ");
			String matricula = lerDado("matrícula", "Informe a matricula do aluno: "); 
			String curso = lerDado("curso", "Informe o curso do aluno: ");
			return new Aluno(nome, cpf, email, matricula, curso);
		} catch (CampoEmBrancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static Aluno dadosNovoAluno(String matricula) {
		try {
			String nome = lerDado("nome", "Informe o nome do aluno: ");
			String cpf = lerDado("cpf", "Informe o CPF do aluno: ");
			String email = lerDado("email", "Informe o email do aluno: ");
			String curso = lerDado("email", "Informe o curso do aluno: ");
			return new Aluno(nome, cpf, email, matricula, curso);
		} catch (CampoEmBrancoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}

	public static void menuAluno(Cadastro<Aluno> cadAluno) {
		String txt = "Informe a opção desejada\n" +
					 "1 - Cadastrar aluno\n" +
					 "2 - Pesquisar aluno\n" +
					 "3 - Atualizar aluno\n" +
					 "4 - Remover aluno\n" +
					 "0 - Voltar para menu anterior\n";
		
		int opcao;
		do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
				case 0:
				break;

				case 1:
					Aluno novoAluno = dadosNovoAluno();
					if (novoAluno != null) {
						if (cadAluno.cadastrar(novoAluno.getMatricula(),novoAluno)) {
							JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
						} else {
							JOptionPane.showMessageDialog(null, "Um aluno com a mátricula informada já existe no cadastro.");
						}
					}
				break;

				case 2:
					try {
						String matricula = lerDado("matrícula", "Informe a matricula do aluno: ");;
						Aluno a = cadAluno.pesquisar(matricula);
						if (a != null) {
							JOptionPane.showMessageDialog(null, a.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
						}
					} catch (CampoEmBrancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				break;

				case 3:
					try {
						String matricula = lerDado("matrícula", "Informe a matricula do aluno: ");;
						Aluno novoCadastro = dadosNovoAluno(matricula);
						if (novoCadastro != null) {
							boolean atualizado = cadAluno.atualizar(matricula, novoCadastro);
							if (atualizado) {
								JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
							} else {
								JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
							}
						}
					} catch (CampoEmBrancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				break;

				case 4:
					try {
						String matricula = lerDado("matrícula", "Informe a matricula do aluno: ");;
						boolean removido = cadAluno.remover(matricula);
						if (removido) {
							JOptionPane.showMessageDialog(null, "Aluno removido do cadastro");
							System.gc();
						} else {
							JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
						}
					} catch (CampoEmBrancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				break;

				default:
					JOptionPane.showMessageDialog(null, "Opção inválida.");
				break;
			}
		} while (opcao != 0);
    }
}
