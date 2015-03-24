package br.edu.ufrn.projetotrello;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Organization;


public class TrelloManager {
	private String key = "3bc8d63d3a7dce554fb5c37a0b664036";
	private String secret = "26f335240862810c6ad3f25ec6ad823cb64a83cdd1002eada1232472eefdf180";
	private String token = "ac184af1eab0b1c0a9cdd52aa4cfcc7c1d036c744dcdcaa6fe8d4160c1ae9c17";
	
	private Trello trelloApi;
	
	public TrelloManager(){
		// inicializa o objeto principal da API
		trelloApi = new TrelloImpl(key, null);
	}
	
	/** 
	 * Imprime o nome da organização passada como parâmetro
	 * @param orgName
	 * */
	public void printOrganization(String orgName){
		Organization org = trelloApi.getOrganization(orgName);
		if(org != null)
			System.out.println(org.getDisplayName());
		else
			System.out.println("Public organization didn't found by this name.");
	}
	
	/** 
	 * Imprime os boards da organização passada como parâmetro
	 * @param orgName
	 * */
	public void printBoardsFromOrganization(String orgName){
		for(Board b : trelloApi.getBoardsByOrganization(orgName))
			printBoard(b.getId());
	}
	
	/**
	 *  Imprime informações do board
	 * @param idBoard
	 * */
	public void printBoard(String idBoard){ 
		Board board = trelloApi.getBoard(idBoard);
		if(board != null){
			System.out.println("BOARD NAME: "+board.getName());
			printListsFromBoard(idBoard);
		}else{
			System.out.println("Public board didn't found by this id.");
		}
	}
	
	/**
	 * Imprime todos os lists de um board
	 * @param idBoard
	 * */
	public void printListsFromBoard(String idBoard){ 
		List<org.trello4j.model.List> lists = trelloApi.getListByBoard(idBoard);
		if(lists != null && lists.size()  > 0){
			System.out.println("\tLISTS FROM BOARD: ");
			for(org.trello4j.model.List l : lists){
				System.out.println("\t"+l.getName());
				printCardsFormList(l.getId());
			}
		}
	}
	
	/**
	 * Imprime todos os cards de uma list
	 * @param idList
	 * */
	public void printCardsFormList(String idList){
		List<Card> cards = trelloApi.getCardsByList(idList);
		if(cards != null && cards.size() > 0){
			System.out.println("\t\tCARDS: ");
			for(Card c : cards){
				System.out.println("\t\t"+c.getName()+", "+getCreationDateOfCard(c.getId()));
			}
		}
	}
	
	/** 
	 * Recupera a data de criação do card ou board a partir de seu id
	 * @param id
	 * @return
	 * */
	private String getCreationDateOfCard(String id){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String epochString = id.substring(0, 8);
		long epoch = Long.parseLong( epochString,16);
		Date expiry = new Date( epoch * 1000 );
		return df.format(expiry);
	}
}
