package util;
public class Chec {
    public static  void  expect(String label,boolean conditin){
        if(conditin){
            System.out.println("pass   :" +label);
        }
        else {
            System.out.println("fail   :" +label);
        }
    }
}
