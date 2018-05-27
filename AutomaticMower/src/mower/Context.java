package mower;

import java.io.InputStream;
import java.util.List;

public class Context {
	private ParseStrategy strategy;
	
	public Context() {
	}

	public Context(ParseStrategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(ParseStrategy strategy) {
		this.strategy = strategy;
	}
	public List<Mower> operation(InputStream input) {
		if(strategy == null) throw new IllegalArgumentException("Please specify parse strategy");
		return strategy.applyStrategy(input);
	}
	
	

}
