package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Theme {

    @JsonProperty("THM_id_NB")
    private int id;

    @JsonProperty("THM_name_VC")
    private String name;

    @JsonProperty("BUT_amount_NB")
    private float budget;

    @JsonProperty("BUT_used_budget_NB")
    private float usedBudget;

    public Theme() {}

    public Theme(Theme theme) {
        this.id = theme.id;
        this.name = theme.name;
        this.budget = theme.budget;
        this.usedBudget = theme.usedBudget;
    }

    public float getBudget() {
        return budget;
    }

    public float getUsedBudget() {
        return usedBudget;
    }

    public void useBudget(float amount){
        if(amount + usedBudget <= budget){
            this.usedBudget += amount;
        }
    }
}
