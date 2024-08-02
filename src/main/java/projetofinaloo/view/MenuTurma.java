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

public class MenuTurma extends Menu {
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

    private static String lerDisciplina() {
        String disciplina = JOptionPane.showInputDialog("Informe o código da disciplina da turma:");
        if (disciplina.isEmpty()) {
            throw new DisciplinaNaoAtribuidaException();
        }
        return disciplina;
    }

    private static String lerProfessor() {
        String professor = JOptionPane.showInputDialog("Informe a mátricula do professor da turma:");
        if (professor.isEmpty()) {
            throw new ProfessorNaoAtribuidoException();
        }
        return professor;
    }

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
            String strOpcao = JOptionPane.showInputDialog(txt);
            opcao = Integer.parseInt(strOpcao);

            switch (opcao) {
                case 0:
                break;

                case 1:
                    Turma turma = dadosNovaTurma(cadP, cadD);
                    if (turma != null) {
                        cadT.cadastrar(turma.getCodTurma() ,turma);
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
                        String codigo = lerDado("turma", "Informe o código da turma:");;
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
