package pl.parser.nbp.SAXparser;

import java.io.IOException;
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
    SAXParser saxParser = saxParserFactory.newSAXParser();
    saxParser.parse( new InputSource(new URL(path).openStream()) ,handler);
    return handler.getCurrencyBuyRateMap().get(currencyCode);
  }
}