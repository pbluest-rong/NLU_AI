package lab9.game_nim_student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> re = new ArrayList<Node>();

		int f_e = data.get(0);
		int check = f_e - 1;
		while (check > f_e / 2) {
			Node child = new Node();
			List<Integer> newData = new ArrayList<Integer>(data);
			newData.remove(0);
			newData.add(0, check);
			newData.add(0, f_e - check);
			child.addAll(newData);
			re.add(child);
			check--;
		}
		return re;
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		Collections.sort(this.data, DESCOMPARATOR);
		if (this.data.get(0) <= 2)
			return true;
		return false;
	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
