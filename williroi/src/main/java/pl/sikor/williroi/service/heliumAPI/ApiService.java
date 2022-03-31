package pl.sikor.williroi.service.heliumAPI;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import pl.sikor.williroi.model.HotspotDTO;
import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.AccountModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.respository.UserRepository;
import pl.sikor.williroi.service.AccountService;

// HELIUM API: https://docs.helium.com/api/

@Service
public class ApiService {

    private String apiAddress = "https://api.helium.io/v1/";
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final UserRepository userRepository;

    public ApiService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* GET ACCOUNT FROM API */
    public AccountModel getAccountFromAPI(String username) {

        if(userRepository.findByUsername(username) != null && userRepository.findByUsername(username).getHntAccount() != null){
            UserModel user = userRepository.findByUsername(username);

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

            String rawJson = restTemplate.getForObject(apiAddress + "accounts/" + user.getHntAccount(), String.class);

            try {
                user.setApiAccount(mapper.readValue(rawJson, AccountModel.class));
                //user.getApiAccount().setBalance(user.getApiAccount().getBalance()/100000000); // DC to HNT divide
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
    
            userRepository.save(user);
            logger.info("ACCOUNT FETCHED FROM API!!");

            return user.getApiAccount();

        }else{
            throw new RuntimeException("NO SUCH USER, OR NO API ACCOUNT ASIGNED!");
        }        
    }

    /* GET ACCOUNT REWARDS FROM API */
    public AccountModel getAccountRewardsFromAPI(String username) {

        if(userRepository.findByUsername(username) != null && userRepository.findByUsername(username).getHntAccount() != null){
            UserModel user = userRepository.findByUsername(username);

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    
            String[] urls = {   apiAddress + "accounts/" + user.getHntAccount() + "/rewards/sum?min_time=-1%20day",
                                apiAddress + "accounts/" + user.getHntAccount() + "/rewards/sum?min_time=-7%20day",
                                apiAddress + "accounts/" + user.getHntAccount() + "/rewards/sum?min_time=-30%20day",
                                apiAddress + "accounts/" + user.getHntAccount() + "/rewards/sum?min_time=-2000%20day"
                            };
    
            List<String> totals = new ArrayList<String>();
            
            for (int i=0; i < urls.length; ++i) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urls[i]);
                URI uri = builder.build(true).toUri();
    
                JsonNode jsonnode = restTemplate.getForObject(uri, JsonNode.class);
                totals.add(jsonnode.get("data").get("total").asText());
                logger.warn(totals.get(i).toString());
            }
            user.setAcc_reward24(totals.get(0));
            user.setAcc_reward7d(totals.get(1));
            user.setAcc_reward30d(totals.get(2));
            user.setAcc_reward_lifetime(totals.get(3));
    
            userRepository.save(user);
            logger.info("ACCOUNT FETCHED FROM API!!");
    
            return user.getApiAccount();

        }else{
            throw new RuntimeException("NO SUCH USER, OR NO API ACCOUNT ASIGNED!");
        }
    }

    /* GET ACCOUNT HOTSPOTS FROM API */
    public List<HotspotDTO> getAccountHotspotsFromApi(String username) {

        if(userRepository.findByUsername(username) != null && userRepository.findByUsername(username).getHntAccount() != null){
            UserModel user = userRepository.findByUsername(username);

            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
    
            String rawJson = restTemplate.getForObject(apiAddress + "accounts/" + user.getHntAccount() + "/hotspots", String.class);
    
            try {
                user.getHotspots().clear();
                user.getHotspots().addAll( mapper.reader().forType(new TypeReference<List<Hotspot>>() {}).withRootName("data").readValue(rawJson) );
    
                for (Hotspot hotspot : user.getHotspots()) {
    
                    getHotspotRewardsFromAPI(hotspot);
    
                    System.out.println("\033[0;33m" + "==========");
                    logger.info(hotspot.getName());
                    logger.info(hotspot.getElevation().toString());
                    logger.info(hotspot.getGain().toString());
                    logger.info(hotspot.getRewards_24());
                    logger.info(hotspot.getRewards_7d());
                    logger.info(hotspot.getRewards_30d());
                    System.out.println("\033[0m" + "==========");
                }
    
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
    
            userRepository.save(user);
            return user.getHotspots();

        }else{
            throw new RuntimeException("NO SUCH USER, OR NO API ACCOUNT ASIGNED!");
        }
    }

    /* GET HOTSPOT REWARDS */
    // https://api.helium.io/v1/hotspots/11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa/rewards/sum?min_time=-1%20day&bucket=day
    // https://api.helium.io/v1/hotspots/11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa/rewards/sum?min_time=-7%20day&bucket=week
    // https://api.helium.io/v1/hotspots/11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa/rewards/sum?min_time=-30%20day&bucket=week
    public Hotspot getHotspotRewardsFromAPI(Hotspot inputHotspot) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String[] urls = {   apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-1%20day",
                            apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-7%20day",
                            apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-30%20day",
                            apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-2000%20day"
        };

        List<String> totals = new ArrayList<String>();
            
        for (int i=0; i < urls.length; ++i) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(urls[i]);
            URI uri = builder.build(true).toUri();

            JsonNode jsonnode = restTemplate.getForObject(uri, JsonNode.class);
            totals.add(jsonnode.get("data").get("total").asText());
            logger.warn(totals.get(i).toString());
        }
        inputHotspot.setRewards_24(totals.get(0));
        inputHotspot.setRewards_7d(totals.get(1));
        inputHotspot.setRewards_30d(totals.get(2));
        inputHotspot.setRewards_lifetime(totals.get(3));

        return inputHotspot;
    }


    public Hotspot calculateHotspotROI(Hotspot _hotspot){

        //if()

        return _hotspot;
    }


    /* GET ALL HOTSPOT REWARDS */
    public List<Hotspot> getAllHotspotsRewards(UserModel user){
        for (Hotspot hotspot : user.getHotspots()) {
            this.getHotspotRewardsFromAPI(hotspot);
        }
        return user.getHotspots();
    }
}
