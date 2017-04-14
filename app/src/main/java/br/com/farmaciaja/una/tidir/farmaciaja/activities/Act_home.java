package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.os.Bundle;
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
import Extras.RecyclerViewFarmacias;
import adapters.FarmaciasAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import fragments.frag_navigator;

public class Act_home extends AppCompatActivity
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
        setContentView(R.layout.act_home);

        //DAL
        farmaciaDal = new FarmaciaDal(this);

        //popularFarmacias();

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

    private void popularFarmacias() {
        Farmacia farmacia = new Farmacia();

        for (int i = 0; i < 10; i++) {
            farmacia.setDescFarmacia("Farmacia DB " + i);
            farmacia.setEndereco("Endereco DB " + i);
            farmacia.setInformacoesFarmacia("Info " + i);
            farmacia.setMediaNotaAtendimento((float) i);
            farmacia.setMediaTempoEntrega((float) (60 - i));
            farmaciaDal.salvarNovaFarmacia(farmacia);

        }


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
        List<RecyclerViewFarmacias> farmaciaMenuItens = new ArrayList<>();

        int[] icons = {R.drawable.account_circle};


        LinkedList farmaciaList = new LinkedList();
        farmaciaList = farmaciaDal.buscarFarmacias();
        Farmacia tempFarmacia;

        for (int i = 0; i < farmaciaList.size(); i++)
        {
            RecyclerViewFarmacias item = new RecyclerViewFarmacias();
            tempFarmacia = (Farmacia) farmaciaList.get(i);

            item.setIconId(icons[0]);
            item.setIdFarmacia(tempFarmacia.get_id());
            item.setNomeFarmacia(tempFarmacia.getDescFarmacia());
            item.setMediaNota(Float.toString(tempFarmacia.getMediaNotaAtendimento()));
            item.setMediaTempo(Float.toString(tempFarmacia.getMediaTempoEntrega()));
            item.setAberto(true);

            farmaciaMenuItens.add(item);
        }

        return farmaciaMenuItens;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_act_home, menu);
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
