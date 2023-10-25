package m19.core;

 enum Category {
    REFERENCE("Referência"),FICTION("Ficção"),SCITECH("Técnica e Científica");
    private String _category;
    
    private Category(String category){
        _category = category;
    }

    public String toString(){
        return _category;
       }
   

}