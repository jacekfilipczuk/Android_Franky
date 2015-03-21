package your.applicazione;

import java.lang.reflect.Array;
import java.util.ArrayList;


//Classe di supporto per contenere una generica tupla del database
public class Tupla {
	private String nazione;
	private String citta;
	private String indirizzo;
	private String nomeLocale;
	private String categoria;
	private String nomeProdotto;
	private float mediaVoti;
	private int numVotanti;
	
	
	public Tupla(){
		nazione="";
		citta="";
		indirizzo="";
		nomeLocale="";
		categoria="";
		nomeProdotto="";
		mediaVoti=0;
		numVotanti=0;
	}
	
	public Tupla(String I_nazione,String I_citta,String I_indirizzo,
			String I_nomeLocale,String I_categoria,String I_nomeProdotto,
			float I_mediaVoti,int I_numVotanti){
		nazione=I_nazione;
		citta=I_citta;
		indirizzo=I_indirizzo;
		nomeLocale=I_nomeLocale;
		categoria=I_categoria;
		nomeProdotto=I_nomeProdotto;
		mediaVoti=I_mediaVoti;
		numVotanti=I_numVotanti;
	
	}
	
	public String getNazione(){
		return nazione;
	}
	
	public String getCitta(){
		return citta;
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}
	
	public String getNomeLocale(){
		return nomeLocale;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public String getNomeProdotto(){
		return nomeProdotto;
	}
	
	public String getMediaVoti(){
		return Float.toString(mediaVoti);
	}
	
	public String getNumVotanti(){
		return Integer.toString(numVotanti);
	}
	
	public String[] getAll(){
		String[] s={nazione,citta,indirizzo,nomeLocale,categoria,nomeProdotto,Float.toString(mediaVoti),Integer.toString(numVotanti)};
		return s;
	}
	
}
