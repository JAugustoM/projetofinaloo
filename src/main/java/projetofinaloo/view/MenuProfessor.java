package projetofinaloo.view;

import projetofinaloo.app.Professor;
import projetofinaloo.cadastros.CadastroProfessor;

import javax.swing.*;

public class MenuProfessor {
    public static Professor dadosNovoProfessor() {
        String nome = lerNome();
        String cpf = lerCpf();
        String email = lerEmail();
        String areaFormacao = lerFormacao();
        String matriculaFUB = lerMatricula();
        return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
    }

    public static Professor dadosNovoProfessor(String matriculaFUB) {
        String nome = lerNome();
        String cpf = lerCpf();
        String email = lerEmail();
        String areaFormacao = lerFormacao();
        return new Professor(nome, cpf, email, areaFormacao, matriculaFUB);
    }

    private static String lerNome() {
        return JOptionPane.showInputDialog("Informe o nome do professor: ");
    }

    private static String lerCpf() {
        return JOptionPane.showInputDialog("Informe o cpf do professor: ");
    }

    private static String lerEmail() {
        return JOptionPane.showInputDialog("Informe o email do professor: ");
    }

    private static String lerFormacao() {
        return JOptionPane.showInputDialog("Informe a area de formação do professor: ");
    }

    private static String lerMatricula() {
        return JOptionPane.showInputDialog("Informe a matrícula do professor: ");
    }

    public static void menuProfessor(CadastroProfessor cadProfessor) {
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
                    cadProfessor.cadastrarProfessor(professor);
                break;

                case 2:
                    String matricula = lerMatricula();
                    Professor p = cadProfessor.pesquisarProfessor(matricula);
                    if (p != null) {
                        JOptionPane.showMessageDialog(null, p.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                    }
                break;

                case 3:
                    matricula = lerMatricula();
                    professor = dadosNovoProfessor(matricula);
                    boolean atualizado = cadProfessor.atualizarProfessor(matricula, professor);
                    if (atualizado) {
                        JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                    }
                break;

                case 4:
                    matricula = lerMatricula();
                    boolean removido = cadProfessor.removerProfessor(matricula);
                    if (removido) {
                        JOptionPane.showMessageDialog(null, "Professor removido do cadastro.");
                        System.gc();
                    } else {
                        JOptionPane.showMessageDialog(null, "Professor não encontrado.");
                    }
                break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
                break;
            }
        } while (opcao != 0);
    }
}
