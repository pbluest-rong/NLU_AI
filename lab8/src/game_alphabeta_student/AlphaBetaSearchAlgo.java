package game_alphabeta_student;

public class AlphaBetaSearchAlgo implements ISearchAlgo {

	@Override
	public void execute(Node node) {
		int v = maxValue(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
		node.setValue(v);
		System.out.println(node.getLabel() + " - " + node.getValue());
	}

	// maxValue method for max player
	public int maxValue(Node node, int alpha, int beta) {
		if (node.isTerminal())
			return node.getValue();

		int v = Integer.MIN_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.max(v, minValue(child, alpha, beta));
			alpha = Math.max(alpha, v);
			if (v >= beta) {
				return v;
			}
		}
		return v;
	}

	// minValue method for min player
	public int minValue(Node node, int alpha, int beta) {
		if (node.isTerminal())
			return node.getValue();

		int v = Integer.MAX_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.min(v, maxValue(child, alpha, beta));
			beta = Math.min(beta, v);
			if (v <= alpha) {
				return v;
			}
		}

		return v;
	}
}
