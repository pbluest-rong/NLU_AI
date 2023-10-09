package k21;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearchAlgo2 implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();
		List<Node> visitedNodes = new ArrayList<Node>();

		frontier.add(root);

		while (!frontier.isEmpty()) {
			System.out.print("frontier: ");
			frontier.forEach(n -> System.out.print(n.getLabel() + ", "));
			System.out.println();

			Node currentN = frontier.pop();
			visitedNodes.add(currentN);

			for (Edge e : currentN.getChildren()) {
				if (e.getEnd() != null && e.getEnd().getLabel().equals(goal)) {
					e.getEnd().setParent(currentN);
					return e.getEnd();
				} else if (!frontier.contains(e.getEnd()) && !visitedNodes.contains(e.getEnd())) {
					e.getEnd().setParent(currentN);
					frontier.add(e.getEnd());
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		return execute(execute(root, start), goal);
	}

	// task3: tree search: Node implement Clone -> clone()?
	public Node treeSearchExecute(Node root, String goal) {
		Stack<Node> frontier = new Stack<Node>();

		frontier.add(root);

		while (!frontier.isEmpty()) {
			System.out.print("frontier: ");
			frontier.forEach(n -> System.out.print(n.getLabel() + ", "));
			System.out.println();

			Node currentN = frontier.pop();

			for (Edge e : currentN.getChildren()) {
				if (e.getEnd() != null && e.getEnd().getLabel().equals(goal)) {
					e.getEnd().setParent(currentN);
					return e.getEnd();
				} else if (!frontier.contains(e.getEnd())) {
					e.getEnd().setParent(currentN);
					frontier.add(e.getEnd().clone());
				}
			}
		}
		return null;

	}
}
