package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
    }

    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count++] = searchable;
        }
    }

    public Searchable findBestMatch(String searchQuery) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = -1;
        String lowerQuery = searchQuery.toLowerCase();

        for (int i = 0; i < count; i++) {
            String text = searchables[i].getSearchTerm().toLowerCase();
            int occurrences = countOccurrences(text, lowerQuery);

            if (occurrences > maxCount) {
                maxCount = occurrences;
                bestMatch = searchables[i];
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(searchQuery);
        }
        return bestMatch;
    }

    private int countOccurrences(String text, String substring) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }
        return count;
    }
}