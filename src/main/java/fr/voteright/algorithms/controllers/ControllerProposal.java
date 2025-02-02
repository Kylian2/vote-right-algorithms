package fr.voteright.algorithms.controllers;

import fr.voteright.algorithms.AlgorithmsApplication;
import fr.voteright.algorithms.utils.HttpTools;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerProposal {

    @GetMapping(value="/proposals", produces = "application/json")
    public String getProposalsOf(@RequestParam int community, @RequestParam int period) throws Exception {
        return HttpTools.get(AlgorithmsApplication.baseUrl+"/algo/communities/"+community+"/proposals/formatted?period="+period);
    }

}
