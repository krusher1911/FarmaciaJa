package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;

import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class DialogPicker extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;
    public Button btnYes, btnNo;

    public DialogPicker(Activity activity) {
        super(activity);
        this.activity = activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.act_dialog_picker);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        String[] nums = new String[20];
        for (int i = 0; i < nums.length; i++)
            nums[i] = Integer.toString(i);

        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(nums);
        np.setValue(1);

        btnYes = (Button) findViewById(R.id.btnSim);
        btnNo = (Button) findViewById(R.id.btnNo);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSim:
                Snackbar mySnackbar = Snackbar.make(activity.findViewById(R.id.coordinatorLayout),
                        R.string.add_carrinho, Snackbar.LENGTH_LONG);
                mySnackbar.show();
                dismiss();
                break;
            case R.id.btnNo:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
