package ru.mentee.power.finaltask;

import ru.mentee.power.finaltask.controller.QuoteController;
import ru.mentee.power.finaltask.model.InMemoryQuoteRepository;
import ru.mentee.power.finaltask.model.JsonQuoteDataSource;
import ru.mentee.power.finaltask.model.QuoteDataSource;
import ru.mentee.power.finaltask.model.QuoteRepository;
import ru.mentee.power.finaltask.model.QuoteService;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

public class QuoteApp {

  public static void main(String[] args) {
    QuoteRepository repository = new InMemoryQuoteRepository();
    QuoteDataSource dataSource = new JsonQuoteDataSource("quotes.json");
    QuoteService service = new QuoteService(repository, dataSource);
    ConsoleQuoteView view = new ConsoleQuoteView();
    QuoteController controller = new QuoteController(service, view);

    controller.run();
  }
}
