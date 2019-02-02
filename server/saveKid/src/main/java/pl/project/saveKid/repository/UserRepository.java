package pl.project.saveKid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.project.saveKid.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findUserByRfid(String rfid);
}
