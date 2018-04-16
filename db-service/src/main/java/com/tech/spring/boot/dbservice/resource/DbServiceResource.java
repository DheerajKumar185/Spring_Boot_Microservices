package com.tech.spring.boot.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.spring.boot.dbservice.model.Quote;
import com.tech.spring.boot.dbservice.model.Quotes;
import com.tech.spring.boot.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("{username}")
	public List<String> getQuotesByUserName(@PathVariable("username") final String username) {
		return quotesRepository.findByUserName(username).stream().map(quote -> quote.getQuote())
				.collect(Collectors.toList());
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote))
				.forEach(quote -> quotesRepository.save(quote));

		return getQuotesByUserName(quotes.getUserName());
	}

	@DeleteMapping("delete/{userName}")
	public List<String> deleteQuote(@PathVariable final String userName) {
		List<Quote> quotes = quotesRepository.findByUserName(userName);
		quotesRepository.delete(quotes);
		return getQuotesByUserName(userName);
	}
}
