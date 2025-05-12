package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Reaction {

    @JsonProperty("like")
    private ArrayList<Integer> like;
    @JsonProperty("dislike")
    private ArrayList<Integer> dislike;

    @JsonIgnore()
    @JsonProperty("created_at")
    private String created_at;

    @JsonIgnore()
    @JsonProperty("updated_at")
    private String updated_at;

    public Reaction() {}

    public ArrayList<Integer> getLike() {
        return like;
    }
}
