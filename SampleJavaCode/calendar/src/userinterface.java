import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
/*
 * @by Group Congo
 * Members
 * Andrew Nishimura
 * Kevin Tone
 * Andy Omiri
 * 
 */
public class userinterface {

	public static String[] months = { "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12" };
	public static JComboBox endmonth = new JComboBox(months);
	public static JTextField enterevent = new JTextField(20);
	public static JComboBox startmonth = new JComboBox(months);
	public static String[] days = new String[31];
	public static JComboBox startday;
	public static String[] years = new String[100];
	public static JComboBox startyear;
	public static String[] hour = new String[24];
	public static JComboBox starthour;
	public static JComboBox startmin;
	public static String[] recuringoptions = { "none", "daily", "monthly",
			"yearly" };
	public static JComboBox recur;
	public static JComboBox endday;
	public static JComboBox endyear;
	public static JComboBox endhour;
	public static JComboBox timezones;
	public static JComboBox pub;
	public static JTextField where;
	public static String[] allzones = { "Pacific/Apia", "Pacific/Honolulu",
			"Pacific/Marquesas", "America/Anchorage", "America/Los_Angeles",
			"America/Vancouver", "America/Denver", "America/Edmonton",
			"America/Chicago", "America/Costa_Rica", "America/Winnipeg",
			"America/Detroit", "America/Montreal", "America/New_York",
			"America/Caracas", "America/Dominica", "America/St_Johns",
			"America/Argentina/Buenos_Aires", "Atlantic/South_Georgia",
			"Atlantic/Cape_Verde", "Africa/Dakar", "Europe/London",
			"Europe/Berlin", "Europe/Rome", "Europe/Istanbul", "Asia/Qatar",
			"Asia/Tehran", "Europe/Moscow", "Asia/Kabul", "Asia/Dushanbe",
			"Asia/Kolkata", "Asia/Kathmandu", "Asia/Qyzylorda", "Indian/Cocos",
			"Asia/Jakarta", "Asia/Hong_Kong", "Asia/Shanghai",
			"Australia/Eucla", "Australia/Perth", "Australia/Eucla",
			"Asia/Tokyo", "Asia/Seoul", "Australia/Darwin", "Australia/Sydney",
			"Pacific/Guam", "Australia/Lord_Howe", "Pacific/Guadalcanal",
			"Pacific/Norfolk", "Antarctica/South_Pole", "Pacific/Fiji",
			"Pacific/Chatham", "Pacific/Tongatapu", "Pacific/Kiritimati" };
	public static JLabel errmsg;
	public static JTextArea des;
	public static JComboBox endmin;
	public static JLabel namemsg;
	public static JLabel stdatemsg;
	public static JLabel endatemsg;
	public static JLabel stcalmsg;
	public static JLabel encalmsg;

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				openwindow();

			}
		});

	}

	public static void openwindow() {
		JFrame F = new JFrame("group congo final project");
		F.setSize(700, 900);

		JPanel Form = new JPanel();

		F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		F.setVisible(true);

		SpringLayout Lay = new SpringLayout();

		JLabel title = new JLabel("make an event");
		title.setSize(100, 100);
		namemsg = new JLabel("");
		Form.add(namemsg);
		title.setFont(new Font(title.getName(), Font.PLAIN, 18));
		Form.add(title);
		JLabel eventname = new JLabel("calendar event");
		Form.add(eventname);
		stcalmsg = new JLabel("");
		Form.add(stcalmsg);
		Form.add(enterevent);
		JLabel startdate = new JLabel("startdate");
		Form.add(startdate);
		days[0] = "01";
		days[1] = "02";
		days[2] = "03";
		days[3] = "04";
		days[4] = "05";
		days[5] = "06";
		days[6] = "07";
		days[7] = "08";
		days[8] = "09";
		for (int i = 9; i < 31; i++) {
			days[i] = Integer.toString(i + 1);
		}
		for (int i2 = 0; i2 < 100; i2++) {
			years[i2] = Integer.toString(i2 + 2014);
		}
		startday = new JComboBox(days);
		startyear = new JComboBox(years);
		Form.add(startmonth);
		Form.add(startday);
		Form.add(startyear);

		JLabel starttime = new JLabel("start time");
		Form.add(starttime);

		for (int i3 = 0; i3 < 24; i3++) {
			hour[i3] = Integer.toString(i3);
		}
		starthour = new JComboBox(hour);
		String[] min = new String[60];// reuse
		min[0] = "00";
		min[1] = "01";
		min[2] = "02";
		min[3] = "03";
		min[4] = "04";
		min[5] = "05";
		min[6] = "06";
		min[7] = "07";
		min[8] = "08";
		min[9] = "09";
		for (int I = 10; I < 60; I++) {
			min[I] = Integer.toString(I);
		}
		startmin = new JComboBox(min);
		stdatemsg = new JLabel("");
		Form.add(stdatemsg);

		JLabel colon = new JLabel(":");
		Form.add(starthour);
		Form.add(startmin);
		JLabel recurence = new JLabel("recurring ?");
		Form.add(recurence);
		recur = new JComboBox(recuringoptions);
		Form.add(colon);
		JLabel Enddate = new JLabel("end date");
		Form.add(Enddate);
		Form.add(recur);
		endday = new JComboBox(days);
		endyear = new JComboBox(years);
		Form.add(endmonth);
		Form.add(endday);
		Form.add(endyear);
		encalmsg = new JLabel("");
		Form.add(encalmsg);
		JLabel endtime = new JLabel("end time");
		Form.add(endtime);
		endhour = new JComboBox(hour);
		endmin = new JComboBox(min);
		endatemsg = new JLabel("");
		Form.add(endatemsg);
		JLabel colon2 = new JLabel(":");
		Form.add(endhour);
		Form.add(endmin);
		Form.add(colon2);

		JLabel timezone = new JLabel("timezone");

		Form.add(timezone);
		timezones = new JComboBox(allzones);
		Form.add(timezones);
		JLabel description = new JLabel("description");
		Form.add(description);
		des = new JTextArea();
		des.setPreferredSize(new Dimension(350, 150));
		des.setLineWrap(true);

		Form.add(des);
		JLabel Location = new JLabel("Location");
		Form.add(Location);
		where = new JTextField(20);
		Form.add(where);
		JLabel Priv = new JLabel("Private or Public event");
		Form.add(Priv);
		String[] privacy = { "public", "private" };
		pub = new JComboBox(privacy);
		Form.add(pub);
		JButton Createit = new JButton("create event");
		Form.add(Createit);
		errmsg = new JLabel("");
		Form.add(errmsg);
		Createit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (checkinput(enterevent.getText(), startmonth
						.getSelectedItem().toString(), startday
						.getSelectedItem().toString(), startyear
						.getSelectedItem().toString(), starthour
						.getSelectedItem().toString(), startmin
						.getSelectedItem().toString(), endmonth
						.getSelectedItem().toString(), endday.getSelectedItem()
						.toString(), endyear.getSelectedItem().toString(),
						endhour.getSelectedItem().toString(), endmin
								.getSelectedItem().toString(), timezones
								.getSelectedItem().toString()) == true) {
					errmsg.setText("calendar created");
					try {
						generatefile(enterevent.getText(), startmonth
								.getSelectedItem().toString(), startday
								.getSelectedItem().toString(), startyear
								.getSelectedItem().toString(), starthour
								.getSelectedItem().toString(), startmin
								.getSelectedItem().toString(), recur
								.getSelectedItem().toString(), endmonth
								.getSelectedItem().toString(), endday
								.getSelectedItem().toString(), endyear
								.getSelectedItem().toString(), endhour
								.getSelectedItem().toString(), endmin
								.getSelectedItem().toString(), timezones
								.getSelectedItem().toString(), des.getText()
								.toString(), where.getText().toString(), pub
								.getSelectedItem().toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					errmsg.setText("Calandar failed");
				}
			}
		});
		Lay.putConstraint(SpringLayout.NORTH, title, 25, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, title, 250, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, eventname, 75,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, eventname, 150, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, enterevent, 75,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, enterevent, 240,
				SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, namemsg, 75, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, namemsg, 500, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, startdate, 100,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, startdate, 150, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, startmonth, 100,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, startmonth, 250,
				SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, startday, 100,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, startday, 320, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, startyear, 100,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, startyear, 390, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, stcalmsg, 100,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, stcalmsg, 500, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, starttime, 150,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, starttime, 150, SpringLayout.WEST,
				Form);

		Lay.putConstraint(SpringLayout.NORTH, starthour, 150,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, starthour, 220, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, colon, 150, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, colon, 265, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, startmin, 150,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, startmin, 270, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, stdatemsg, 150,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, stdatemsg, 500, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, recurence, 150,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, recurence, 340, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, recur, 150, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, recur, 420, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, Enddate, 200, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, Enddate, 150, SpringLayout.WEST,
				Form);

		Lay.putConstraint(SpringLayout.NORTH, endmonth, 200,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, endmonth, 250, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, endday, 200, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, endday, 320, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, endyear, 200, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, endyear, 390, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, encalmsg, 200,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, encalmsg, 500, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, endatemsg, 250,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, endatemsg, 500, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, endtime, 250, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, endtime, 150, SpringLayout.WEST,
				Form);

		Lay.putConstraint(SpringLayout.NORTH, endhour, 250, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, endhour, 220, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, colon2, 250, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, colon2, 265, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, endmin, 250, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, endmin, 270, SpringLayout.WEST,
				Form);

		Lay.putConstraint(SpringLayout.NORTH, timezone, 300,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, timezone, 150, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, timezones, 300,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, timezones, 230, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, description, 350,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, description, 150,
				SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, des, 350, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, des, 220, SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, Location, 525,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, Location, 150, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, where, 525, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, where, 220, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, Priv, 575, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, Priv, 150, SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, pub, 575, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, pub, 300, SpringLayout.WEST, Form);
		Lay.putConstraint(SpringLayout.NORTH, Createit, 625,
				SpringLayout.NORTH, Form);
		Lay.putConstraint(SpringLayout.WEST, Createit, 200, SpringLayout.WEST,
				Form);
		Lay.putConstraint(SpringLayout.NORTH, errmsg, 625, SpringLayout.NORTH,
				Form);
		Lay.putConstraint(SpringLayout.WEST, errmsg, 368, SpringLayout.WEST,
				Form);
		Form.setLayout(Lay);

		F.add(Form);

	}

	/*
	 * TODO Need to implement Later
	 */
	public static boolean checkinput(String name, String stmonth, String stday,
			String styr, String sthr, String stmin, String enmonth,
			String enday, String enyear, String enhour, String enmin,
			String TIMEZONE) throws IllegalArgumentException {
		boolean test = true;
		TimeZone TZ = TimeZone.getTimeZone(TIMEZONE);
		Calendar StartDate = Calendar.getInstance(TZ);
		StartDate.setLenient(false);
		Calendar Current = Calendar.getInstance(TZ);
		Calendar EndDate = Calendar.getInstance(TZ);
		EndDate.setLenient(false);
		StartDate.set(Integer.parseInt(styr), Integer.parseInt(stmonth) - 1,
				Integer.parseInt(stday), Integer.parseInt(sthr),
				Integer.parseInt(stmin), 0);

		EndDate.set(Integer.parseInt(enyear), Integer.parseInt(enmonth) - 1,
				Integer.parseInt(enday), Integer.parseInt(enhour),
				Integer.parseInt(enmin), 0);
		namemsg.setText("");
		stdatemsg.setText("");
		endatemsg.setText("");
		encalmsg.setText("");
		stcalmsg.setText("");
		errmsg.setText("");
		if (name.equals("")) {
			test = false;
			namemsg.setText("Must have event name");
		}
		try {
			if (Current.getTimeInMillis() - StartDate.getTimeInMillis() > 0) {
				test = false;
				stdatemsg.setText("The time is already passed");
				try {
					if (StartDate.getTimeInMillis() - EndDate.getTimeInMillis() >= 0) {
						test = false;
						endatemsg.setText("The end time is before the start");
					}
				} catch (IllegalArgumentException e) {
					test = false;
					encalmsg.setText("this date does not exist");
				}
			}
		} catch (IllegalArgumentException e) {
			test = false;
			stcalmsg.setText("this date does not exist");
		}
		try {

			if (StartDate.getTimeInMillis() - EndDate.getTimeInMillis() >= 0) {
				test = false;
				endatemsg.setText("The end time is before the start");
			}
		} catch (IllegalArgumentException e) {
			test = false;
			encalmsg.setText("this date does not exist");
		}
		return test;
	}

	/*
	 * 
	 * Generates .ics file
	 */
	public static void generatefile(String name, String stmonth, String stday,
			String styr, String sthr, String stmin, String isrecurring,
			String enmonth, String enday, String enyear, String enhour,
			String enmin, String tzone, String details, String location,
			String priv) throws IOException {
		File ics = new File(name + ".ics"); // TODO change to .ics after testing
		BufferedWriter write = new BufferedWriter(new FileWriter(ics));
		write.write("BEGIN:VCALENDAR");
		write.newLine();
		write.write("VERSION:2.0");
		write.newLine();
		write.write("BEGIN:VEVENT");
		write.newLine();
		write.write("SUMMARY:" + name);
		write.newLine();
		write.write("DTSTART;TZID=" + tzone + ";VALUE=DATE-TIME:" + styr
				+ stmonth + stday + "T" + sthr + stmin + "00Z");
		write.newLine();
		if (isrecurring.equals("none") == false) {
			write.write("RRULE:FREQ=" + isrecurring.toUpperCase());
			write.newLine();
		}
		write.write("DTEND;TZID=" + tzone + ";VALUE=DATE-TIME:" + enyear
				+ enmonth + enday + "T" + enhour + enmin + "00Z");
		write.newLine();
		write.write("LOCATION:" + location);
		write.newLine();
		write.write("DESCRIPTION:" + details);
		write.newLine();
		write.write("CLASS:" + priv.toUpperCase());
		write.newLine();
		write.write("END:VEVENT");
		write.newLine();
		write.write("END:VCALENDAR");
		write.close();
	}
}
