package com.swd.services;

import com.swd.entities.Reservation;
import com.swd.entities.Tables;
import com.swd.exception.EntityNotFoundException;
import com.swd.repositories.ReservationRepository;
import com.swd.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Tables getById(Long id) {
        return tableRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Tables.class, "id", id.toString()));
    }

    public List<Tables> getAll() {
        return tableRepository.findAll();
    }

    public Boolean isBooked(Long id) {
        Tables table = tableRepository.findById(id).orElse(null);
        if (table == null) {
            throw new EntityNotFoundException(Tables.class, "id", id.toString());
        }
        return false;
    }

    public Tables saveTable(Tables table) {
        return tableRepository.save(table);
    }

    public Boolean existsById(Long id) {
        return tableRepository.existsById(id);
    }

    public void deleteTableById(Long id) {
        tableRepository.deleteById(id);
    }

    public List<Tables> getAvailableTables(Date startTime, Date endTime) {
        // Find all tables that are not booked at a specific time
        // 1. Get all tables
        // 2. Get all reservations that are booked at a specific time
        // 3. Get all tables that are not in the list of tables that are booked at a specific time

        // Convert time and date to Instant
        List<Tables> tables = tableRepository.findAll();

        List<Reservation> reservations = reservationRepository.findAllReservationsInTime(startTime, endTime);

        return tables;
    }
}
