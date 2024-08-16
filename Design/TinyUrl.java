package Design;

import java.util.HashMap;
import java.util.Map;
/*
 * https://leetcode.com/problems/encode-and-decode-tinyurl
 */
public class TinyUrl {
    private static final String TINY_URL = "https://tinyurl.com";
    private static final int LENGTH = 8;
    private static final StringBuilder chars = new StringBuilder();
    private final Map<String, String> urls = new HashMap<>();
    private final Map<String, String> revUrls = new HashMap<>();


    public TinyUrl() {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            chars.append(ch);
            chars.append(Character.toUpperCase(ch));
        }
        for (int i = 0; i <= 9; i++) {
            chars.append(i);
        }
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (urls.containsKey(longUrl))
            return TINY_URL + urls.get(longUrl);
        String key = null;

        do {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < LENGTH; i++) {
                int idx = (int)(Math.random() * chars.length());
                sb.append(chars.charAt(idx));
            }
            key = sb.toString();
        } while (revUrls.containsKey(key));

        urls.put(key, longUrl);
        revUrls.put(longUrl, key);
        return TINY_URL + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return urls.get(shortUrl.replace(TINY_URL, ""));
    }
}
