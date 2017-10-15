package com.example.riki.gameapp.Fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.riki.gameapp.Model.Videogame;
import com.example.riki.gameapp.R;

import java.util.ArrayList;

/**
 * Created by RIKI on 12/10/2017.
 */

public class OffersFragment extends SectionFragment {

    protected ArrayList<Videogame> gamesInOffer;

    public OffersFragment() {
        gamesInOffer = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.generic_section, container, false);

        String sectionName = "Ofertas";
        filterOffers();
        setSection(sectionName, rootView, gamesInOffer);

        return rootView;
    }

    private void filterOffers() {
        for(int i = 0; i < this.videogames.size(); i++) {
            if(!this.videogames.get(i).getDiscountSteam().equals("0") || !this.videogames.get(i).getDiscountHB().equals("0")) {
                this.gamesInOffer.add(this.videogames.get(i));
            }
        }
    }

    @Override
    public ArrayList<Videogame> getVideogames() {
        return gamesInOffer;
    }
}
