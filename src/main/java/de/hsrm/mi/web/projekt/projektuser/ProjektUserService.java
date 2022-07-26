package de.hsrm.mi.web.projekt.projektuser;

import org.springframework.stereotype.Service;

public interface ProjektUserService {

    ProjektUser neuenBenutzerAnlegen(String username, String klartextpasswort, String rolle) throws ProjektUserServiceException; 
    ProjektUser findeBenutzer(String username) throws ProjektUserServiceException;

}
