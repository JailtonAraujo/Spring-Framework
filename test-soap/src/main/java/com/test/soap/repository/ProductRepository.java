package com.test.soap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.soap.Entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    
    public ProductEntity findByUid (String uid);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM product WHERE uid = ?1", nativeQuery = true)
    public void deleteByUid (String uid);
}
