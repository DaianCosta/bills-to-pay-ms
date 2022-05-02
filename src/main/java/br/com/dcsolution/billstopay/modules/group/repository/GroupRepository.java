package br.com.dcsolution.billstopay.modules.group.repository;

import br.com.dcsolution.billstopay.modules.group.entity.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("FROM Group g WHERE LOWER(g.name) like %:searchTerm%")
    Page<Group> search(@Param("searchTerm") final String searchTerm, final Pageable pageable);
}
