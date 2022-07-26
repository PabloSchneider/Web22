package de.hsrm.mi.web.projekt.projektuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerprofilRepository;
import de.hsrm.mi.web.projekt.security.ProjektSecurityConfig;

@Service
public class ProjektUserServiceImpl implements ProjektUserService{

    @Autowired ProjektSecurityConfig securityConfig;
    @Autowired ProjektUserRepository projektRepository;
    @Autowired BenutzerprofilRepository bRepository;
    @Autowired PasswordEncoder pEncoder;

    @Transactional
    @Override
    public ProjektUser neuenBenutzerAnlegen(String username, String klartextpasswort, String rolle) throws ProjektUserServiceException{
        
       
        if(rolle == null || rolle.equals("")){
            rolle = "USER";
        }
        if(!projektRepository.findById(username).isPresent()){
            ProjektUser user  = new ProjektUser(username,pEncoder.encode(klartextpasswort), rolle);
            BenutzerProfil benutzer = new BenutzerProfil();
            benutzer.setName(username);

            benutzer.setProjektUser(user);
            user.setProfil(benutzer);
            
            projektRepository.save(user);
            bRepository.save(benutzer);

            return user;
        }
        else{
            throw new ProjektUserServiceException("Benutzer gibt es Schon");
        }
    }
    @Override
    public ProjektUser findeBenutzer(String username) throws ProjektUserServiceException{
        if(projektRepository.findById(username).isPresent()){
            return projektRepository.findById(username).get();
        }
        else{
            throw new ProjektUserServiceException("Benutzer nicht gefunden");
        }
    }
    
}
