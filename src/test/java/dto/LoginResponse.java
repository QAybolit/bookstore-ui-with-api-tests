package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    String userId;
    String username;
    String password;
    String token;
    String expires;
}
