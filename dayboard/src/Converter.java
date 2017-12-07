import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;

public class Converter {

	public static class Completion {
		public String get_star_ts;
	}

	public static class Member {
		public String name;
		public String id;
		public String last_star_ts;
		public int global_score;
		public int local_score;
		public int stars;
		public Map<String, Map<String, Completion>> completion_day_level;
	}

	public static class Data {
		public Map<String, Member> members;
		public String ownerId;
		public String event;
	}

	public static class TimeLineDate {
		public String month;
		public String day;
		public String year;
		public String hour;
		public String minute;
		public String second;
		public String millisecond;
	}

	public static class TimeLineEvent {
		public TimeLineMedia media;
		public TimeLineDate start_date;
		public TimeLineText text;
	}

	public static class TimeLineMedia {
		public String url;
	}

	public static class TimeLineText {
		public String headline;
		public String text;
	}

	public static class TimeLineTitle {
		public TimeLineMedia media;
		public TimeLineText text;
	}

	public static class TimeLine {
		public TimeLineTitle title;
		public TimeLineEvent[] events;
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input/69394.json"))).trim();
		Gson gson = new Gson();
		Data data = gson.fromJson(input, Data.class);
		for (int day = 6; day <= 6; day++) {
			TimeLine timeLine = new TimeLine();
			timeLine.title = new TimeLineTitle();
			timeLine.title.media = new TimeLineMedia();
			timeLine.title.media.url = "img/xebia_aoc.png";
			timeLine.title.text = new TimeLineText();
			timeLine.title.text.headline = "Day 6";
			timeLine.title.text.text = "<p>This year Xebia is sponsoring Advent of Code.</p>";
			ArrayList<TimeLineEvent> events = new ArrayList<>();
			TimeLineEvent tle1 = new TimeLineEvent();
			tle1.start_date = new TimeLineDate();
			tle1.start_date.year = data.event;
			tle1.start_date.month = "12";
			tle1.start_date.day = "" + day;
			tle1.start_date.hour = "06";
			tle1.start_date.minute = "00";
			tle1.start_date.second = "00";
			tle1.start_date.millisecond = "000";
			tle1.text = new TimeLineText();
			tle1.text.headline = "Puzzle 6 available";
			tle1.text.text = "<p>At 6 AM (Amsterdam time) the puzzle becomes available.</p>";
			events.add(tle1);
			for (int star = 1; star <= 2; star++) {
				for (String key : data.members.keySet()) {
					Member member = data.members.get(key);
					Map<String, Map<String, Completion>> completion = member.completion_day_level;
					if (completion.containsKey("" + day) && completion.get("" + day).containsKey("" + star)) {
						TimeLineEvent tle = new TimeLineEvent();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX'00'");
						String dateStr = completion.get("" + day).get("" + star).get_star_ts;
						Date date = null;
						try {
							date = formatter.parse(dateStr);
						} catch (ParseException e) {
							System.out.println("Could not parse date: " + dateStr);
							System.exit(1);
						}
						tle.start_date = new TimeLineDate();
						tle.start_date.year = new SimpleDateFormat("yyyy").format(date);
						tle.start_date.month = new SimpleDateFormat("MM").format(date);
						tle.start_date.day = new SimpleDateFormat("dd").format(date);
						tle.start_date.hour = new SimpleDateFormat("HH").format(date);
						tle.start_date.minute = new SimpleDateFormat("mm").format(date);
						tle.start_date.second = new SimpleDateFormat("ss").format(date);
						tle.start_date.millisecond = "000";
						tle.text = new TimeLineText();
						tle.text.headline = member.name;
						tle.text.text = "has gotten star #" + star;
						events.add(tle);
					}
				}
			}
			timeLine.events = events.toArray(new TimeLineEvent[] {});
			System.out.println(gson.toJson(timeLine));
		}
	}

}
