package br.edu.ufrn.projetogithub;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitHubManager {
	
	GitHub github;
	String token; 

	public GitHubManager() throws IOException {
		this.github = GitHub.connect();
		this.token = "9820d78f80626b3a6637cb5fe5514059278c33f6";
	}
	
	/** TODO 
	 * consultar milestones
	 * ver issues de milestones fechados: criador, data de criação, quem fechou, data fechado
	 * ver issues de milestones aberto: criador, data de criação, usuario do ultimo evento (desenvolvedor atual)
	 * verificar complexidade (se existir) de cada issue
	 * verificar tipo de issue (label)
	 * numero de issues fechadas e proporção de contribuidores
	 * https://api.github.com/orgs/4soft/repos?access_token=9820d78f80626b3a6637cb5fe5514059278c33f6
	 * */
	public void analisarDistribuicaoTarefasPorMembroEquipe(String organizacao, String repositorio) throws IOException{		
		GitHub.connectUsingOAuth(token);
		GHRepository repoitorio =  github.getOrganization(organizacao).getRepository(repositorio);
		
	}
	
}
