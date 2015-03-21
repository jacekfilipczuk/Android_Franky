package your.applicazione;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class VisualizzaRicerca extends Activity{

	private Activity frame;
	
    /** Called when the activity is first created. */
    private ListView listview;
    private ArrayList<Tupla> mListItem;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizza_ricerca);
        frame=this;
        
        LinearLayout layout = (LinearLayout) findViewById(R.id.dettagli);
        
        String nazione="";
        String citta="";
        String nomeprodotto="";
        String categoria ="";
        mListItem=new ArrayList<Tupla>();
        Bundle extras = getIntent().getExtras();
        
        //SALVO I DATI RICEVUTI DALL'ACTIVITY PRECEDENTE
        if(extras!=null){
        	nazione=extras.getString("nazione");
        	citta=extras.getString("citta");
        	nomeprodotto=extras.getString("nomeprodotto");
        	categoria=extras.getString("spinner");
        }
        
        /****RICHIESTA AL SERVER*****/
        try{
	    	 SAXBuilder builder=new SAXBuilder("org.ccil.cowan.tagsoup.Parser");
	    	 URL url = new URL("http://frankybdsir.altervista.org/Server/server.php?azione=cerca&nazione="+nazione+"&citta="+citta+"&categoria="+categoria+"&nomeprodotto="+nomeprodotto);
	    	 Document doc = builder.build(url);
	    	 Element risultati=doc.getRootElement();
	    	 List<Element> listaChildren=risultati.getChildren();//Lista elementi xml ovvero le topole con le info
	    	 Iterator<Element> iterator = listaChildren.iterator();
	    	 int flag=0;//vale zero se il risultato della ricerca è nullo
	    	 TextView intestazione=new TextView(this);
	    	 intestazione.setText("Nome Locale-Voto");
	    	 intestazione.setGravity(0x11);
	    	 intestazione.setTextSize(24);
	    	 intestazione.setEllipsize(TruncateAt.END);
    		 layout.addView(intestazione);
	    	 while(iterator.hasNext()){
	    		 flag=1;
	    		 Element cur=iterator.next();
	    		 List<Element> elementi=cur.getChildren();
	    		 Log.d("bdsir","Nazione:"+elementi.get(0).getValue() );
	    		 Log.d("bdsir","Citta:"+elementi.get(1).getValue() );
	    		 Log.d("bdsir","Indirizzo:"+elementi.get(2).getValue() );
	    		 Log.d("bdsir","Nome Locale:"+elementi.get(3).getValue() );
	    		 Log.d("bdsir","Categoria:"+elementi.get(4).getValue() );
	    		 Log.d("bdsir","Nome Prodotto:"+elementi.get(5).getValue() );
	    		 Log.d("bdsir","Voto medio:"+elementi.get(6).getValue() );
	    		 Log.d("bdsir","Numero votanti:"+elementi.get(7).getValue() );
	    		 
	    		 nazione=elementi.get(0).getValue();
	    		 citta=elementi.get(1).getValue();
	    		 String indir=elementi.get(2).getValue().trim();
	    		 String nome=elementi.get(3).getValue().trim();//Recupero il contenuto del tag Nome Locale nell'xml
	    		 categoria=elementi.get(4).getValue();
	    		 nomeprodotto=elementi.get(5).getValue();
	    		 String voto = elementi.get(6).getValue().trim();
	    		 String numVotant=elementi.get(7).getValue().trim();
	    		 
	    		 final Tupla t = new Tupla(nazione, citta, indir, nome, categoria, nomeprodotto, Float.parseFloat(voto), Integer.parseInt(numVotant));
	    		 mListItem.add(t);
	    		 
	    		 /***CREO BOTTONE CON IL NOME LOCALE E IL VOTO***/
	    		 Button btn = new Button(this); 
	    	     btn.setText(nome+" "+voto);
	    	     btn.setEllipsize(TruncateAt.END); //DOVREBBE RENDERE I BOTTONI TUTTI UGUALI
	    	    // btn.setLayoutParams(ViewGroup.LayoutParams.FILL_PARENT); Per fare i bottoni di larghezza uguale
	    	    
	    	     btn.setOnClickListener(new View.OnClickListener()
	    	        {
	    	        	public void onClick(View v){
	    	        		Intent intent =new Intent(frame, VisualizzaDettagli.class);
	    	        		intent.putExtra("dati", t.getAll());
	    	        		startActivity(intent);
	    	        	}
	    	        }
	    	     );
	    	     layout.addView(btn);
	    	   
	    	 }
	    	 if(flag==0){
	    		 TextView mess=new TextView(this);
	    		 mess.setText("Nessuna informazione trovata!");
	    		 mess.setGravity(0x11);
	    		 mess.setTextSize(24);
	    		 mess.setEllipsize(TruncateAt.END);
	    		 layout.addView(mess);
	    	 }
	     }catch(Exception e){
	    	 e.printStackTrace();
	    	 Intent intent = new Intent(frame, Conferma.class);
             intent.putExtra("mess", "Errore durante la connessione.Riprovare");
 			 startActivity(intent);
     		
	     }
        
        
    }
 
   
    

        
        
}


