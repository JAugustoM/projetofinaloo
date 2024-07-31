package projetofinaloo.app;

public class Aluno extends PessoaFisica{

	String matricula, 
	       curso;
	
	public Aluno(String nome, String cpf, String email, String matricula, String curso) {
		super(nome, cpf, email);
		this.matricula = matricula;
		this.curso = curso;
	}

	public final String getMatricula() {
		return matricula;
	}

	public final String getCurso() {
		return curso;
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Destruindo objeto: \n" + this);
	}
	
	@Override
	public String toString() {
		String resposta = super.toString();
		resposta += "MATRICULA: " + matricula + '\n';
		resposta += "CURSO: " + curso + '\n';
		return resposta;
	}

}
