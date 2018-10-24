package pl.parser.nbp.Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import pl.parser.nbp.userConsole.Query;

public class FileNamesExtractor {

  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
  String generalPath = "http://www.nbp.pl/kursy/xml/";
  String dirPath = "http://www.nbp.pl/kursy/xml/dir";

  public List<String> getCorrectXmlFilesPathsFromDirs(Query query) throws IOException {
    List<String> fileNames = new ArrayList<>();
    for (String path : getCorrectDirFilePaths(query.getStartDate(), query.getEndDate())) {
      URL url = new URL(path);
      URLConnection connection = url.openConnection();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(
              connection.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        if (this.checkIfFilenameIsRelevant(inputLine, query.getStartDate(), query.getEndDate())) {
          fileNames.add(generalPath + inputLine + ".xml");
        }
      }
      in.close();
    }
    return fileNames;
  }

  public List<String> getCorrectDirFilePaths(LocalDate startDate, LocalDate endDate) {
    int yearStart = startDate.getYear();
    int yearEnd = endDate.getYear();
    List<String> dirPaths = new ArrayList<>();
    for (int year = yearStart; year <= yearEnd; year++) {
      if (year == Year.now().getValue()) {
        dirPaths.add(dirPath + ".txt");
      } else {
        dirPaths.add(dirPath + year + ".txt");
      }
    }
    return dirPaths;
  }

  public boolean checkIfFilenameIsRelevant(String filename, LocalDate startDate, LocalDate endDate) {
    if (!filename.startsWith("c")) {
      return false;
    }
    String date = filename.substring(5, 11);
    LocalDate fileDate = LocalDate.parse(date, formatter);
    if ((fileDate.isAfter(startDate) && fileDate.isBefore(endDate)) || fileDate.equals(startDate) || fileDate.equals(endDate)) {
      return true;
    }
    return false;
  }
}