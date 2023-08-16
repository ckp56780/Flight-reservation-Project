package com.flight_reservation.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flight_reservation.dto.ReservationRequest;
import com.flight_reservation.entity.Flight;
import com.flight_reservation.entity.Passenger;
import com.flight_reservation.entity.Reservation;
import com.flight_reservation.repository.FlightRepository;
import com.flight_reservation.repository.PassengerRepository;
import com.flight_reservation.repository.ReservationRepository;
//import com.flight_reservation.utilities.PDFGenerator;
@Service
public class ReservationServiceImpl implements ReservationService {
	
//	@Autowired
//	private PDFGenerator  pdfGenerator;
	
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private ReservationRepository ReservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		//String filePath="D:\\JAN_STS\\flight_reservation\\tickets\\reservation"+Reservation.class.getId()+".pdf";
		
	//save all data into passenger table:
	Passenger passenger=new Passenger();
	passenger.setFirstName(request.getFirstName());
	passenger.setLastName(request.getLastName());
	passenger.setMiddleName(request.getMiddleName());
	passenger.setEmail(request.getEmail());
	passenger.setPhone(request.getPhone());
	passengerRepo.save(passenger);
	
	
	long flightId=request.getFlightId();
	Optional<Flight> findById = flightRepo.findById(flightId);
	Flight flight = findById.get();
	
	
	Reservation reservation=new Reservation();
	reservation.setFlight(flight);
	reservation.setPassenger(passenger);
	reservation.setCheckedIn(false);
	reservation.setNumberOfBags(0);
	
	//String filePath="D:\\JAN_STS\\flight_reservation\\tickets\\reservation"+reservation.getId()+".pdf";
	
	ReservationRepo.save(reservation);
	
	
	
	//pdfGenerator.generateItinerary(reservation,filePath);
	
		return reservation;
	}

}
