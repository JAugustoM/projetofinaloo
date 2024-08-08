package projetofinaloo.view;

import javax.swing.JOptionPane;

import projetofinaloo.exceptions.CampoEmBrancoException;

/**
 * Classe abstrata que define métodos para input de informação utilizados nos menus do projeto
 * @author José Augusto
 */
public abstract class Menu {
    /**
     * Método utilizada para ler uma informação, lança a exceção {@code CampoEmBrancoException}
     * caso o input esteja vazio ou retorna o dado lido caso o input não esteja vazio
     * @param campo A qual campo se refere o input, qual atributo será preenchido
     * @param mensagem Mensagem que será mostrada ao usuário na janela de input
     * @return A String digitada pelo usuário
     * @throws CampoEmBrancoException Se o usuário deixar a caixa de input vazia
     */
    protected static String lerDado(String campo ,String mensagem) {
        String dado = JOptionPane.showInputDialog(mensagem).trim();
        if (dado.isEmpty()) {
            throw new CampoEmBrancoException("Campo " + campo + " em branco.");
        } else {
            return dado;
        }
    }

    /**
     * Método utilizada para ler uma informação, retorna o dado lido caso o input não esteja vazio, 
     * caso contrátio retorna uma String padrão
     * @param campo A qual campo se refere o input, qual atributo será preenchido
     * @param mensagem Mensagem que será mostrada ao usuário na janela de input
     * @param padrao String padrão que será retornada caso o usuário digite nada
     * @return A String digitada pelo usuário ou uma String padrão
     */
    protected static String lerDado(String campo ,String mensagem, String padrao) {
        String dado = JOptionPane.showInputDialog(mensagem).trim();
        if (dado.isEmpty()) {
            return padrao;
        } else {
            return dado;
        }
    }

}
