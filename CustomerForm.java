import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CustomerForm extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_form);
        Button btnEducation = (Button) findViewById(R.id.Save);
        btnEducation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//validate data entries
                String custName = ((EditText) findViewById(R.id.CustName)).getText().toString().trim();
                String custAddr = ((EditText) findViewById(R.id.CustAddr)).getText().toString().trim();
                String custPhone = ((EditText) findViewById(R.id.CustPhone)).getText().toString().trim();
                char custGender = 'X';
                switch (((RadioGroup) findViewById(R.id.CustGender)).getCheckedRadioButtonId()) {
                    case R.id.GMale:
                        custGender='M';
                        break;
                    case R.id.GFemale:
                        custGender='F';
                        break;
                }
                Context context=CustomerForm.this;
                if(custName.equals("") || custAddr.equals("") || custPhone.equals("") || custGender == 'X') {
                    String e = "Please complete the data.";
                    new AlertDialog.Builder(context)
                            .setTitle("Invalid Data")
                            .setMessage(e)
                            .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub
                                }
                            }).show();
                }
                else {
//If OK, then send the data to save
                    Intent i = new Intent(CustomerForm.this, SaveCustomer.class);
                    i.putExtra("CustName", custName);
                    i.putExtra("CustAddr", custAddr);
                    i.putExtra("CustPhone", custPhone);
                    i.putExtra("CustGender", custGender);
                    startActivity(i);
                }
            }
        } );
    }


}
