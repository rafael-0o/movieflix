package br.com.movieflix.Exeption;

public class Username0rPasswordInvalidExeption extends RuntimeException{
    public Username0rPasswordInvalidExeption (String mensage){
        super(mensage);
    }
}
