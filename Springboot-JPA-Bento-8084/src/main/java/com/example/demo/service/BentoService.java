package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bento;
import com.example.demo.repository.BentoRepository;

@Service
public class BentoService {
	@Autowired
	private BentoRepository bentoRepository;
	
	public Bento getBentoById(Long id) {
		return bentoRepository.findById(id).orElse(null);
	}
	
	public Bento createBento(Bento bento) {
		return bentoRepository.save(bento);
	}
	
	public List<Bento> getAllBentos() {
		return bentoRepository.findAll();
	}
	
	public void decreaseBentoQuantity(Long id, Integer amount) {
		bentoRepository.decreaseBentoQuantity(id, amount);
	}
	
	public void increaseBentoQuantity(Long id, Integer amount) {
		bentoRepository.increaseBentoQuantity(id, amount);
	}
	
}
