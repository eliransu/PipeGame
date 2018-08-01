package Search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import Solution.Solution;

public class BFS<TypeState> extends CommonSearcher<TypeState> {
	@Override
	public Solution search(Searchable searchable) {
		this.openList = new LinkedList<State<TypeState>>();
		openList.add(searchable.getInitialState());
		while (!openList.isEmpty())
		{
			State<TypeState> n = popOpenList();
			hs.add(n);
			if (searchable.isGoalState(n))
			{
				System.out.println("is goal!");
				return backTrace(searchable.getInitialState(), n);
			}
			ArrayList<State<TypeState>> successors = searchable.getAllPosibileStates(n);
			for (State<TypeState> s : successors)
			{
				if (!hs.contains(s) && !openList.contains(s))
				{
					s.setCameFrom(n);
					addToOpenList(s);
					hs.add(s);
				}
			}
			}
		return null;
		
	}
	

}
