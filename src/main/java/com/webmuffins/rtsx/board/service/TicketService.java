package com.webmuffins.rtsx.board.service;

import java.util.List;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Ticket;

public interface TicketService {

    TicketResponseDto createTicket(TicketRequestDto dto);

    Ticket findEntityById(UUID id);

    TicketResponseDto findById(UUID id);

    List<TicketResponseDto> getAllTickets();

    TicketResponseDto updateTicket(UUID id, TicketRequestDto dto);

    void deleteTicketById(UUID id);
}
