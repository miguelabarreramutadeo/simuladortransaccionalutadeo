package com.utadeo.miguelabarreram.simuladortransaccional.database;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.unique.DefaultUniqueDelegate;

public class SQLiteUniqueDelegate extends DefaultUniqueDelegate {

	public SQLiteUniqueDelegate(Dialect dialect) {
		super( dialect );
	}

	@Override
	protected String getDropUnique() {
		return " drop index ";
	}
}
