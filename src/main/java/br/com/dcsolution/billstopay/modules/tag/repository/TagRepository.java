package br.com.dcsolution.billstopay.modules.tag.repository;

import br.com.dcsolution.billstopay.modules.tag.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;


@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("FROM Tag t WHERE LOWER(t.name) like %:searchTerm%")
    Page<Tag> search(@Param("searchTerm") final String searchTerm, final Pageable pageable);

    @Query("FROM Tag t WHERE LOWER(t.name) = :searchTerm and t.id <> :id")
    Optional<Tag> isExistName(@Param("searchTerm") final String searchTerm, @Param("id") final Integer id);

}
