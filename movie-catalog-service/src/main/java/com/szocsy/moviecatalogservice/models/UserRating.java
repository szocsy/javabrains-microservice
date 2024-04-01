package com.szocsy.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    List<Rating> userRating;
    private List<Rating> userRating(){
        return userRating;
    }

    public void setUserRating(List<Rating> userRating){
        this.userRating = userRating;
    }
    public List<Rating> getUserRating(){
        return userRating;
    }
}
