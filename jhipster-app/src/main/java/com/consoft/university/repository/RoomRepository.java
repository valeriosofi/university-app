package com.consoft.university.repository;

import com.consoft.university.domain.Room;
import com.consoft.university.domain.Booking;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Room entity.
 */
@SuppressWarnings("unused")
public interface RoomRepository extends JpaRepository<Room,Long> {
   @Query("select distinct room from Room room where room.id NOT IN "
            + "(select distinct booking.room from Booking booking where booking.course is not null AND booking.timeSlot = '10.00-11.30' AND booking.date = '2017-05-14')")
    List<Room> findAllFreeRooms();
}
