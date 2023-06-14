/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;

/**
 *
 * @author HP
 */
public class Q2SoldierArrangement {
    
    Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();
    ArrayList<Q1General> generals = hierarchy.initializeGeneral();
    
    //Collection sorting
    public ArrayList<Q1General> sortStrengthCollection() {
        Collections.sort(generals, Comparator.comparingInt(a -> a.strength));
        return generals;
    }

    //Selection sorting
    public ArrayList<Q1General> sortLeadershipSelection() {
        for (int i = 0; i < generals.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < generals.size(); j++) {
                if (generals.get(j).leadership < generals.get(minIndex).leadership) {
                    minIndex = j;
                }
            }
            Q1General temp = generals.get(i);
            generals.set(i, generals.get(minIndex));
            generals.set(minIndex, temp);
        }
        return generals;
    }

    //Insertion sorting
    public ArrayList<Q1General> sortIntelligenceInsertion() {
        for (int i = 1; i < generals.size(); i++) {
            Q1General currentGeneral = generals.get(i);
            int j = i - 1;
            while (j >= 0 && generals.get(j).intelligence > currentGeneral.intelligence) {
                generals.set(j + 1, generals.get(j));
                j--;
            }
            generals.set(j + 1, currentGeneral);
        }
        return generals;
    }

    //Bubble sorting
    public ArrayList<Q1General> sortPoliticBubble() {
        boolean needNextPass = true;
        for (int k = 1; k < generals.size() && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < generals.size() - k; i++) {
                if (generals.get(i).politic > generals.get(i + 1).politic) {
                    Q1General temp = generals.get(i);
                    generals.set(i, generals.get(i + 1));
                    generals.set(i + 1, temp);
                    needNextPass = true;
                }
            }
        }

        return generals;
    }

    //Merge sorting
    public ArrayList<Q1General> sortHitPointMerge(ArrayList<Q1General> list) {
        if (list.size() <= 1) {
            return list;
        }

        // Divide the list into two halves
        int mid = list.size() / 2;
        ArrayList<Q1General> leftHalf = new ArrayList<>(list.subList(0, mid));
        ArrayList<Q1General> rightHalf = new ArrayList<>(list.subList(mid, list.size()));

        // Recursively sort the two halves
        ArrayList<Q1General> sortedLeft = sortHitPointMerge(leftHalf);
        ArrayList<Q1General> sortedRight = sortHitPointMerge(rightHalf);

        ArrayList<Q1General> merged = merge(sortedLeft, sortedRight);
        // Merge the sorted halves
        return merged;
    }

    /**
     * Merge two sorted lists
     */
    ArrayList<Q1General> merge(ArrayList<Q1General> list1, ArrayList<Q1General> list2) {
        ArrayList<Q1General> merged = new ArrayList<>();
        int i = 0; // Current index in list1
        int j = 0; // Current index in list2

        while (i < list1.size() && j < list2.size()) {
            Q1General general1 = list1.get(i);
            Q1General general2 = list2.get(j);

            if (general1.hitPoint < general2.hitPoint) {
                merged.add(general1);
                i++;
            } else {
                merged.add(general2);
                j++;
            }
        }

        // Add remaining elements from list1, if any
        while (i < list1.size()) {
            merged.add(list1.get(i));
            i++;
        }

        // Add remaining elements from list2, if any
        while (j < list2.size()) {
            merged.add(list2.get(j));
            j++;
        }

        return merged;
    }

    //searching
    ArrayList<Q1General> searchByAttribute(int keyAttribute, ArrayList<Q1General> sortedGenerals, Function<Q1General, Integer> attributeGetter) {
        ArrayList<Q1General> matchingGenerals = new ArrayList<>();
        int left = 0;
        int right = sortedGenerals.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            Q1General currentGeneral = sortedGenerals.get(mid);
            int currentAttribute = attributeGetter.apply(currentGeneral);

            if (currentAttribute == keyAttribute) {
                matchingGenerals.add(currentGeneral);
                // Check for other generals with the same strength to the left
                int prev = mid - 1;
                while (prev >= 0 && attributeGetter.apply(sortedGenerals.get(prev)) == keyAttribute) {
                    matchingGenerals.add(sortedGenerals.get(prev));
                    prev--;
                }
                // Check for other generals with the same strength to the right
                int next = mid + 1;
                while (next < sortedGenerals.size() && attributeGetter.apply(sortedGenerals.get(next)) == keyAttribute) {
                    matchingGenerals.add(sortedGenerals.get(next));
                    next++;
                }
                return matchingGenerals;
            } else if (currentAttribute < keyAttribute) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return matchingGenerals; // General with the specified ability not found
    }

    public String performSearch(ArrayList<Q1General> sortedGeneral, int keyValue,
            Function<Q1General, Integer> attributeGetter, String attributeName) {
        String s = "";
        ArrayList<Q1General> matchingGeneral = searchByAttribute(keyValue, sortedGeneral, attributeGetter);
        if (!matchingGeneral.isEmpty()) {
            s += "\nSearched generals for " + attributeName + ":";
            for (Q1General general : matchingGeneral) {
                s += "\nGeneral name: " + general.name;
                s += "\nGeneral " + attributeName + ": " + attributeGetter.apply(general);
            }
        } else {
            s += "\nGenerals with the specified ability not found for " + attributeName + ".";
        }
        return s;
    }

    public ArrayList<ArrayList<Q1General>> buildTeam(int minStrength, int maxStrength, ArrayList<Q1General> sortedGenerals, Function<Q1General, Integer> attributeGetter) {

        ArrayList<ArrayList<Q1General>> totalTeamAttribute = new ArrayList<>();

        int N = sortedGenerals.size();
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int currentSumAttribute = attributeGetter.apply(sortedGenerals.get(i)) + attributeGetter.apply(sortedGenerals.get(left)) + attributeGetter.apply(sortedGenerals.get(right));

                if (currentSumAttribute >= minStrength && currentSumAttribute <= maxStrength) {
                    ArrayList<Q1General> suggestedTeam = new ArrayList<>();
                    suggestedTeam.add(sortedGenerals.get(i));
                    suggestedTeam.add(sortedGenerals.get(left));
                    suggestedTeam.add(sortedGenerals.get(right));
                    totalTeamAttribute.add(suggestedTeam);
                    left++;
                    right--;
                } else if (currentSumAttribute < minStrength) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return totalTeamAttribute;
    }

    public void printTeam(Q1WuKingdomHierarchy hierarchy, ArrayList<Q1General> sortedGeneral,
            int minStrength, int maxStrength, String teamName, Function<Q1General, Integer> attributeGetter) {
        ArrayList<ArrayList<Q1General>> team = buildTeam(minStrength, maxStrength, sortedGeneral, attributeGetter);
        System.out.println("\n" + teamName + ":");
        for (int i = 0; i < team.size(); i++) {
            for (int j = 0; j < team.get(i).size(); j++) {
                if (j == team.get(i).size() - 1) {
                    System.out.println(team.get(i).get(j).name);
                } else {
                    System.out.println(team.get(i).get(j).name + ", ");
                }
            }
            System.out.println("\n");
        }

    }
}
