package e.nickl.projektaccountregistration;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A class fields that extends LinearLayout.
 * Creates different fields based on input type.
 */
public class Fields extends LinearLayout
{

    private Context context;
    private String name;
    private boolean required, valid=false;
    private String inputType;
    private EditText editText;
    private TextView textView;
    private CheckBox checkBox;

    public Fields(Context context, String name, boolean required, String inputType)
    {
        super(context);
        this.context = context;
        this.name = name;
        this.required = required;
        this.inputType = inputType.toLowerCase();
        init();
    }

    /**
     * Method to initialize the account registration
     */
    public void init()
    {
            // Inflates XML file (new_field) to a LinearLayout
            LinearLayout layout = (LinearLayout) inflate(context, R.layout.new_field, null);
            // Finds and creates the views from the XML file
            editText = layout.findViewById(R.id.editTextField);
            textView = layout.findViewById(R.id.textField);
            checkBox = layout.findViewById(R.id.checkBox);

            if (required)
            {
                textView.setText(name + "*");
            }
            else
            {
                textView.setText(name);
            }
            // Switch statement to determine what input type to use for
            // the EditTextField.
            switch (inputType)
            {
                case "text":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case "password":
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    break;
                case "number":
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case "email":
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;
            }

            editText.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {

                }

                @Override
                public void afterTextChanged(Editable editable)
                {
                    // Checks if the input is valid and sets a
                    // checkbox visible with color green if the
                    // input is required and valid
                    String temp = editable.toString();
                    isValidInput(temp, inputType);
                    if(valid == false && required){
                        checkBox.setChecked(false);
                        checkBox.setVisibility(INVISIBLE);
                    }
                    if(valid == true && required)
                    {
                        checkBox.setChecked(true);
                        checkBox.setVisibility(VISIBLE);
                    }

                }
            });
        // Adds the layout to the main layout
        addView(layout);
    }

    /**
     * Method to evalute if the input is valid depending on different input types
     * @param input String with the input data from the field
     * @param inputType String with the fields input type
     * @return boolean true or false depending on valid input or not.
     */
    public boolean isValidInput(String input, String inputType)
    {
        // Pattern to use when validating email input
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        switch (inputType)
        {
            case "text":
                if(input.length() > 2)
                    valid = true;
                else
                    valid = false;
                break;
            case "password":
                if(input.length() > 8)
                    valid = true;
                else
                    valid = false;
                break;
            case "number":
                if(input.length()> 0)
                    valid = true;
                else
                    valid = false;
                break;
            case "email":
                if(input.matches(emailPattern))
                    valid = true;
                else
                    valid = false;
                break;
            default:
                    valid=false;
                    break;
        }
        return false;
    }

    /**
     * Method to return if the input is valid or not
     * @return boolean value true or false
     */
    public boolean isValid()
    {
        return valid;
    }

    /**
     * Method to see if the input field is required to be filled or not.
     * @return boolean value true or false
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * Method to get the field name
     * @return String with the name of the field
     */
    public String getName()
    {
        return name;
    }

    /**
     * Method to get the input text from the field
     * @return String with the current input of the field
     */
    public String getInputText()
    {
        return editText.getText().toString();
    }

    /**
     * Method to set the clear the input field
     */
    public void setEmpty()
    {
        editText.setText("");
    }

}
