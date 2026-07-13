package mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
