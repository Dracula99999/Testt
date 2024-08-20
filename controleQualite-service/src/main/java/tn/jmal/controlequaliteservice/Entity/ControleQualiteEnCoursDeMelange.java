package tn.jmal.controlequaliteservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ControleQualiteEnCoursDeMelange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matierePremiereId;
    private String designation;
    private LocalDateTime dateDebutControl;
    private String malaxeur;
    private String numeroLot;
    private Double temperatureValidation;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "controleQualite")
    private List<ParametreMesure> parametresMesures;

    private String acceptance;
    private String visaTechCQ;
    private String visaRespCQ;
    private LocalDateTime dateCloture;
}
