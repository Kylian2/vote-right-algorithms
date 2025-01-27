package fr.voteright.algorithms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.JsonObject;


@RestController
public class HomeResource {

    @GetMapping(value="/", produces = "application/json")
    public String index() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("VoteRight", "Algorithmes");
        return jsonObject.toString();
    }

}
