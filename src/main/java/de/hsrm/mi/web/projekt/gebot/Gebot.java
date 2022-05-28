package de.hsrm.mi.web.projekt.gebot;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.angebot.Angebot;
import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;

@Entity
public class Gebot {
    @Id
    @GeneratedValue
    private long id;
    @Version
    private long version;
    @ManyToOne
    private BenutzerProfil gebieter;
    @ManyToOne
    private Angebot angebot;
    private long betrag;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime gebotszeitpunkt;

    public Gebot(){
        gebotszeitpunkt = LocalDateTime.now();
        betrag = 0;
    }

    public Gebot(BenutzerProfil gebieter, Angebot angebot, long betrag) {
        this.gebieter = gebieter;
        this.angebot = angebot;
        this.betrag = betrag;
        this.gebotszeitpunkt = LocalDateTime.now();
    }


    public BenutzerProfil getGebieter() {
        return gebieter;
    }


    public Angebot getAngebot() {
        return angebot;
    }

    public long getBetrag() {
        return betrag;
    }

    public void setBetrag(long betrag) {
        this.betrag = betrag;
    }

    public LocalDateTime getGebotszeitpunkt() {
        return gebotszeitpunkt;
    }

    public void setGebotszeitpunkt(LocalDateTime gebotszeitpunkt) {
        this.gebotszeitpunkt = gebotszeitpunkt;
    }
    public long getId() {
        return id;
    }
    public long getVersion() {
        return version;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        Gebot other = (Gebot) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
