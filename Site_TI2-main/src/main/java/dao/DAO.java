package dao;

public interface DAO<T>{
	
	public T get(int key);
	public void add(T p);
	public void update(T p);
	public void delete(T p);
	public T[] getAll();
	
}