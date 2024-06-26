package com.space.netspace.controllers;

import com.space.netspace.dto.QuoteDTO;
import com.space.netspace.models.Quote;
import com.space.netspace.services.QuoteService;
import com.space.netspace.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService quoteService;
    private final UserService userService;

    @Autowired
    public QuoteController(QuoteService quoteService, UserService userService) {
        this.quoteService = quoteService;
        this.userService = userService;
    }


    @GetMapping()
    public List<QuoteDTO> getAll() {
        return convertToListOfQuoteDTO(quoteService.findAll());
    }

    @GetMapping("/best")
    public List<QuoteDTO> getBest() {
        return convertToListOfQuoteDTO(quoteService.bestOrWorst(true));
    }

    @GetMapping("/worst")
    public List<QuoteDTO> getWorst() {
        return convertToListOfQuoteDTO(quoteService.bestOrWorst(false));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody QuoteDTO quoteDTO) {
        quoteService.save(convertToQuote(quoteDTO, new Quote()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody QuoteDTO quoteDTO, @PathVariable("id") int id) {
        quoteService.update(convertToQuote(quoteDTO, quoteService.findOne(id)));
        System.out.println(id);
        System.out.println(quoteService.findOne(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id) {
        quoteService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private List<QuoteDTO> convertToListOfQuoteDTO(List<Quote> convertList) {
        return convertList.stream()
                .map(this::convrtToQuoteDTO)
                .collect(Collectors.toList());
    }

    private Quote convertToQuote(QuoteDTO quoteDTO, Quote quote) {
        quote.setContent(quoteDTO.getContent());
        quote.setOwner(userService.findOne(quoteDTO.getOwnerId()));
        return quote;
    }

    private QuoteDTO convrtToQuoteDTO(Quote quote) {
        QuoteDTO quoteDTO = new QuoteDTO();
        quoteDTO.setContent(quote.getContent());
        quoteDTO.setOwnerId(quote.getOwner().getId());
        quoteDTO.setUpdatedAt(quote.getUpdatedAt());
        quoteDTO.setCreatedAt(quote.getCreatedAt());
        quoteDTO.setVotes(quote.getVotes());
        return quoteDTO;
    }
}
