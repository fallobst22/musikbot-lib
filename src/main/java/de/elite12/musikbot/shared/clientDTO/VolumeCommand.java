package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VolumeCommand implements ClientDTO {
    /**
     * Volume to set in Percent
     */
    private short volume;
}
