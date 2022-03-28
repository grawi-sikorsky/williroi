package pl.sikor.williroi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.heliumAPI.account.AccountHotspots;
import pl.sikor.williroi.model.heliumAPI.account.AccountModel;
import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;

// account 12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9

@Service
public class AccountService {

    private String apiAddress = "https://api.helium.io/v1/";
    
    public AccountModel getAccountFromAPI(){
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress+"accounts/12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9", String.class);
        AccountModel account = new AccountModel();

        try {
            account = mapper.readValue(rawJson, AccountModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("ACCOUNT DETAILS: ");
        System.out.println("Account balance [DC]: " + account.balance);
        Double hntbal;
        hntbal = Double.valueOf(account.balance) / 100000000;
        System.out.println("Account balance [HNT]: " + hntbal);
        System.out.println();
        
        return account;
    }

    public void getAccountHotspotsFromApi(String accountID){
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress + "accounts/" + accountID + "/hotspots", String.class);
        Hotspot[] hotspots;
        AccountHotspots accHot;
        List<Hotspot> listhotspot;

        try {
            //hotspots = mapper.readValue(rawJson, Hotspot[].class);
            //List<Hotspot> hotspotsArray = new ArrayList<Hotspot>(Arrays.asList(hotspots));
            //accHot = mapper.readValue(rawJson, new TypeReference<List<Hotspot>>(){});
            //hotspotsArray.forEach(System.out::println);
            listhotspot = mapper.readValue(rawJson, new TypeReference<List<Hotspot>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        

    }
}
