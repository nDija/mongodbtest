package com.boulanger.mongotest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boulanger.mongotest.model.Store;

public interface StoreRepository extends MongoRepository<Store, String>{
    Store findByName(String storeName);
}
