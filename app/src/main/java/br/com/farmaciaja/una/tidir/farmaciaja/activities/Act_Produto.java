package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class Act_Produto extends AppCompatActivity {

    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DialogPicker dialogPicker = new DialogPicker(Act_Produto.this);
                dialogPicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogPicker.show();
            }
        });

    }




}
