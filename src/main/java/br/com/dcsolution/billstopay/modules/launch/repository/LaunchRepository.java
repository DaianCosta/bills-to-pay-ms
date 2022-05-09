package br.com.dcsolution.billstopay.modules.launch.repository;

import br.com.dcsolution.billstopay.modules.launch.entity.Launch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaunchRepository extends JpaRepository<Launch, Integer> {

    @Query("FROM Launch l WHERE LOWER(l.description) like %:searchTerm%")
    Page<Launch> search(@Param("searchTerm") final String searchTerm, final Pageable pageable);

    @Query("FROM Launch l WHERE LOWER(l.description) = :searchTerm and l.id <> :id")
    Optional<Launch> isExistDescription(@Param("searchTerm") final String searchTerm, @Param("id") final Integer id);

}
