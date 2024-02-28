package com.cp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
    @Autowired
    private RequestRepository requestRepository;

    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    public Request getRequestById(Integer id) {
        return requestRepository.findById(id).orElse(null);
    }

    public Request updateRequest(Request updatedRequest) {
        return requestRepository.save(updatedRequest);
    }

    public void deleteRequestById(Integer id) {
    	requestRepository.deleteById(id);
    }
    
    public List<Request> findAll() {
        return (List<Request>) requestRepository.findAll();
    }

	public List<Request> searchRequests(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
