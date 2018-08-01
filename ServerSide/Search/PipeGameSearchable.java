package Search;

import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Problem.PipeProblem;
public class PipeGameSearchable extends CommonSearchable<char[][]> {
	static HashSet<String> hashState = new HashSet<>();
	ArrayList<State<char[][]>>tempPosibleStates = new ArrayList<>();
	public PipeGameSearchable(PipeProblem p) {
		this.initialState = new State<char[][]>(p.getProblem());
		this.allPosibileStates = new ArrayList<State<char[][]>>();
	}
	@Override
	public State<char[][]> getInitialState() {
		// TODO Auto-generated method stub
		return this.initialState;
	}
	Point EndPoint(char[][] arraytofindend) {
		for (int i = 0; i < arraytofindend.length; i++)
			for (int j = 0; j < arraytofindend[0].length; j++) {
				if (arraytofindend[i][j] == 'g')
					return new Point(i, j);
			}
		return null;
	}
	Point StartPoint(char[][] arraytofindend) {
		for (int i = 0; i < arraytofindend.length; i++)
			for (int j = 0; j < arraytofindend[i].length; j++) {
				if (arraytofindend[i][j] == 's')
					return new Point(i, j);
			}
		return null;
	}
	@Override
	public boolean isGoalState(State<char[][]> ifgoal) {
		StringBuilder chekcertemp = new StringBuilder();
		for (int i=0;i<ifgoal.getState().length;i++)
			for (int j=0;j<ifgoal.getState()[i].length;j++)
			{
				chekcertemp.append(ifgoal.getState()[i][j]);
			}
//		System.out.println(chekcertemp.substring(0, 5));
//		System.out.println(chekcertemp.substring(5, 10));
//		System.out.println(chekcertemp.substring(10, 15));
//		System.out.println(chekcertemp.substring(15, 20));
//		System.out.println(chekcertemp.substring(20, 25));
		//if (chekcertemp.equals(obj))
		//String thereal = chekcertemp.toString();
		//System.out.println(chekcertemp);
		Point p = EndPoint(ifgoal.getState());
		if (recursionIfGoal(ifgoal.getState(), p.getX(), p.getY(), new Point(-1,-1)))
			return true;
		
		return false;
	}
	public boolean recursionIfGoal(char[][] board,int i, int j, Point whereIcameFrom)
	{
		boolean x=false;
		//System.out.println("the max state is : " + maxcostforstate);
		//System.out.println("the there cost state is : " + costforstate);
		//boolean x=false;
		if (board[i][j] == 's')
		{
			return true;
		}
		if ((i==0) && (j==0)) // right and down
		{
			if ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1]))))) // right
					if (j+1!=whereIcameFrom.getY())
					{

						x = recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
					}
			if ((isVaild(board, i+1, j) && ((isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))))) // down
					if (i+1!=whereIcameFrom.getX())
					{
						 x = recursionIfGoal(board, i+1, j, new Point(i,j));
							if (x==true)
								return true;
					}
		}
		else if ((i==board.length-1) && (j==0)) // up and right
		{
			if  ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1]))))) // right
				if (j+1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j]))))) // up
				if (i-1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else if ((i==0) && (j==board[i].length-1)) // down and left
		{
			if ((isVaild(board, i+1, j)) && (isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))) // down
				if (i+1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i+1, j, new Point(i,j));
						if (x==true)
							return true;
				}
					if ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j-1, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else if ((i==board.length-1) && (j==board[i].length-1)) // left and up
		{
			if  ((isVaild(board, i, j-1) && ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j-1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j]))))) // up
				if (i-1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else if (i==0) // left up right
		{
			if  ((isVaild(board, i, j-1) && ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j-1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1])))))// right
				if (j+1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i+1, j) && ((isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))))) // down
				if (i+1!=whereIcameFrom.getX())
				{
					x=  recursionIfGoal(board, i+1, j, new Point(i,j));
					if (x==true)
						return true;
				}
		}
		else if (j==0) // up right down
		{
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j])))))
				if (i-1!=whereIcameFrom.getX())
				{
					 x = recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1]))))) // right
				if (j+1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i+1, j) && ((isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))))) // down
				if (i+1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i+1, j, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else if(i==board.length-1) // left right up
		{
			if  ((isVaild(board, i, j-1) && ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j-1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1]))))) // right
				if (j+1!=whereIcameFrom.getY())
				{
					 x= recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j]))))) // up
				if (i-1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else if (j==board[i].length-1) // up down left
		{
			if  ((isVaild(board, i, j-1) && ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					x=  recursionIfGoal(board, i, j-1, new Point(i,j));
					if (x==true)
						return true;
				}
			if  ((isVaild(board, i+1, j) && ((isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))))) // down
				if (i+1!=whereIcameFrom.getX())
				{
					x= recursionIfGoal(board, i+1, j, new Point(i,j));
					if (x==true)
						return true;
				}
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j]))))) // up
				if (i-1!=whereIcameFrom.getX())
				{
					  x= recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		else // all situations
		{
			if  ((isVaild(board, i, j-1) && ((isLeftable(board[i][j])) && (CheckMyLeft(board[i][j-1]))))) // left
				if (j-1!=whereIcameFrom.getY())
				{
					x=  recursionIfGoal(board, i, j-1, new Point(i,j));
					if (x==true)
						return true;
				}
			if  ((isVaild(board, i+1, j) && ((isDownable(board[i][j])) && (CheckMyDown(board[i+1][j]))))) // down
				if (i+1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i+1, j, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i-1, j) && ((isUpable(board[i][j])) && (CheckMyUp(board[i-1][j]))))) // up
				if (i-1!=whereIcameFrom.getX())
				{
					 x= recursionIfGoal(board, i-1, j, new Point(i,j));
						if (x==true)
							return true;
				}
			if  ((isVaild(board, i, j+1) && ((isRightable(board[i][j])) && (CheckMyRight(board[i][j+1]))))) // right
				if (j+1!=whereIcameFrom.getY())
				{
					 x = recursionIfGoal(board, i, j+1, new Point(i,j));
						if (x==true)
							return true;
				}
		}
		return false;
	}
	@Override
	public void setAllPosibleStates(State<char[][]> cs) {
			State<char[][]> onlytherestate;
			int i = 0, j = 0;
			Point start = StartPoint(cs.getState());
			Point end = EndPoint(cs.getState());
			recGivePostate(cs.getState(), end.getX(), end.getY(), new Point(-1,-1),0);
			//this.allPosibileStates.add(onlytherestate);
			//for (i = 0; i < cs.getState().length; i++)
//				for (j = 0; j < cs.getState()[i].length; j++) {
//					if (ifBoxContain(cs.getState()[i][j])) { // if we have something inside the box
// // clone the array.
//						if (cs.getState()[i][j] == '|' || cs.getState()[i][j] == '-')
//						{
//							char[][] temp = new char[cs.getState().length][cs.getState()[0].length];
//							for (int t=0;t<cs.getState().length;t++)
//								 for (int k=0;k<cs.getState()[t].length;k++)
//									temp[t][k] = cs.getState()[t][k];
//							temp[i][j] = rotate(temp[i][j]);
//							if (checkIfCaneToBePosibileState(temp, new Point(i,j)))
//							{
//								onlytherestate = new State<char[][]>(temp);
//								onlytherestate.charThatIChangedInBoard(i,j);
//								onlytherestate.setCameFrom(cs);
//								StringBuilder sbd = new StringBuilder();
//								for (int k=0;k<onlytherestate.getState().length;k++)
//									for (int p=0;p<onlytherestate.getState()[i].length;p++)
//									{
//										sbd.append(onlytherestate.getState()[k][p]);
//									}
//								String theStateinArray=sbd.toString();
//								if (!hashState.contains(theStateinArray))
//									{
//										onlytherestate.setCost(1);
//								hashState.add(theStateinArray);
//								allPosibileStates.add(onlytherestate);
//									}
//							}
//						}
//						else
//						{
//							for (int trying=0;trying<3;trying++)
//							{
//								char[][] temp = new char[cs.getState().length][cs.getState()[0].length];
//								for (int t=0;t<cs.getState().length;t++)
//									 for (int k=0;k<cs.getState()[t].length;k++)
//										temp[t][k] = cs.getState()[t][k];
//								temp[i][j] = rotate(temp[i][j]);
//								if (checkIfCaneToBePosibileState(temp, new Point(i,j)))
//								{
//									onlytherestate = new State<char[][]>(temp);
//									onlytherestate.charThatIChangedInBoard(i,j);
//									onlytherestate.setCameFrom(cs);
//									StringBuilder sbd = new StringBuilder();
//									for (int k=0;k<onlytherestate.getState().length;k++)
//										for (int p=0;p<onlytherestate.getState()[i].length;p++)
//										{
//											sbd.append(onlytherestate.getState()[k][p]);
//										}
//									String theStateinArray=sbd.toString();
//									if (!hashState.contains(theStateinArray))
//										{
//											onlytherestate.setCost(1);
//									hashState.add(theStateinArray);
//									allPosibileStates.add(onlytherestate);
//										}
//								}
//							}
//						}
//						}
//					}
				}

	@Override
	public ArrayList<State<char[][]>> getAllPosibileStates(State<char[][]> s) {
		// TODO Auto-generated method stub
		setAllPosibleStates(s);
		for (int i=0;i<allPosibileStates.size();i++)
		{
			//System.out.println(allPosibileStates.get(i).toString());
		}
		return this.allPosibileStates;
	}
	public boolean checkIfCaneToBePosibileState(char[][] ifposibile,Point whatIChanged)
	{
		int whatx = whatIChanged.getX();
		int whaty = whatIChanged.getY();
		int i=ifposibile.length-1;
		int j=ifposibile[0].length-1;
		
		if (whatIChanged.getX()==0 && whatIChanged.getY()==0)
		{
			if (ifposibile[whatx][whaty] != 'F')
				return false;
			return true;
		}
		else if (whatIChanged.getX()==ifposibile.length-1 && whatIChanged.getY()==0)
		{
			if (ifposibile[whatx][whaty] != 'L')
				return false;
			return true;
		}
		else if (whatIChanged.getX()==0 && whatIChanged.getY() == ifposibile[0].length-1)
		{
			if (ifposibile[whatx][whaty] != '7')
				return false;
			return true;
		}
		else if (whatIChanged.getX() == ifposibile.length-1 && whatIChanged.getY()==ifposibile[0].length-1)
		{
			if (ifposibile[whatx][whaty] != 'J')
				return false;
			return true;
		}
		
		else if(whatIChanged.getY()==0) {
			if ((ifposibile[whatx][whaty] != '|') && (ifposibile[whatx][whaty] != 'L') && (ifposibile[whatx][whaty] != 'F'))
					return false;
			return true;
		}
		else if(whatIChanged.getX()==0)
		{
			if ((ifposibile[whatx][whaty] != '-') && (ifposibile[whatx][whaty] != '7') && (ifposibile[whatx][whaty] != 'F'))
				return false;
		return true;
		}
	else if(whatIChanged.getX()==i)
		{
		if ((ifposibile[whatx][whaty] != '-') && (ifposibile[whatx][whaty] != 'L') && (ifposibile[whatx][whaty] != 'J'))
			return false;
	return true;
		}
	else if(whatIChanged.getY()==j)
		{
		if ((ifposibile[whatx][whaty] != '|') && (ifposibile[whatx][whaty] != '7') && (ifposibile[whatx][whaty] != 'J'))
			return false;
	return true;
		}
	else 
	{
		/*if(CheckMyDown(ifposibile[whatIChanged.getX()+1][whatIChanged.getY()]))
			return true;
		if(CheckMyLeft(ifposibile[whatIChanged.getX()][whatIChanged.getY()-1]))
			return true;
		if(CheckMyRight(ifposibile[whatIChanged.getX()][whatIChanged.getY()+1]))
			return true;
		if(CheckMyUp(ifposibile[whatIChanged.getX()-1][whatIChanged.getY()]))
			return true;
		return false; */
		return true;
	}
	}
boolean ifBoxContain(char charvalue) {
	switch (charvalue) {
	case '|':
		return true;
	case '-':
		return true;
	case 'F':
		return true;
	case '7':
		return true;
	case 'L':
		return true;
	case 'J':
		return true;
	default:
		return false;
	}
}
char rotate(char rotateable) {
	switch (rotateable) {
	case '|': {
		rotateable = '-';
		return rotateable;
	}
	case '-': {
		rotateable = '|';
		return rotateable;
	}
	case '7': {
		rotateable = 'J';
		return rotateable;
	}
	case 'J': {
		rotateable = 'L';
		return rotateable;
	}
	case 'F': {
		rotateable = '7';
		return rotateable;
	}
	case 'L':{
		rotateable = 'F';
		return rotateable;
	}
			
	default:
		return rotateable;
	}

}
public boolean CheckMyRight(char check) {
	switch (check) {
	case '-':
		return true;
	case 'J':
		return true;
	case 's':
		return true;
	case '7':
		return true;
	default:
		return false;
	}
}

public boolean CheckMyDown(char check) {
	switch (check) {
	case '|':
		return true;
	case 'J':
		return true;
	case 'L':
		return true;
	case 's':
		return true;
	default:
		return false;
	}
}

public boolean CheckMyLeft(char check) {
	switch (check) {
	case '-':
		return true;
	case 'L':
		return true;
	case 'F':
		return true;
	case 's':
		return true;
	default:
		return false;
	}
}

public boolean CheckMyUp(char check) {
	switch (check) {
	case '7':
		return true;
	case 'F':
		return true;
	case '|':
		return true;
	case 's':
		return true;
	default:
		return false;
	}
}

public boolean isRightable(char rightable)
{
	switch(rightable)
	{
	case '-':
		return true;
	case 'F':
		return true;
	case 'L':
		return true;
	case 's':
		return true;
	case 'g':
		return true;
	default:
		return false;
	}
}
public boolean isDownable(char downtable)
{
	switch(downtable)
	{
	case '|':
		return true;
	case 'F':
		return true;
	case '7':
		return true;
	case 's':
		return true;
	case 'g':
		return true;
	default:
		return false;
	}
}
public boolean isUpable(char upable)
{
	switch(upable)
	{
	case '|':
		return true;
	case 'J':
		return true;
	case 'L':
		return true;
	case 's':
		return true;
	case 'g':
		return true;
	default:
		return false;
	}
}
public boolean isLeftable(char leftable)
{
	switch(leftable)
	{
	case '-':
		return true;
	case 'J':
		return true;
	case '7':
		return true;
	case 's':
		return true;
	case 'g':
		return true;
	default:
		return false;
	}
}
public double calculateCost(char[][] board,Point start)

{
	double counter=0;
	if (start.getX()==0 && start.getY()==0)// right down
	{
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
	}
	else if (start.getX()==0 && start.getY() == board[0].length-1) // down and left
	{
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
	}
	else if (start.getX()==board.length-1 && start.getY()== 0)
	{
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
	}
	else if(start.getX()==board.length-1 && start.getY() == board[0].length-1) // left and up
	{
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
	}
	else if (start.getX()==0 && start.getY()!=0) //  right and down and left
	{
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
	}
	else if (start.getY()==0 && start.getX()!=0) // down and right and up
	{
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
	}
	else if (start.getX()==board.length-1) // up and left and right
	{
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
	}
	else if (start.getY()==board[0].length-1) // up down and left
	{
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
	}
	else
	{
		if (CheckMyUp(board[start.getX()-1][start.getY()]))
			counter++;	
		if (CheckMyLeft(board[start.getX()][start.getY()-1]))
			counter++;
		if (CheckMyDown(board[start.getX()+1][start.getY()]))
			counter++;
		if (CheckMyRight(board[start.getX()][start.getY()+1]))
			counter++;
	}
	
	return counter*counter;
}
public boolean is4stroke(char a)
{
	if (a == '7' || a== 'F' || a=='L' || a=='J')
		return true;
	return false;
}
void recGivePostate(char[][] board,int gi, int gj, Point camefrom, int counter)
{
	boolean res = false;
	State<char[][]> temp;
	if (is4stroke(board[gi][gj]))
	{
		int POWER;
		char[][] tempboar0 = clone(board);
		POWER=4;
//	for (int p = 1; p < POWER; p++) // check!
//	{
//		tempboar0[gi][gj] = rotate(tempboar0[gi][gj]);
//		if (p!=2)
//		{State<char[][]> tempState = new State<char[][]>(clone(tempboar0));
//		if (ifIcanAddit(tempState,gi,gj,p,counter));
//		}
//	}
	}
	if (isVaild(board, gi, gj-1) && CheckMyLeft(board[gi][gj-1]) && isLeftable(board[gi][gj]) && (!camefrom.equals(new Point(gi,gj-1))))
	{
		res = true;
			recGivePostate(board, gi, gj-1, new Point(gi,gj),counter+1);
	}
	if (isVaild(board, gi+1, gj) && CheckMyDown(board[gi+1][gj]) && isDownable(board[gi][gj]) && (!camefrom.equals(new Point(gi+1,gj))))
	{
		res = true;
			recGivePostate(board, gi+1, gj, new Point(gi,gj),counter+1);
	}
	if (isVaild(board, gi-1, gj) && CheckMyUp(board[gi-1][gj]) && isUpable(board[gi][gj]) && (!camefrom.equals(new Point(gi-1,gj))))
	{
		res = true;
		recGivePostate(board, gi-1, gj, new Point(gi,gj),counter+1);
	}
	if (isVaild(board, gi, gj+1) && CheckMyRight(board[gi][gj+1]) && isRightable(board[gi][gj]) && (!camefrom.equals(new Point(gi,gj+1)))) 
	{
		res = true;
		recGivePostate(board, gi, gj+1, new Point(gi,gj),counter+1);
	}
	 // i stucked
	if ((res == false) && (!isSnail(board, gi, gj,counter)))
		{
		int POWER = 1;
			char[][] tempboard0 = clone(board);

			if (tempboard0[gi][gj] == '|' || tempboard0[gi][gj] == '-')
				POWER=1;
			else
				POWER=4;
			for (int p = 1; p < POWER; p++) // check!
			{
				tempboard0[gi][gj] = rotate(tempboard0[gi][gj]);
				State<char[][]> tempState = new State<char[][]>(clone(tempboard0));
				if (ifIcanAddit(tempState,gi,gj,p,counter));
			}
			
			if ((gi+1 != camefrom.getX() || gj != camefrom.getY()) && isVaild(board, gi+1, gj) && (ifBoxContain(board[gi+1][gj])))
			{
				char[][] tempboard1 = clone(board);

				if (tempboard1[gi+1][gj] == '|' || tempboard1[gi+1][gj] == '-')
					POWER=2;
				else
					POWER=4;
				for (int p = 1; p < POWER; p++)
				{
					tempboard1[gi+1][gj] = rotate(tempboard1[gi+1][gj]);
					State<char[][]> tempState = new State<char[][]>(clone(tempboard1));
					if (ifIcanAddit(tempState,gi+1,gj,p,counter));
				}
			}
			if ((gi != camefrom.getX() || gj+1 != camefrom.getY()) && isVaild(board, gi, gj+1) && (ifBoxContain(board[gi][gj+1])))
			{
				char[][] tempboard2 = clone(board);
				if (tempboard2[gi][gj+1] == '|' || tempboard2[gi][gj+1] == '-')
					POWER=2;
				else
					POWER=4;
				for (int p = 1; p < POWER; p++)
				{
					tempboard2[gi][gj+1] = rotate(tempboard2[gi][gj+1]);
					if (ifIcanAddit(new State<char[][]>(clone(tempboard2)),gi,gj+1,p,counter))
					{}
				}
			}
			if ((gi-1 != camefrom.getX() || gj != camefrom.getY()) && isVaild(board, gi-1, gj) && (ifBoxContain(board[gi-1][gj])))
			{
				char[][] tempboard3 = clone(board);
				if (tempboard3[gi-1][gj] == '|' || tempboard3[gi-1][gj] == '-')
					POWER=2;
				else
					POWER=4;
				for (int p = 1; p < POWER; p++)
				{
					tempboard3[gi-1][gj] = rotate(tempboard3[gi-1][gj]);
					if (ifIcanAddit(new State<char[][]>(clone(tempboard3)),gi-1,gj,p,counter))
					{}
				}
			}
			if ((gi != camefrom.getX() || gj-1 != camefrom.getY()) && isVaild(board, gi, gj-1) && (ifBoxContain(board[gi][gj-1])))
			{
				char[][] tempboard4 = clone(board);
				if (tempboard4[gi][gj-1] == '|' || tempboard4[gi][gj-1] == '-')
					POWER=2;
				else
					POWER=4;
				for (int p = 1; p < POWER; p++)
				{
					tempboard4[gi][gj-1] = rotate(tempboard4[gi][gj-1]);
					if (ifIcanAddit(new State<char[][]>(clone(tempboard4)),gi,gj-1,p,counter))
					{}
				}
			}
		}
}
public boolean isVaild(char[][]board,int i,int j)
{
	if ((i>=0) && (i<board.length) && (j>=0) && (j<board[0].length))
		return true;
	return false;
}
public boolean isSnail(char[][] board,int i, int j,int counter)
{
	if (counter==1 || counter==0)
		return false;
	if (isVaild(board, i-1, j) && (board[i-1][j]=='g'))
		return true;
	if (isVaild(board, i+1, j) && (board[i+1][j]=='g'))
		return true;
	if (isVaild(board, i, j-1) && (board[i][j-1]=='g'))
		return true;
	if (isVaild(board, i, j+1) && (board[i][j+1]=='g'))
		return true;
	return false;
}
char[][] clone(char [][] temp)
{
	char tempchar[][] = new char[temp.length][temp[0].length]; 
	for (int i=0;i<temp.length;i++)
		for (int j=0;j<temp[0].length;j++)
			tempchar[i][j] = temp[i][j];
	return tempchar;
}
public boolean ifIcanAddit(State<char[][]> tryou,int thei, int thej, int rounds, int counter)
{

	StringBuilder SBD = new StringBuilder();
	for (int i=0; i<tryou.getState().length;i++)
		for (int j=0;j<tryou.getState()[i].length;j++)
			SBD.append(tryou.getState()[i][j]);
	
	String theSTRING = SBD.toString();
	if (!hashState.contains(theSTRING))
	{
		tryou.whatIChanged = new Point(thei,thej,rounds);
		hashState.add(theSTRING);
		//System.out.println(tryou.toString());
		tryou.setCost(counter);
		this.allPosibileStates.add(tryou);
		return true;
	}
	return false;
}
}