package com.thehecklers.neoai;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;

import java.util.*;
import java.util.stream.Collectors;

public class NeoVectorStore implements VectorStore {
    private final EmbeddingClient client;
    private final PlaceRepository repo;

    public NeoVectorStore(EmbeddingClient client, PlaceRepository repo) {
        this.client = client;
        this.repo = repo;
    }

    @Override
    public void add(List<Document> documents) {
        //repo.saveAll(documents);
    }

    @Override
    public Optional<Boolean> delete(List<String> idList) {
        return Optional.empty();
    }

    @Override
    public List<Document> similaritySearch(String query) {
        //return null;
        System.out.println("Similarity Search query: " + query);
        List<Document> documents = new ArrayList<>();
        //Iterator<Place> iterator = repo.findPlacesByAmenitiesLikeIgnoreCaseOrNameLikeIgnoreCaseOrHostLikeIgnoreCase(query)
        Iterator<Place> iterator = repo.findPlacesByNameContainingIgnoreCase(query)
                .iterator();
        iterator.forEachRemaining(p -> documents.add(new Document(p.id(), Map.of(p.id(), p))));
        return documents;
    }

    @Override
    public List<Document> similaritySearch(String query, int k) {
        //return null;
        return similaritySearch(query).stream().limit(k).collect(Collectors.toList());
    }

    @Override
    public List<Document> similaritySearch(String query, int k, double threshold) {
        //return null;
        return similaritySearch(query, k);
    }
}