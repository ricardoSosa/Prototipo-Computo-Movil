package com.example.riki.gameapp.Model;

/**
 * Created by RIKI on 13/10/2017.
 */

public class Videogame {
    private String titleName;
    private String description;
    private String[] category;
    private String[] releaseDate;
    private String image;
    private String originalPriceSteam;
    private String discountSteam;
    private String finalPriceSteam;
    private String originalPriceHB;
    private String discountHB;
    private String finalPriceHB;

    public Videogame(String[] gameInfo) {
        this.titleName = gameInfo[0];
        this.description = gameInfo[1];
        this.category = gameInfo[2].split(",");
        this.releaseDate = gameInfo[3].split(",");
        this.image = gameInfo[4];
        this.originalPriceSteam = gameInfo[5];
        this.discountSteam = gameInfo[6];
        this.finalPriceSteam = gameInfo[7];
        this.originalPriceHB = gameInfo[8];
        this.discountHB = gameInfo[9];
        this.finalPriceHB = gameInfo[10];
    }

    public String getTitleName() {
        return titleName;
    }

    public String getDescription() {
        return description;
    }

    public String[] getCategories() {
        return category;
    }

    public String getCategory(int index) {
        return category[index];
    }

    public String[] getReleaseDate() {
        return releaseDate;
    }

    public String getImage() {
        return image;
    }

    public String getOriginalPriceSteam() {
        return originalPriceSteam;
    }

    public String getDiscountSteam() {
        return discountSteam;
    }

    public String getFinalPriceSteam() {
        return finalPriceSteam;
    }

    public String getOriginalPriceHB() {
        return originalPriceHB;
    }

    public String getDiscountHB() {
        return discountHB;
    }

    public String getFinalPriceHB() {
        return finalPriceHB;
    }

}
