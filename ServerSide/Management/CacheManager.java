package Management;

public interface CacheManager<Type> {
	void save(Integer ProblemID,Type dataToSave);
	Type load(Type userLookingFor);
	boolean isExist(Type ifDataIsExist);
}
