
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Q5TestGraph {
	static Q5Graph<Integer> graph1 = new Q5Graph<>();
	static Scanner sc = new Scanner(System.in);
	static ArrayList<ArrayList<Q1General>> Allteam = new ArrayList<>();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Q1WuKingdomHierarchy hierarchy = new Q1WuKingdomHierarchy();
		ArrayList<Q1General> sortedGeneral = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			graph1.addVertex(i);
		}

		addUndirectedEdge(1, 3);
		addUndirectedEdge(1, 10);
		addUndirectedEdge(1, 2);
		addUndirectedEdge(1, 6);

		addUndirectedEdge(2, 4);

		addUndirectedEdge(3, 4);
		graph1.addEdge(7, 3);

		addUndirectedEdge(4, 5);

		addUndirectedEdge(5, 6);
		addUndirectedEdge(5, 7);

		addUndirectedEdge(6, 7);
		addUndirectedEdge(6, 8);

		addUndirectedEdge(7, 9);

		addUndirectedEdge(8, 7);
		addUndirectedEdge(8, 9);
		addUndirectedEdge(8, 10);

		addUndirectedEdge(9, 10);

		System.out.print("Enter node without food : ");
		int index = sc.nextInt();
		sc.nextLine();
		
		if (index < 6 || index > 8)
			graph1.removeVertex(graph1.getIndex(index));
		
        
		Q5HamiltonianCycle<Integer> hamCycle = new Q5HamiltonianCycle<>(graph1.head,
				graph1.getAllVertexObjects().size());
		int pathSize = hamCycle.findCycle();
		
		int food = pathSize * 100;
		
		System.out.println();
		System.out.print("Select team type (Politic or Intelligence) : ");
		
		String Generals = sc.nextLine();
		
		System.out.println();
		System.out.print("Choose team strength (C - S) : ");
		String grade = sc.nextLine();
		System.out.println();
		
		
		if (Generals.equals("Politic")) {
			sortedGeneral = hierarchy.sortPoliticBubble();
			food *= inputIdentify(Generals, grade, sortedGeneral, hierarchy);
		} else if (Generals.equals("Intelligence")) {
			sortedGeneral = hierarchy.sortIntelligenceSelection();
			food *= inputIdentify(Generals, grade, sortedGeneral, hierarchy);
		}
		
        for (int i = 0; i < Allteam.size(); i++) {
        	System.out.print(i + " ");
            for (int j = 0; j < Allteam.get(i).size(); j++) {
                if(j==Allteam.get(i).size()-1){
                    System.out.print(Allteam.get(i).get(j).getName());
                }else{
                System.out.print(Allteam.get(i).get(j).getName() + ", ");
                }
            }
            System.out.println("");
        }
        
        System.out.println();
        System.out.print("Select a team based on index : ");
        int selectTeam = sc.nextInt();
		
        String otherGrade = "";
        
        ArrayList<Q1General> selectedTeam = Allteam.get(selectTeam);
        
        otherGrade = teamGrading(Generals, selectedTeam);
        
        food *= determineOtherGradesMulti(Generals, otherGrade);
        
        System.out.println("\nGrade 1 : " + grade);
        System.out.println("\nGrade 2 : " + otherGrade);
        System.out.println("\nTotal Food : " + food);
        
		
	}

	public static void addUndirectedEdge(int source, int destination) {
		graph1.addEdge(source, destination);
		graph1.addEdge(destination, source);
	}

	public static double inputIdentify(String type, String grade, ArrayList<Q1General> sortedGeneral, Q1WuKingdomHierarchy hierarchy) {
		if (type.equals("Politic")) {
			switch(grade) {
			case "S" :
				Allteam = hierarchy.buildTeam(250, 300, sortedGeneral, Q1General::getPolitic);
				return 2;
			case "A" :
				Allteam = hierarchy.buildTeam(220, 329, sortedGeneral, Q1General::getPolitic);
				return 1.5;
			case "B" :
				Allteam = hierarchy.buildTeam(190, 219, sortedGeneral, Q1General::getPolitic);
				return 1.2;
			case "C" :
				Allteam = hierarchy.buildTeam(0, 189, sortedGeneral, Q1General::getPolitic);
				return 1;
			}
		}
		else if (type.equals("Intelligence")) {
			switch(grade) {
			case "S" :
				Allteam = hierarchy.buildTeam(250, 300, sortedGeneral, Q1General::getIntelligence);
				return 1.8;
			case "A" :
				Allteam = hierarchy.buildTeam(220, 329, sortedGeneral, Q1General::getIntelligence);
				return 1.3;
			case "B" :
				Allteam = hierarchy.buildTeam(190, 219, sortedGeneral, Q1General::getIntelligence);
				return 1;
			case "C" :
				Allteam = hierarchy.buildTeam(0, 189, sortedGeneral, Q1General::getIntelligence);
				return 0.8;
			}
		}
		return -1;
	}
	
	public static String teamGrading(String type, ArrayList<Q1General> team) {
		int grade = 0;
		
		if (type.equals("Politic")) {
			for (int i = 0; i < team.size(); i++)
				grade += team.get(i).getIntelligence();
		}
		
		else {
			for (int i = 0; i < team.size(); i++)
				grade += team.get(i).getPolitic();
		}
		
		if (grade >= 250) 
			return "S";
		else if (grade >= 220) 
			return "A";
		else if (grade >= 190) 
			return "B";
		else if (grade <= 190) 
			return "C";
		
		return "";
	}
	
	public static double determineOtherGradesMulti(String type, String grade) {
		if (type.equals("Intelligence")) {
			switch(grade) {
			case "S" :
				return 2;
			case "A" :
				return 1.5;
			case "B" :
				return 1.2;
			case "C" :
				return 1;
			}
		}
		else if (type.equals("Politic")) {
			switch(grade) {
			case "S" :
				return 1.8;
			case "A" :
				return 1.3;
			case "B" :
				return 1;
			case "C" :
				return 0.8;
			}
		}
		return -1;
	}

}
