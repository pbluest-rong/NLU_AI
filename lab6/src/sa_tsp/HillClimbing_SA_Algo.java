package sa_tsp;

import student.Node;

public class HillClimbing_SA_Algo {
	int stepClimbed = 0;
	int stepClimbedAfterRandomRestart = 0;
	int randomRestarts = 0;

	public Node execute(Node initialState) {
		Node neighbour = null;
		if (initialState.getH() == 0) {
			return initialState;
		}
		stepClimbed++;
		while (initialState.getH() != 0) {
			neighbour = findBestSub(initialState);
			if (neighbour.getH() >= initialState.getH()) { // currentState is best state
				stepClimbed = 0;
				return initialState;
			}
			initialState = neighbour;
		}
		return initialState;
	}

	public Node findBestSub(Node state) {
		int i = Integer.MAX_VALUE;
		Node temp = new Node();
		for (Node child : state.generateAllCandidates()) {
			if (child.getH() < i) {
				i = child.getH();
				temp = child;
			}
		}
		return temp;
	}

	public Node executeHillClimbingWithRandomRestart(Node initialState) {
		Node temp = execute(initialState);
		if (temp.getH() == 0) {
			return temp;
		}
		while (temp.getH() != 0) {
			randomRestarts++;
			temp.generateBoard();
			temp = execute(temp);
		}
		return temp;
	}

	public Node executeSA(Node initialState) {
		double T = 1000.0;
		double coolingRate = 0.995;
		Node current = new Node(initialState);
		while (current.getH() != 0) {
			Node next = current.selectNextRandomCandidate();
			int deltaE = next.getH() - current.getH();
			if (deltaE < 0) {
				current = new Node(next);
			} else if (Math.exp(deltaE / T) > Math.random()) {
				current = new Node(next);
			}
			T *= coolingRate;
		}
		return current;
	}
}
