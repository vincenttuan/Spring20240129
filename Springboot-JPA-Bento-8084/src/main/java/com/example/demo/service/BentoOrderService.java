package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bento;
import com.example.demo.model.BentoOrder;
import com.example.demo.repository.BentoOrderRepository;
import com.example.demo.repository.BentoRepository;

import jakarta.transaction.Transactional;

@Service
public class BentoOrderService {
	
	@Autowired
	private BentoOrderRepository bentoOrderRepository;
	
	@Autowired
	private BentoService bentoService;
	
	@Transactional
	public BentoOrder createBentoOrder(BentoOrder bentoOrder) {
		Long bentoId = bentoOrder.getBento().getId();
		// 確認是否有此便當
		Bento bento = bentoService.getBentoById(bentoId);
		if(bento == null) {
			throw new RuntimeException("Bento 不存在");
		}
		// 確認庫存數量是否足夠
		if(bento.getQuantity() < bentoOrder.getAmount()) {
			throw new RuntimeException("Bento 庫存不足");
		}
		
		// 下訂單
		BentoOrder savedBentoOrder = bentoOrderRepository.save(bentoOrder);
		// Bento 庫存要扣除
		bentoService.decreaseBentoQuantity(bentoId, bentoOrder.getAmount());
		
		return bentoOrder;
	}
	
	@Transactional
    public void deleteBentoOrder(Long id) {
        BentoOrder bentoOrder = bentoOrderRepository.findById(id).orElse(null);
		if (bentoOrder != null) {
			bentoOrderRepository.delete(bentoOrder);
			// Bento 的數量要加回來
			bentoService.increaseBentoQuantity(bentoOrder.getBento().getId(), bentoOrder.getAmount());
		}
    }
	
	public List<BentoOrder> getAllBentoOrders() {
		return bentoOrderRepository.findAll();
	}
    
	public BentoOrder getBentoOrderById(Long id) {
		return bentoOrderRepository.findById(id).orElse(null);
	}
	
}
