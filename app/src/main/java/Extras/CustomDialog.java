package Extras;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.farmaciaja.una.tidir.farmaciaja.R;

/**
 * Created by bravo3465 on 26/10/15.
 */
public class CustomDialog extends DialogFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.custom_dialog, null);

    }
}
