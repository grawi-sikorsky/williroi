package pl.sikor.williroi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.account.AccountModel;

@Service
public class AccountService {

    private String apiAddress = "https://api.helium.io/v1/";
    
    public AccountModel getAccountFromAPI(){
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress+"/accounts/12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9", String.class);
        AccountModel account = new AccountModel();

        try {
            account = mapper.readValue(rawJson, AccountModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("ACCOUNT DETAILS: ");
        System.out.println(account.balance);
        System.out.println();
        
        return account;
    }

    public getHotspotsFromApi
}
