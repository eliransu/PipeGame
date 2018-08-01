package Management;


public interface Solver<TypeSolution,TypeProblem> {
	TypeSolution solve(TypeProblem problem);
}