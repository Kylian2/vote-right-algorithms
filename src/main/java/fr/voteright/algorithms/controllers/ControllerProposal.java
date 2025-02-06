package fr.voteright.algorithms.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.voteright.algorithms.AlgorithmsApplication;
import fr.voteright.algorithms.algorithms.Greedy;
import fr.voteright.algorithms.models.Community;
import fr.voteright.algorithms.models.Proposal;
import fr.voteright.algorithms.utils.HttpTools;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ControllerProposal {

    @GetMapping(value="/proposals", produces = "application/json")
    public ResponseEntity<String> getProposalsOf(@RequestParam int community, @RequestParam int period) throws Exception {

        String request = HttpTools.get(AlgorithmsApplication.baseUrl + "/algo/communities/" + community + "/proposals/formatted?period=" + period);
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Proposal> proposals = objectMapper.readValue(request, new TypeReference<>() {});

        Community cmy = Community.getCommunity(community, period);

        Greedy greedy = new Greedy();
        try{
            ArrayList<Proposal> bestProposals = greedy.maximizeTotalSatisfaction(proposals, new Community(cmy)).toArrayList();
            return ResponseEntity.ok(objectMapper.writeValueAsString(bestProposals));
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}
