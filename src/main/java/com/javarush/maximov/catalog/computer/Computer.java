package com.javarush.maximov.catalog.computer;

import com.javarush.maximov.catalog.motherboard.Motherboard;
import com.javarush.maximov.catalog.powersupply.PowerSupply;
import com.javarush.maximov.catalog.videocard.VideoCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "computers")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Motherboard motherboard;

    @OneToOne(cascade = CascadeType.PERSIST)
    private PowerSupply powerSupply;

    @OneToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<VideoCard> videoCards;

    @Column(name = "testing")
    private boolean testing = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ComputerStatus computerStatus = ComputerStatus.FORMED;
}
