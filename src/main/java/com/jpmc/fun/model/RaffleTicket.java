/**
 * 
 */
package com.jpmc.fun.model;

import lombok.Data;

/**
 * @author Rishabh
 *
 */
@Data
public class RaffleTicket {

	private Integer raffleTicketId;
	private String raffleKey;
	private String sid;
	private Boolean winner;

}
