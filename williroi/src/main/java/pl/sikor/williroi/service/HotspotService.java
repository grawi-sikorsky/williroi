package pl.sikor.williroi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pl.sikor.williroi.model.HotspotDTO;
import pl.sikor.williroi.model.UserModel;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.respository.HotspotRepository;
import pl.sikor.williroi.respository.UserRepository;

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
                    System.out.println(hotspot.getAddress());
                    System.out.println(hotspot.getElevation());
                    System.out.println(hotspot.getGain());
                    System.out.println(hotspot.getLocation());
                    System.out.println(hotspot.getName());
                    System.out.println(hotspot.getOwner());
                    System.out.println(hotspot.getReward_scale());
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
            throw new RuntimeException("USER DONT EXISTS..");
        }
    }

    /* UPDATE USER HOTSPOT */
    public Hotspot updateHotspot(String username, HotspotDTO incomingHotspot){

        if(hotspotRepository.findHotspotByAddress(incomingHotspot.getAddress()) != null &&
            userRepository.findByUsername(username) != null) {

            Hotspot hotspot = hotspotRepository.findHotspotByAddress(incomingHotspot.getAddress());
            hotspot.getHotspotDto().setPrice(incomingHotspot.getPrice());

            hotspotRepository.save(hotspot);

            System.out.println("HOTSPOT DETAILS: ");
            System.out.println(hotspot.getAddress());
            System.out.println(hotspot.getElevation());
            System.out.println(hotspot.getGain());
            System.out.println(hotspot.getLocation());
            System.out.println(hotspot.getName());
            System.out.println(hotspot.getOwner());
            System.out.println(hotspot.getReward_scale());

            return hotspot;

        } else {
            logger.error("USER OR HOTSPOT DONT EXISTS..");
            throw new RuntimeException("USER OR HOTSPOT DONT EXISTS..");
        }
    }

}
