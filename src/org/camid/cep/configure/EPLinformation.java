package org.camid.cep.configure;

public class EPLinformation {
	private String name;
	private String statement;
	private String listener;

	public EPLinformation(String name, String statement, String listener) {
		this.name = name;
		this.statement = statement;
		this.listener = listener;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getListener() {
		return listener;
	}

	public void setListener(String listener) {
		this.listener = listener;
	}

	@Override
	public String toString() {
		return "EPLinformation [name=" + name + ", statement=" + statement + ", listener=" + listener + "]";
	}

	
}
