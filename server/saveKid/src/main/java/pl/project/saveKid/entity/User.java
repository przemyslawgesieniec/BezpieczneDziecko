package pl.project.saveKid.entity;

import lombok.Getter;
import lombok.Setter;
import pl.project.saveKid.Dto.UserDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "rfid")
    private String rfid;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "firebase_token")
    private String firebaseToken;

    public User() {}

    public User(String rfid, Boolean state)
    {
        this.rfid = rfid;
        this.state = state;
    }

    public UserDto toDto()
    {
        return UserDto
                .builder()
                .rfid(this.rfid)
                .state(this.state)
                .firebaseToken(this.firebaseToken)
                .build();
    }
}
