package Problem;

import java.util.ArrayList;

public class PipeProblem extends Problem<char[][]>{

	public PipeProblem(ArrayList<String> arrayproblem) {
		theProblem = new char[arrayproblem.size()][arrayproblem.get(0).length()];
		for (int i=0; i<arrayproblem.size(); i++)
		{
			theProblem[i] = arrayproblem.get(i).toCharArray();
		}
	}
	@Override
	public char[][] getProblem() {
		// TODO Auto-generated method stub
		return this.theProblem;
	}

	@Override
	public void setProblem(char[][] t) {
		this.theProblem = t;
		
	}
}
