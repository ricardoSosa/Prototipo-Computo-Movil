package com.example.riki.gameapp.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.riki.gameapp.Model.Videogame;
import com.example.riki.gameapp.R;

/**
 * Created by RIKI on 12/10/2017.
 */

public class DetailedInfoFragment extends Fragment {

    protected Videogame videogame;

    public DetailedInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.detailed_information, container, false);

        Button backButton = (Button) rootView.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ERASDEMI.TTF");

        TextView titleName = (TextView) rootView.findViewById(R.id.title_name);
        titleName.setText(videogame.getTitleName());
        titleName.setTypeface(custom_font);

        ImageView titleImage = (ImageView) rootView.findViewById(R.id.title_image);
        String imageUrl = this.videogame.getImage();
        Glide.with(rootView.getContext()).load(imageUrl).into(titleImage);

        TextView description = (TextView) rootView.findViewById(R.id.description);
        description.setText(videogame.getDescription());
        description.setTypeface(custom_font);

        String discSteam = "";
        if (!videogame.getDiscountSteam().equals("0")) {
            discSteam = " - " + videogame.getDiscountSteam();
        }
        TextView priceSteam = (TextView) rootView.findViewById(R.id.price_steam);
        priceSteam.setText("Steam: " + "$" + videogame.getFinalPriceSteam() + discSteam);
        priceSteam.setTypeface(custom_font);

        String discHB = "";
        if (!videogame.getDiscountHB().equals("0")) {
            discHB = " - " + videogame.getDiscountHB();
        }
        TextView priceHb = (TextView) rootView.findViewById(R.id.price_hb);
        priceHb.setText("Humble: " + "$" + videogame.getFinalPriceHB() + discHB);
        priceHb.setTypeface(custom_font);

        return rootView;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }
}
