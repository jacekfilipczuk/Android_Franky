package your.applicazione;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Cerca extends Activity{
	
	
	
	private Activity frame;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ricerca);
        frame =this;
        
        /****Collego i vari text field alle rispettive variabili****/
        EditText nazione=(EditText)findViewById(R.id.nazionetext);
        EditText citta=(EditText)findViewById(R.id.cittatext);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categorie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        /***FINE COLLEGAMENTO***/
        
        /****GEOLOCATION******/
        LocationManager locationManager;
        Geocoder geocoder;
        locationManager = (LocationManager)this.getSystemService(LOCATION_SERVICE); 
        geocoder = new Geocoder(this);
        LocationListener listener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				
			}
		};
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,listener );
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); 
        
        try{
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
            if(!addresses.isEmpty()){ // Riempio automaticamente i campi nazione e citta presi dalla geolocation
            	nazione.setText(addresses.get(0).getCountryName());
            	citta.setText(addresses.get(0).getLocality());
            }
        }catch(IOException e){
        	e.printStackTrace();
        };
        /***FINE GEOLOCATION****/
        
        
        //Bottone cerca
        final Button cerca = (Button)findViewById(R.id.button2);
        cerca.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v){
        		/***RECUPERO GLI INPUT INSERITI DALL'UTENTE***/
        		 EditText nazione=(EditText)findViewById(R.id.nazionetext);
        		 EditText citta=(EditText)findViewById(R.id.cittatext);
        		 EditText nomeprodotto=(EditText)findViewById(R.id.nomeprodottotext);
        	     Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        	     /***FINE RECUPERO***/
        	     
        		/*********************PASSO I PARAMETRI ALLA PROSSIMA ACTIVITY**********************/
        		Intent intent = new Intent(frame, VisualizzaRicerca.class);
        		intent.putExtra("nazione", nazione.getText().toString());
        		intent.putExtra("citta", citta.getText().toString());
        		intent.putExtra("nomeprodotto", nomeprodotto.getText().toString());
        		intent.putExtra("spinner", spinner.getSelectedItem().toString());
				startActivity(intent);;
				/****FINE PASSAGGIO****/
		}
        	
        		
        		
        });

}
}

