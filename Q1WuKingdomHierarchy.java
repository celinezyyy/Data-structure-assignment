/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;

public class Q1WuKingdomHierarchy {

    
    Q1TreeNode root;

    public ArrayList<Q1General> initializeGeneral() {
        
        ArrayList<Q1General> generals = new ArrayList<>();
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
        
        return generals;

    }

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

  

}


}
