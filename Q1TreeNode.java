/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package data.struc.assignment;

import java.util.*;
/**
 *
 * @author HP
 */
public class Q1TreeNode {
    Q1General general;
    Q1Emperor emperor;
    Q1Chief chief;
    List<Q1TreeNode> children;
    
    Q1TreeNode(Q1General general){
        this.general = general;
        children = new ArrayList<>();
    }
    
    Q1TreeNode(Q1Emperor emperor){
        this.emperor = emperor;
        children = new ArrayList<>();
    }
    
    Q1TreeNode(Q1Chief chief){
        this.chief = chief;
        children = new ArrayList<>();
    }
    
    void addChild(Q1TreeNode child){
        children.add(child);
    }
    
}
