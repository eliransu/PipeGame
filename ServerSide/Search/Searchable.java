package Search;

import java.util.ArrayList;

public interface Searchable<T> {
	
	public State<T> getInitialState();
	public boolean isGoalState(State<T> ifgoal);
	void setAllPosibleStates(State<T> cs);
	public ArrayList<State<T>> getAllPosibileStates(State<T> s);
}
