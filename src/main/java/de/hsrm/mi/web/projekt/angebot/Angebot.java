package de.hsrm.mi.web.projekt.angebot;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import de.hsrm.mi.web.projekt.benutzerprofil.BenutzerProfil;
import de.hsrm.mi.web.projekt.gebot.Gebot;

@Entity
public class Angebot{
    
    @Id 
    @GeneratedValue
    private long id;

    @Version
    private long version;

    private String beschreibung;
    private long mindestpreis;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime ablaufzeitpunkt;
    private String abholort;
    private double lat;
    private double lon;
    
    @ManyToOne
    private BenutzerProfil anbieter;

    @OneToMany(mappedBy="angebot", cascade = CascadeType.ALL , orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Gebot> gebote;
    
    public Angebot(){
        beschreibung = "";
        mindestpreis = 0;
        ablaufzeitpunkt = LocalDateTime.now();
        abholort = "";
        lat = 0;
        lon = 0;
        gebote = new ArrayList<>();
    }

    public Angebot(String beschreibung, long mindestpreis, LocalDateTime ablaufzeitpunkt, String abholort) {
        this.beschreibung = beschreibung;
        this.mindestpreis = mindestpreis;
        this.ablaufzeitpunkt = ablaufzeitpunkt;
        this.abholort = abholort;
    }

    public long getId() {
        return id;
    }


    public long getVersion() {
        return version;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public long getMindestpreis() {
        return mindestpreis;
    }

    public void setMindestpreis(long mindestpreis) {
        this.mindestpreis = mindestpreis;
    }

    public LocalDateTime getAblaufzeitpunkt() {
        return ablaufzeitpunkt;
    }

    public void setAblaufzeitpunkt(LocalDateTime ablaufzeitpunkt) {
        this.ablaufzeitpunkt = ablaufzeitpunkt;
    }

    public String getAbholort() {
        return abholort;
    }

    public void setAbholort(String abholort) {
        this.abholort = abholort;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public BenutzerProfil getAnbieter() {
       return anbieter;
    }
    public void setAnbieter(BenutzerProfil anbieter){
        this.anbieter = anbieter;
    }
    public List<Gebot> getGebote() {    
        return gebote;
    }
    @Override
    public String toString() {
        return "Angebot [abholort=" + abholort + ", ablaufzeitpunkt=" + ablaufzeitpunkt + ", beschreibung="
                + beschreibung + ", id=" + id + ", lat=" + lat + ", lon=" + lon + ", minsestpreis=" + mindestpreis
                + ", version=" + version + "]";
    }
    
    

}
