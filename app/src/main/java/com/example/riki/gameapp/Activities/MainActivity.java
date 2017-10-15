package com.example.riki.gameapp.Activities;

import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.riki.gameapp.Fragments.AllFragment;
import com.example.riki.gameapp.Fragments.DetailedInfoFragment;
import com.example.riki.gameapp.Fragments.FeaturedFragment;
import com.example.riki.gameapp.Fragments.OffersFragment;
import com.example.riki.gameapp.Model.Videogame;
import com.example.riki.gameapp.R;
import com.google.android.gms.games.video.Video;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.example.riki.gameapp.Fragments.ImportFragment;
import com.example.riki.gameapp.Fragments.MainFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    SupportMapFragment sMapFragment;
    android.support.v4.app.FragmentManager sFm;
    protected ArrayList<Videogame> videogames;

    public MainActivity() {
        videogames = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sMapFragment = SupportMapFragment.newInstance();
        initiateVideogames();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sFm = getSupportFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fm = getFragmentManager();
        FeaturedFragment ff = new FeaturedFragment();
        ff.setVideogames(this.videogames);
        sFm.beginTransaction().replace(R.id.content_frame, ff).addToBackStack(null).commit();

        sMapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //Search button
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if (existsVideogame(query)) {
                    DetailedInfoFragment dif = new DetailedInfoFragment();
                    dif.setVideogame(getVideogame(query));
                    sFm.beginTransaction().replace(R.id.content_frame, dif).addToBackStack(null).commit();
                } else {
                    Toast.makeText(getApplicationContext(), "Videojuego no existente", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();

        int id = item.getItemId();

        if (sMapFragment.isAdded())
            sFm.beginTransaction().hide(sMapFragment).commit();

        if (id == R.id.nav_featured) {
            FeaturedFragment ff = new FeaturedFragment();
            ff.setVideogames(this.videogames);
            sFm.beginTransaction().replace(R.id.content_frame, ff).addToBackStack(null).commit();
        } else if (id == R.id.nav_offers) {
            OffersFragment of = new OffersFragment();
            of.setVideogames(this.videogames);
            sFm.beginTransaction().replace(R.id.content_frame, of).addToBackStack(null).commit();
        } else if (id == R.id.nav_all) {
            AllFragment af = new AllFragment();
            af.setVideogames(this.videogames);
            sFm.beginTransaction().replace(R.id.content_frame, af).addToBackStack(null).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    public void visualizeDetailedInfo(Videogame videogame) {
        DetailedInfoFragment detailedInfoFragment = new DetailedInfoFragment();
        detailedInfoFragment.setVideogame(videogame);
        sFm.beginTransaction().replace(R.id.content_frame, detailedInfoFragment).addToBackStack(null).commit();
    }

    private void initiateVideogames() {
        Resources res = getResources();

        addVideogame(res.getStringArray(R.array.cuphead));
        addVideogame(res.getStringArray(R.array.nba2k18));
        addVideogame(res.getStringArray(R.array.vallHalla));
        addVideogame(res.getStringArray(R.array.projectCars2));
        addVideogame(res.getStringArray(R.array.theWitcher3));
        addVideogame(res.getStringArray(R.array.cryptOfTheNecroDancer));
    }

    private void addVideogame(String[] gameInfo) {
        this.videogames.add(new Videogame(gameInfo));
    }

    private boolean existsVideogame(String name) {
        boolean exists = false;
        for(int i = 0; i < videogames.size(); i++) {
            if(this.videogames.get(i).getTitleName().equals(name)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    private Videogame getVideogame(String name) {
        Videogame videogame = null;
        for(int i = 0; i < videogames.size(); i++) {
            if(this.videogames.get(i).getTitleName().equals(name)) {
                videogame = this.videogames.get(i);
                break;
            }
        }

        return videogame;
    }

}
