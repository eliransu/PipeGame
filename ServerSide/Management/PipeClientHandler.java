package Management;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import Problem.PipeProblem;
import Problem.Problem;
import Search.BFS;
import Search.BFSearcher;
import Search.BestFirstSearch;
import Search.DFS;
import Search.PipeGameSearchable;
import Solution.PipeSolution;
import Solution.Solution;

public class PipeClientHandler implements ClientHandler {
	Solver solver;
	CacheManager<ArrayList<String>> cm;
	ArrayList<String> ArrayString = new ArrayList<String>();
	Logger logger = Logger.getLogger(MyServer.class.getName());
	PipeSolution solution;
	
	public PipeClientHandler() {
		cm = new PipeCacheManager();
		this.solver = new PipeSolver(new DFS<char[][]>());
	}
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) throws IOException {
		logger.info("Entered to handleClient");
		BufferedReader bf = new BufferedReader(new InputStreamReader(inFromClient));
		PrintWriter pw = new PrintWriter(outToClient,true);
		String line = new String();
		int i=0;
		while (	!(line = bf.readLine()).equals("done"))
		{
			ArrayString.add(line);
			//theProblem = theProblem + line;
		}
		if (cm.isExist(ArrayString))
		{
			ArrayList<String> TEMP = new ArrayList<>();
			TEMP = cm.load(ArrayString);
			for (i=0; i<TEMP.size(); i++)
			{
				pw.write(TEMP.get(i));
			}
		}
		else
		{
			logger.info("Entered to the Solve area");
			PipeProblem p = new PipeProblem(ArrayString);
			//this.solver = new PipeSolver(new BestFirstSearch<char[][]>());
			solution = (PipeSolution) this.solver.solve(p);
			for (i=0; i<solution.getSolution().size(); i++)
			{
				pw.println(solution.getSolution().get(i));
			}
			cm.save(p.hashCode(), solution.getSolution());
			logger.info("SOLUTION OVERED!");
			
			//cm.save(ArrayString.hashCode(),MYSOLUTION!!);
			// I NEED TO CHANGE THIS IS TEST --->>>>>> TO THE REAL SOLUTION!!
		}
		
	}

}
