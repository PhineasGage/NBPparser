package pl.parser.nbp.saxParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLParser {

  private ItemHandler handler = new ItemHandler();

  public double[] SAXparseXmlDocumentForBuyRate(String path, String currencyCode) throws SAXException, IOException, ParserConfigurationException {
    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    try (InputStream input = new URL(path).openStream()) {
      SAXParser saxParser = saxParserFactory.newSAXParser();
      saxParser.parse(input, handler);
      return handler.getCurrencyBuyRateMap().get(currencyCode);
    }
  }
}