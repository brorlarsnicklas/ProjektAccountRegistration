package e.nickl.projektaccountregistration;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nickl on 2017-12-12.
 */

public class AccountRegForm extends LinearLayout {

    private Context context;
    String textField = "text", numberField = "number", emailField = "email", passwordField = "password";
    private boolean registerComplete = false;
    private EditText editText;
    private TextView textView;
    private List<Fields> fieldList;
    private HashMap<String, String> registrationData;


    public AccountRegForm(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);
        fieldList = new ArrayList<>();
        registrationData = new HashMap<String, String>();
    }

    public void addField(Context context, String name, boolean required, String inputType)
    {
        Fields fields = new Fields(context, name, required, inputType);
        addView(fields);
        fieldList.add(fields);
    }

    public void registerAccount()
    {
        for(int i = 0; i < fieldList.size(); i++){
            if(fieldList.get(i).isRequired() && !fieldList.get(i).isValid())
            {
                showMessage("Must fill all required fields! \nRequired fields are marked with a * .");
                registerComplete = false;
                break;
            }
            else
            {
                String tempName;
                String tempText;
                tempName = fieldList.get(i).getName();
                tempText = fieldList.get(i).getInputText();

                Log.e(tempName,tempText);
                registrationData.put(tempName,tempText);
                registerComplete = true;
            }
        }
        if(registerComplete)
        {
            clearFields();
            showMessage("Account registration successful!");

        }
    }

    public void showMessage(String message)
    {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void clearFields()
    {
        for(int i = 0; i < fieldList.size(); i++)
        {
            fieldList.get(i).setEmpty();
        }
    }

    public HashMap<String,String> getRegistrationData()
    {
        return registrationData;
    }
}
