package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Proposal {

    @JsonProperty("PRO_id_NB")
    private int id;

    @JsonProperty("PRO_title_VC")
    private String title;

    @JsonProperty("PRO_budget_NB")
    private float budget;

    @JsonProperty("PRO_theme_NB")
    private int theme;

    @JsonProperty("vote")
    private Vote vote;

    @JsonProperty("reaction")
    private Reaction reaction;

    public Proposal() {}

}

