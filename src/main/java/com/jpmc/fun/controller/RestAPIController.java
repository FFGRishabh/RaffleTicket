/**
 * 
 */
package com.jpmc.fun.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.fun.jdbc.RaffleJDBCRepository;
import com.jpmc.fun.model.RaffleTicket;

/**
 * @author Rishabh
 *
 */
@RestController
public class RestAPIController {
	
	@Autowired
	RaffleJDBCRepository raffleDao;
	
	@PostMapping("/getAllRaffleTickets")
	public ResponseEntity<?> getAllRaffleTickets() {

		List<RaffleTicket> allRaffleTicket = raffleDao.getAllRaffleTicket();
		return ResponseEntity.ok(allRaffleTicket);
	}

	@PostMapping("/generateRaffleWinner")
	public ResponseEntity<?> generateRaffleWinner() {

		List<RaffleTicket> allNonWinners = raffleDao.getAllRaffleTicketExcludingWinners();

		if (!CollectionUtils.isEmpty(allNonWinners)) {

			Random random = new Random();
			RaffleTicket raffleTicket = allNonWinners.get(random.nextInt(allNonWinners.size()));
			int updateWinner = raffleDao.updateWinner(raffleTicket.getSid());

			if (updateWinner > 0) {
				return ResponseEntity.ok(raffleTicket);
			} else {
				return ResponseEntity.ok("Something goes wrong!");
			}
		}

		return ResponseEntity.ok("No more participant left to Win!");
	}

}
