package lab7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		initPopulation();
		int k = 0;
		while (k < MAX_ITERATIONS) {
			List<Node> newpopulation = new ArrayList<Node>();
			for (int i = 0; i < this.POP_SIZE; i++) {
				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();
				Node child = reproduce(x, y);
				if (MUTATION_RATE < rd.nextDouble())
					mutate(child);

				if (child.getH() == 0)
					return child;

				newpopulation.add(child);
			}
			this.population = newpopulation;
			k++; // Increment the iteration counter
		}
		Collections.sort(this.population);
		return this.population.get(0);
	}

// Mutate an individual by selecting a random Queen and 
//move it to a random row.
	public void mutate(Node node) {
		int c = rd.nextInt(Node.N);
		int newRow = rd.nextInt(Node.N);
		node.getState()[c].setRow(newRow);
	}

//Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {
		int c = rd.nextInt(Node.N);
		Node n = new Node();
		n.generateBoard();

		for (int i = 0; i < c + 1; i++) {
			if (i <= c)
				n.setRow(i, x.getRow(i));
			else
				n.setRow(i, y.getRow(i));
		}
		return n;
	}

//Select K individuals from the population at random and 
//select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {
		int max = Integer.MAX_VALUE;
		Node result = new Node();
		List<Node> arr = new ArrayList<Node>();
		for (int i = 0; i < 5; i++) {
			int temp = rd.nextInt(POP_SIZE);
			arr.add(population.get(temp));
		}
		for (Node child : arr) {
			if (child.getH() < max) {
				max = child.getH();
				result = child;
			}
		}
		return result;
	}

//Select a random parent from the population
	public Node getParentByRandomSelection() {
		int temp = rd.nextInt(POP_SIZE);
		return population.get(temp);
	}

	public static void main(String[] args) {
		GA_NQueenAlgo ga_NQueenAlgo = new GA_NQueenAlgo();
		Node x = new Node();
		x.generateBoard();
		x.displayBoard();
		System.out.println("\n");
		Node y = new Node();
		y.generateBoard();
		y.displayBoard();
		System.out.println("\n");
		Node z = ga_NQueenAlgo.reproduce(x, y);
		z.displayBoard();
		System.out.println("\n");
		Node result = ga_NQueenAlgo.execute(); // Store the result in a variable
		result.displayBoard(); // Display the board of the result
	}

}
