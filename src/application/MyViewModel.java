package application;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyViewModel extends Observable implements Observer  {

	MyModel m;

	public MyViewModel(MyModel m) {
		this.m = m;
	}
	public boolean connect(String ip, String port) {
		Integer portInt = Integer.parseInt(port);
		try {
			return m.connect(ip, portInt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<String> solve(char[][] problem) {
		ArrayList<String> clientProblem = new ArrayList<>();
		for (int i = 0 ; i < problem.length; i ++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0 ; j < problem[0].length; j++) {
				line.append(problem[i][j]);
			}
			clientProblem.add(line.toString());
		}
		try {
			return m.solve(clientProblem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
