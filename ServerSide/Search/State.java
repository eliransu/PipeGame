package Search;

import java.util.Comparator;

public class State<T> implements Comparable<State<T>> {
	T state;
	State<T> cameFrom;
	double cost;
	Point whatIChanged;
	public State(T t)
	{
		this.state = t;
	}
	public T getState() {
		return state;
	}
	public void setState(T state) {
		this.state = state;
	}
	public State<T> getCameFrom() {
		return cameFrom;
	}
	void changeState(int i,int j,char whatImChanging)
	{
		
	}
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void charThatIChangedInBoard (int x,int y)
	{
		this.whatIChanged = new Point(x,y);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		String myresult = this.state.toString();
		//return result;
		//if ((this.whatIChanged !=null) && (this.cameFrom!=null))
		//	return whatIChanged.getX() * whatIChanged.getY() * cameFrom.hashCode();
	//	else
			return myresult.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	@Override
	public int compareTo(State<T> o) {
		// TODO Auto-generated method stub
		return  (int) ( o.getCost() -  this.getCost());
	}
	
}
