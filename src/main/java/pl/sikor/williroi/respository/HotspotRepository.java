package pl.sikor.williroi.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sikor.williroi.model.heliumAPI.Hotspot;

@Repository
public interface HotspotRepository extends CrudRepository<Hotspot,Long> {

    Hotspot findHotspotByAddress(String address);
    
}
