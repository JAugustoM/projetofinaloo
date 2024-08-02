package projetofinaloo.app;

public class Disciplina {
    private String nome,
                   codigo;
    private int cargaHoraria;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString() {
        String txt = "";
        txt += "NOME: " + nome + '\n' +
               "CÓDIGO: " + codigo + '\n' +
               "CARGA HORÁRIA: " + cargaHoraria + "h\n";
        return txt;
    }
}
