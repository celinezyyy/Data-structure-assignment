/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

/**
 *
 * @author HP
 */
public class Q1General extends Q1Character {
    boolean used;
    
    public Q1General(String name, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        super(name, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.used = false;
    }
    
    public boolean isUsed() {
        return used;
    }
    
    public void setUsed(boolean used) {
        this.used = used;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public int getLeadership(){
        return leadership;
    }
    
    public int getIntelligence(){
        return intelligence;
    }
    
    public int getPolitic(){
        return politic;
    }
    
    public int getHitPoint(){
        return hitPoint;
    }
}