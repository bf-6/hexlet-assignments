package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetween(Double min, Double max, Sort sort);

    List<Product> findByPriceGreaterThanEqual(Double min, Sort sort);

    List<Product> findByPriceLessThanEqual(Double max, Sort sort);

    List<Product> findAll(Sort sort);
    // END
}
