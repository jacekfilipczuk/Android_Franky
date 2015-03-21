package your.applicazione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Conferma extends Activity{
	
	private Activity frame;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conferma);
        frame =this;
        Bundle extras = getIntent().getExtras();
        TextView testo = (TextView)findViewById(R.id.conferma1);
        testo.setText(extras.getString("mess"));
        
        Button home1=(Button)findViewById(R.id.home1);
        home1.setOnClickListener( new View.OnClickListener(){
        	public void onClick(View v){
        	
        		Intent intent = new Intent(frame, Main.class);
    			startActivity(intent);
        	}
        	
        	});
        
    }
    
}
