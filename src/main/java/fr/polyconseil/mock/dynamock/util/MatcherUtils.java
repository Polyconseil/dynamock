package fr.polyconseil.mock.dynamock.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class MatcherUtils {
	
	public static boolean match(String requestBody, String urlPattern) {
		if (StringUtils.isEmpty(urlPattern)) {
			return true;
		}
		Pattern regex = Pattern.compile(urlPattern, Pattern.DOTALL);
		Matcher m = regex.matcher(requestBody);
		return m.find();
	}
}
