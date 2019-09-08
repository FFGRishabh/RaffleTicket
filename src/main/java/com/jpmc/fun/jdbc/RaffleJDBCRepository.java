/**
 * 
 */
package com.jpmc.fun.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jpmc.fun.model.RaffleTicket;

/**
 * @author Rishabh
 *
 */
@Repository
public class RaffleJDBCRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public int insert(@Valid RaffleTicket raft) {
		return jdbcTemplate.update(conn -> buildInsertRaffleTicket(conn, raft));
	}

	private PreparedStatement buildInsertRaffleTicket(Connection conn, @Valid RaffleTicket raft) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO RAFFLE_TICKET (RAFFLE_TICKET_ID, RAFFLE_KEY, SID) VALUES (NEXTVAL('RAFFLE_SEQ'), ? , ?)");
		
		ps.setString(1, raft.getRaffleKey().toUpperCase());
		ps.setString(2, raft.getSid().toUpperCase());

		return ps;
	}

	public List<RaffleTicket> getAllRaffleTicket() {
		List<RaffleTicket> rtList = jdbcTemplate.query("SELECT * FROM RAFFLE_TICKET", new BeanPropertyRowMapper<RaffleTicket>(RaffleTicket.class));
		return rtList;
	}

	public List<RaffleTicket> getAllRaffleTicketExcludingWinners() {
		List<RaffleTicket> rtList = jdbcTemplate.query("SELECT * FROM RAFFLE_TICKET where winner = 'FALSE'", new BeanPropertyRowMapper<RaffleTicket>(RaffleTicket.class));
		return rtList;
	}

	public int updateWinner(String sid) {
		return jdbcTemplate.update("UPDATE RAFFLE_TICKET SET WINNER = TRUE WHERE SID = '" + sid + "'");
	}

}
