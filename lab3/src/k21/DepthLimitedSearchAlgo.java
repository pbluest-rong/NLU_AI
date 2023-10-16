package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthLimitedSearchAlgo {
	// return solution, failure/cutoff
	public Node recursiveExecute(Node root, String goal, int limitedDepth) {
		System.out.println("takenNode: " + root.getLabel() + " depth: " + root.getDepth());
		
		if (root.getLabel().equals(goal))
			return root;
		else if (root.getDepth() >= limitedDepth)
			return null;// return cutoff
		else {
			// cutoff_occurred? <-false
			for (Edge e : root.getChildren()) {
				e.getEnd().setParent(root);
				e.getEnd().setDepth(root.getDepth() + 1);
				Node result = recursiveExecute(e.getEnd(), goal, limitedDepth);
				if(result!=null) return result;
			}
			return null;
		}
	}

	// stack
	public Node execute(Node root, String goal, int limited) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> visitedNodes = new ArrayList<Node>();

		frontier.add(root);

		while (!frontier.isEmpty()) {
			System.out.print("frontier: ");
			frontier.forEach(n -> System.out.print(n.getLabel() + ", "));
			System.out.println();

			Node currentN = frontier.pop();
			System.out.println("takenNode: " + currentN.getLabel() + " depth: " + currentN.getDepth());
			if (currentN.getLabel().equals(goal))
				return currentN;
			else {
				visitedNodes.add(currentN);
				// check
				if (currentN.getDepth() < limited) {
					for (Edge e : currentN.getChildren()) {
						if (!frontier.contains(e.getEnd()) && !visitedNodes.contains(e.getEnd())) {
							e.getEnd().setParent(currentN);
							e.getEnd().setDepth(currentN.getDepth() + 1);
							frontier.add(e.getEnd());
						}
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);

		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);

		DepthLimitedSearchAlgo algo1 = new DepthLimitedSearchAlgo();
		System.out.println("---------------Graph Search BFS--------------");
//		Node result1 = algo1.execute(nodeS, "D", 2);
		Node result1 = algo1.recursiveExecute(nodeS, "F", 2);
		System.out.println(NodeUtils.printPath(result1));
	}
}
