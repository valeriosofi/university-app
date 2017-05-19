package com.consoft.university.repository;

import com.consoft.university.domain.Room;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.*;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Room entity.
 */
@SuppressWarnings("unused")
public interface RoomRepository extends JpaRepository<Room,Long> {
   @Query("select distinct room from Room room where room.id NOT IN "
            + "(select distinct booking.room from Booking booking where booking.timeSlot = :timeSlot AND booking.date = :date "
           + "AND (booking.course is not null OR (booking.studyGroup is not null AND "
           + "booking.room.capacity <=(select SUM(booking2.studyGroup.numMembers) from Booking booking2 where booking2.room=booking.room AND booking2.timeSlot = :timeSlot AND booking2.date = :date ))))")
    List<Room> findAllFreeRooms(@Param("timeSlot") String timeSlot, @Param("date") LocalDate date);
}
