package de.hsrm.mi.web.projekt.api.gebot;

public record addGebotRequestDTO(
    Long benutzerprofilid,
    Long angebotid,
    Long betrag)
    {}