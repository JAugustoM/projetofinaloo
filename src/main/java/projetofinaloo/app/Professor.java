package projetofinaloo.app;

public class Professor extends PessoaFisica{
	
	String areaFormacao, 
	       matriculaFUB; 
	
	public Professor(String nome, String cpf, String email, String areaFormacao, String matriculaFUB) {
		super(nome, cpf, email);
		this.areaFormacao = areaFormacao;
		this.matriculaFUB = matriculaFUB;
	}

	public final String getAreaFormacao() {
		return areaFormacao;
	}

	public final String getMatriculaFUB() {
		return matriculaFUB;
	}

	@Override
	public String toString() {
		String resposta = super.toString();
		resposta += "FORMAÇÃO: " + areaFormacao + '\n';
		resposta += "MATRICULA: " + matriculaFUB + '\n';
		return resposta;
	}
}
