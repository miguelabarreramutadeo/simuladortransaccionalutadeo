package com.utadeo.miguelabarreram.simuladortransaccional.database;

public interface SQLiteStorageEngine {
	
	boolean supportsCascadeDelete();
	String getTableTypeString(String engineKeyword);
	boolean hasSelfReferentialForeignKeyBug();
	boolean dropConstraints();

}
