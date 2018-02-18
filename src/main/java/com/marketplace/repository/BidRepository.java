package com.marketplace.repository;

import com.marketplace.resource.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<Bid, String> {

    Bid findFirstByProjectIdOrderByBidPriceAsc(String projectId);

}
