package pl.sikor.williroi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.AccountModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.service.heliumAPI.ApiService;

@CrossOrigin(origins = {"http://localhost:4200","http://10.0.2.2:8080"})
@RestController
@RequestMapping("/user/{username}/api")
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/hotspots")
    public List<Hotspot> getHotspotsFromApi(@PathVariable String username){
        return apiService.getAccountHotspotsFromApi(username);
    }

    @GetMapping("/account")
    public AccountModel getAccountFromApi(@PathVariable String username){
        return apiService.getAccountFromAPI(username);
    }

    @GetMapping("/rewards")
    public List<Hotspot> getHotspotsRewardsFromApi(@PathVariable String username){
        return apiService.getAllHotspotsRewards(new UserModel());
    }
}
