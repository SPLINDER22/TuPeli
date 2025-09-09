package com.urnovie.tupeli.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long idUser;

    private String username;

    private String email;

    private String password;
}
