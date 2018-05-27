package mower;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

public interface ParseStrategy {
	public List<Mower> applyStrategy(InputStream input);
	public void register(String name, Supplier<? extends Orientation> supplier);
	public void remove(String name);

}
