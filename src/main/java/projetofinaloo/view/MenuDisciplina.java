package projetofinaloo.view;

import javax.swing.JOptionPane;

import projetofinaloo.app.Disciplina;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.exceptions.CampoEmBrancoException;

/**
 * Classe que dá acesso ao usuário ao cadastro de disciplinas
 * @see projetofinaloo.app.Disciplina
 * @see projetofinaloo.cadastros.Cadastro
 */
public class MenuDisciplina extends Menu {
	/**
	 * Método utilizado para criar uma nova instância de Disciplina 
	 * @return Uma nova instância de Disciplina ou {@code null} caso um dos campos esteja em branco
	 */
    public static Disciplina dadosNovaDisciplina() {
        try {
            String nome = lerDado("nome", "Informe o nome da disciplina:");
            String codigo = lerDado("código", "Informe o código da disciplina:");
            int cargaHoraria = Integer.parseInt(lerDado("carga horária",
													 "Informe a carga horária, em horas, da disciplina:"));
            return new Disciplina(nome, codigo, cargaHoraria);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
        }
    }

	/**
	 * Método utilizado para criar uma nova instância de Disciplina com um código já conhecido,
	 * permitindo a atualização do cadastro dessa Disciplina
	 * @param codigo Código da instáncia de Disciplina que será retornada
	 * @return Uma nova instância de Disciplina ou {@code null} caso um dos campos esteja em branco
	 */
    public static Disciplina dadosNovaDisciplina(String codigo) {
        try {
            String nome = lerDado("nome", "Informe o nome da disciplina:");
            int cargaHoraria = Integer.parseInt(lerDado("carga horária",
													  "Informe a carga horária, em horas, da disciplina:"));
            return new Disciplina(nome, codigo, cargaHoraria);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
        }
    }
    
	/**
	 * Menu que dá acesso ao cadastro de disciplinas, permite a execução de operações
	 * de cadastro, pesquisa, atualização e remoção de disciplinas pelo usuário
	 * @param cadDisciplina Cadastro de disciplinas que será acessado pelo menu
	 */
    public static void menuDisciplina(Cadastro<Disciplina> cadDisciplina) {
        String txt = "Informe a opção desejada\n" +
					 "1 - Cadastrar disciplina\n" +
					 "2 - Pesquisar disciplina\n" +
					 "3 - Atualizar disciplina\n" +
					 "4 - Remover disciplina\n" +
					 "0 - Voltar para menu anterior\n";
		
		int opcao;

        do {
			try {
				String strOpcao = JOptionPane.showInputDialog(txt);
				opcao = Integer.parseInt(strOpcao);
			} catch (NumberFormatException e) {
				opcao = -1;
			}

			switch (opcao) {
				case 0:
				break;

				case 1:
					Disciplina novaDisciplina = dadosNovaDisciplina();
					if (novaDisciplina != null) {
						if(cadDisciplina.cadastrar(novaDisciplina.getCodigo(), novaDisciplina)) {
							JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso.");
						} else {
							JOptionPane.showMessageDialog(null, "Uma disciplina com o código informado já existe no cadastro.");
						}
					}
				break;

				case 2:
					try {
						String codigo = lerDado("código", "Informe o código da disciplina: ");
						Disciplina d = cadDisciplina.pesquisar(codigo);
						if (d != null) {
							JOptionPane.showMessageDialog(null, d.toString());
						} else {
							JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
						}
					} catch (CampoEmBrancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				break;

				case 3:
					try {
						String codigo = lerDado("código", "Informe o código da disciplina: ");
						Disciplina novoCadastro = dadosNovaDisciplina(codigo);
						if (novoCadastro != null) {
							boolean atualizado = cadDisciplina.atualizar(codigo, novoCadastro);
							if (atualizado) {
								JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
							} else {
								JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
							}
						}
					} catch (CampoEmBrancoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				break;

				case 4:
					try {
						String codigo = lerDado("código", "Informe o código da disciplina: ");
						boolean removido = cadDisciplina.remover(codigo);
						if (removido) {
							JOptionPane.showMessageDialog(null, "Disciplina removida do cadastro");
							System.gc();
						} else {
							JOptionPane.showMessageDialog(null, "Disciplina não encontrada.");
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
