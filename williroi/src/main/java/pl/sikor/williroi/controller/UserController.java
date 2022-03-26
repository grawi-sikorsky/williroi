package pl.sikor.williroi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.account.AccountModel;

@RestController
public class UserController {

    @GetMapping("/")
    public void staradupa() {
        String uri = "https://api.helium.io/v1/accounts/12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9/";

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(uri, String.class);
        AccountModel account = new AccountModel();

        try {
            account = mapper.readValue(rawJson, AccountModel.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("ACCOUNT DETAILS: ");
        System.out.println(account.balance);
        System.out.println();
    }

}
