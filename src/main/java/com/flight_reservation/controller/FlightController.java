package com.flight_reservation.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flight_reservation.entity.Flight;
import com.flight_reservation.repository.FlightRepository;

@Controller
public class FlightController {
	
	//this below method is used for get all method by repository layer.
	@Autowired
	private FlightRepository flightRepo;
	
	@RequestMapping("/findFlights")
	public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to,@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, ModelMap modelMap  ) {
		List<Flight> findFlights = flightRepo.findFlights(from,to,departureDate);
//		System.out.println(findFlights); //it will display all flights objects.
		//with the help of model map we can exchange the data from jsp to servalet
		modelMap.addAttribute("findFlights", findFlights); //"findFlights"-- it is variable
		return "displayFlights";
	}
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") long flightId,ModelMap modelMap) {
		Optional<Flight> findById = flightRepo.findById(flightId);
		Flight flight = findById.get();
		//System.out.println(flightId);
//		System.out.println(flight.getId());
//		System.out.println(flight.getArrivalCity());
//		System.out.println(flight.getDepartureCity());
//		System.out.println(flight.getFlightNumber());
//		System.out.println(flight.getOperatingAirlines());//ThIS AABOVE 5 LINE WILL BE SHOW ON CONSOLE
	//To show record into jsp use ModelMap 
		modelMap.addAttribute("flight", flight);
		return "showReservation";
		
	}

}
