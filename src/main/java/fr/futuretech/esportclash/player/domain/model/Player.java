package fr.futuretech.esportclash.player.domain.model;

import fr.futuretech.esportclash.core.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player extends BaseEntity {
    @Id
    private String id;
    @Column
    private String name;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player() {

    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

}
