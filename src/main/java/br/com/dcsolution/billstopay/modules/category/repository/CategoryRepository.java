package br.com.dcsolution.billstopay.modules.category.repository;

import br.com.dcsolution.billstopay.modules.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("FROM Category c WHERE LOWER(c.name) like %:searchTerm%")
    Page<Category> search(@Param("searchTerm") final String searchTerm, final Pageable pageable);

    @Query("FROM Category c WHERE LOWER(c.name) = :searchTerm and c.id <> :id")
    Optional<Category> isExistName(@Param("searchTerm") final String searchTerm, @Param("id") final Integer id);

}
