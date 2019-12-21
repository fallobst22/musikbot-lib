package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SimpleResponse implements ClientDTO{
    private ResponseType response;

    public static enum ResponseType {
        NO_SONG_AVAILABLE
    }
}
