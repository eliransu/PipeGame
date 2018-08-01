package Search;

import java.util.ArrayList;

public abstract class CommonSearchable<T> implements Searchable<T> {
	State<T> initialState;
	State<T> goalState;
	ArrayList<State<T>> allPosibileStates;
}
