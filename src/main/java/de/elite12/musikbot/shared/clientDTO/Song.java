package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Song implements ClientDTO {
    private String songlink;
    private String songtitle;
    private String songtype;
}
