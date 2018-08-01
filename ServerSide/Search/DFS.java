package Search;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import Solution.Solution;

public class DFS<TypeState> extends CommonSearcher<TypeState> {
	
	@Override
	public Solution search(Searchable searchable) {
		// TODO Auto-generated method stub
		Stack<State<TypeState>> myStack = new Stack<>();
		myStack.push(searchable.getInitialState());
		while (!myStack.isEmpty())
		{
			State<TypeState> n = myStack.pop();
			numOfEvaluatedNodes++;
			//COLOR??
			if (searchable.isGoalState(n))
			{
				System.out.println("this is goal!");
				return backTrace(searchable.getInitialState(), n);
			}
			ArrayList<State<TypeState>> posibileStates = searchable.getAllPosibileStates(n);
			for (State<TypeState> s : posibileStates)
			{
				if (!hs.contains(s) && (!myStack.contains(s)))
				{
				s.setCameFrom(n);
				hs.add(s);
				myStack.push(s);
				}
			}
		}
		return null;
	}
	
}
