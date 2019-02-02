package pl.project.saveKid.Dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private String rfid;
    private Boolean state;

    public UserDto(String rfid, Boolean state) {
        this.rfid = rfid;
        this.state = state;
    }
}
