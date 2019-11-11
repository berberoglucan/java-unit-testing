package io.can.unittestingdemo._01.whatIsUnitTest;

public class FizzBuzz {


    /*
    * 1'den 100'e kadar sayıları alan ve eğer aldığı sayı
    * a) 3'e bölünüyorsa String olarak "Fizz"
    * b) 5'e bölünüyorsa String olarak "Buzz"
    * c) Hem 3'e hem de 5'e bölünüyorsa String olarak "FizzBuzz"
    * d) Hem 3'e hem de 5'e bölünmüyorsa sayının kendisini
    * e) 1'den küçük ve 100'den büyük bir sayı geldiğinde IllegalArgumentException fırlat
    * */

    public String stringFor(int number) {

        if (number < 0 || number > 100) {
            throw new IllegalArgumentException();
        }

        if (number % 15 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }

        return String.valueOf(number);
    }

}
