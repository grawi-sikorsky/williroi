package pl.sikor.williroi.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.respository.UserRepository;

// 11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa cricket
// 1126G1HMj1zM9WbDPfXxeheFUU6HG4ffFh7c97sHWvaS5ahRTSTM - bobcat

@Service
public class HotspotService {
    
    private static final Logger logger = LoggerFactory.getLogger(HotspotService.class);
    private final UserRepository userRepository;

    public HotspotService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<Hotspot> getUserHotspots(String username){
        
        if(userRepository.findByUsername(username) != null){

            UserModel user = new UserModel(userRepository.findByUsername(username));

            // user.setHntAccount(userModel.getHntAccount());
            // user.setApiAccount(accountService.getAccountFromAPI(hntAccount));
            // user.setHotspots(accountService.getAccountHotspotsFromApi(hntAccount));
            
            if(user.getHotspots() != null)
            {
                for (Hotspot hotspot : user.getHotspots()) {
                    System.out.println("HOTSPOT DETAILS: ");
                    System.out.println(hotspot.address);
                    System.out.println(hotspot.elevation);
                    System.out.println(hotspot.gain);
                    System.out.println(hotspot.location);
                    System.out.println(hotspot.name);
                    System.out.println(hotspot.owner);
                    System.out.println(hotspot.reward_scale);
                    System.out.println(hotspot.rewards_24);
                }
            }
            else
            {
                logger.error("No hotspots asigned to user!");
                return user.getHotspots();
            }
            return user.getHotspots();

        } else {
            logger.error("USER DONT EXISTS..");
            throw new RuntimeException();
        }



        
    }
}
