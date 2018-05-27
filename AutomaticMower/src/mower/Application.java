package mower;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Application {
	public static void main(String[] args) throws FileNotFoundException {
		Context context = new Context();
		ParseFile pf = new ParseFile();
		Orientation north = new Orientation.North();
		Orientation south = new Orientation.South();
		Orientation east = new Orientation.East();
		Orientation west = new Orientation.West();
		pf.register("N", ()->north);
		pf.register("E", ()->east);
		pf.register("W", ()->west);
		pf.register("S", ()->south);
		context.setStrategy(pf);
		List<Mower> mowers = context.operation(new FileInputStream("src/test"));
		mowers.forEach(mower -> System.out.println(mower));
		
	}

}
