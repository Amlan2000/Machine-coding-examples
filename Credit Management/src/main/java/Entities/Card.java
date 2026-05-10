package Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Card {

    private String cardID;
    private double creditLimit;
}
