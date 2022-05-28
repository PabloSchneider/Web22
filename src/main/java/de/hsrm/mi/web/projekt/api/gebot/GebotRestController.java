package de.hsrm.mi.web.projekt.api.gebot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.gebot.Gebot;
import de.hsrm.mi.web.projekt.gebot.GebotServiceImpl;

@RestController
@RequestMapping("/rest")
public class GebotRestController {
    
    @Autowired GebotServiceImpl gServiceImpl;
    @GetMapping(value ="api/gebot", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<GetGebotResponseDTO> getAllGebote(){
        List<Gebot> gebote = gServiceImpl.findeAlleGebote();
        List<GetGebotResponseDTO> gebotDTO = new ArrayList<>();
        for (Gebot gebot : gebote) {
            gebotDTO.add(GetGebotResponseDTO.from(gebot));
        }
        return gebotDTO;
    }
    @PostMapping(value = "/api/gebot", produces=MediaType.APPLICATION_JSON_VALUE)
    public long addGebot(@RequestBody GetGebotResponseDTO newGebot){
        gServiceImpl.bieteFuerAngebot(newGebot.gebieterid(), newGebot.angebotid(), newGebot.betrag());
        return newGebot.gebotid();
    }
    @DeleteMapping(value = "/api/gebot/{id}")
    public void deleteGebot(@PathVariable long gebotid){
        gServiceImpl.loescheGebot(gebotid);
    }
}
