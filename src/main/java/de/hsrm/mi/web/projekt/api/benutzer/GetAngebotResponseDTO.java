package de.hsrm.mi.web.projekt.api.benutzer;

import java.time.LocalDateTime;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.gebot.Gebot;

public record GetAngebotResponseDTO (long angebotid,
String beschreibung,
long anbieterid,
String anbietername,
long mindestpreis,
LocalDateTime ablaufzeitpunkt,
String abholort, double lat, double lon,
long topgebot, long gebote) 
{
    
    static GetAngebotResponseDTO from(Angebot a){
        long maxpreis = a.getMindestpreis();
        for (Gebot e : a.getGebote()){
            if(e.getBetrag() > maxpreis){
                maxpreis = e.getBetrag();
            }
        }
        return new GetAngebotResponseDTO(a.getId(), a.getBeschreibung(), a.getAnbieter().getId(), a.getAnbieter().getName(), a.getMindestpreis(), a.getAblaufzeitpunkt(), a.getAbholort(), a.getLat(), a.getLon(), maxpreis, (long) a.getGebote().size());
    }
}
