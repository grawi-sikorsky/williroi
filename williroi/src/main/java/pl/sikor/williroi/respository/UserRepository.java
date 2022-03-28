package pl.sikor.williroi.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.sikor.williroi.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {
    
    UserModel findByUsername(String username);
}
