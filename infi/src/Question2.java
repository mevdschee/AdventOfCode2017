import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Question2 {

	private static void visualize(String[] inputs) {
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
		ArrayList<int[]> points = new ArrayList<>();
		for (String input : inputs) {
			String[] coords = input.split(",");
			int x = Integer.parseInt(coords[0]);
			int y = Integer.parseInt(coords[1]);
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
			points.add(new int[] { x, y });
		}
		int width = maxX - minX;
		int height = maxY - minY;
		int offsetX = minX * -1;
		int offsetY = minY * -1;
		int scale = 10;

		Dimension imgDim = new Dimension((width + 1) * scale, (height + 1) * scale);
		BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = mazeImage.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.fillRect(0, 0, imgDim.width, imgDim.height);
		BasicStroke bs = new BasicStroke(1);
		g2d.setStroke(bs);
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < points.size(); i++) {
			int x = points.get(i)[0];
			int y = points.get(i)[1];
			g2d.fillOval(scale * (offsetX + x), scale * (offsetY + y), scale, scale);
		}
		ImageIcon ii = new ImageIcon(mazeImage);
		JOptionPane.showMessageDialog(null, ii);
	}

	public static void main(String[] args) throws IOException {
		String inputs = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int split = inputs.indexOf("](");
		ArrayList<int[]> robots = new ArrayList<>();
		for (String input : inputs.substring(1, split).split("\\]\\[")) {
			String[] coords = input.split(",");
			robots.add(new int[] { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]) });
		}
		ArrayList<ArrayList<int[]>> moves = new ArrayList<>();
		moves.add(new ArrayList<>());
		for (String input : inputs.substring(split + 2, inputs.length() - 1).split("\\)\\(")) {
			String[] coords = input.split(",");
			ArrayList<int[]> last = moves.get(moves.size() - 1);
			if (last.size() == robots.size()) {
				last = new ArrayList<>();
				moves.add(last);
			}
			last.add(new int[] { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]) });
		}
		HashMap<String, Boolean> collisions = new HashMap<>();
		for (int m = 0; m < moves.size(); m++) {
			for (int r = 0; r < robots.size(); r++) {
				robots.get(r)[0] += moves.get(m).get(r)[0];
				robots.get(r)[1] += moves.get(m).get(r)[1];
			}
			for (int r = 0; r < robots.size(); r++) {
				for (int r2 = r - 1; r2 >= 0; r2--) {
					if (robots.get(r)[0] == robots.get(r2)[0] && robots.get(r)[1] == robots.get(r2)[1]) {
						collisions.put(robots.get(r)[0] + "," + robots.get(r)[1], true);
					}
				}
			}
		}
		visualize(collisions.keySet().toArray(new String[] {}));
		System.out.println(collisions.keySet().size());
	}

}
