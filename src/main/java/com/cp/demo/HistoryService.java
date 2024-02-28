package com.cp.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
   
	@Autowired
    private HistoryRepository historyRepository;

    public History createHistory(History history) {
        return historyRepository.save(history);
    }

    public History getHistoryById(Integer id) {
        return historyRepository.findById(id).orElse(null);
    }

    public History updateHistory(History updatedHistory) {
        return historyRepository.save(updatedHistory);
    }

    public void deleteHistoryById(Integer id) {
        historyRepository.deleteById(id);
    }
}
