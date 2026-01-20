package com.example.inventory.service;

import com.example.inventory.dto.InventoryItemRequest;
import com.example.inventory.model.InventoryItem;

import java.util.List;
import java.util.UUID;

public interface InventoryItemService {
    InventoryItem createInventoryItem(InventoryItemRequest request);
    InventoryItem getInventoryItemById(UUID id);
    List<InventoryItem> getAllInventoryItems();
    InventoryItem updateInventoryItem(UUID id, InventoryItemRequest request);
    void deleteInventoryItem(UUID id);
    boolean checkAvailability(UUID productId, Integer quantity);
}