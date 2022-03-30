package pl.sikor.williroi.service.heliumAPI;

import java.net.URI;
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
        UserModel user = new UserModel();

        if(userRepository.findByUsername(username) != null && userRepository.findByUsername(username).getHntAccount() != null){
            user = userRepository.findByUsername(username);
        }else{
            throw new RuntimeException("NO SUCH USER, OR NO API ACCOUNT ASIGNED!");
        }

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);

        String rawJson = restTemplate.getForObject(apiAddress + "accounts/" + user.getHntAccount(), String.class);

        try {
            user.setApiAccount(mapper.readValue(rawJson, AccountModel.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        userRepository.save(user);
        logger.info("ACCOUNT FETCHED FROM API!!");

        return user.getApiAccount();
    }

    /* GET ACCOUNT HOTSPOTS FROM API */
    public List<Hotspot> getAccountHotspotsFromApi(String username) {
        UserModel user = new UserModel();

        if(userRepository.findByUsername(username) != null && userRepository.findByUsername(username).getHntAccount() != null){
            user = userRepository.findByUsername(username);
            //hotspotdto = user.getHotspots();
        }else{
            throw new RuntimeException("NO SUCH USER, OR NO API ACCOUNT ASIGNED!");
        }

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
                logger.info(hotspot.getAddress());
                logger.info(hotspot.getLocation());
                logger.info(hotspot.getElevation().toString());
                logger.info(hotspot.getGain().toString());
                logger.info(hotspot.getRewards_24());
                System.out.println("\033[0m" + "==========");
            }

            userRepository.save(user);
            return user.getHotspots();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        userRepository.save(user);
        return user.getHotspots();
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

        String url24 = apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-1%20day&bucket=day";
        String url7 = apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-7%20day&bucket=week";
        String url30 = apiAddress + "hotspots/" + inputHotspot.getAddress() + "/rewards/sum?min_time=-30%20day&bucket=week";

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url24);
        URI uri = builder.build(true).toUri();

        JsonNode jsonnode = restTemplate.getForObject(uri, JsonNode.class);
        String json = restTemplate.getForObject(uri, String.class);

        String total = jsonnode.get("data").get(0).get("total").asText();

        inputHotspot.setRewards_24(total);

        return inputHotspot;
    }

    /* GET ALL HOTSPOT REWARDS */
    public List<Hotspot> getAllHotspotsRewards(UserModel user){
        for (Hotspot hotspot : user.getHotspots()) {
            this.getHotspotRewardsFromAPI(hotspot);
        }
        return user.getHotspots();
    }
}
