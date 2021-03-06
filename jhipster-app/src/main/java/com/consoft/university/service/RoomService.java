package com.consoft.university.service;

import com.consoft.university.domain.Room;
import java.time.LocalDate;
import java.util.List;

/**
 * Service Interface for managing Room.
 */
public interface RoomService {

    /**
     * Save a room.
     *
     * @param room the entity to save
     * @return the persisted entity
     */
    Room save(Room room);

    /**
     *  Get all the rooms.
     *  
     *  @return the list of entities
     */
    List<Room> findAll();

    /**
     *  Get the "id" room.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Room findOne(Long id);

    /**
     *  Delete the "id" room.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
    
    List<Room> findAllFreeRooms(String timeSlot, LocalDate date, Integer numMembers);
}
