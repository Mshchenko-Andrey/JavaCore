package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchables;
    private static final Comparator<Searchable> SEARCHABLE_COMPARATOR =
            Comparator.comparingInt((Searchable s) -> s.getName().length())
                    .reversed()
                    .thenComparing(Searchable::getName);

    public SearchEngine() {
        this.searchables = new HashSet<>();
    }

    public void add(Searchable searchable) {
        if (searchable != null) {
            searchables.add(searchable);
        }
    }

    public TreeSet<Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return new TreeSet<>(SEARCHABLE_COMPARATOR);
        }

        String lowerQuery = query.toLowerCase();
        return searchables.stream()
                .filter(item -> item.getSearchTerm().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(SEARCHABLE_COMPARATOR)
                ));
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query);
        }

        return search(query).first();
    }

    public int size() {
        return searchables.size();
    }

    public void clear() {
        searchables.clear();
    }
}