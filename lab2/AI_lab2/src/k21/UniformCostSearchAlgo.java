package k21;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UniformCostSearchAlgo implements ISearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		List<Node> exploredNodes = new ArrayList<Node>();
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((o1, o2) -> {
			return (int) (o1.getPathCost() - o2.getPathCost());
		});
		frontier.add(root);
		while (!frontier.isEmpty()) {
			System.out.print("frontier: ");
			frontier.forEach(n -> System.out.print(n.getLabel() + " - " + n.getPathCost() + "; "));
			System.out.println();
			System.out.print("poll Node: "+ frontier.peek().getLabel());
			System.out.println("\n");
			Node currentNode = frontier.poll();
			exploredNodes.add(currentNode);

			if (currentNode.getLabel().equals(goal))
				return currentNode;
			else {
				for (Edge e : currentNode.getChildren()) {
					if (!exploredNodes.contains(e.getEnd())) {
						e.getEnd().setPathCost(currentNode.getPathCost() + e.getWeight());
						e.getEnd().setParent(currentNode);
						frontier.add(e.getEnd());
					} else {
						for (Node nodeInFrontier : frontier)
							if (e.getEnd().getPathCost() <= nodeInFrontier.getPathCost()) {
								e.getEnd().setPathCost(currentNode.getPathCost() + e.getWeight());
								e.getEnd().setParent(currentNode);
								nodeInFrontier = e.getEnd();
							}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
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

		ISearchAlgo algo1 = new UniformCostSearchAlgo();
		System.out.println("---------------Graph Search UCS--------------");
		Node result1 = algo1.execute(nodeS, "G");
		System.out.println(NodeUtils.printPath(result1)+" - "+result1.getPathCost());
	}
}
