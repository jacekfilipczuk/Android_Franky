package your.applicazione;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

public class Inserisci extends Activity{
	
	private Activity frame;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserimento);
        frame =this;
        
        final Spinner categoria = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categorie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoria.setAdapter(adapter);
        final EditText nazione=(EditText)findViewById(R.id.nazionetext);
        final EditText citta=(EditText)findViewById(R.id.cittatext);
        final EditText indirizzo =(EditText)findViewById(R.id.indirizzotext);
        final EditText luogo =(EditText)findViewById(R.id.nomelocaletext);
        final EditText nomeprodotto =(EditText)findViewById(R.id.nomeprodottotext);
        final RatingBar voto = (RatingBar)findViewById(R.id.ratingBar1);
        

        /****GEOLOCATION******/
        LocationManager locationManager;
        Geocoder geocoder;
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE); 
        geocoder = new Geocoder(this);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); 
        try{
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
            if(!addresses.isEmpty()){
            	nazione.setText(addresses.get(0).getCountryName());
            	citta.setText(addresses.get(0).getLocality());
            	indirizzo.setText(addresses.get(0).getAddressLine(0));

            }
        }catch(IOException e){
        	e.printStackTrace();
        };
        
        /******BOTTONE INSERIMENTO*******/
        final Button inserimento = (Button)findViewById(R.id.button2);
        inserimento.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		String mess="";
        		 /*****INVIO DATI AL SERVER************/
                try{
        	    	 SAXBuilder builder=new SAXBuilder("org.ccil.cowan.tagsoup.Parser");
        	    	 Log.d("bd","inizio");
        	    	 
        	    	 Log.d("bdsir","Nazione:"+nazione.getText().toString() );
    	    		 Log.d("bdsir","Citta:"+citta.getText().toString() );
    	    		 Log.d("bdsir","Indirizzo:"+indirizzo.getText().toString());
    	    		 Log.d("bdsir","Nome Locale:"+luogo.getText().toString() );
    	    		 Log.d("bdsir","Categoria:"+categoria.getSelectedItem().toString() );
    	    		 Log.d("bdsir","Nome Prodotto:"+nomeprodotto.getText().toString() );
    	    		 Log.d("bdsir","Voto medio:"+Float.toString(voto.getRating()) );
    	    		 
    	    		 
        	    	 
        	    	 
        	    	 URL url = new URL("http://frankybdsir.altervista.org/Server/server.php?azione=inserimento&nazione=" +
        	    	 		""+nazione.getText().toString().replace(" ","_" )+"&citta="+citta.getText().toString().replace(" ","_" )+"&indirizzo="+indirizzo.getText().toString().replace(" ","_" )+"" +
        	    	 				"&luogo="+luogo.getText().toString().replace(" ","_" )+"&nomecategoria="+categoria.getSelectedItem().toString().replace(" ","_" )+"&nomeprodotto=" +
        	    	 						""+nomeprodotto.getText().toString().replace(" ","_" )+"&voto="+Integer.toString((int)voto.getRating()));
        	    	 Document doc = builder.build(url); 
        	    	 Element risultati=doc.getRootElement();
        	    	 Log.d("bd","root element"+risultati.toString());
        	    	 List<Element> listaChildren=risultati.getChildren();//Lista elementi xml ovvero le topole con le info
        	    	 Log.d("bd","children"+listaChildren.toString());

        	    	 Element cur=listaChildren.get(0);
        	    	 Log.d("bd","figliolo"+cur.toString());
        	    	 mess=cur.getValue();
        	    	 Log.d("bd","Messaggio dal server: "+cur.getValue());

        	    	 AlertDialog.Builder dialog = new AlertDialog.Builder(frame); //CREO IL DIALOG CON IL MESSAGGIO DI RISPOSTA DEL SERVER
        	    	 dialog.setMessage(mess);
        	    	 dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
        	    	      public void onClick(DialogInterface dialog, int which) {  
        	    	    	   
        	    	    } });   
        	    	 
        	    	 dialog.create();
        	    	 Log.d("bd","fine");
        	     }catch(Exception e){
        	    	 e.printStackTrace();
        	    	 mess="Errore durante la connessione al server, riprovare!";
        	     }
                Intent intent = new Intent(frame, Conferma.class);
                intent.putExtra("mess", mess);
    			startActivity(intent);
        		
        		
        			
        		
        	}
        });
        
        
       
}
}
