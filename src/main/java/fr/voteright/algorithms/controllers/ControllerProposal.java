package fr.voteright.algorithms.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.voteright.algorithms.AlgorithmsApplication;
import fr.voteright.algorithms.models.Proposal;
import fr.voteright.algorithms.utils.HttpTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class ControllerProposal {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value="/proposals", produces = "application/json")
    public String getProposalsOf(@RequestParam int community, @RequestParam int period) throws Exception {
        String request = HttpTools.get(AlgorithmsApplication.baseUrl + "/algo/communities/" + 13 + "/proposals/formatted?period=2025");
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Proposal> utilisateurs = objectMapper.readValue(request, new TypeReference<ArrayList<Proposal>>() {
        });

        System.out.println(utilisateurs);
        return request;
    }
}
