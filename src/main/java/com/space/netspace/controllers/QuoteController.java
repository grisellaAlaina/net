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
    private final ModelMapper modelMapper;

    @Autowired
    public QuoteController(QuoteService quoteService, UserService userService, ModelMapper modelMapper) {
        this.quoteService = quoteService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

//    @GetMapping()
//    public List<Quote> getAll() {
//        return quoteService.findAll();
//    }

    @GetMapping()
    public List<QuoteDTO> getAll() {
        return quoteService.findAll().stream()
                .map(this::convrtToQuoteDTO)
                .collect(Collectors.toList());
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

    private Quote convertToQuote(QuoteDTO quoteDTO, Quote quote) {
        quote.setContent(quoteDTO.getContent());
        System.out.println(quoteDTO.getContent());
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
