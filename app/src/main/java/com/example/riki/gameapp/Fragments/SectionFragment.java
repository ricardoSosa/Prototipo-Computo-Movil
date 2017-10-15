package com.example.riki.gameapp.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.riki.gameapp.Activities.MainActivity;
import com.example.riki.gameapp.Adapters.GamesListAdapter;
import com.example.riki.gameapp.Model.Videogame;
import com.example.riki.gameapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by RIKI on 13/10/2017.
 */

public class SectionFragment extends android.support.v4.app.Fragment {

    protected GridView gamesListGV;
    protected ArrayList<Videogame> videogames;

    public SectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.generic_section, container, false);

        return rootView;
    }

    public void setVideogames(ArrayList<Videogame> videogames) {
        this.videogames = videogames;
    }

    protected void setSection(String sectionName, View view, ArrayList<Videogame> videogames) {
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ERASDEMI.TTF");
        setSectionName(sectionName, view);
        setFontLabels(custom_font, view);
        setAdapters(view);
        setGridView(view, videogames);
        setListeners();
    }

    private void setSectionName(String name, View view) {
        TextView sectionName = (TextView) view.findViewById(R.id.section_name);
        sectionName.setText(name);
    }

    private void setFontLabels(Typeface custom_font, View view) {
        TextView sectionName = (TextView) view.findViewById(R.id.section_name);
        sectionName.setTypeface(custom_font);

        //Filters
        TextView categoriesLabel = (TextView) view.findViewById(R.id.categories_label);
        categoriesLabel.setTypeface(custom_font);
        TextView priceLabel = (TextView) view.findViewById(R.id.price_label);
        priceLabel.setTypeface(custom_font);
    }

    private void setAdapters(View view) {
        final Spinner categories_spinner = (Spinner) view.findViewById(R.id.categories_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> categories_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.categories_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        categories_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        categories_spinner.setAdapter(categories_adapter);

        categories_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = categories_spinner.getSelectedItem().toString();
                if(category.equals("Sin filtro")) {
                    gamesListGV.invalidateViews();
                    GamesListAdapter newAdapter = new GamesListAdapter(getVideogames(), getActivity());
                    gamesListGV.setAdapter(newAdapter);
                } else {
                    ArrayList<Videogame> games = getVideogames();
                    ArrayList<Videogame> filterGames = new ArrayList<>();
                    for (int i = 0; i < games.size(); i++) {
                        for (int e = 0; e < games.get(i).getCategories().length; e++){
                            if(games.get(i).getCategory(e).equals(category)) {
                                filterGames.add(games.get(i));
                            }
                        }
                    }
                    gamesListGV.invalidateViews();
                    GamesListAdapter newAdapter = new GamesListAdapter(filterGames, getActivity());
                    gamesListGV.setAdapter(newAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner prices_spinner = (Spinner) view.findViewById(R.id.prices_spinner);
        ArrayAdapter<CharSequence> prices_adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.prices_array, R.layout.spinner_item);
        prices_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        prices_spinner.setAdapter(prices_adapter);

        prices_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String prices = prices_spinner.getSelectedItem().toString();
                if(prices.equals("Sin filtro")) {
                    gamesListGV.invalidateViews();
                    GamesListAdapter newAdapter = new GamesListAdapter(getVideogames(), getActivity());
                    gamesListGV.setAdapter(newAdapter);
                } else if (prices.equals("Ascendente")){
                    ArrayList<Videogame> games = (ArrayList<Videogame>) getVideogames().clone();
                    ArrayList<String> pricesStr = new ArrayList<>();
                    ArrayList<Videogame> filterGames = new ArrayList<>();
                    for (int i = 0; i < games.size(); i++){
                        pricesStr.add(games.get(i).getFinalPriceSteam());
                    }
                    Collections.sort(pricesStr);
                    for (int e = 0; e < pricesStr.size(); e++) {
                        for (int x = 0; x < games.size(); x++) {
                            if (games.get(x).getFinalPriceSteam().equals(pricesStr.get(e))) {
                                filterGames.add(games.get(x));
                                games.remove(x);
                            }
                        }
                    }
                    gamesListGV.invalidateViews();
                    GamesListAdapter newAdapter = new GamesListAdapter(filterGames, getActivity());
                    gamesListGV.setAdapter(newAdapter);
                } else if (prices.equals("Descendente")) {
                    ArrayList<Videogame> games = (ArrayList<Videogame>) getVideogames().clone();
                    ArrayList<String> pricesStr = new ArrayList<>();
                    ArrayList<Videogame> filterGames = new ArrayList<>();
                    for (int i = 0; i < games.size(); i++){
                        pricesStr.add(games.get(i).getFinalPriceSteam());
                    }
                    Collections.sort(pricesStr);
                    for (int e = pricesStr.size()-1; e >= 0; e--) {
                        for (int x = 0; x < games.size(); x++) {
                            if (games.get(x).getFinalPriceSteam().equals(pricesStr.get(e))) {
                                filterGames.add(games.get(x));
                                games.remove(x);
                            }
                        }
                    }
                    gamesListGV.invalidateViews();
                    GamesListAdapter newAdapter = new GamesListAdapter(filterGames, getActivity());
                    gamesListGV.setAdapter(newAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setGridView(View view, ArrayList<Videogame> videogames) {
        gamesListGV = (GridView) view.findViewById(R.id.grid_list);
        GamesListAdapter adapter = new GamesListAdapter(videogames, getActivity());
        gamesListGV.setAdapter(adapter);
    }

    private void setListeners() {
        gamesListGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity act = (MainActivity)getActivity();
                act.visualizeDetailedInfo((Videogame) parent.getItemAtPosition(position));
            }

        });
    }

    public ArrayList<Videogame> getVideogames() {
        return this.videogames;
    }
}
