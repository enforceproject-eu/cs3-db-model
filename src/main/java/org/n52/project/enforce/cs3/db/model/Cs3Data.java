package org.n52.project.enforce.cs3.db.model;

import java.time.LocalDate;

import org.locationtech.jts.geom.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * <p>
 * Data DTO.
 * </p>
 *
 * @author Benjamin Pross (b.pross @52north.org)
 * @since 1.0.0
 */
@Entity
@Table(
        name = "cs3_data")
public class Cs3Data {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cs3_data_generator")
    @SequenceGenerator(name = "cs3_data_generator", sequenceName = "cs3_data_seq", allocationSize = 1)
    private Integer id;

    @Column(
            name = "fictionary")
    private Integer fictionary;

    @Column(
            name = "date_of_report")
    private LocalDate dateOfReport;

    @Column(
            name = "description")
    private String description;

    @Column(
            name = "municipality")
    private String municipality;

    @Column(
            name = "coords",
            columnDefinition = "geometry(Point,4326)")
    private Point coords;

    @Column(
            name = "responses")
    private String responses;


    public Cs3Data() {}

    public Cs3Data(Integer id) {
        this.id = id;
    }

    public Cs3Data(Integer id, Integer fictionary, LocalDate dateOfReport, String description, String municipality, Point coords, String responses) {
        this(id);
        this.fictionary = fictionary;
        this.dateOfReport = dateOfReport;
        this.description = description;
        this.municipality = municipality;
        this.coords = coords;
        this.responses = responses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFictionary() {
        return fictionary;
    }

    public void setFictionary(Integer fictionary) {
        this.fictionary = fictionary;
    }

    public LocalDate getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(LocalDate dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public Point getCoords() {
        return coords;
    }

    public void setCoords(Point coords) {
        this.coords = coords;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fictionary: " + fictionary + ", ");
        sb.append("dateOfReport: " + dateOfReport + ", ");
        sb.append("description: " + description + ", ");
        sb.append("municipality: " + municipality + ", ");
        sb.append("coords: " + coords + ", ");
        sb.append("responses: " + responses + ", ");
        return sb.toString();
    }
}
