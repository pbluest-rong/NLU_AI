package game_alphabeta_student;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		while (true)
			if (node.isTerminal()) {
				System.out.println("End game: " + node.getLabel());
				break;
			} else {
				// max
				maxValue(node);
				for (Node child : node.getChildren())
					if (child.getValue() == node.getValue()) {
						System.out.println("max: " + node + " --> " + child);
						node = child;
					}
				// min
				minValue(node);
				for (Node child : node.getChildren())
					if (child.getValue() == node.getValue()) {
						System.out.println("min: " + node + " --> " + child);
						node = child;
					}
			}
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		// Enter your code here
		int value = node.getValue();
		if (!node.isTerminal()) {
			value = minValue(node.getChildren().get(0));

			for (int i = 1; i < node.getChildren().size(); i++) {
				Node child = node.getChildren().get(i);
				int childValue = minValue(child);
				if (value < childValue)
					value = childValue;
			}
			node.setValue(value);
		}
		return value;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		int value = node.getValue();
		if (!node.isTerminal()) {
			value = maxValue(node.getChildren().get(0));

			for (int i = 1; i < node.getChildren().size(); i++) {
				Node child = node.getChildren().get(i);
				int childValue = maxValue(child);
				if (value > childValue)
					value = childValue;
			}
			node.setValue(value);
		}
		return value;
	}
}
