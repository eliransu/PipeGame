package Search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import Solution.PipeSolution;
import Solution.Solution;

public class BFSearcher<TypeState> implements SearcherInterface {

	ArrayList<String> theSolution = new ArrayList<>();
	@Override
	public Solution search(Searchable searchable) {
		// TODO Auto-generated method stub
		Queue<State<TypeState>> open = new LinkedList<>();
		HashSet<State<TypeState>> closed = new HashSet<>();
		open.add(searchable.getInitialState());
		while (!open.isEmpty())
		{
			State<TypeState> n = open.poll();
			closed.add(n);
			if (searchable.isGoalState(n))
			{
				System.out.println("blate");
				while (n!=searchable.getInitialState())
				{
					int x = n.whatIChanged.getX();
					int y = n.whatIChanged.getY();
					theSolution.add(x+","+y+",1");
					n = n.getCameFrom();
				}
				for (int i=0;i<theSolution.size();i++)
					System.out.println(theSolution.get(i));
				PipeSolution sss = new PipeSolution();
				sss.setSolution(theSolution);
				return sss;
			}
			ArrayList<State<TypeState>> posibileStates = searchable.getAllPosibileStates(n);
			for (State<TypeState> state : posibileStates)
			{
				TypeState ar = state.getState();

				if (!closed.contains(state) && !(open.contains(state))) {
					state.setCameFrom(n);
					open.add(state);
				}
			}
		}
		return null;
	}
}
