/**
 * 
 */
package com.jpmc.fun.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jpmc.fun.jdbc.RaffleJDBCRepository;
import com.jpmc.fun.model.RaffleTicket;

/**
 * @author Rishabh
 *
 */
@Controller
public class MainController {

	@Autowired
	RaffleJDBCRepository raffleDao;

	// Either you don't pass anything or pass welcome from the url, it hit below API
	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {

		model.addAttribute("title", "Raffle Ticket Portal");

		RaffleTicket raft = new RaffleTicket();
		model.addAttribute("raft", raft);

		return "welcomePage";
	}
	

	@PostMapping("/postSid")
	public String saveOrUpdate(@Valid @ModelAttribute RaffleTicket raft, BindingResult bindingResult) throws Exception {

		int count = raffleDao.insert(raft);
		System.out.println(count + " Record Inserted:" + raft.getSid());

		return "redirect:/welcome";
	}

}
