package util;

import javax.swing.JOptionPane;

public class Validation {

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
	
	

}
