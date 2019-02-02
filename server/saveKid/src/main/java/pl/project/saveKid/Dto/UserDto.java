package pl.project.saveKid.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto
{
    private String rfid;
    private String firebaseToken;
    private Boolean state;
}
