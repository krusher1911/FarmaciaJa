package br.com.farmaciaja.una.tidir.farmaciaja.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Entidades.Produto;
import adapters.ProdutoAdapter;
import adapters.ViewPagerAdapter;
import br.com.farmaciaja.una.tidir.farmaciaja.R;
import fragments.RecyclerView_Produtos;
import fragments.fragment_farmacia_info;

import static br.com.farmaciaja.una.tidir.farmaciaja.R.id.map;

public class Act_Produto extends AppCompatActivity implements OnMapReadyCallback {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;


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
                .findFragmentById(map);
        mapFragment.getMapAsync(this);


        //toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(nomeFarmacia);

        //tablayout

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new RecyclerView_Produtos(), "Produtos");
        viewPagerAdapter.addFragments(new fragment_farmacia_info(), "Informações");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


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
