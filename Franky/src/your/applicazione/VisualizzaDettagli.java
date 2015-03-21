package your.applicazione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VisualizzaDettagli extends Activity{

	private Activity frame;

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.visualizza_dettagli);
	        frame=this;
	        Bundle extras = getIntent().getExtras();
	        String[] info=extras.getStringArray("dati");
	        //info={nazione,citta,indirizzo,nomeLocale,categoria,nomeProdotto,mediaVoti,numVotanti}
	        
	        TextView indirizzo =(TextView)findViewById(R.id.indirizzo);
	        TextView luogo =(TextView)findViewById(R.id.nomelocale);
	        TextView nomeprodotto =(TextView)findViewById(R.id.nomeprodotto);
	        TextView categoria =(TextView)findViewById(R.id.categoria);
	        TextView votanti =(TextView)findViewById(R.id.votanti);
	        TextView media =(TextView)findViewById(R.id.media);
	        indirizzo.setText(" "+info[2]);
	        luogo.setText(" "+info[3]);
	        categoria.setText(info[4]);
	        nomeprodotto.setText(info[5]);
	        media.setText(" "+info[6]);
	        votanti.setText(" "+info[7]);
	        
	        Button home = (Button)findViewById(R.id.home);
	        home.setText("Home");
	        home.setOnClickListener(new View.OnClickListener()
	        {
	        	public void onClick(View v){
	        		Intent intent =new Intent(frame, Main.class);
	        		startActivity(intent);
	        	}
	        }
	     );
	 }
	 
}
