package de.hsrm.mi.web.projekt.gebot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilService;

@Service
public class GebotServiceImpl implements GebotService{
    Logger logger = LoggerFactory.getLogger(GebotServiceImpl.class);

    BenutzerprofilService bService;
    GebotRepository gebotRepository;

    @Autowired
    public GebotServiceImpl(GebotRepository gebotRepository, BenutzerprofilService bService){
        this.gebotRepository = gebotRepository;
        this.bService = bService;
    }
    @Override
    public List<Gebot> findeAlleGebote() {
        return gebotRepository.findAll();
    }

    @Override
    public List<Gebot> findeAlleGeboteFuerAngebot(long angebotid) {
        List<Gebot> alle = findeAlleGebote();
        List<Gebot> filteredGebote = new ArrayList<>();
        for (Gebot gebot : alle) {
            if(gebot.getAngebot().getId() == angebotid){
                filteredGebote.add(gebot);
            }
        }
        return filteredGebote;
    }

    @Override
    @Transactional
    public Gebot bieteFuerAngebot(long benutzerprofilid, long angebotid, long betrag) {
        Optional<Gebot> getGebot = gebotRepository.findByAngebotIdAndBieterId(angebotid, benutzerprofilid);
        Gebot gebot;
        if(!getGebot.isPresent()){
            if(bService.holeBenutzerProfilMitId(benutzerprofilid).isPresent() && bService.findeAngebotMitId(angebotid).isPresent()){
                gebot = new Gebot(bService.holeBenutzerProfilMitId(benutzerprofilid).get(),bService.findeAngebotMitId(angebotid).get(),betrag);
                bService.holeBenutzerProfilMitId(benutzerprofilid).get().getGebote().add(gebot);
                bService.findeAngebotMitId(angebotid).get().getGebote().add(gebot);
                gebotRepository.save(gebot);
            }
            else{
                gebot = null;

            }            
        }else{
            gebot = getGebot.get();
            gebot.setBetrag(betrag);
            gebot.setGebotszeitpunkt(LocalDateTime.now());
            gebotRepository.save(gebot);
        }
        logger.info("!!!!!!!!!!--------"+gebot+"------!!!!!!!!!!!!!");
        return gebot;
    }

    @Override
    @Transactional
    public void loescheGebot(long gebotid) {
        Gebot gebot = gebotRepository.findById(gebotid).get();
        bService.holeBenutzerProfilMitId(gebot.getGebieter().getId()).get().getGebote().remove(gebot);
        bService.findeAngebotMitId(gebot.getAngebot().getId()).get().getGebote().remove(gebot);
        gebotRepository.deleteById(gebotid);
    }
    
}
