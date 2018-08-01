package Search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

import Solution.PipeSolution;
import Solution.Solution;

public class BestFirstSearch<TypeState> extends CommonSearcher<TypeState>
{
	@Override
	
	public Solution search(Searchable searchable) {
		this.openList= new PriorityQueue<State<TypeState>>();
		this.addToOpenList((searchable.getInitialState()));
		while (!openList.isEmpty())
		{
			State<TypeState> n = this.popOpenList();
			//System.out.println(n.getCost());
			hs.add(n);
			if (searchable.isGoalState(n))
			{
				System.out.println("overed : " + this.getNumOfNodesEvaluated() + "evaluated nodes!");
				return backTrace(searchable.getInitialState(), n);
			}
			ArrayList<State<TypeState>> posibileStates = searchable.getAllPosibileStates(n);
			for (State<TypeState> state : posibileStates)
			{
				TypeState ar = state.getState();

				if (!hs.contains(state) && !(openList.contains(state))) {
					if (state!=searchable.getInitialState())
					{
						state.setCameFrom(n);
						addToOpenList(state);
					}
				}
			}
		}
		return null;
	}

	

	
}
