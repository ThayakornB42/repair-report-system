package com.cp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairmanService {
	
    @Autowired
    private RepairmanRepository repairmanRepository;

    public Repairman createRepairman(Repairman repairman) {
        return repairmanRepository.save(repairman);
    }

    public Repairman getRepairmanById(Integer id) {
        return repairmanRepository.findById(id).orElse(null);
    }

    public List<Repairman> getAllRepairman() {
        return (List<Repairman>) repairmanRepository.findAll();
    }
    
    public Repairman updateRepairman(Repairman updatedUser) {
        return repairmanRepository.save(updatedUser);
    }

    public void deleteRepairmanById(Integer id) {
    	repairmanRepository.deleteById(id);
    }
   
}