package lab4.inform.student;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchAlgo implements IInformedSearchAlgo {
	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<Node>((o1, o2) -> {
			Double f1 = o1.getH() + o1.getG();
			Double f2 = o2.getH() + o2.getG();
			int result = f1.compareTo(f2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		});

		List<Node> exploredNodes = new ArrayList<Node>();
		frontier.add(root);

		while (!frontier.isEmpty()) {
			System.out.print("frontier: ");
			frontier.forEach(n -> System.out.print(n.getLabel() + " - " + (n.getG() + n.getH()) + "; "));
			System.out.println();
			System.out.print("poll Node: " + frontier.peek().getLabel());
			System.out.println("\n");

			Node currentNode = frontier.poll();
			exploredNodes.add(currentNode);

			if (currentNode.getLabel().equals(goal))
				return currentNode;
			else {
				for (Edge e : currentNode.getChildren()) {
					Node childNode = e.getEnd();

					if (!exploredNodes.contains(e.getEnd()) && !frontier.contains(childNode)) {
						frontier.add(childNode);
						childNode.setG(currentNode.getG() + e.getWeight());
						childNode.setParent(currentNode);
					} else {
						double f1 = childNode.getH() + childNode.getG();
						for (Node nodeInFrontier : frontier) {
							double f2 = nodeInFrontier.getH() + nodeInFrontier.getG();
							if (f1 <= f2) {
								childNode.setG(currentNode.getG() + e.getWeight());
								childNode.setParent(currentNode);
								nodeInFrontier = childNode;
							}
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
		if (startNode != null)
			startNode.setG(0);
		return (startNode != null) ? execute(startNode, goal) : null;
	}
}
