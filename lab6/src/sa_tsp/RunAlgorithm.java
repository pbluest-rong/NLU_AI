package sa_tsp;

import student.Node;
import student.Queen;

//https://www.baeldung.com/java-simulated-annealing-for-traveling-salesman
//other: http://www.theprojectspot.com/ztutorial-post/simulated-annealing-algorithm-for-beginners/6
public class RunAlgorithm {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Queen q1 = new Queen(4, 0);
		Queen q2 = new Queen(5, 1);
		Queen q3 = new Queen(6, 2);
		Queen q4 = new Queen(3, 3);
		Queen q5 = new Queen(4, 4);
		Queen q6 = new Queen(5, 5);
		Queen q7 = new Queen(6, 6);
		Queen q8 = new Queen(5, 7);

		Queen[] state = { q1, q2, q3, q4, q5, q6, q7, q8 };

		Node test = new Node(state);
		test.displayBoard();

		System.out.println("--------------------------------");
		HillClimbing_SA_Algo algo = new HillClimbing_SA_Algo();
		System.out.println("HillClimbing ALgo");
		algo.executeHillClimbingWithRandomRestart(test).displayBoard();
		System.out.println("SA Algo");
		algo.executeSA(test).displayBoard();
	}

}
