/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Q2MainFunction {
    public static void main(String[]args){
        Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();
        Q2SoldierArrangement soldier = new Q2SoldierArrangement();
        
         // Sort according to ability
        System.out.println("\nEnter which attribute to search for: ");
        Scanner in = new Scanner(System.in);
        String attribute = in.nextLine();
        
        // Binary search for a specific value of ability
        System.out.println("Enter the desired value of the specific ability:");
        int keyValue = in.nextInt();

        ArrayList<Q1General> sortedGeneral = new ArrayList<>();
        switch (attribute) {
            case "Strength":
                sortedGeneral = soldier.sortStrengthCollection();
                System.out.println("\n" + soldier.performSearch(sortedGeneral, keyValue, Q1General::getStrength, "Strength"));
                System.out.println("\nSorted general list:");
                for(Q1General general:sortedGeneral){
                    System.out.println(general.name + general.strength);
                }
                System.out.println("\nSuggested teams:");
                soldier.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getStrength);
                soldier.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getStrength);
                soldier.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getStrength);
                soldier.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getStrength);
                break;
            case "Intelligence":
                sortedGeneral = soldier.sortIntelligenceInsertion();
                System.out.println("\n" + soldier.performSearch(sortedGeneral, keyValue, Q1General::getIntelligence, "Intelligence"));
                System.out.println("\nSorted general list:");
                for(Q1General general:sortedGeneral){
                    System.out.println(general.name + general.intelligence);
                }
                System.out.println("\nSuggested teams:");
                soldier.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getIntelligence);
                break;
            case "Leadership":
                sortedGeneral = soldier.sortLeadershipSelection();
                System.out.println("\n" + soldier.performSearch(sortedGeneral, keyValue, Q1General::getLeadership, "Leadership"));
                System.out.println("\nSorted general list:");
                for(Q1General general:sortedGeneral){
                    System.out.println(general.name + general.leadership);
                }
                System.out.println("\nSuggested teams:");
                soldier.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getIntelligence);
                soldier.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getIntelligence);
                break;
            case "Politic":
                sortedGeneral = soldier.sortPoliticBubble();
                System.out.println("\n" + soldier.performSearch(sortedGeneral, keyValue, Q1General::getPolitic, "Politic"));
                System.out.println("\nSorted general list:");
                for(Q1General general:sortedGeneral){
                    System.out.println(general.name + general.politic);
                }
                System.out.println("\nSuggested teams:");
                soldier.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getPolitic);
                soldier.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getPolitic);
                soldier.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getPolitic);
                soldier.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getPolitic);
                break;
            case "Hit Point":
                sortedGeneral = new ArrayList<>(soldier.sortHitPointMerge(hierarchy.initializeGeneral()));
                System.out.println("\n" + soldier.performSearch(sortedGeneral, keyValue, Q1General::getHitPoint, "Hit Point"));
                System.out.println("\nSorted general list:");
                for(Q1General general:sortedGeneral){
                    System.out.println(general.name + general.hitPoint);
                }
                System.out.println("\nSuggested teams:");
                soldier.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getHitPoint);
                soldier.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getHitPoint);
                soldier.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getHitPoint);
                soldier.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getHitPoint);
                break;
            default:
                System.out.println("Please enter a correct attribute.");
        }

    }
}
