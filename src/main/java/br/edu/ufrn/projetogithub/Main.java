package br.edu.ufrn.projetogithub;
import br.edu.ufrn.projetogithub.GitHubManager;




public class Main {

	public static void main(String[] args)  {
		try{
			GitHubManager gitApi = new GitHubManager();
			gitApi.analisarDistribuicaoTarefasPorMembroEquipe("4soft", "concreto");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
