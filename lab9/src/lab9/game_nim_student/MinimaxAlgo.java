package lab9.game_nim_student;

import java.util.List;

public class MinimaxAlgo {
	public void executeEnd(Node MINplayerNode) {
		Node node = MINplayerNode;
		String player = "min";
		while (true)
			if (node.isTerminal()) {
				if (player.equals("min"))
					System.out.println(player + ":  " + node.toString() + " - " + minValue(node));
				else if (player.equals("max"))
					System.out.println(player + ":  " + node.toString() + " - " + maxValue(node));
				break;
			} else {
				if (player.equals("min")) {
					System.out.println(player + ":  " + node.toString() + " - " + minValue(node));
					player = "max";
					node = node.getSuccessors().get(0);
				} else if (player.equals("max")) {
					System.out.println(player + ":  " + node.toString() + " - " + maxValue(node));
					player = "min";
					node = node.getSuccessors().get(0);
				}
			}

	}

	public void execute(Node node) {
		int v = minValue(node);
		System.out.println(v);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal())
			return 0;
		else {
			int v = Integer.MIN_VALUE;
			List<Node> successors = node.getSuccessors();
			for (Node s : successors) {
				v = Math.max(v, minValue(s));
			}
			return v;
		}
	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node) {
		if (node.isTerminal())
			return 1;
		else {
			int v = Integer.MAX_VALUE;
			List<Node> successors = node.getSuccessors();
			for (Node s : successors) {
				v = Math.min(v, maxValue(s));
			}
			return v;
		}
	}
}
