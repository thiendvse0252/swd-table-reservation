package com.swd.services;


import com.swd.entities.Reservation;
import com.swd.exception.EntityNotFoundException;
import com.swd.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation getById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Reservation.class, "reservation_id", id.toString()));
    }

    public List<Reservation> getAll(){
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Boolean existsById(Long id) {
        return reservationRepository.existsById(id);
    }

    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }
}
