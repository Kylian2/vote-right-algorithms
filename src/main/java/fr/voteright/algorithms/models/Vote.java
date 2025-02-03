package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Vote {

    @JsonProperty("system")
    private int system;
    @JsonProperty("positive")
    private ArrayList<Integer> positive;
    @JsonProperty("negative")
    private ArrayList<Integer> negative;

    public Vote() {}

    public ArrayList<Integer> getPositive() {
        return positive;
    }

    public ArrayList<Integer> getNegative() {
        return negative;
    }
}
