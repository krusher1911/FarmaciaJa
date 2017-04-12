package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import Entidades.Produto;
import adapters.ProdutoAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;

public class Act_Produto extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    String nomeFarmacia;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_produto);

        Intent myIntent = getIntent();
        nomeFarmacia = myIntent.getStringExtra("nomeFarmacia");

        //map
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nomeFarmacia);

        RecyclerView rv = (RecyclerView) findViewById(R.id.produto_recycler_view);

        //creating sample data
        List<Produto> produtosList = new ArrayList<>();
        int qt_produtos = 50;
        Produto[] lista_produto = new Produto[qt_produtos];
        for (int i = 0; i < qt_produtos; i++) {
            lista_produto[i] = new Produto(i, 1, "Produto " + i, "Nome produto " + i, (double) i);
            produtosList.add(lista_produto[i]);
        }

        //setting produto adapter
        ProdutoAdapter pa = new ProdutoAdapter(produtosList);
        rv.setAdapter(pa);

        //Layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        populateView();

    }

    public void populateView() {

        TextView t = (TextView) findViewById(R.id.textView_titulo_farmacia);
        t.setText(nomeFarmacia);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));

    }
}
