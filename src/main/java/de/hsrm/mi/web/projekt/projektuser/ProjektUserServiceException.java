package de.hsrm.mi.web.projekt.projektuser;

public class ProjektUserServiceException extends Exception{

    public ProjektUserServiceException(String message){
        super(message);
    }
    public ProjektUserServiceException(){
        super("Huch eine ProjektuserService Exeption wurde geworfen");
    }
}
