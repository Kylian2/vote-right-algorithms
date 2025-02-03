package fr.voteright.algorithms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.voteright.algorithms.AlgorithmsApplication;
import fr.voteright.algorithms.utils.HttpTools;

import java.util.ArrayList;

public class Community {
    @JsonProperty("CMY_id_NB")
    private int id;

    @JsonProperty("CMY_name_VC")
    private String name;

    @JsonProperty("CMY_description_TXT")
    private String description;

    @JsonProperty("CMY_nb_member_NB")
    private int numberOfMembers;

    @JsonIgnore()
    @JsonProperty("CMY_image_VC")
    private String CMY_image_VC;

    @JsonIgnore()
    @JsonProperty("CMY_emoji_VC")
    private String CMY_emoji_VC;

    @JsonIgnore()
    @JsonProperty("CMY_color_VC")
    private String CMY_color_VC;

    @JsonIgnore()
    @JsonProperty("CMY_creator_NB")
    private String CMY_creator_NB;

    @JsonProperty("CMY_budget_NB")
    private float budget;

    @JsonProperty("CMY_used_budget_NB")
    private float usedBudget;

    @JsonProperty("CMY_fixed_fees_NB")
    private float fixedFees;

    @JsonProperty("CMY_budget_theme_NB")
    private ArrayList<Theme> themes;

    Community() {}

    public Community(Community community) {
        this.id = community.id;
        this.name = community.name;
        this.description = community.description;
        this.numberOfMembers = community.numberOfMembers;
        this.budget = community.budget;
        this.usedBudget = community.usedBudget;
        this.fixedFees = community.fixedFees;
        this.themes = new ArrayList<>();
        for(Theme theme : community.getThemes()){
            this.themes.add(new Theme(theme));
        }
    }

    public static Community getCommunity(int community, int period){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String request = HttpTools.get(AlgorithmsApplication.baseUrl + "/communities/" + community );
            Community cmy = objectMapper.readValue(request, new TypeReference<>() {});

            request = HttpTools.get(AlgorithmsApplication.baseUrl + "/algo/communities/" + community + "/budget?period=" + period);
            Community budget = objectMapper.readValue(request, new TypeReference<>() {});

            cmy.themes = budget.getThemes();
            cmy.budget = budget.budget;
            cmy.usedBudget = budget.usedBudget;
            cmy.fixedFees = budget.fixedFees;
            return cmy;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }
}
