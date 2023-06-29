
import java.util.Iterator;

import com.PositionalList.PositionalList;


public class Proposition {

    public PositionalList<String> info;

    public boolean truthValue;

    public Proposition (String info){

        this.info = new PositionalList<>();

        this.info.addFirst(info);

        truthValue = true;

    }

    public Proposition(){

        this.info = new PositionalList<>();

        truthValue = true;

    }

    public void show(){

        Iterator<String> it = info.iterator();

        System.out.print("[ ");

        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }

        System.out.println("]");

    }





    public static Proposition OR(Proposition p1, Proposition p2){

        PositionalList<String> list = new PositionalList<>();

        Iterator<String> it1 = p1.info.iterator();
        Iterator<String> it2 = p2.info.iterator();

        Proposition proposition = new Proposition();

        list.addFirst("(");

        while(it1.hasNext()){

            list.addLast(it1.next());

        }

        list.addLast("|");

        while(it2.hasNext()){

            list.addLast(it2.next());

        }

        list.addLast(")");

        proposition.info = list;

        proposition.truthValue = p1.truthValue || p2.truthValue;

        return proposition;
    }

    public static Proposition AND(Proposition p1, Proposition p2){

        PositionalList<String> list = new PositionalList<>();

        Iterator<String> it1 = p1.info.iterator();
        Iterator<String> it2 = p2.info.iterator();

        Proposition proposition = new Proposition();

        list.addFirst("(");

        while(it1.hasNext()){

            list.addLast(it1.next());

        }

        list.addLast("^");

        while(it2.hasNext()){

            list.addLast(it2.next());

        }

        list.addLast(")");

        proposition.info = list;

        proposition.truthValue = p1.truthValue && p2.truthValue;

        return proposition;

        
    }

    public static Proposition NOT(Proposition p1){

        PositionalList<String> list = new PositionalList<>();
        Iterator<String> it1 = p1.info.iterator();

        Proposition proposition = new Proposition();

        list.addFirst("~");

        list.addLast("(");

        while(it1.hasNext()){

            list.addLast(it1.next());

        }

        list.addLast(")");

        proposition.info = list;

        proposition.truthValue = !p1.truthValue;

        return proposition; 
    }

    public static Proposition IMPLY(Proposition p1, Proposition p2){

        PositionalList<String> list = new PositionalList<>();

        Iterator<String> it1 = p1.info.iterator();
        Iterator<String> it2 = p2.info.iterator();

        Proposition proposition = new Proposition();

        list.addFirst("(");

        while(it1.hasNext()){

            list.addLast(it1.next());

        }

        list.addLast("-->");

        while(it2.hasNext()){

            list.addLast(it2.next());

        }

        list.addLast(")");

        proposition.info = list;

        if(p1.truthValue == true && p2.truthValue == false) proposition.truthValue = false;
        else proposition.truthValue = true;

        return proposition;
        
    }

    
    
}
