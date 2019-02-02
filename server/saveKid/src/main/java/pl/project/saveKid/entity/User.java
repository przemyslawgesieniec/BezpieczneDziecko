package pl.project.saveKid.entity;

import lombok.Getter;
import lombok.Setter;
import pl.project.saveKid.Dto.UserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String rfid;
    private Boolean state;


    public User(String rfid, Boolean state) {
        this.rfid = rfid;
        this.state = state;
    }

    public User() {
    }

    public UserDto toDto(){
        return UserDto
                .builder()
                .rfid(this.rfid)
                .state(this.state)
                .build();
    }
}
