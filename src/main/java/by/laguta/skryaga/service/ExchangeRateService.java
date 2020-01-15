package by.laguta.skryaga.service;

import by.laguta.skryaga.model.ExchangeRate;
import by.laguta.skryaga.service.exception.ExchangeRateUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExchangeRateService {

    @Value("${ecopress.url}")
    private String ecopressUrl;

    public Optional<ExchangeRate> getLowestRate() throws ExchangeRateUpdateException {
        Set<ExchangeRate> exchangeRates = getExchangeRates();
        return exchangeRates.isEmpty() ? Optional.empty() : Optional.of(Collections.min(exchangeRates));
    }

    private Set<ExchangeRate> getExchangeRates() throws ExchangeRateUpdateException {
        Set<ExchangeRate> exchangeRateSet;
        try {
            Document doc = getDocument();
            Elements table = doc.select(".nal");
            Elements rows = table.select("tr:not(.nnal)");
            List<Element> currencyRows = rows.subList(3, rows.size());
            exchangeRateSet = currencyRows.stream()
                    .map(row -> parseExchangeRate(row, LocalDateTime.now()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("Could not load page ", e);
            throw new ExchangeRateUpdateException("Error loading exchange rates from server", e);
        }
        return exchangeRateSet;
    }

    private Document getDocument() throws IOException {
        return Jsoup.connect(ecopressUrl).timeout(3000).get();
    }

    private ExchangeRate parseExchangeRate(Element row, LocalDateTime date) {
        try {
            Element name = row.select(".name").first();
            if (name != null && name.children().isEmpty()) {
                String bankName = name.text();
                Element address = row.select(".addr")
                        .first();
                String bankAddress = "";
                if (address != null) {
                    bankAddress = address.text();
                }

                Element usdBuyElement = row.select(".kursy").first();
                Double buyCourse = getCourse(usdBuyElement);
                Element usdSellElement = usdBuyElement.nextElementSibling();
                Double sellCourse = getCourse(usdSellElement);
                return new ExchangeRate(
                        date,
                        "USD",
                        bankName,
                        bankAddress,
                        buyCourse,
                        sellCourse);
            }
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Double getCourse(Element courseEleemnt) {
        Elements usdBuyChildNodes = courseEleemnt.children();
        if (usdBuyChildNodes.isEmpty()) {
            return Double.valueOf(courseEleemnt.text());
        } else {
            for (Element element : usdBuyChildNodes) {
                if (element.tagName().equals("strong")) {
                    return Double.valueOf(element.text());
                }
            }
        }
        return null;
    }
}
