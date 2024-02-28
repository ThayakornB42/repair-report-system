package com.cp.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairmanRepository extends CrudRepository<Repairman, Integer> {

}
