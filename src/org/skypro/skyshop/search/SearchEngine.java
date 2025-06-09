package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

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

    public TreeMap<String, Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return new TreeMap<>();
        }

        String lowerQuery = query.toLowerCase();
        return searchables.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toMap(
                        Searchable::getName,
                        item -> item,
                        (existing, replacement) -> existing,
                        TreeMap::new
                ));
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

            if (count > maxCount || (count == maxCount && bestMatch == null)) {
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


    public int size() {
        return searchables.size();
    }


    public void clear() {
        searchables.clear();
    }
}