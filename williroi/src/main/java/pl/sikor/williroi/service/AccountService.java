package pl.sikor.williroi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.account.AccountHotspots;
import pl.sikor.williroi.model.heliumAPI.account.AccountModel;
import pl.sikor.williroi.model.heliumAPI.account.hotspot.Hotspot;
import pl.sikor.williroi.respository.UserRepository;

// account 12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9

@Service
public class AccountService {

    private String apiAddress = "https://api.helium.io/v1/";
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final UserRepository userRepository;
    
    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AccountModel getAccountFromAPI(String helium_account_address){
        
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress+"accounts/"+ helium_account_address, String.class);
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

    public List<Hotspot> getAccountHotspotsFromApi(String accountID) throws RuntimeException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress + "accounts/" + accountID + "/hotspots", String.class);
        Hotspot[] hotspots;
        AccountHotspots accHot;
        List<Hotspot> listhotspot;
        UserModel user = userRepository.findByUsername("username");

        try {
            //hotspots = mapper.readValue(rawJson, Hotspot[].class);
            //List<Hotspot> hotspotsList = new ArrayList<Hotspot>(Arrays.asList(hotspots));
            //accHot = mapper.readValue(rawJson, new TypeReference<List<Hotspot>>(){});

            listhotspot = mapper.reader().forType(new TypeReference<List<Hotspot>>(){}).withRootName("data").readValue(rawJson);
            hotspots = mapper.reader().forType(Hotspot[].class).withRootName("data").readValue(rawJson);

            for (Hotspot hotspot : hotspots) {
                System.out.println("\033[0;33m" + "==========");
                logger.info(hotspot.address);
                logger.info(hotspot.location);
                logger.info(hotspot.elevation.toString());
                logger.info(hotspot.gain.toString());
                System.out.println("\033[0m" + "==========");
            }
            return listhotspot;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
