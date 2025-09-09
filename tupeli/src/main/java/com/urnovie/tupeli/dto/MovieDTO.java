package com.urnovie.tupeli.dto;

import com.urnovie.tupeli.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private Long idMovie;

    private Long idUser;

    private String title;

    private Long estreno;

    private String director;

    private String genre;

    private String posterUrl;
}
