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

    /**
     * Calcule le nombre d'utilisateurs satisfaits en fonction des votes positifs,
     * négatifs et des réactions de like. Un utilisateur est considéré comme satisfait
     * s'il a exprimé un vote positif ou a réagi positivement (en likant) sans avoir
     * exprimé un vote négatif.
     *
     * @return Le nombre d'utilisateurs satisfaits.
     */
    public int satisfiedUser(){

        int likeCount = vote.getPositive().size();


        for(int i = 0; i < reaction.getLike().size(); i++){
            if(!vote.getNegative().contains(reaction.getLike().get(i)) && !vote.getPositive().contains(reaction.getLike().get(i))){
                likeCount++;
            }
        }

        return likeCount;
    }

    public int getTheme() {
        return theme;
    }

    public float getBudget() {
        return budget;
    }
}

