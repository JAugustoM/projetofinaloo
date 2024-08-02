package projetofinaloo.view;

import javax.swing.JOptionPane;

import projetofinaloo.app.Disciplina;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.exceptions.CampoEmBrancoException;

public class MenuDisciplina extends Menu {
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
    
    public static void menuDisciplina(Cadastro<Disciplina> cadDisciplina) {
        String txt = "Informe a opção desejada\n" +
					 "1 - Cadastrar disciplina\n" +
					 "2 - Pesquisar disciplina\n" +
					 "3 - Atualizar disciplina\n" +
					 "4 - Remover disciplina\n" +
					 "0 - Voltar para menu anterior\n";
		
		int opcao;

        do {
			String strOpcao = JOptionPane.showInputDialog(txt);
			opcao = Integer.parseInt(strOpcao);

			switch (opcao) {
				case 0:
				break;

				case 1:
					Disciplina novaDisciplina = dadosNovaDisciplina();
					if (novaDisciplina != null) {
						cadDisciplina.cadastrar(novaDisciplina.getCodigo(), novaDisciplina);
					}
				break;

				case 2:
					try {
						String codigo = lerDado("código", "Informe o código da disciplina: ");;
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
						String codigo = lerDado("código", "Informe o código da disciplina: ");;
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
						String codigo = lerDado("código", "Informe o código da disciplina: ");;
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
