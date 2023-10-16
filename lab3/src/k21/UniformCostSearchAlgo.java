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
//			System.out.print("poll Node: "+ frontier.peek().getLabel());
//			System.out.println("\n");
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
		Node startNode = execute(root, start);
		if(startNode!=null) startNode.setPathCost(0);
		return (startNode != null) ? execute(startNode, goal) : null;
	}
}
