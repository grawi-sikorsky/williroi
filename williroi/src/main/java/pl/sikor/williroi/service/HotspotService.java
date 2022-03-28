package pl.sikor.williroi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;

// 11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa cricket

@Service
public class HotspotService {
    private String apiAddress = "https://api.helium.io/v1/";

    public Hotspot getHotspotFromApi(String hotspotID){
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress + "/hotspots/" + hotspotID, String.class);
        Hotspot hotspot = new Hotspot();

        try {
            hotspot = mapper.readValue(rawJson, Hotspot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("HOTSPOT DETAILS: ");
        System.out.println(hotspot.address);
        System.out.println(hotspot.elevation);
        System.out.println(hotspot.gain);
        System.out.println(hotspot.location);
        System.out.println(hotspot.name);
        System.out.println(hotspot.owner);
        System.out.println(hotspot.reward_scale);
        
        return hotspot;
    }
}
