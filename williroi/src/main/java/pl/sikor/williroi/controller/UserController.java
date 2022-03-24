package pl.sikor.williroi.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.account.AccountModel;
import pl.sikor.williroi.model.account.hotspot.Hotspot;

@RestController
public class UserController {

    @GetMapping("/")
    public void staradupa(){
        String uri = "https://api.helium.io/v1/accounts/12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9/";

        RestTemplate restTemplate = new RestTemplate();

        AccountModel account = restTemplate.getForObject(uri, AccountModel.class);
        
        AccountModel[] forNow = restTemplate.getForObject(uri, AccountModel[].class);
        //account = Arrays.asList(forNow);
        


        System.out.println("https://api.helium.io/v1/hotspots/112WxrNMPwkFBhHTSQmkqMe1VKoCd3zzPVNU5DFziyAjBf61iyfH/rewards/sum?min_time=-120%20day&bucket=day");
        System.out.println(account.hotspot_count);
        System.out.println(account.balance);
    }

    
}
