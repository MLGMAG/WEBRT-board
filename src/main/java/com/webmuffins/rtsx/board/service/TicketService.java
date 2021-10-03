package com.webmuffins.rtsx.board.service;

import java.util.List;

import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Ticket;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto dto);

    Ticket findEntityById(Long id);

    TicketResponseDto findById(Long id);

    List<TicketResponseDto> getAllTickets();

    TicketResponseDto updateTicket(Long id, TicketRequestDto dto);

    void deleteTicketById(Long id);
}
