import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader {
	protected ArrayList<int[]> rectangles;
	protected ArrayList<int[]> circles;
	protected int rectanglesCount = 0;
	protected int circlesCount = 0;
	protected int[][] data;
	protected int[][] dataCircles;
	private static final Pattern rectanglePattern = Pattern.compile("rect (\\d+) (\\d+) (\\d+) (\\d+)");
	private static final Pattern circlePattern = Pattern.compile("circle (\\d+) (\\d+) (\\d+)");

	public FileReader() throws NumberFormatException, IOException {
		readFile();
	}

	private void readFile() throws NumberFormatException, IOException {
		rectangles = read(rectanglePattern);
		circles = read(circlePattern);
		data = makeData(rectangles);
		dataCircles = makeDataCircle(circles);
	}
	private static int[][] makeDataCircle(ArrayList<int[]> circles){
		int[][] data = new int[circles.size() * 2][2];
		int i = 0;
		for (int[] circle : circles) {
			data[i][0] = circle[0] - circle[2];
			data[i][1] = circle[1] + circle[2];
			data[++i][0] = circle[0] + circle[2];
			data[i][1] = circle[1] - circle[2];
			i++;
		}
		return data;
	}
	private static int[][] makeData(ArrayList<int[]> rectangles) {
		int[][] data = new int[rectangles.size() * 2][2];
		int i = 0;
		for (int[] rect : rectangles) {
			data[i][0] = rect[0];
			data[i][1] = rect[1];
			data[++i][0] = rect[2];
			data[i][1] = rect[3];
			i++;
		}
		return data;
	}

	private static ArrayList<int[]> read(Pattern pattern) throws NumberFormatException, IOException {
		ArrayList<int[]> data = new ArrayList<>();

		for (String line : Files.readAllLines(Paths.get("./rectangles.txt"), StandardCharsets.US_ASCII)) {
			Matcher matcher = pattern.matcher(line.trim());
			if (matcher.find()) {
				if (pattern.equals(rectanglePattern)) {
					data.add(new int[] { Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
							Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)) });
				} else {
					data.add(new int[] { Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
							Integer.parseInt(matcher.group(3))});
				}
			}
		}
		return data;
	}
}
