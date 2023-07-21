package com.swd.repositories;


import com.swd.entities.Reservation;
import com.swd.entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
    Reservation findByUserIdAndTableId(Long userId, Long tableId);

    @Query("SELECT r FROM Reservation r WHERE r.startTime >= ?1 AND r.endTime <= ?2")
    List<Reservation> findAllReservationsInTime(Date startTime, Date endTime);
}
