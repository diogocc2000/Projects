package m19.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;

import m19.core.exception.BadEntrySpecificationException;

public class Parser {

  private Library _library;

  Parser(Library lib) {
    _library = lib;
  }

  void parseFile(String filename) throws IOException, BadEntrySpecificationException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  private void parseLine(String line) throws BadEntrySpecificationException {
    String[] components = line.split(":");

    switch(components[0]) {
      case "DVD":
        parseDVD(components, line);
        break;

      case "BOOK":
        parseBook(components, line);
        break;
      case "USER":
        parseUser(components, line);
        break;
        
      default:
        throw new BadEntrySpecificationException("Invalid type " + components[0] +
                                                " in line " + line);
    }
  }

  private void parseDVD(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 7)
      throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);
    Dvd dvd = new Dvd(_library.getNextWorkid(),components[1], components[2], Integer.parseInt(components[3]),
                      Category.valueOf(components[4]), components[5],
                      Integer.parseInt(components[6]));
    
    // add dvd to _library
    _library.addDvd(dvd);
  }

  private void parseBook(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 7)
      throw new BadEntrySpecificationException("Wrong number of fields (6) in " + line);

    Book book = new Book(_library.getNextWorkid(),components[1], components[2], Integer.parseInt(components[3]),
                         Category.valueOf(components[4]),components[5],
                         Integer.parseInt(components[6]));
    
    // add book to _library
    _library.addBook(book);
  }

  private void parseUser(String[] components, String line) throws BadEntrySpecificationException {
    if (components.length != 3)
      throw new BadEntrySpecificationException("Wrong number of fields (2) in " + line);
    User user = new User(_library.getNextUserid(),components[1], components[2]);
    
    // add user to _library
    _library.addUser(user);
    // Pode ser necessário ter um try-catch adicional neste método
  }

 }