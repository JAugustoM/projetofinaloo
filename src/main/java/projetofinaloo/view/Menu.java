package projetofinaloo.view;

import javax.swing.JOptionPane;

import projetofinaloo.exceptions.CampoEmBrancoException;

public abstract class Menu {
    protected static String lerDado(String campo ,String mensagem) {
        String dado = JOptionPane.showInputDialog(mensagem);
        if (dado.isEmpty()) {
            throw new CampoEmBrancoException("Campo " + campo + " em branco.");
        } else {
            return dado;
        }
    }
}
