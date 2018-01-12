import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SaveCustomer extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_customer);
//get the inputed data
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String custName = b.getString("CustName");
        String custAddr = b.getString("CustAddr");
        String custPhone = b.getString("CustPhone");
        char custGender = b.getChar("CustGender");
        DBAdapter db = new DBAdapter(this
        try {
            db.open();
            long id = db.insertCustomer(custName,custAddr,custGender,custPhone);
            Toast
                    .makeText(this, "Data successfully saved", Toast.LENGTH_SHORT)
                    .show();
        }
        catch(Exception ex) {
            Toast
                    .makeText(this, "Saving error", Toast.LENGTH_SHORT)
                    .show();
        }
        finally {
            db.close();
            Button btnBack = (Button) findViewById(R.id.Button01);
            btnBack.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(SaveCustomer.this, MainActivity.class);
                    startActivity(i);
                }
            } );
        }
    }
}
