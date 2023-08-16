package com.flight_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flight_reservation.Service.ReservationService;
import com.flight_reservation.dto.ReservationRequest;
import com.flight_reservation.entity.Reservation;


@Controller
public class ReservationController {
	@Autowired
	 private ReservationService reservationService;
	
	@RequestMapping("/confirmReservation")
	//created here dto layer to fetch data
	public String confirmReservation(ReservationRequest request ,ModelMap modelMap) {
		Reservation reservationId = reservationService.bookFlight(request);
		modelMap.addAttribute("reservationId", reservationId.getId());
		return "confirmReservation";
	}

}
