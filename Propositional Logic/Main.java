public class Main {
    
    public static void main(String[] args) {
        
        Proposition p1= new Proposition("Oruç is 20 years old");
        Proposition p2= new Proposition("Oruç is Cmpe student");

        p2 = Proposition.NOT(p2);

        Proposition p3 = Proposition.OR(p1, p2);
        Proposition p4 = Proposition.AND(p3,p1);
        Proposition p5 = Proposition.IMPLY(p4,p3);
        p5.show();
        System.out.println(Proposition.IMPLY(p1, p2).truthValue);


        p4.show();
        p3.show();

    }

}
