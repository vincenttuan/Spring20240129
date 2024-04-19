package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Bento;

@Repository
public interface BentoRepository extends JpaRepository<Bento, Long> {
	// 減少 Bento 的庫存數量
    @Modifying
    @Transactional
    @Query("update Bento b set b.quantity = b.quantity - :amount where b.id = :id and b.quantity >= :amount")
    public void decreaseBentoQuantity(@Param("id") Long id, @Param("amount") Integer amount); 
    
    // 增加 Bento 的庫存數量
    @Modifying
    @Transactional
    @Query("update Bento b set b.quantity = b.quantity + :amount where b.id = :id")
    public void increaseBentoQuantity(@Param("id") Long id, @Param("amount") Integer amount);
	
}
