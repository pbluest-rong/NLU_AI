package k21;

public class Tester {
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
		

		ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
		System.out.println("---------------Graph Search BFS--------------");
		Node result1 = algo1.execute(nodeS, "D", "H");
		System.out.println(NodeUtils.printPath(result1));
		System.out.println("---------------Tree Search BFS--------------");
		Node result2 = ((BreadthFirstSearchAlgo) algo1).treeSearchExecute(nodeS, "H");
		System.out.println(NodeUtils.printPath(result2));
		
		ISearchAlgo algo2 = new DepthFirstSearchAlgo2();
		System.out.println("---------------Graph Search DFS--------------");
		Node result3 = algo2.execute(nodeS, "H");
		System.out.println(NodeUtils.printPath(result3));
		System.out.println("----------------Tree Search DFS--------------");
		Node result4 =((DepthFirstSearchAlgo2) algo2).treeSearchExecute(nodeS, "H");
		System.out.println(NodeUtils.printPath(result4));
	}
}
