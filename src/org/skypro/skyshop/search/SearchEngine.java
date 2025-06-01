package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new ArrayList<>();
    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            searchables.add(searchable);
        }
    }


    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();
        if (query == null || query.isBlank()) {
            return results;
        }

        String lowerQuery = query.toLowerCase();
        for (Searchable item : searchables) {
            if (item.getSearchTerm().toLowerCase().contains(lowerQuery)) {
                results.add(item);
            }
        }
        return results;
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query);
        }

        Searchable bestMatch = null;
        int maxCount = 0;
        String lowerQuery = query.toLowerCase();

        for (Searchable item : searchables) {
            String text = item.getSearchTerm().toLowerCase();
            int count = countOccurrences(text, lowerQuery);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(query);
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