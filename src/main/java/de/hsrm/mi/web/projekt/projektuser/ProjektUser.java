package de.hsrm.mi.web.projekt.projektuser;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;

@Entity
public class ProjektUser {
    
    @Id
    @NotBlank @NotNull
    @Size(min = 3)
    private String username;

    @NotBlank @NotNull
    @Size(min = 3)
    private String password;

    private String role;

    @OneToOne
    private BenutzerProfil profil;


    public ProjektUser(){
        username = "";
        password = "";
        role = "";
    }
    public ProjektUser(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getRole() {
        return role;
    }

    public BenutzerProfil getProfil() {
        return profil;
    }

    public void setProfil(BenutzerProfil profil) {
        this.profil = profil;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProjektUser other = (ProjektUser) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    
}
