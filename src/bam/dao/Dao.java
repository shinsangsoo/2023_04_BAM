package bam.dao;

public class Dao {
	public int lastId;
	
	public Dao() {
		this.lastId = 0;
	}
	
	public int setLastId() {
		return lastId + 1;
	}
}