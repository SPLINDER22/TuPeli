package com.urnovie.tupeli.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieInfoDTO {
    private String title;
    private String year;
    private String director;
    private String genre;
    private String posterUrl;
}
