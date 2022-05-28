package de.hsrm.mi.web.projekt.benutzerprofil;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import de.hsrm.mi.web.projekt.angebot.Angebot;



@Controller
@SessionAttributes("profil")
public class BenutzerprofilController {
    Logger logger = LoggerFactory.getLogger(BenutzerprofilController.class);

    @Autowired BenutzerprofilService bService;

    @ModelAttribute("profil")
    public void initProfil(Model m){
        logger.info("----Init----");
        m.addAttribute("profil", new BenutzerProfil());
    }
    @GetMapping("/benutzerprofil/clearsession")
    public String clearSession(SessionStatus status){
        status.setComplete();
        return "redirect:/benutzerprofil";
    }
    @GetMapping("/benutzerprofil")
    public String getProfilAnsicht(Model m, @ModelAttribute("profil") BenutzerProfil profil){
        logger.info(""+profil.getId());

        return "benutzerprofil/profilansicht";
    }
    @GetMapping("benutzerprofil/bearbeiten")
    public String getProfileditor(Model m) {
        return "benutzerprofil/profileditor";
    }
    @PostMapping("benutzerprofil/bearbeiten")
    public String postProfileditor(Model m, @Valid @ModelAttribute("profil") BenutzerProfil profil, BindingResult profilError){
        //logger.info(profilError.getAllErrors().toString());
        m.addAttribute("profil", profil);
        if(profilError.hasErrors()){
            return "benutzerprofil/profileditor";
        }
        bService.speichereBenutzerProfil(profil);
        return "redirect:/benutzerprofil";
    }
    @GetMapping("/benutzerprofil/liste")
    public String getBenutzerProfilListe(Model m){
        logger.info("---------benutzerprofil/liste---------");

        m.addAttribute("profilliste",bService.alleBenutzerProfile());
        return "benutzerprofil/profilliste";
    }
    
    @GetMapping(value="/benutzerprofil/liste", params = "op")
    public String getBenutzerProfilListeLoeschenBearbeiten(Model m, @RequestParam String op, @RequestParam(value = "id") long id) {
        if(op.equals("bearbeiten")){
            m.addAttribute("profil", bService.holeBenutzerProfilMitId(id).get());
            return "redirect:/benutzerprofil/bearbeiten";
        }
        if(op.equals("loeschen")){
            bService.loescheBenutzerProfilMitId(id);
            return "redirect:/benutzerprofil/liste";
        }
        return null;
    }
    @GetMapping("/benutzerprofil/angebot")
    public String getBenutzerProfilAngebot(Model m){
        m.addAttribute("angebot", new Angebot());
        return "benutzerprofil/angebotsformular";
    }
    @PostMapping("/benutzerprofil/angebot")
    public String postAngebot(Model m, @ModelAttribute("angebot") Angebot angebot
    ){
        BenutzerProfil profil = (BenutzerProfil)m.getAttribute("profil");
        logger.info(angebot.toString()+"--"+profil.toString());
        //m.addAttribute("angebot", angebot);
        bService.fuegeAngebotHinzu(profil.getId(), angebot);
        m.addAttribute("profil", bService.holeBenutzerProfilMitId(profil.getId()).get());
        logger.info(angebot.toString()+"--"+profil.toString());
        return "redirect:/benutzerprofil";
    }
    @GetMapping("/benutzerprofil/angebot/{id}/del")
    public String deleteAngebot(Model m
    //,  @ModelAttribute("profil") BenutzerProfil profil
    , @PathVariable("id") long id){
        //logger.info(profil.toString() + "id: " + profil.getId());
        BenutzerProfil profil = (BenutzerProfil)m.getAttribute("profil");
        bService.loescheAngebot(id);
        //logger.info(profil.toString() + "id: " + profil.getId());
        m.addAttribute("profil", bService.holeBenutzerProfilMitId(profil.getId()).get());
        // logger.info(profil.toString() + "id: " + profil.getId());
        return "redirect:/benutzerprofil";
    }
        
}
