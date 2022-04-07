package pl.sikor.williroi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pl.sikor.williroi.service.heliumAPI.ApiService;

@Slf4j
@Component
public class SheduleService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private ApiService apiService;
    
    public SheduleService(ApiService apiService) {
        this.apiService = apiService;
    }

    @Scheduled(cron = "0/20 * * * * *")
    public void scheduled(){
        logger.info("=====>>>>>Use cron  {}",System.currentTimeMillis());
        //apiService.getAccountRewardsFromAPI("kloc");
        //logger.info("getAccountRewardsFromAPI() done?",System.currentTimeMillis());
        //apiService.getAccountFromAPI("kloc");
        //logger.info("getAccountFromAPI() done?",System.currentTimeMillis());
        //apiService.getAllHotspotsRewards("kloc");
        apiService.getAccountHotspotsFromApi("kloc");
    }
 
    @Scheduled(fixedRate = 15000)
    public void scheduled1() {
        logger.info("=====>>>>>Use fixedRate{}", System.currentTimeMillis());
    }
 
    @Scheduled(fixedDelay = 15000)
    public void scheduled2() {
        logger.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }
}