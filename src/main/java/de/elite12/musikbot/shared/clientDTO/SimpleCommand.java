package de.elite12.musikbot.shared.clientDTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SimpleCommand implements ClientDTO {

    private CommandType command;

    public static enum CommandType {
        PAUSE,STOP,SHUTDOWN
    }
}
