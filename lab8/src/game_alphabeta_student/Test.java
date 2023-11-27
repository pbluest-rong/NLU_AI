package game_alphabeta_student;

public class Test {
	public static void main(String[] args) {
		Node A = new Node("A");

		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		A.addChild(B);
		A.addChild(C);
		A.addChild(D);

		Node E = new Node("E");
		Node F = new Node("F");
		B.addChild(E);
		B.addChild(F);

		Node G = new Node("G");
		Node H = new Node("H");
		Node I = new Node("I");
		C.addChild(G);
		C.addChild(H);
		C.addChild(I);

		Node J = new Node("J");
		Node K = new Node("K");
		D.addChild(J);
		D.addChild(K);

		Node L = new Node("L", 4);
		Node M = new Node("M", 8);
		E.addChild(L);
		E.addChild(M);

		Node N = new Node("N", 9);
		Node O = new Node("O", 3);
		F.addChild(N);
		F.addChild(O);

		Node P = new Node("N", 2);
		Node Q = new Node("Q", -2);
		G.addChild(P);
		G.addChild(Q);

		Node R = new Node("R", 9);
		Node S = new Node("S", 1);
		H.addChild(R);
		H.addChild(S);

		Node T = new Node("T", 8);
		Node U = new Node("U", 4);
		I.addChild(T);
		I.addChild(U);

		Node V = new Node("V", 3);
		Node W = new Node("W", 6);
		Node X = new Node("X", 5);
		J.addChild(V);
		J.addChild(W);
		J.addChild(X);

		Node Y = new Node("Y", 7);
		Node Z = new Node("Z", 1);
		K.addChild(Y);
		K.addChild(Z);

		MiniMaxSearchAlgo miniMaxSearchAlgo = new MiniMaxSearchAlgo();
		//
//		miniMaxSearchAlgo.execute(A);
		
		AlphaBetaSearchAlgo alphaBetaSearchAlgo = new AlphaBetaSearchAlgo();
		alphaBetaSearchAlgo.execute(A);
	}
}
