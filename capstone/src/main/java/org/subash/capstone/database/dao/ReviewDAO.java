package org.subash.capstone.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.subash.capstone.database.entity.Review;

public interface ReviewDAO extends JpaRepository<Review, Long> {
}
