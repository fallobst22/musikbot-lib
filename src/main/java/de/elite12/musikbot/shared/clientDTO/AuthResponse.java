package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthResponse implements ClientDTO
{
    private boolean success;
}
