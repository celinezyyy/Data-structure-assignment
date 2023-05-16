/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

/**
 *
 * @author HP
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Q1MainFunction {

    public static void main(String[] args) {
        // Create a hierarchy
        Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();
        hierarchy.traverse(hierarchy.root);

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
                sortedGeneral = hierarchy.sortStrengthCollection();
                hierarchy.performSearch(sortedGeneral, keyValue, Q1General::getStrength, "Strength");
                System.out.println("\nSuggested teams:");
                hierarchy.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getStrength);
                hierarchy.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getStrength);
                hierarchy.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getStrength);
                hierarchy.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getStrength);
                break;
            case "Intelligence":
                sortedGeneral = hierarchy.sortIntelligenceSelection();
                hierarchy.performSearch(sortedGeneral, keyValue, Q1General::getIntelligence, "Intelligence");
                System.out.println("\nSuggested teams:");
                hierarchy.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getIntelligence);
                break;
            case "Leadership":
                sortedGeneral = hierarchy.sortLeadershipSelection();
                hierarchy.performSearch(sortedGeneral, keyValue, Q1General::getLeadership, "Leadership");
                System.out.println("\nSuggested teams:");
                hierarchy.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getIntelligence);
                hierarchy.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getIntelligence);
                break;
            case "Politic":
                sortedGeneral = hierarchy.sortPoliticBubble();
                hierarchy.performSearch(sortedGeneral, keyValue, Q1General::getPolitic, "Politic");
                System.out.println("\nSuggested teams:");
                hierarchy.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getPolitic);
                hierarchy.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getPolitic);
                hierarchy.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getPolitic);
                hierarchy.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getPolitic);
                break;
            case "Hit Point":
                sortedGeneral = new ArrayList<>(hierarchy.sortHitPointMerge(hierarchy.generals));
                hierarchy.performSearch(sortedGeneral, keyValue, Q1General::getHitPoint, "Hit Point");
                System.out.println("\nSuggested teams:");
                hierarchy.printTeam(hierarchy, sortedGeneral, 250, 300, "S team",Q1General::getHitPoint);
                hierarchy.printTeam(hierarchy, sortedGeneral, 220, 329, "A team",Q1General::getHitPoint);
                hierarchy.printTeam(hierarchy, sortedGeneral, 190, 219, "B team",Q1General::getHitPoint);
                hierarchy.printTeam(hierarchy, sortedGeneral, 0, 189, "C team",Q1General::getHitPoint);
                break;
            default:
                System.out.println("Please enter a correct attribute.");
        }

    }

}
