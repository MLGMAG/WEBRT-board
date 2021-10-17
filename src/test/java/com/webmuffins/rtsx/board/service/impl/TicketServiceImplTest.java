package com.webmuffins.rtsx.board.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Ticket;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";

    private Ticket ticket;
    private TicketResponseDto ticketResponseDto;
    private TicketRequestDto ticketRequestDto;
    private List<TicketRequestDto> ticketRequestDtoList;
    private List<Ticket> ticketList;
    private List<TicketResponseDto> ticketResponseDtoList;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private Mapper<Ticket, TicketRequestDto, TicketResponseDto> ticketMapper;

    @InjectMocks
    private TicketServiceImpl testInstance;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticketRequestDto = new TicketRequestDto();
        ticketResponseDto = new TicketResponseDto();
        ticketRequestDtoList = Collections.singletonList(ticketRequestDto);
        ticketList = Collections.singletonList(ticket);
        ticketResponseDtoList = Collections.singletonList(ticketResponseDto);

        ticket.setTitle(DEFAULT_TITLE);
        ticket.setId(DEFAULT_ID);

        ticketResponseDto.setTitle(DEFAULT_TITLE);
        ticketResponseDto.setId(DEFAULT_ID);

        ticketRequestDto.setTitle(DEFAULT_TITLE);
    }

    @Test
    void shouldGetAllTickets() {
        when(ticketRepository.findAll()).thenReturn(ticketList);
        when(ticketMapper.mapEntityListToDtoList(ticketList)).thenReturn(ticketResponseDtoList);

        List<TicketResponseDto> actual = testInstance.getAllTickets();

        assertThat(actual).isNotNull()
                .isEqualTo(ticketResponseDtoList);
    }

    @Test
    void shouldFindTicketById() {
        when(ticketRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ticket));
        when(ticketMapper.mapEntityToDto(ticket)).thenReturn(ticketResponseDto);

        TicketResponseDto actual = testInstance.findById(DEFAULT_ID);

        assertThat(actual).isNotNull()
                .isEqualTo(ticketResponseDto);
    }

    @Test
    void shoudlFindEntityById() {
        when(ticketRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ticket));

        Ticket actual = testInstance.findEntityById(DEFAULT_ID);

        assertThat(actual).isNotNull()
                .isEqualTo(ticket);
    }

    @Test
    void shouldThrowExceptionWhenNotFindTicket() {
        when(ticketRepository.findById(DEFAULT_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> testInstance.findById(DEFAULT_ID));
    }

    @Test
    void shouldCreateTicket() {
        when(ticketMapper.mapDtoToEntity(ticketRequestDto)).thenReturn(ticket);
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        when(ticketMapper.mapEntityToDto(ticket)).thenReturn(ticketResponseDto);

        TicketResponseDto actual = testInstance.createTicket(ticketRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(ticketResponseDto);
    }

    @Test
    void shouldUpdateTicket() {
        TicketRequestDto updateRequestDto = new TicketRequestDto();
        updateRequestDto.setTitle("new title");
        Ticket updatedTicket = new Ticket();
        updatedTicket.setId(DEFAULT_ID);
        updatedTicket.setTitle("new title");
        ticketResponseDto.setTitle("new title");
        when(ticketMapper.mapDtoToEntity(updateRequestDto)).thenReturn(updatedTicket);
        when(ticketRepository.save(updatedTicket)).thenReturn(updatedTicket);
        when(ticketMapper.mapEntityToDto(updatedTicket)).thenReturn(ticketResponseDto);

        TicketResponseDto actual = testInstance.updateTicket(DEFAULT_ID, updateRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(ticketResponseDto);
    }

    @Test
    void shouldDeleteTicketById() {
        testInstance.deleteTicketById(DEFAULT_ID);

        verify(ticketRepository).deleteById(DEFAULT_ID);
    }
}
