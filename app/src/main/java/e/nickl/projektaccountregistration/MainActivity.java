package e.nickl.projektaccountregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    AccountRegForm accountReg;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountReg = findViewById(R.id.accountRegForm);
        accountReg.addField(new Fields(this,"Namn", true, accountReg.textField));
        accountReg.addField(new Fields(this, "Email", true, accountReg.emailField));
        accountReg.addField(new Fields(this, "Password", true, accountReg.passwordField));
        accountReg.addField(new Fields(this, "Age", false, accountReg.numberField));

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(true)
                {
                    accountReg.registerAccount();
                    Log.e("DATA", ""+ accountReg.getRegistrationData());
                }
            }
        });



    }


}
