package pl.parser.nbp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.xml.sax.SAXException;
import pl.parser.nbp.helpers.FileNamesExtractor;
import pl.parser.nbp.saxParser.XMLParser;
import pl.parser.nbp.userConsole.Query;
import pl.parser.nbp.userConsole.UserConsole;

public class CurrencyExchangeRateCalculator {

  private FileNamesExtractor fileNamesExtractor;
  private XMLParser xmlParser;
  private UserConsole userConsole;

  public CurrencyExchangeRateCalculator(FileNamesExtractor fileNamesExtractor, XMLParser xmlParser, UserConsole userConsole) {
    this.fileNamesExtractor = fileNamesExtractor;
    this.xmlParser = xmlParser;
    this.userConsole = userConsole;
  }

  public void getMeanAndStandardDeviationFromInput() throws IOException, ParserConfigurationException, SAXException {
    Query query = userConsole.getQuery();
    List<String> xmlPaths = fileNamesExtractor.getCorrectXmlFilesPathsFromDirs(query);
    List<double[]> resultSet = new ArrayList<>();
    for (String xmlPath : xmlPaths) {
      resultSet.add(xmlParser.SAXparseXmlDocumentForBuyRate(xmlPath, query.getCurrencyCode()));
    }
    DescriptiveStatistics descriptiveStatisticsBuy = new DescriptiveStatistics();
    DescriptiveStatistics descriptiveStatisticsSale = new DescriptiveStatistics();
    for (double[] buySale : resultSet) {
      descriptiveStatisticsBuy.addValue(buySale[0]);
    }
    for (double[] buySale : resultSet) {
      descriptiveStatisticsSale.addValue(buySale[1]);
    }
    System.out.print("Arithmetic mean of buy rate = ");
    System.out.println(descriptiveStatisticsBuy.getMean());
    System.out.print("Standard Deviation of sale rate = ");
    System.out.println(descriptiveStatisticsSale.getStandardDeviation());
  }
}