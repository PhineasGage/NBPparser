package pl.parser.nbp;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import pl.parser.nbp.helpers.FileNamesExtractor;
import pl.parser.nbp.saxParser.XMLParser;
import pl.parser.nbp.userConsole.UserConsole;

public class MainClass {


  public static void main(String[] args) throws IOException {
    XMLParser xmlParser= new XMLParser();
    FileNamesExtractor fileNamesExtractor = new FileNamesExtractor();
    UserConsole userConsole = new UserConsole();
    CurrencyExchangeRateCalculator currencyExchangeRateCalculator = new CurrencyExchangeRateCalculator( fileNamesExtractor, xmlParser, userConsole);
    try {
      currencyExchangeRateCalculator.getMeanAndStandardDeviationFromInput();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }
  }
}



