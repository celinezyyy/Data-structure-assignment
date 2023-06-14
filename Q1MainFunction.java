/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

/**
 *
 * @author HP
 */
public class Q1MainFunction {

    public static void main(String[] args) {
        // Create a hierarchy
        Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();
        hierarchy.initializeGeneral();
        hierarchy.traverse(hierarchy.root);

     
    }

}
