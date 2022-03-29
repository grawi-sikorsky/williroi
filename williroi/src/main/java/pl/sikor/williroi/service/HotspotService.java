package pl.sikor.williroi.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pl.sikor.williroi.model.HotspotDTO;
import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.respository.UserRepository;
import pl.sikor.williroi.respository.HotspotRepository;

// 11n56PhWsutCeLSubUiyyrzfT8absNarNWNkAcEjL4csyUB3Nqa cricket
// 1126G1HMj1zM9WbDPfXxeheFUU6HG4ffFh7c97sHWvaS5ahRTSTM - bobcat

@Service
public class HotspotService {
    
    private static final Logger logger = LoggerFactory.getLogger(HotspotService.class);
    private final UserRepository userRepository;
    private final HotspotRepository hotspotRepository;

    public HotspotService(UserRepository userRepository, HotspotRepository hotspotRepository) {
        this.userRepository = userRepository;
        this.hotspotRepository = hotspotRepository;
    }

    /* GET USER HOTSPOTS FROM DB */
    public List<Hotspot> getUserHotspots(String username){        
        if(userRepository.findByUsername(username) != null){

            UserModel user = userRepository.findByUsername(username);
            
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

    /* UPDATE USER HOTSPOT */
    public Hotspot updateHotspot(String username, HotspotDTO incomingHotspot){

        if(hotspotRepository.findHotspotByAddress(incomingHotspot.getAddress()) != null &&
            userRepository.findByUsername(username) != null) {

            Hotspot hotspot = hotspotRepository.findHotspotByAddress(incomingHotspot.getAddress());
            hotspot.price = incomingHotspot.getPrice();

            hotspotRepository.save(hotspot);

            System.out.println("HOTSPOT DETAILS: ");
            System.out.println(hotspot.name);
            System.out.println(hotspot.address);
            System.out.println(hotspot.elevation);
            System.out.println(hotspot.gain);
            System.out.println(hotspot.location);
            System.out.println(hotspot.reward_scale);
            System.out.println(hotspot.rewards_24);



            return hotspot;

        } else {
            logger.error("USER OR HOTSPOT DONT EXISTS..");
            throw new RuntimeException("USER OR HOTSPOT DONT EXISTS..");
        }
    }

}
