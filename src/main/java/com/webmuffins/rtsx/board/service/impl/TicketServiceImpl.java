package com.webmuffins.rtsx.board.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Ticket;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.TicketRepository;
import com.webmuffins.rtsx.board.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TicketRepository ticketRepository;
    private final Mapper<Ticket, TicketRequestDto, TicketResponseDto> ticketMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, Mapper<Ticket, TicketRequestDto, TicketResponseDto> ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketResponseDto createTicket(TicketRequestDto dto) {
        Ticket ticket = ticketMapper.mapDtoToEntity(dto);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.mapEntityToDto(savedTicket);
    }

    @Override
    public Ticket findEntityById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Can not find ticket with such id: '%s'", id)));
    }

    @Override
    public TicketResponseDto findById(Long id) {
        Ticket ticket = findEntityById(id);
        return ticketMapper.mapEntityToDto(ticket);
    }

    @Override
    public List<TicketResponseDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.mapEntityListToDtoList(tickets);
    }

    @Override
    public TicketResponseDto updateTicket(Long id, TicketRequestDto dto) {
        Ticket ticketToUpdate = ticketMapper.mapDtoToEntity(dto);
        ticketToUpdate.setId(id);
        Ticket updatedTicket = ticketRepository.save(ticketToUpdate);
        return ticketMapper.mapEntityToDto(updatedTicket);
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);
        LOG.info("Deleted ticket with id : {}", id);
    }
}
