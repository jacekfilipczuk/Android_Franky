package your.applicazione;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {
	
	
	
	private Activity frame;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        frame =this;
        
        final Button cerca = (Button)findViewById(R.id.button1);
        final Button inserisci= (Button)findViewById(R.id.button2);
        
        //Bottone cerca
        cerca.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v){
        		Intent intent = new Intent(Main.this, Cerca.class);
				startActivity(intent);
		}
        	
        		
        		
        });
        
        //Bottone inserisci
        inserisci.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v){
        		Intent intent = new Intent(frame, Inserisci.class);
				startActivity(intent);
		}
        	
        		
        		
        });
        	
        

        
        
    }
}