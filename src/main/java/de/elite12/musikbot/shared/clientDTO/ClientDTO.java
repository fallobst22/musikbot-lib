package de.elite12.musikbot.shared.clientDTO;

public interface ClientDTO {
    public default String getType() {
        return this.getClass().getSimpleName();
    }
}
