package Management;

import Problem.PipeProblem;
import Search.PipeGameSearchable;
import Search.SearcherInterface;
import Solution.PipeSolution;

public class PipeSolver implements Solver<PipeSolution,PipeProblem> {
	SearcherInterface s;
	
	public PipeSolver(SearcherInterface s)
	{
		this.s = s;
	}
	public PipeSolution solve(PipeProblem problem) {
		return (PipeSolution) s.search(new PipeGameSearchable(problem));
		// TODO Auto-generated method stub
		
		
	}



}