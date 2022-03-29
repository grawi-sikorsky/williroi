package pl.sikor.williroi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.sikor.williroi.model.HotspotDTO;
import pl.sikor.williroi.model.heliumAPI.Hotspot;
import pl.sikor.williroi.service.HotspotService;

@RestController
@RequestMapping("/user/{username}/hotspots")
public class HotspotController {
    
    private final HotspotService hotspotService;

    public HotspotController(HotspotService hotspotService) {
        this.hotspotService = hotspotService;
    }

    @GetMapping
    public List<Hotspot> getUserHotspots(@PathVariable String username){
        return hotspotService.getUserHotspots(username);
    }
    
    @PatchMapping
    public Hotspot updateUserHotspot(@PathVariable String username, @RequestBody HotspotDTO hotspot){
        return hotspotService.updateHotspot(username, hotspot);
    }
    
    
}
