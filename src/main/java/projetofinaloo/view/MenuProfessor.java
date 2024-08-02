package projetofinaloo.view;

import projetofinaloo.app.Professor;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.exceptions.CampoEmBrancoException;

import javax.swing.JOptionPane;

public class MenuProfessor extends Menu {
    public static Professor dadosNovoProfessor() {
        try {
            String nome = lerDado("nome", "Informe o nome do professor: ");
            String cpf = lerDado("cpf", "Informe o CPF do professor: ");
            String email = lerDado("email", "Informe o email do professor: ");
            String areaFormacao = lerDado("area de formação", "Informe a area de formação do professor: ");
            String matriculaFUB = lerDado("matricula", "Informe a matrícula do professor: ");
            return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
        }
    }

    public static Professor dadosNovoProfessor(String matriculaFUB) {
        try {
            String nome = lerDado("nome", "Informe o nome do professor: ");
            String cpf = lerDado("cpf", "Informe o CPF do professor: ");
            String email = lerDado("email", "Informe o email do professor: ");
            String areaFormacao = lerDado("area de formação", "Informe a area de formação do professor: ");
            return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
        } catch (CampoEmBrancoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
        }
    }

    public static void menuProfessor(Cadastro<Professor> cadProfessor) {
        String txt = "Informe a opção desejada\n" +
					 "1 - Cadastrar professor\n" +
					 "2 - Pesquisar professor\n" +
					 "3 - Atualizar professor\n" +
					 "4 - Remover professor\n" +
					 "0 - Voltar para menu anterior\n";

        int opcao;
        do {
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 0:
                break;

                case 1:
                    Professor professor = dadosNovoProfessor();
                    if (professor != null) {
                        cadProfessor.cadastrar(professor.getMatriculaFUB() ,professor);
                    }
                break;

                case 2:
                    try {
                        String matricula = lerDado("matricula", "Informe a matrícula do professor: ");
                        Professor p = cadProfessor.pesquisar(matricula);
                        if (p != null) {
                            JOptionPane.showMessageDialog(null, p.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                break;

                case 3:
                    try {
                        String matricula = lerDado("matricula", "Informe a matrícula do professor: ");
                        Professor p = dadosNovoProfessor(matricula);
                        boolean atualizado = cadProfessor.atualizar(matricula, p);
                        if (atualizado) {
                            JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }   
                break;

                case 4:
                    try {
                        String matricula = lerDado("matricula", "Informe a matrícula do professor: ");;
                        boolean removido = cadProfessor.remover(matricula);
                        if (removido) {
                            JOptionPane.showMessageDialog(null, "Professor removido do cadastro.");
                            System.gc();
                        } else {
                            JOptionPane.showMessageDialog(null, "Professor não encontrado.");
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
