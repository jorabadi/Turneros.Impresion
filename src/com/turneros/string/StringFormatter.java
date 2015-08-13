package com.turneros.string;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFormatter {

    private static final String fieldStart = "\\$\\{";
    private static final String fieldEnd = "\\}";

    private static final String regex = fieldStart + "([^}]+)" + fieldEnd;
    private static final Pattern pattern = Pattern.compile(regex);

    public static String format(String format, Object object) {
        Matcher m = pattern.matcher(format);
        String result = format;
        while (m.find()) {
			try {
				String fieldName = m.group(1);
	            Field field = object.getClass().getField(fieldName);
				String value = field.get(object).toString();
				result = result.replaceFirst(regex, value);
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        return result;
    }
}