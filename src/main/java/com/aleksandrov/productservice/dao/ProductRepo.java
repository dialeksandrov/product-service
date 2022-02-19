package com.aleksandrov.productservice.dao;

import com.aleksandrov.productservice.dao.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

public interface ProductRepo extends JpaRepository<ProductEntity, Long> {

    @Query(value = "select * from products where currency_prop = :currency and language_prop = :language", nativeQuery = true)
    Page<ProductEntity> findAllSorted(@Param("currency") String currency, @Param("language") String language, Pageable paging);

    @Query(value = "select * from products where UPPER(product_name) like :product_name% and currency_prop = :currency and language_prop = :language", nativeQuery = true)
    Page<ProductEntity> findByName(@Param("product_name") String name, @Param("currency") String currency,
                                                @Param("language") String language, Pageable paging);

    @Query(value = "select * from products where UPPER(description) like :description% and currency_prop = :currency and language_prop = :language", nativeQuery = true)
    Page<ProductEntity> findByDescription(@Param("description") String description, @Param("currency") String currency,
                                          @Param("language") String language, Pageable paging);

    @Query(value = "select * from products where id = :product_id and currency_prop = :currency and language_prop = :language_prop", nativeQuery = true)
    ProductEntity findByIdAndSort(@Param("product_id") Long id, @Param("currency") String currency, @Param("language_prop") String language);

}
