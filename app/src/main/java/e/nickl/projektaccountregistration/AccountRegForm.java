package e.nickl.projektaccountregistration;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Creates a Account registration form that extends LinearLayout with options to:
 * Add text fields with different input types.
 * Register accounts where it saves the data.
 * Show messages.
 * Clear text in fields.
 * Retrieve registration data.
 */
public class AccountRegForm extends LinearLayout
{
    private Context context;
    // Different input types
    String textField = "text", numberField = "number", emailField = "email", passwordField = "password";
    // Boolean to check if the registration was completed with valid input.
    private boolean registerComplete = false;
    // List with the fields
    private List<Fields> fieldList;
    // Map with the registration data
    private HashMap<String, String> registrationData;

    public AccountRegForm(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;

        // Sets the layout orientation to vertical
        setOrientation(VERTICAL);

        // Creates a ArrayList to contain the fields from the layout
        fieldList = new ArrayList<>();

        // Creates a HashMap to store registration data.
        registrationData = new HashMap<String, String>();
    }

    /**
     * Creates a field with chosen input type and adds it to the layout.
     * @param context
     * @param name  the name of the field, shown in the textView
     * @param required boolean to define if a field requires valid input or not
     * @param inputType string to define the input of the text field
     */
    public void addField(Context context, String name, boolean required, String inputType)
    {
        Fields fields = new Fields(context, name, required, inputType);
        addView(fields);
        fieldList.add(fields);
    }

    /**
     * Evaluates if all required fields are valid, returns an
     * error message if the input is invalid.
     * Else registers the account and saves the data to a HashMap.
     */
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
                registrationData.put(fieldList.get(i).getName(),fieldList.get(i).getInputText());
                registerComplete = true;
            }
        }
        // Toast message for successful registration and clears all the fields.
        if(registerComplete)
        {
            clearFields();
            showMessage("Account registration successful!");
        }
    }

    /**
     * Method to show a message
     * @param message takes a string with the message to show
     */
    public void showMessage(String message)
    {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Method to clear the fields from text.
     *
     */
    public void clearFields()
    {
        for(int i = 0; i < fieldList.size(); i++)
        {
            fieldList.get(i).setEmpty();
        }
    }
    
    /**
     * Method to get the registration data from the account registration
     */
    public HashMap<String,String> getRegistrationData()
    {
        return registrationData;
    }
}
