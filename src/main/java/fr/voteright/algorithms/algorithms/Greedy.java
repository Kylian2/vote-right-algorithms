package fr.voteright.algorithms.algorithms;

import fr.voteright.algorithms.models.Community;
import fr.voteright.algorithms.models.Proposal;
import fr.voteright.algorithms.utils.List;

import java.util.ArrayList;

public class Greedy {

    /**
     * Optimise la sélection des propositions pour maximiser la satisfaction totale de la communauté,
     * tout en respectant les contraintes budgétaires des thèmes. Retourne une liste chaînée des propositions
     * sélectionnées.
     *
     * @param proposals La liste des propositions à examiner.
     * @param community La communauté avec ses membres et ses budgets thématiques.
     * @return Une liste chaînée contenant les propositions sélectionnées pour maximiser la satisfaction. Si aucune n'est selectionnée l'element data de la liste sera null.
     * @throws Exception Si la liste des propositions est vide ou si la communauté est null.
     */
    public List<Proposal> maximizeTotalSatisfaction(ArrayList<Proposal> proposals, Community community) throws Exception {
        if(proposals.isEmpty()){
            throw new Exception("Une liste de proposition non vide est attendue");
        }
        if(community == null || community.getThemes() == null || community.getThemes().isEmpty()){
            throw new Exception("La communauté est null ou contient un element invalide");
        }
        Community cmy = new Community(community); //pour ne pas modifier la liste en paramètre


        //Création d'une liste de proposition respectant les contraintes budgétaires
        ArrayList<Proposal> validProposals = new ArrayList<Proposal>();
        for (Proposal proposal : proposals) {
            int themeIndex = proposal.getTheme() - 1;
            float availableBudget = cmy.getThemes().get(themeIndex).getBudget() - cmy.getThemes().get(themeIndex).getUsedBudget();
            if (proposal.getBudget() <= availableBudget) {
                validProposals.add(proposal);
            }
        }
        if (validProposals.isEmpty()) {
            return new List<>(null);
        }

        //Selection de la meilleur proposition
        List<Proposal> selectedProposals = new List<>(null);
        int best = 0;
        int satisfactionScoreMax = 0;

        for (int i = 0; i < validProposals.size(); i++) {
            int satisfactionScore = validProposals.get(i).satisfiedUser();
            if(satisfactionScore > satisfactionScoreMax){
                satisfactionScoreMax = satisfactionScore;
                best = i;
            }
        }

        selectedProposals.setData(validProposals.get(best));
        cmy.getThemes().get(validProposals.get(best).getTheme()-1).useBudget(validProposals.get(best).getBudget());
        validProposals.remove(best);
        if(!validProposals.isEmpty()){
            selectedProposals.setTail(maximizeTotalSatisfaction(validProposals, cmy));
        }

        return selectedProposals;
    }
}
