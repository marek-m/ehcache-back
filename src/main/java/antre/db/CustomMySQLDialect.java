package antre.db;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class CustomMySQLDialect extends MySQL5InnoDBDialect {
	@Override
	public String getTableTypeString() {
		return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
	}

}

