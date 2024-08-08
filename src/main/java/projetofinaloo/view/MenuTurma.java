package projetofinaloo.view;

import javax.swing.JOptionPane;

import projetofinaloo.app.Aluno;
import projetofinaloo.app.Disciplina;
import projetofinaloo.app.Professor;
import projetofinaloo.app.Turma;
import projetofinaloo.cadastros.Cadastro;
import projetofinaloo.exceptions.CampoEmBrancoException;
import projetofinaloo.exceptions.DisciplinaNaoAtribuidaException;
import projetofinaloo.exceptions.ProfessorNaoAtribuidoException;

/**
 * Classe que dá acesso ao usuário ao cadastro de turmas
 * @see projetofinaloo.app.Turma
 * @see projetofinaloo.cadastros.Cadastro
 */
public class MenuTurma extends Menu {
    /**
	 * Método utilizado para criar uma nova instância de Turma 
     * @param cadProfessor Cadastro de professores
     * @param cadDisciplina Cadastro de disciplinas
	 * @return Uma nova instância de Turma ou {@code null} caso um dos campos esteja em branco ou o Professor ou Disciplina
     * especificado não exista no cadastro
	 */
    public static Turma dadosNovaTurma(Cadastro<Professor> cadProfessor, Cadastro<Disciplina> cadDisciplina) {
        try {
            String codTurma = lerDado("turma", "Informe o código da turma");
            String codDisciplina = lerDisciplina();
            if (cadDisciplina.pesquisar(codDisciplina) == null) {
                JOptionPane.showMessageDialog(null, "Código de disciplina inválido, discipína não encotrada.");
                return null;
            }
            String codProfessor = lerProfessor();
            if (cadProfessor.pesquisar(codProfessor) == null) {
                JOptionPane.showMessageDialog(null, "Matrícula de professor inválida, professor não encotrado.");
                return null;
            }
            int vagas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de vagas:"));
            return new Turma(codTurma, codDisciplina, codProfessor, vagas);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    /**
	 * Método utilizado para criar uma nova instância de Turma com um código já conhecido,
     * permitindo a atualização do cadastro dessa Turma
     * @param cadProfessor Cadastro de professores
     * @param cadDisciplina Cadastro de disciplinas
     * @param codTurma Código da instância de Turma que será retornada
	 * @return Uma nova instância de Turma ou {@code null} caso um dos campos esteja em branco ou o Professor ou Disciplina
     * especificado não exista no cadastro
	 */
    public static Turma dadosNovaTurma(Cadastro<Professor> cadProfessor, Cadastro<Disciplina> cadDisciplina, String codTurma) {
        try {
            String codDisciplina = lerDisciplina();
            if (cadDisciplina.pesquisar(codDisciplina) == null) {
                JOptionPane.showMessageDialog(null, "Código de disciplina inválido, disciplina não encotrada.");
                return null;
            }
            String codProfessor = lerProfessor();
            if (cadProfessor.pesquisar(codProfessor) == null) {
                JOptionPane.showMessageDialog(null, "Matrícula de professor inválida, professor não encotrado.");
                return null;
            }
            int vagas = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade de vagas:"));
            return new Turma(codTurma, codDisciplina, codProfessor, vagas);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    /**
     * Método utilizado para ler o código de uma Disciplina vindo do usuário, lança a 
     * exceção {@code DisciplinaNaoAtribuidaException} caso o input esteja vazio
     * @return A String lida
     * @throws DisciplinaNaoAtribuidaException caso o input esteja vazio
     */
    private static String lerDisciplina() {
        String disciplina = JOptionPane.showInputDialog("Informe o código da disciplina da turma:").trim();
        if (disciplina.isEmpty()) {
            throw new DisciplinaNaoAtribuidaException();
        }
        return disciplina;
    }

    /**
     * Método utilizado para ler a matrícula de um Professor vindo do usuário, lança a 
     * exceção {@code ProfessorNaoAtribuidoException} caso o input esteja vazio
     * @return A String lida
     * @throws ProfessorNaoAtribuidoException caso o input esteja vazio
     */
    private static String lerProfessor() {
        String professor = JOptionPane.showInputDialog("Informe a mátricula do professor da turma:").trim();
        if (professor.isEmpty()) {
            throw new ProfessorNaoAtribuidoException();
        }
        return professor;
    }


    /**
     * Método utilizado para apresentar as informações de uma turma, isto é: seu código, professor responsável,
     * disciplina, total de alunos matriculados e lista de presença
     * @param t Turma que será apresentada
     * @param cadP Cadastro de professores, será utilizado para obter o nome do Professor
     * @param cadD Cadastro de disciplinas, será utilizado para obter o nome da Disciplina
     */
    private static void mostrarTurma(Turma t, Cadastro<Professor> cadP, Cadastro<Disciplina> cadD) {
        Professor p = cadP.pesquisar(t.getCodProfessor());
        String professor = "Professor fora do cadastro. Por favor atualize a turma.";
        if (p != null) {
            professor = p.getNome() + " - " + p.getMatriculaFUB();
        }

        Disciplina d = cadD.pesquisar(t.getCodDisciplina());
        String disciplina = "Disciplina fora do cadastro. Por favor atualize a turma.";
        if (d != null) {
            disciplina = d.getNome() + " - " + d.getCodigo();
        }
        
        String txt = "Código da turma: " + t.getCodTurma() + '\n' +
                     "Professor: " + professor + '\n' +
                     "Disciplina: " + disciplina + '\n' +
                     "Total de alunos: " + t.getAlunos().size() + "/" + t.getVagas() + '\n' +
                     "Lista de presença:\n";

        for (Aluno a : t.getAlunos()) {
            txt += a.getNome() + " - " + a.getMatricula() + '\n';
        }

        JOptionPane.showMessageDialog(null, txt);
    }

    /**
	 * Menu que dá acesso ao cadastro de professores, permite a execução de operações
	 * de cadastro, pesquisa, atualização e remoção de turmas pelo usuário. Além disso,
     * permite a matrícula e remoção de alunos
	 * @param cadT Cadastro de turmas que será acessado pelo menu
     * @param cadP Cadastro de professores que será acessado pelo menu
     * @param cadD Cadastro de disciplinas que será acessado pelo menu
     * @param cadA Cadastro de alunos que será acessado pelo menu
	 */
    public static void menuTurma(Cadastro<Turma> cadT,Cadastro<Professor> cadP, 
                                 Cadastro<Disciplina> cadD, Cadastro<Aluno> cadA) {
        String txt = "Informe a opção desejada\n" +
					 "1 - Cadastrar turma\n" +
					 "2 - Pesquisar turma\n" +
					 "3 - Atualizar turma\n" +
					 "4 - Remover turma\n" +
                     "5 - Matricular Aluno\n" +
                     "6 - Remover Aluno\n" + 
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
                    Turma turma = dadosNovaTurma(cadP, cadD);
                    if (turma != null) {
                        if (cadT.cadastrar(turma.getCodTurma() ,turma)) {
                            JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Uma turma com o código informado já existe no cadastro.");
                        }
                    }
                break;

                case 2:
                    try {
                        String codigo = lerDado("turma", "Informe o código da turma: ");
                        Turma t = cadT.pesquisar(codigo);
                        if (t != null) {
                            mostrarTurma(t, cadP, cadD);
                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não encontrado.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                break;

                case 3:
                    try {
                        String codigo = lerDado("turma", "Informe o código da turma: ");
                        Turma t = dadosNovaTurma(cadP, cadD, codigo);
                        if (t == null) {
                            break;
                        }
                        boolean atualizado = cadT.atualizar(codigo, t);
                        if (atualizado) {
                            JOptionPane.showMessageDialog(null, "Cadastro atualizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }   
                break;

                case 4:
                    try {
                        String codigo = lerDado("turma", "Informe o código da turma:");
                        boolean removido = cadT.remover(codigo);
                        if (removido) {
                            JOptionPane.showMessageDialog(null, "Turma removida do cadastro.");
                            System.gc();
                        } else {
                            JOptionPane.showMessageDialog(null, "Turma não encontrada.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                break;

                case 5:
                    try {
                        String codigo = lerDado("turma", "Informe o código da turma: ");
                        Turma t = cadT.pesquisar(codigo);
                        if (t == null) {
                            JOptionPane.showMessageDialog(null, "Turma não encontrado.");
                            break;
                        }
                        String matricula = lerDado("matricula", "Informe a matricula do aluno: ");
                        Aluno a = cadA.pesquisar(matricula);
                        if (a == null) {
                            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                            break;
                        }
                        if (t.matricularAluno(a)) {
                            JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Aluno já matriculado ou turma cheia.");
                        }
                    } catch (CampoEmBrancoException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                break;

                case 6:
                    try {
                        String codigo = lerDado("turma", "Informe o código da turma: ");
                        Turma t = cadT.pesquisar(codigo);
                        if (t == null) {
                            JOptionPane.showMessageDialog(null, "Turma não encontrado.");
                            break;
                        }
                        String matricula = lerDado("matricula", "Informe a matricula do aluno: ");
                        if (t.removerAluno(matricula)) {
                            JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
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
