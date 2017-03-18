package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import DAL.FarmaciaDal;
import Entidades.Farmacia;
import adapters.FarmaciasAdapter;
import Extras.RecyclerViewFarmacias;
import fragments.frag_navigator;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class Act_farmacias extends AppCompatActivity
{
    //DAL
    public FarmaciaDal farmaciaDal;

    //ENTIDADES
    public Farmacia farmacia;

    //VARIAVEIS
    public LinkedList listFarmacias;

    //OBJETOS
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_farmacias);

        //DAL
        farmaciaDal = new FarmaciaDal(this);

        //ENDTIDADES
        farmacia = new Farmacia();

        //VARIAVEIS
        listFarmacias = new LinkedList();

        //TOOLBAR
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //NAVIGATION DRAWER
        frag_navigator navigationDrawer = (frag_navigator)getSupportFragmentManager().findFragmentById(R.id.fragment);
        navigationDrawer.setUp((DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        criarRecylerView();
    }

    private void criarRecylerView()
    {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list_farmacias);

        FarmaciasAdapter adapter = new FarmaciasAdapter(this, this.carregarItensMenu());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public List<RecyclerViewFarmacias> carregarItensMenu()
    {
        List<RecyclerViewFarmacias> slideMenuItens = new ArrayList<>();

        int[] icons = {R.drawable.account_circle, R.drawable.account_circle, R.drawable.account_circle };
        String[] titulos = {"Araujo", "Pacheco", "Droga Clara"};
        String[] notas = {"4.6", "3.9" , "4,3"};
        String[] tempo = {"30", "25", "35"};
        boolean[] situacao = {true, false, true};

        for (int i = 0; i < icons.length; i++)
        {
            RecyclerViewFarmacias item = new RecyclerViewFarmacias();

            item.setIconId(icons[i]);
            item.setNomeFarmacia(titulos[i]);
            item.setMediaNota(notas[i]);
            item.setMediaTempo(tempo[i]);
            item.setAberto(situacao[i]);

            slideMenuItens.add(item);
        }

        return slideMenuItens;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_act_farmacias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.action_settings)
        {
            return true;
        }
        else if(id == R.id.procurar)
        {

        }
        else if(id == R.id.action_map)
        {
            intent = new Intent(this, Act_Map.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
