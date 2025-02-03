package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Reaction {

    @JsonProperty("like")
    private ArrayList<Integer> like;
    @JsonProperty("dislike")
    private ArrayList<Integer> dislike;

    public Reaction() {}

    public ArrayList<Integer> getLike() {
        return like;
    }
}
