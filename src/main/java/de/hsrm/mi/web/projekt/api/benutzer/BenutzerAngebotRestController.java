package de.hsrm.mi.web.projekt.api.benutzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.api.gebot.GetGebotResponseDTO;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilServiceImpl;
import de.hsrm.mi.web.projekt.gebot.Gebot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class  BenutzerAngebotRestController {
    
    @Autowired BenutzerprofilServiceImpl bService;

    @GetMapping(value="/angebot", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAngebotResponseDTO> getAlleAngebote() {
        List<GetAngebotResponseDTO> angebote = new ArrayList<>();
        for (Angebot a : bService.alleAngebote()) {
            angebote.add(GetAngebotResponseDTO.from(a));
        }
        return angebote;
    }

    @GetMapping(value="/angebot/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetAngebotResponseDTO getAngebotById(@PathVariable long id){
        return GetAngebotResponseDTO.from(bService.findeAngebotMitId(id).get());
    }

    @GetMapping(value="/angebot/{id}/gebot", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetGebotResponseDTO> getGebobteByAngebotID(@PathVariable long id){
        List<GetGebotResponseDTO> geboteDTO = new ArrayList<>();
        Optional<Angebot> angebot = bService.findeAngebotMitId(id);
        if(!angebot.isPresent()){
            return null;
        }
        for (Gebot gebot : angebot.get().getGebote()) {
            geboteDTO.add(GetGebotResponseDTO.from(gebot));
        }
        return geboteDTO;
    }
    
    @GetMapping(value="/benutzer/{id}/angebot", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetAngebotResponseDTO> getAngeboteByBenutzerID(@PathVariable long id){
        List<GetAngebotResponseDTO> angebote = new ArrayList<>();
        Optional<BenutzerProfil> bp = bService.holeBenutzerProfilMitId(id);
        if(!bp.isPresent()){
            return null;
        }
        for (Angebot a : bp.get().getAngebote()) {
            angebote.add(GetAngebotResponseDTO.from(a));
        }
        return angebote;

    }


    
}
