package mower;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;


public class ParseFile implements ParseStrategy {
	private final Map<String,  Supplier<? extends Orientation>> orientations = new HashMap<>();
	private Area area;
	@Override
	public List<Mower> applyStrategy(InputStream input) {
		List<Mower> mowers = new ArrayList<>();
		Scanner scanner = new Scanner(Objects.requireNonNull(input));
		String line;
		try {
			if(scanner.hasNext()) {
				//On recupère les coordonnées du coin supérieur droit de la pelouse
				line = scanner.nextLine();
				String[] coord = line.split(" ");
				if(coord.length !=2) throw new IllegalArgumentException("File error parsing : top right coordinate"+line);
				area = new Area(new Coordinate(0, 0), new Coordinate(Integer.valueOf(coord[0]),Integer.valueOf(coord[1]) ));
				Mower mower;
				while(scanner.hasNext()) {
					line = scanner.nextLine();
					String[] mowerParams = line.split(" ");
					if(mowerParams.length !=3) throw new IllegalArgumentException("File error parsing : mower parameters "+line);
					mower = new Mower(new Coordinate(Integer.valueOf(mowerParams[0]), Integer.valueOf(mowerParams[1])),mowerParams[2] );
					//Get and apply instructions
					if(scanner.hasNextLine()) {
						line = scanner.nextLine();
						String[] bytes = line.split("");
						//Apply instructions
						for(String b : bytes) {
							applyInstruction(mower, b);
						}
						mowers.add(mower);
					}
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException : "+e.getMessage());
		}finally {
			scanner.close();
			try {
				input.close();
			} catch (IOException e) {
				//Nothing
			}
		}
		return mowers;
	}

	private void applyInstruction(Mower mower, String instruction) {
		Orientation o;
		switch (instruction) {
		case "A":
			String str = mower.getOrientation();
			o = orientations.getOrDefault(str, () -> {throw new IllegalArgumentException("unknown orientation "+str);}).get();
			o.goDirection(area, mower.getPosition());
			break;
		case "G":
			mower.turnLeft();
			break;
		case "D":
			mower.turnRight();
			break;
		default: throw new IllegalArgumentException("Unknown instruction"+instruction);
		}
	}

	@Override
	public void register(String name, Supplier<? extends Orientation> supplier) {
		orientations.put(Objects.requireNonNull(name), Objects.requireNonNull(supplier));
		
	}
	@Override
	public void remove(String name) {
		orientations.remove(Objects.requireNonNull(name));
	}
}
