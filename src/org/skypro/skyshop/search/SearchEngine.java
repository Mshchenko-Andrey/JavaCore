package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] searchables;
    private int count;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count++] = searchable;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;

        for (int i = 0; i < count && found < 5; i++) {
            if (searchables[i].getSearchTerm().toLowerCase()
                    .contains(query.toLowerCase())) {
                results[found++] = searchables[i];
            }
        }
        return results;
    }
}