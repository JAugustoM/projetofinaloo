package projetofinaloo.app;

public class Disciplina {
    private String nome,
                   codigo,
                   cargaHoraria;

    public Disciplina(String nome, String codigo, String cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCargaHoraria() {
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
