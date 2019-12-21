package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthRequest implements ClientDTO
{
    private String key;
}
