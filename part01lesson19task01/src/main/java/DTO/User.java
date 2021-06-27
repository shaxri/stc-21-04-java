package DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

@Data
@With
@Builder
@Value
public class User {
    long id;
    String login;
    String password;
    String name;
    String email;
}
