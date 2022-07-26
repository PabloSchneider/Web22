package de.hsrm.mi.web.projekt.benutzerprofil;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.angebot.AngebotRepository;
import de.hsrm.mi.web.projekt.geo.AdressInfo;
import de.hsrm.mi.web.projekt.geo.GeoServiceImpl;

@Service
public class BenutzerprofilServiceImpl implements BenutzerprofilService{
    Logger logger = LoggerFactory.getLogger(BenutzerprofilServiceImpl.class);

    private BenutzerprofilRepository repository;
    private GeoServiceImpl geoService;
    private AngebotRepository angebotRepsitory;
    
    @Autowired 
    public BenutzerprofilServiceImpl(BenutzerprofilRepository repository, GeoServiceImpl geoService, AngebotRepository angebotRepsitory){
        this.repository = repository;
        this.geoService = geoService;
        this.angebotRepsitory = angebotRepsitory;
    }

    @Override
    public BenutzerProfil speichereBenutzerProfil(BenutzerProfil bp) {
        List<AdressInfo> info = geoService.findeAdressInfo(bp.getAdresse());
        if(info.isEmpty()){
            bp.setLat(0);
            bp.setLon(0);
        }else{
            AdressInfo adressInfo = info.get(0);
            bp.setLat(adressInfo.lat());
            bp.setLon(adressInfo.lon());
        }
        
        bp = repository.save(bp);        
        logger.info(""+bp.getLat()+ " ----- " + bp.getLon());
        return bp;
    }
    @Override
    public Optional<BenutzerProfil> holeBenutzerProfilMitId(Long id) {
        return repository.findById(id); 

    }
    @Override
    public List<BenutzerProfil> alleBenutzerProfile(){
        return repository.findAll(Sort.by("name"));
    }
    @Transactional
    @Override
    public void loescheBenutzerProfilMitId(Long loesch){
        repository.deleteById(loesch);
    }
    @Transactional
    @Override
    public void fuegeAngebotHinzu(long id, Angebot angebot) {
        BenutzerProfil bp = holeBenutzerProfilMitId(id).get();
        if(geoService.findeAdressInfo(angebot.getAbholort()).isEmpty()){
            angebot.setLat(0);
            angebot.setLon(0);
        }else{
            AdressInfo adressInfo = geoService.findeAdressInfo(angebot.getAbholort()).get(0);
            angebot.setLat(adressInfo.lat());
            angebot.setLon(adressInfo.lon());
        }
        angebot.setAnbieter(bp);
        bp.getAngebote().add(angebot);
        repository.save(bp);
    }
    
    @Transactional
    @Override
    public void loescheAngebot(long id) {
        Angebot angebot = angebotRepsitory.findById(id).get();
        BenutzerProfil profil = angebot.getAnbieter();
        profil.getAngebote().remove(angebot);
        angebotRepsitory.deleteById(id);
        //repository.save(profil);
    }

    @Override
    public List<Angebot> alleAngebote() {
        return angebotRepsitory.findAll();
    }

    @Override
    public Optional<Angebot> findeAngebotMitId(long angebotid) {
        return angebotRepsitory.findById(angebotid); 
        
    }
    
}
