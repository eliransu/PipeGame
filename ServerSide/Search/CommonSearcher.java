package Search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

import Solution.PipeSolution;

public abstract class CommonSearcher<TypeState> implements SearcherInterface<TypeState> {
	ArrayList<String> theSolution = new ArrayList<>();
	HashSet<State<TypeState>>hs = new HashSet<>();
	protected int numOfEvaluatedNodes =0;
	Queue<State<TypeState>> openList;
	public State<TypeState> popOpenList()
	{
		numOfEvaluatedNodes++;
		return this.openList.poll();
	}
	public void addToOpenList(State<TypeState> s)
	{
		this.openList.add(s);
	}
	public int getNumOfNodesEvaluated() {return this.numOfEvaluatedNodes;}
	protected PipeSolution backTrace(State<TypeState> init ,State<TypeState> g)
	{
		int x = 0,y = 0,rounds = 0;
		while (g!=init)
		{
			x = g.whatIChanged.getX();
			y = g.whatIChanged.getY();
			rounds = g.whatIChanged.getRounds();
			theSolution.add(x+","+y+","+rounds);
			g = g.getCameFrom();
		}
		theSolution.add("done");
		System.out.println(this.getNumOfNodesEvaluated());
		for (int i=0;i<theSolution.size();i++)
			System.out.println(theSolution.get(i));
		PipeSolution sss = new PipeSolution();
		System.out.println("num of evaluations :: " + this.numOfEvaluatedNodes);
		sss.setSolution(theSolution);
		return sss;
		
	}
}
