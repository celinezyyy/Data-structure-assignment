/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

import java.util.*;
import java.util.function.Function;

/**
 *
 * @author HP
 */
public class Q1WuKingdomHierarchy {

    ArrayList<Q1General> generals = new ArrayList<>();
    Q1TreeNode root;

    public Q1WuKingdomHierarchy() {
        //Tree map function
        root = new Q1TreeNode(new Q1Emperor("Sun Quan", "Cavalry", 96, 98, 72, 77, 95));
        Q1TreeNode militaryChief = new Q1TreeNode(new Q1Chief("Zhang Zhao", "Archer", 22, 80, 89, 99, 60));
        Q1TreeNode managementChief = new Q1TreeNode(new Q1Chief("Zhou Yu", "Cavalry", 80, 86, 97, 80, 90));
        root.addChild(militaryChief);
        root.addChild(managementChief);

        generals.add(new Q1General("Xu Sheng", "Archer", 90, 78, 72, 40, 94));
        generals.add(new Q1General("Zhu Ge Jin", "Archer", 63, 61, 88, 82, 71));
        generals.add(new Q1General("Lu Su", "Infantry", 43, 87, 84, 88, 53));
        generals.add(new Q1General("Tai Shi Ci", "Cavalry", 96, 81, 43, 33, 97));
        generals.add(new Q1General("Xiao Qiao", "Infantry", 42, 52, 89, 77, 34));
        generals.add(new Q1General("Da Qiao", "Cavalry", 39, 62, 90, 62, 41));
        generals.add(new Q1General("Zhou Tai", "Infantry", 92, 89, 72, 43, 99));
        generals.add(new Q1General("Gan Ning", "Archer", 98, 92, 45, 23, 97));
        generals.add(new Q1General("Lu Meng", "Cavalry", 70, 77, 93, 83, 88));
        generals.add(new Q1General("Huang Gai", "Infantry", 83, 98, 72, 42, 89));

        for (Q1General general : generals) {
            Q1TreeNode node = new Q1TreeNode(general);
            if (general.intelligence > general.strength) {
                managementChief.addChild(node);
            } else {
                militaryChief.addChild(node);
            }
        }

    }

    //sorting functions
    void traverse(Q1TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.emperor.name);
        for (Q1TreeNode child : node.children) {
            System.out.println("--" + child.chief.name); // Display the Chiefs
            for (Q1TreeNode grandchild : child.children) {
                System.out.println("----" + grandchild.general.name); // Display the Generals
            }
        }
    }

    //sorting
    ArrayList<Q1General> sortStrengthCollection() {
        Collections.sort(generals, Comparator.comparingInt(a -> a.strength));
        for (Q1General general : generals) {
            System.out.println(general.name + " " + general.strength);
        }
        return generals;
    }

    ArrayList<Q1General> sortLeadershipSelection() {
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
        for (Q1General general : generals) {
            System.out.println(general.name + " " + general.leadership);
        }
        return generals;
    }

    ArrayList<Q1General> sortIntelligenceSelection() {
        for (int i = 1; i < generals.size(); i++) {
            Q1General currentGeneral = generals.get(i);
            int j = i - 1;
            while (j >= 0 && generals.get(j).intelligence > currentGeneral.intelligence) {
                generals.set(j + 1, generals.get(j));
                j--;
            }
            generals.set(j + 1, currentGeneral);
        }
        for (Q1General general : generals) {
            System.out.println(general.name + " " + general.intelligence);
        }
        return generals;
    }

    ArrayList<Q1General> sortPoliticBubble() {
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

    public List<Q1General> sortHitPointMerge(List<Q1General> list) {
        if (list.size() <= 1) {
            return list;
        }

        // Divide the list into two halves
        int mid = list.size() / 2;
        List<Q1General> leftHalf = list.subList(0, mid);
        List<Q1General> rightHalf = list.subList(mid, list.size());

        // Recursively sort the two halves
        List<Q1General> sortedLeft = sortHitPointMerge(leftHalf);
        List<Q1General> sortedRight = sortHitPointMerge(rightHalf);

        List<Q1General> merged = merge(sortedLeft, sortedRight);
        // Merge the sorted halves
        return merged;
    }

    /**
     * Merge two sorted lists
     */
    List<Q1General> merge(List<Q1General> list1, List<Q1General> list2) {
        List<Q1General> merged = new ArrayList<>();
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

    void performSearch(ArrayList<Q1General> sortedGeneral, int keyValue,
            Function<Q1General, Integer> attributeGetter, String attributeName) {
        ArrayList<Q1General> matchingGeneral = searchByAttribute(keyValue, sortedGeneral, attributeGetter);
        if (!matchingGeneral.isEmpty()) {
            System.out.println("\nSearched generals for " + attributeName + ":");
            for (Q1General general : matchingGeneral) {
                System.out.println("General name: " + general.name);
                System.out.println("General " + attributeName + ": " + attributeGetter.apply(general));
            }
        } else {
            System.out.println("\nGenerals with the specified ability not found for " + attributeName + ".");
        }
    }

    ArrayList<ArrayList<Q1General>> buildTeam(int minStrength, int maxStrength, ArrayList<Q1General> sortedGenerals, Function<Q1General, Integer> attributeGetter) {

        ArrayList<ArrayList<Q1General>> totalTeamStrength = new ArrayList<>();

        int N = sortedGenerals.size();
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int currentSumAttribute = attributeGetter.apply(sortedGenerals.get(i)) + attributeGetter.apply(sortedGenerals.get(left)) + attributeGetter.apply(sortedGenerals.get(right));

                if (currentSumAttribute >= minStrength && currentSumAttribute <= maxStrength) {
                    ArrayList<Q1General> teamStrength = new ArrayList<>();
                    teamStrength.add(sortedGenerals.get(i));
                    teamStrength.add(sortedGenerals.get(left));
                    teamStrength.add(sortedGenerals.get(right));
                    totalTeamStrength.add(teamStrength);
                    left++;
                    right--;
                } else if (currentSumAttribute < minStrength) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return totalTeamStrength;
    }
    
    void printTeam(Q1WuKingdomHierarchy hierarchy, ArrayList<Q1General> sortedGeneral,
            int minStrength, int maxStrength, String teamName,Function<Q1General, Integer> attributeGetter) {
        ArrayList<ArrayList<Q1General>> team = buildTeam(minStrength, maxStrength, sortedGeneral, attributeGetter);
        System.out.println("\n" + teamName + ":");
        for (int i = 0; i < team.size(); i++) {
            for (int j = 0; j < team.get(i).size(); j++) {
                if(j==team.get(i).size()-1){
                    System.out.print(team.get(i).get(j).name);
                }else{
                System.out.print(team.get(i).get(j).name + ", ");
                }
            }
            System.out.println("");
        }
    }

}
