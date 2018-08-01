package Search;

import Solution.Solution;

public interface SearcherInterface<TypeState>
{
	Solution search(Searchable searchable);
}
