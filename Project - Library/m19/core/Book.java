package m19.core;

import java.io.Serializable;


public class Book extends Work implements Serializable {

    private String _author;
    private String _isbn;

    public Book (int id,String title,String author,int price, Category category,String isbn,int numberOfCopies){
        
        super(id,price,category,title,numberOfCopies);
        _author = author;
        _isbn = isbn;
        
      
    } 

    @Override
    public String getDescription(){
        return super.getWorkId() + " - " + super.getNumberOfCopies() + " de " + super.getTotalNumberOfCopies() + " - Livro - " + super.getWorkTitle() + " - " + super.getWorkPrice() + " - " + super.getWorkCategory().toString() + " - " + _author + " - " + _isbn;
    }

    @Override
    public String getOwner(){
        return _author;
    }
}
