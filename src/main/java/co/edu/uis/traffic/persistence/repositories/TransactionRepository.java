package co.edu.uis.traffic.persistence.repositories;

import co.edu.uis.traffic.persistence.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
