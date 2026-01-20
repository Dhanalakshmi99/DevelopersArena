package com.example.inventory.repository;

import com.example.inventory.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface InventoryRepository extends MongoRepository<InventoryItem, UUID> {
}