package de.hsrm.mi.web.projekt.projektuser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjektUserController {
    @Autowired ProjektUserServiceImpl userServiceImpl;

    @GetMapping("/registrieren")
    public String getRegistrierenAnsicht(Model m){
        m.addAttribute("projektuser", new ProjektUser("","","USER"));
        return "projektuser/registrieren";
    }

    @PostMapping("/registrieren")
    public String postRegistrierenAnsicht(Model m, @Valid @ModelAttribute("projektuser") ProjektUser user, BindingResult userError) throws ProjektUserServiceException{
        try{
        ProjektUser usser = userServiceImpl.neuenBenutzerAnlegen(user.getUsername(), user.getPassword(), user.getRole());
        m.addAttribute("projektuser", usser);
        }
        catch(ProjektUserServiceException e){
            e.printStackTrace();
            return "projektuser/registrieren";
        }
        if(userError.hasErrors()){
            return "projektuser/registrieren";
        }
        
        return "redirect:/benutzerprofil";
    }
}
