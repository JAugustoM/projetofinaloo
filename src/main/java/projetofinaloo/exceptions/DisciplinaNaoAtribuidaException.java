package projetofinaloo.exceptions;

/**
 * Exceção lançada quando uma disciplina válida não é atribuida durante a
 * criação de uma turma
 * @author José Augusto
 */
public class DisciplinaNaoAtribuidaException extends RuntimeException {
    public DisciplinaNaoAtribuidaException() {
        super("Uma turma não pode ser criada sem uma disciplina");
    }
}
