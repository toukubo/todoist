package net.enclosing.todoist;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class Utils {
	public static void printItems(List<Item> items) {
		for (Item item : items) {
			System.err.print(item.getId()+":");
			System.err.println(item.getContent());
		}
	}

	public static void printLabels(List<Label> labels) {
		for (Label label : labels) {
			System.err.print(label.getId()+":");
			System.err.println(label.getName());
		}

	}
	public static String encode(String url) throws UnsupportedEncodingException {
        String[] urlElements = url.split("/");
        String result = "";
        for (String s : urlElements) {
            result += URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20").replaceAll("%3A", ":") + "/";
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

}
