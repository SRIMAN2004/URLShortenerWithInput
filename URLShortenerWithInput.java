import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class URLShortenerWithInput {
    // HashMap to store long URLs against short URL keys
    private Map<String, String> urlMap;
    private Map<String, String> keyMap;
    private String domain;
    private String keyChars;
    private Random random;
    private int keyLength;

    // Constructor
    public URLShortenerWithInput(String domain, int keyLength) {
        this.urlMap = new HashMap<>();
        this.keyMap = new HashMap<>();
        this.domain = domain;
        this.keyLength = keyLength;
        this.keyChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        this.random = new Random();
    }

    // Method to generate a random key
    private String generateKey() {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            key.append(keyChars.charAt(random.nextInt(keyChars.length())));
        }
        return key.toString();
    }

    // Method to shorten a URL
    public String shortenURL(String longURL) {
        if (keyMap.containsKey(longURL)) {
            return domain + "/" + keyMap.get(longURL);
        } else {
            String key;
            do {
                key = generateKey();
            } while (urlMap.containsKey(key));

            urlMap.put(key, longURL);
            keyMap.put(longURL, key);

            return domain + "/" + key;
        }
    }

    // Method to retrieve the original URL
    public String getOriginalURL(String shortURL) {
        String key = shortURL.substring(domain.length() + 1);
        return urlMap.get(key);
    }

    // Main method for user input and shortening the URL
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        URLShortenerWithInput urlShortener = new URLShortenerWithInput("http://short.url", 6);

        // Input long URL from the user
        System.out.println("Enter the URL to shorten:");
        String originalURL = scanner.nextLine();

        // Shorten the URL
        String shortURL = urlShortener.shortenURL(originalURL);

        // Display the shortened URL
        System.out.println("Shortened URL: " + shortURL);

        // Optionally, retrieve the original URL from the shortened URL
        System.out.println("Enter the shortened URL to retrieve the original URL:");
        String inputShortURL = scanner.nextLine();
        String retrievedURL = urlShortener.getOriginalURL(inputShortURL);

        System.out.println("Retrieved Original URL: " + retrievedURL);

        scanner.close();
    }
}