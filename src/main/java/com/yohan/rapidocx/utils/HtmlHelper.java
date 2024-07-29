package com.yohan.rapidocx.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlHelper {

    public static String convertHslToRgb(String style) {
        Pattern pattern = Pattern.compile("hsl\\((\\d+),\\s*(\\d+)%,\\s*(\\d+)%\\)");
        Matcher matcher = pattern.matcher(style);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            int h = Integer.parseInt(matcher.group(1));
            int s = Integer.parseInt(matcher.group(2));
            int l = Integer.parseInt(matcher.group(3));
            String rgb = hslToRgb(h, s, l);
            matcher.appendReplacement(sb, rgb);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static String hslToRgb(int h, int s, int l) {
        double r, g, b;

        double sNorm = s / 100.0;
        double lNorm = l / 100.0;

        if (s == 0) {
            r = g = b = lNorm; // achromatic
        } else {
            double q = lNorm < 0.5 ? lNorm * (1 + sNorm) : lNorm + sNorm - lNorm * sNorm;
            double p = 2 * lNorm - q;
            r = hue2rgb(p, q, h / 360.0 + 1 / 3.0);
            g = hue2rgb(p, q, h / 360.0);
            b = hue2rgb(p, q, h / 360.0 - 1 / 3.0);
        }

        return String.format("rgb(%d, %d, %d)", Math.round(r * 255), Math.round(g * 255), Math.round(b * 255));
    }

    private static double hue2rgb(double p, double q, double t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1 / 6.0) return p + (q - p) * 6 * t;
        if (t < 1 / 2.0) return q;
        if (t < 2 / 3.0) return p + (q - p) * (2 / 3.0 - t) * 6;
        return p;
    }

}
