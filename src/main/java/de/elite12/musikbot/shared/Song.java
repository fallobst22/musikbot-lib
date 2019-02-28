package de.elite12.musikbot.shared;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.log4j.Logger;

@XmlRootElement
public class Song implements Serializable {
    private static final long serialVersionUID = -6307098992296645493L;

    @XmlElement(required = true)
    private int id;
    private boolean played;
    private String link;
    private String autor;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date insert;
    private String title;
    private boolean skipped = false;
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date playedat;
    private int sort;
    private int dauer;
    private String type;
    private String gravatarid;

    public Song() {
        this.id = 0;
        this.setInsert(new Timestamp(new Date().getTime()));
        this.autor = null;
        this.title = null;
        this.link = null;
        this.played = false;
        this.skipped = false;
        this.setPlayedat(null);
        this.sort = 0;
        this.dauer = 0;
        this.type = "youtube";
        this.gravatarid = "platzhalter";
    }

    public Song(ResultSet rs) {
        try {
            if (rs.isBeforeFirst()) {
                if (!rs.next()) {
                    throw new RuntimeException("Empty Song Result");
                }
            }
            this.id = rs.getInt("SONG_ID");
            this.setInsert((Timestamp) rs.getObject("SONG_INSERT_AT"));
            this.autor = rs.getString("AUTOR");
            this.title = rs.getString("SONG_NAME");
            this.link = rs.getString("SONG_LINK");
            this.played = rs.getBoolean("SONG_PLAYED");
            this.skipped = rs.getBoolean("SONG_SKIPPED");
            this.setPlayedat((Timestamp) rs.getObject("SONG_PLAYED_AT"));
            this.sort = rs.getInt("SONG_SORT");
            this.dauer = rs.getInt("SONG_DAUER");
            this.gravatarid = "platzhalter";
            if (this.link.contains("spotify")) {
                this.type = "spotify";
            } else {
                this.type = "youtube";
            }
        } catch (SQLException e) {
            Logger.getLogger(this.getClass()).error("SQLException", e);
            throw new RuntimeException("SQL Exception!");
        }
    }

    public Song(int id, Timestamp inserted, String autor, String name, String link, boolean played, boolean skipped,
            Timestamp playedat, int sort, int dauer) {
        this.id = id;
        this.setInsert(inserted);
        this.autor = autor;
        this.title = name;
        this.link = link;
        this.played = played;
        this.skipped = skipped;
        this.setPlayedat(playedat);
        this.sort = sort;
        this.dauer = dauer;
        if (this.link.contains("spotify")) {
            this.type = "spotify";
        } else {
            this.type = "youtube";
        }
    }

    public int getId() {
        return id;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Date getInsert() {
        if (this.insert != null) {
            return (Date) this.insert.clone();
        }
        return null;
    }

    public void setInsert(Timestamp insert) {
        if (insert != null) {
            this.insert = new Date(insert.getTime());
        }
    }

    public boolean isSkipped() {
        return skipped;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", link=" + link + ", autor=" + autor + ", title=" + title + "]";
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    @XmlTransient
    public Date getPlayedat() {
        if (this.playedat != null) {
            return (Date) this.playedat.clone();
        }
        return null;
    }

    public void setPlayedat(Timestamp playedat) {
        if (playedat != null) {
            this.playedat = new Date(playedat.getTime());
        }
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String gettype() {
        return this.type;
    }
    
    public void settype(String type) {
        this.type = type;
    }

    @XmlElement
    public String getGravatarid() {
        return gravatarid;
    }

    public void setGravatarid(String gravatarid) {
        this.gravatarid = gravatarid;
    }
}
