package com.dreamgamescasestudy.rest.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column
    private String username;

    @Column
    @Builder.Default
    private int level = 1;

    @Column
    @Builder.Default
    private int coins = 5000;

    @Column
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Country country = Country.getRandomCountry();

    @Column
    @Builder.Default
    private int pendingCoins = 0;


    public void updateFieldsForNewLevel(){
        this.coins = getCoins() + 25;
        this.level = getLevel() + 1;
    }
}

