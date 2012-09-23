package util;

import java.util.Calendar;

import javax.swing.JOptionPane;

//classe singleton

public class Validation {

	private static Validation instance;

	private Validation() {

	}

	public static Validation getInstance() {
		if (instance == null)
			instance = new Validation();
		return instance;
	}

	public boolean validateIP(String ip) {

		try {
			if ((ip == null) || ip.equals("")) {
				JOptionPane.showMessageDialog(null, "Enter an IP address");
				return false;
			}

			String ips[] = ip.split("\\.");

			if (ips.length != 4) {
				JOptionPane.showMessageDialog(null, "Invalid IP");
				return false;
			}

			for (String s : ips) {
				int i = Integer.parseInt(s);
				if ((i < 0) || (i > 255)) {
					JOptionPane.showMessageDialog(null, "Invalid IP");
					return false;
				}
			}

			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid IP");
			return false;
		}

	}

	public boolean validateMask(String mask) {

		try {
			if ((mask == null) || (mask.equals(""))) {
				JOptionPane.showMessageDialog(null, "Enter a subnet mask");
				return false;
			}

			String parts[] = mask.split("\\.");

			if (parts.length != 4) {
				JOptionPane.showMessageDialog(null, "Invalid mask");
				return false;
			}
			for (String s : parts) {
				int i = Integer.parseInt(s);
				if ((i != 0) && (i != 128) && (i != 192) && (i != 224) && (i != 240) && (i != 248) && (i != 252) && (i != 254)
						&& (i != 255)) {
					JOptionPane.showMessageDialog(null, "Invalid mask");
					return false;
				}
			}

			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid mask");
			return false;
		}
	}

	public boolean validateDate(String date, String time) {
		try {

			if ((date == null) || (date.equals(""))) {
				JOptionPane.showMessageDialog(null, "Enter a date");
				return false;
			}
			String[] dates = date.split("/");
			System.out.println(dates.length + " passei aqui");
			if (dates.length != 3) {
				JOptionPane.showMessageDialog(null, "Invalid date");
				return false;
			}

			if ((time == null) || (time.equals(""))) {
				JOptionPane.showMessageDialog(null, "Enter a time");
				return false;
			}
			String[] times = time.split(":");
			if (times.length != 3) {
				JOptionPane.showMessageDialog(null, "Invalid time");
				return false;
			}

			int[] intdatetime = new int[6];
			for (int i = 0; i < dates.length; i++) {
				intdatetime[i] = Integer.parseInt(dates[i]);

			}

			for (int i = 0; i < times.length; i++) {
				intdatetime[i + 3] = Integer.parseInt(times[i]);

			}

			Calendar c = Calendar.getInstance();

			c.setLenient(false);

			c.set(intdatetime[2], intdatetime[1] - 1, intdatetime[0], intdatetime[3], intdatetime[4], intdatetime[5]);

			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid date or time");
			return false;
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Invalid date or time");
			return false;
		}

	}
	
	public boolean validateMAC(String mac){
		
		if ((mac == null) || (mac.equals(""))) {
			JOptionPane.showMessageDialog(null, "Enter a MAC address");
			return false;
		}
		
		if (mac.length()>14){
			JOptionPane.showMessageDialog(null, "Invalid MAC address");
			return false;
		}
		
		String regex = new String("^[0-9a-fA-F.]+$");
		if (!mac.matches(regex)){
			JOptionPane.showMessageDialog(null, "Invalid MAC address");
			return false;
		}
		
		return true;
	}
}
