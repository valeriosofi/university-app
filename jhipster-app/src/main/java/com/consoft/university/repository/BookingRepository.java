package com.consoft.university.repository;

import com.consoft.university.domain.Booking;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Booking entity.
 */
@SuppressWarnings("unused")
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
