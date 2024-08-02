package projetofinaloo.exceptions;

/**
 * Exceção lançada quando um professor válido não é atribuido durante a
 * criação de uma turma
 * @author José Augusto
 */
public class ProfessorNaoAtribuidoException extends RuntimeException {
    public ProfessorNaoAtribuidoException() {
        super("Uma turma não pode ser criada sem um professor");
    }
}
