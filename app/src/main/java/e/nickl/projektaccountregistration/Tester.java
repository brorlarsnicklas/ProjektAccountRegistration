package e.nickl.projektaccountregistration;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by nickl on 2017-12-17.
 */

public class Tester extends Fields {


    @Override
    public void init() {
        super.init();
    }

    @Override
    public boolean isValidInput(String input) {
        return super.isValidInput(input);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

    @Override
    public boolean isRequired() {
        return super.isRequired();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getInputText() {
        return super.getInputText();
    }

    @Override
    public void setEmpty() {
        super.setEmpty();
    }

    @Override
    public EditText getEditextField() {
        return super.getEditextField();
    }

    @Override
    public TextView getTextViewField() {
        return super.getTextViewField();
    }
    // Sets the color of the text in TextView to green
    public Tester(Context context, String name, boolean required, String inputType) {
        super(context, name, required, inputType);
        getTextViewField().setTextColor(Color.GREEN);
    }


}
