package pl.sikor.williroi.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @GetMapping("/")
    public void staradupa(){
        String uri = "https://api.helium.io/v1/hotspots/112WxrNMPwkFBhHTSQmkqMe1VKoCd3zzPVNU5DFziyAjBf61iyfH/rewards/sum?min_time=-120%20day&bucket=day";

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);
        System.out.println("https://api.helium.io/v1/hotspots/112WxrNMPwkFBhHTSQmkqMe1VKoCd3zzPVNU5DFziyAjBf61iyfH/rewards/sum?min_time=-120%20day&bucket=day");
        System.out.println(result);
    }

    
}
