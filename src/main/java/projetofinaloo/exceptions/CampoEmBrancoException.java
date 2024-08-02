package projetofinaloo.exceptions;

/**
 * Exceção que é lançada quando um campo de input é deixado em branco.
 * @author José Augusto 
 */
public class CampoEmBrancoException extends RuntimeException{
    public CampoEmBrancoException(String errorMessage) {
        super(errorMessage);
    }
}
