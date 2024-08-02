package projetofinaloo.cadastros;

import java.util.HashMap;


/**
 * Classe parametrizada que implementa todos os cadastros usados na aplicação
 * e seus métodos, possue um HashMap como único atributo responsável
 * por armazenar todos os objetos de cada cadastro.
 * @author José Augusto
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html">HashMap JavaDoc</a>
*/
public class Cadastro<E> {
    private HashMap<String, E> cadastro;

    public Cadastro() {
        cadastro = new HashMap<>();
    }

	/**
	 * Adiciona um novo registro no cadastro.
	 * @param chave - String que identifica o objeto
	 * @param e - Objeto a ser cadastrado 
	 * @return O número de registros no cadastro
	 */
    public int cadastrar(String chave ,E e) {
		cadastro.put(chave, e);
		return cadastro.size();
	}

	/**
	 * Retorna o objeto identificado pela chave passada como argumento ou null
	 * caso a chave não exista no HashMap.
	 * @param chave - String que identifica um objeto no cadastro
	 * @return Objeto no cadastro a qual corresponde a chave especificada, ou null caso o objeto não seja encontrado
	 */
    public E pesquisar(String chave) {
		E resposta =  null;
		if (cadastro.containsKey(chave)) {
			resposta = cadastro.get(chave);
		}
		return resposta;
	}

	/**
	 * Remove um objeto do cadastro, recebe a chave que identifica o objeto 
	 * no cadastro como argumento.
	 * @param chave - String que identifica um objeto no cadastro
	 * @return <code>true</code> se o objeto especificado foi removido, <code>false</code> caso o contrário.
	 */
    public boolean remover(String chave) {
		if (cadastro.containsKey(chave)) {
			cadastro.remove(chave);
			return true;
		}
		return false;
	}

	/**
	 * Substitui um objeto no cadastro por outro, mantendo-se a chave que o identifica,
	 * dessa forma "atualizando" o registro no cadastro. Retorna <code>true</code> caso 
	 * a operação ocorra com sucesso, <code>false</code> caso o contrário.
	 * @param chave - String que identifica um objeto no cadastro
	 * @param e - Objeto que irá substituir o antigo
	 * @return <code>true</code> se o objeto especificado foi atualizado com suceesso, 
	 * <code>false</code> caso o contrário
	 */
    public boolean atualizar(String chave, E e) {
		if (cadastro.containsKey(chave)) {
			cadastro.replace(chave, e);
			return true;
		}
		return false;
	}
}
