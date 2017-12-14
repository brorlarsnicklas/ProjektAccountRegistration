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
 * Created by nickl on 2017-12-13.
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

    public Fields(Context context, String name)
    {
        super(context);
        this.context = context;
        this.name = name;
    }

    public void init()
    {
        LinearLayout layout = (LinearLayout) inflate(context, R.layout.new_field, null);

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

        addView(layout);
    }

    public boolean isValidInput(String input, String inputType)
    {
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

    public boolean isValid()
    {
        return valid;
    }

    public boolean isRequired()
    {
        return required;
    }

    public String getName()
    {
        return name;
    }

    public String getInputText()
    {
        return editText.getText().toString();
    }

    public void setEmpty()
    {
        editText.setText("");
    }

}
