package pl.project.saveKid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.saveKid.Dto.UserDto;
import pl.project.saveKid.entity.User;
import pl.project.saveKid.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto userPassedTheGate(String rfid){

        User user = userRepository.findUserByRfid(rfid);
        user.setState(!user.getState());
        userRepository.save(user);
        return user.toDto();
    }
}
