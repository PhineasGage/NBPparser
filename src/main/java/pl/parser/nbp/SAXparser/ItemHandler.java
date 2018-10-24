package pl.parser.nbp.SAXparser;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemHandler extends DefaultHandler {

  private boolean isCurrencyCode = false;
  private boolean isBuyRate = false;
  private boolean isSaleRate = false;
  private Map<String, double[]> currencyBuyRateMap = new HashMap<>();
  private String code;
  private double[] buySaleRate = new double[2];

  @Override
  public void startElement(
      String uri,
      String localName,
      String qName,
      Attributes attributes)
      throws SAXException {
    if (qName.equals("kod_waluty")) {
      isCurrencyCode = true;
    } else if (qName.equals("kurs_kupna")) {
      isBuyRate = true;
    } else if (qName.equals("kurs_sprzedazy")) {
      isSaleRate = true;
    }
  }

  @Override
  public void characters(char ch[], int start, int length) throws SAXException {
    if (isCurrencyCode) {
      code = new String(ch, start, length);
      isCurrencyCode = false;
    } else if (isBuyRate) {
      buySaleRate[0] = Double.parseDouble(new String(ch, start, length).replace(",", "."));
      isBuyRate = false;
    } else if (isSaleRate) {
      buySaleRate[1] = Double.parseDouble(new String(ch, start, length).replace(",", "."));
      isSaleRate = false;
      currencyBuyRateMap.put(code, Arrays.copyOf(buySaleRate, 2));
      code = "";
    }
  }

  public Map<String, double[]> getCurrencyBuyRateMap() {
    return currencyBuyRateMap;
  }
}