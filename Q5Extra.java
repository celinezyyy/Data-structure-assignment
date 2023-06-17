
import java.util.ArrayList;
import java.util.Scanner;

public class Q5Extra {
	static Q5Graph<Integer> graph = new Q5Graph<>();
	static ArrayList<ArrayList<Q1General>> Allteam = new ArrayList<>();

	public static void main(String[] args) {
		//init
		Scanner sc = new Scanner(System.in);
		Q2SoldierArrangement soldier = new Q2SoldierArrangement();
		ArrayList<Q1General> sortedGeneral = new ArrayList<>();
		String path;
		String otherType = "";
		
		//init graph
		initGraph();
		
		
		//find 1st cycle to backup if the node removed later will produce an acyclic graph
		Q5HamiltonianCycle<Integer> hamCycle1 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		hamCycle1.findCycle();
		
		path = hamCycle1.pathString;

		System.out.print("Enter node without food : ");
		int index = sc.nextInt();
		sc.nextLine();
		
		//removing vertex based on user prompt
		graph.removeVertex(graph.getIndex(index));  

		//finding the path after the node have been removed
		Q5HamiltonianCycle<Integer> hamCycle2 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		int pathSize = hamCycle2.findCycle() - 2; //excluding 1st node (1) and last node (1)
		
		//print out path
		System.out.println("Path(s) : ");
		//if graph is acyclic after removing, then print out backup
		if(!hamCycle2.hasCycle) {
			System.out.println(path);
			System.out.println("Node " + index + " has to be connected!");
			pathSize = hamCycle1.size - 2;
		}
		//else continue printing
		else {
			System.out.println(hamCycle2.pathString);
		}
		
		//figure out food value from the node visited
		int food = pathSize * 100;
		
		//prompt user to select politic or intelligence team
		System.out.print("Select team type (Politic or Intelligence) : ");

		String teamType = sc.nextLine();

		//prompt user to select the team strength
		System.out.println();
		System.out.print("Choose team strength (C - S) : ");
		String grade = sc.nextLine();
		System.out.println();

		//if the team selected is politic
		if (teamType.equals("Politic")) {
			//get the list of team that is sorted by its politic stats
			sortedGeneral = soldier.sortPoliticBubble();
			//multiply food value by politic stats
			food *= inputIdentify(teamType, grade, sortedGeneral, soldier);
			//set the other type to intelligence
			otherType = "Intelligence";
		//vice versa
		} else if (teamType.equals("Intelligence")) {
			sortedGeneral = soldier.sortIntelligenceInsertion();
			food *= inputIdentify(teamType, grade, sortedGeneral, soldier);
			otherType = "Politic";
		}
		
		//print the team names
		for (int i = 0; i < Allteam.size(); i++) {
			System.out.print(i + " ");
			for (int j = 0; j < Allteam.get(i).size(); j++) {
				if (j == Allteam.get(i).size() - 1) {
					System.out.print(Allteam.get(i).get(j).name);
				} else {
					System.out.print(Allteam.get(i).get(j).name + ", ");
				}
			}
			System.out.println("");
		}
		
		//after printing, prompt user to select the team based on the index given
		System.out.println();
		System.out.print("Select a team based on index : ");
		int selectTeam = sc.nextInt();

		String otherGrade = "";
		
		//get the team from the list of teams based on index
		ArrayList<Q1General> selectedTeam = Allteam.get(selectTeam);

		//get the team grade of the other type (if we choose politic at first, the other type will be intelligence)
		otherGrade = teamGrading(teamType, selectedTeam);
		//multiply the food value
		food *= determineOtherGradesMulti(teamType, otherGrade);
		
		//print out the team types and final food value
		System.out.println("\nGrade 1 (" + teamType + ") : "+ grade);
		System.out.println("\nGrade 2 (" + otherType + ") : " + otherGrade);
		System.out.println("\nTotal Food : " + food);
	}

	public static void initGraph() {
		
		//this method is to initialize the graph
		
		for (int i = 1; i <= 10; i++) {
			graph.addVertex(i);
		}

		addUndirectedEdge(1, 3);
		addUndirectedEdge(1, 10);
		addUndirectedEdge(1, 2);
		addUndirectedEdge(1, 6);

		addUndirectedEdge(2, 4);

		addUndirectedEdge(3, 4);
		graph.addEdge(3, 7);

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
	}

	public static void addUndirectedEdge(int source, int destination) {
		
		//this method is for adding undirected edges
		
		graph.addEdge(source, destination);
		graph.addEdge(destination, source);
	}

	public static double inputIdentify(String type, String grade, ArrayList<Q1General> sortedGeneral,
			Q2SoldierArrangement hierarchy) {
		//this method is for building the teams according to the grade
		
		if (type.equals("Politic")) {
			switch (grade) {
			case "S":
				Allteam = hierarchy.buildTeam(250, 300, sortedGeneral, Q1General::getPolitic);
				return 2;
			case "A":
				Allteam = hierarchy.buildTeam(220, 249, sortedGeneral, Q1General::getPolitic);
				return 1.5;
			case "B":
				Allteam = hierarchy.buildTeam(190, 219, sortedGeneral, Q1General::getPolitic);
				return 1.2;
			case "C":
				Allteam = hierarchy.buildTeam(0, 189, sortedGeneral, Q1General::getPolitic);
				return 1;
			}
		} else if (type.equals("Intelligence")) {
			switch (grade) {
			case "S":
				Allteam = hierarchy.buildTeam(250, 300, sortedGeneral, Q1General::getIntelligence);
				return 1.8;
			case "A":
				Allteam = hierarchy.buildTeam(220, 249, sortedGeneral, Q1General::getIntelligence);
				return 1.3;
			case "B":
				Allteam = hierarchy.buildTeam(190, 219, sortedGeneral, Q1General::getIntelligence);
				return 1;
			case "C":
				Allteam = hierarchy.buildTeam(0, 189, sortedGeneral, Q1General::getIntelligence);
				return 0.8;
			}
		}
		return -1;
	}

	public static String teamGrading(String type, ArrayList<Q1General> team) {
		
		//this method is for determining the other stats grade
		
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
		
		//this method is for determining the multiplication value of the other stats according to the grade given
		
		if (type.equals("Intelligence")) {
			switch (grade) {
			case "S":
				return 2;
			case "A":
				return 1.5;
			case "B":
				return 1.2;
			case "C":
				return 1;
			}
		} else if (type.equals("Politic")) {
			switch (grade) {
			case "S":
				return 1.8;
			case "A":
				return 1.3;
			case "B":
				return 1;
			case "C":
				return 0.8;
			}
		}
		return -1;
	}

}
