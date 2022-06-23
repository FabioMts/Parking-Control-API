package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.exception.ConflictException;
import com.api.parkingcontrol.exception.ResourceNotFoundException;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository repository;


    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        return repository.save(parkingSpotModel);
    }


    public void existsByLicensePlateCar(String licensePlateCar) {
        boolean licensePlateCarExists = repository.existsByLicensePlateCar(licensePlateCar);
        if(!licensePlateCarExists) {
            throw new ConflictException("License Plate Car is already in use!");
        }
    }

    public void existsByParkingSpotNumber(String parkingSpotNumber) {
        boolean parkingSpotNumberExists = repository.existsByParkingSpotNumber(parkingSpotNumber);
        if(!parkingSpotNumberExists) {
            throw new ConflictException("Parking Spot is already in use!");
        }
    }


    public void existsByApartmentAndBlock(String apartment, String block) {
        boolean apartmentAndBlockExists = repository.existsByApartmentAndBlock(apartment, block);
        if(!apartmentAndBlockExists) {
            throw new ConflictException("Parking Spot already registered for this apartment/block!");
        }
    }

    public void validadeParkingSpot(ParkingSpotDTO parkingSpotDto) {
        existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock());
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ParkingSpotModel findById(UUID id) {
         Optional<ParkingSpotModel> obj = repository.findById(id);
                return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
         repository.delete(parkingSpotModel);
    }
}
