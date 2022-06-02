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

    @Scheduled(fixedDelay = 60000*10)
    public void scheduled(){
        apiService.getAccountRewardsFromAPI("kloc");

        apiService.getAccountFromAPI("12zQHwN4HkZX1f7Noznkc759rfFDkNefMR8gek9MTd8j4y7ftX9");
    }
    @Scheduled(fixedDelay = 60000*3)
    public void hotspotShedule(){
        apiService.getAccountHotspotsFromApi("kloc");
    }

}