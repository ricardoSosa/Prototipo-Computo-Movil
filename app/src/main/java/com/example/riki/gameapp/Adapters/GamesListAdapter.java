package com.example.riki.gameapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.riki.gameapp.Model.Videogame;
import com.example.riki.gameapp.R;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by RIKI on 13/10/2017.
 */

public class GamesListAdapter extends BaseAdapter {

    protected ArrayList<Videogame> videogames;
    protected Activity activity;

    public GamesListAdapter(ArrayList<Videogame> videogames, Activity activity) {
        this.videogames = videogames;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return videogames.size();
    }

    @Override
    public Object getItem(int position) {
        return videogames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.game_item, null);
        }

        Videogame videogame = videogames.get(position);

        ImageView gameImage = (ImageView) v.findViewById(R.id.gameImage);
        String imageUrl = videogame.getImage();
        Glide.with(v.getContext()).load(imageUrl).into(gameImage);

        Typeface custom_font = Typeface.createFromAsset(activity.getAssets(), "fonts/ERASDEMI.TTF");

        String discSteam = "";
        if (!videogame.getDiscountSteam().equals("0")) {
            discSteam = " - " + videogame.getDiscountSteam();
        }
        TextView priceSteam = (TextView) v.findViewById(R.id.price_steam);
        priceSteam.setText("Steam: " + "$" + videogame.getFinalPriceSteam() + discSteam);
        priceSteam.setTypeface(custom_font);

        String discHB = "";
        if (!videogame.getDiscountHB().equals("0")) {
            discHB = " - " + videogame.getDiscountHB();
        }
        TextView priceHb = (TextView) v.findViewById(R.id.price_hb);
        priceHb.setText("Humble: " + "$" + videogame.getFinalPriceHB() + discHB);
        priceHb.setTypeface(custom_font);

        TextView categories = (TextView) v.findViewById(R.id.categories);
        categories.setText("Categorías: " + Arrays.toString(videogame.getCategories()));
        categories.setTypeface(custom_font);

        return v;
    }
}
