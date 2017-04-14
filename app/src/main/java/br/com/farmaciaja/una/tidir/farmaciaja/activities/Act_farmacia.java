package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import DAL.FarmaciaDal;
import Entidades.Farmacia;
import adapters.ViewPagerAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import fragments.RecyclerView_Produtos;
import fragments.fragment_farmacia_info;

import static br.com.farmaciaja.una.tidir.farmaciaja.R.id.map;

public class Act_farmacia extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    int idFarmacia;
    String nomeFarmacia;
    FarmaciaDal farmaciaDal = new FarmaciaDal(this);
    Farmacia farmacia;
    private TextView txtTempoAtendimento;
    private TextView txtMediaAtendimento;
    private TextView descFarmacia;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_farmacia);

        Intent myIntent = getIntent();
        idFarmacia = Integer.parseInt(myIntent.getStringExtra("idFarmacia"));

        //map
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        populateView();

        //toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(farmacia.getDescFarmacia());

        //tablayout

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new RecyclerView_Produtos(), "Produtos");
        viewPagerAdapter.addFragments(new fragment_farmacia_info(), "Informações");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_farmacia, menu);
        return true;
    }

    public void populateView() {

        double intervaloMedia = 7.5;
        int tempoMediaMin;
        int tempoMediaMax;
        String sMedia;
        String nota;

        farmacia = farmaciaDal.getObjectById(idFarmacia);

        descFarmacia = (TextView) findViewById(R.id.textView_titulo_farmacia);
        descFarmacia.setText(farmacia.getDescFarmacia());

        txtTempoAtendimento = (TextView) findViewById(R.id.txtTempoAtendimento);
        tempoMediaMin = (int) (farmacia.getMediaTempoEntrega() - intervaloMedia);
        tempoMediaMax = (int) (farmacia.getMediaTempoEntrega() + intervaloMedia);
        sMedia = tempoMediaMin + " - " + tempoMediaMax;
        txtTempoAtendimento.setText(sMedia);

        txtMediaAtendimento = (TextView) findViewById(R.id.txtMediaAtendimento);

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

        LatLng latLng = new LatLng(-19.919694, -43.939474);
        map.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Marker"));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.moveCamera(cameraUpdate);
        map.getUiSettings().setScrollGesturesEnabled(false);

    }
}
