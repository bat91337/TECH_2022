package Controllers;

import Entitites.Colors;
import Entitites.Kotiki;
import Entitites.Owner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class ControllerKotiki implements Conroller<Kotiki, Long> {
    private static Scanner in;
    private List<Owner> owners;
    public ControllerKotiki() {
        in = new Scanner(System.in);
        owners = new ArrayList<Owner>();

    }

    @Override
    public void create() {
        Owner owner1 = null;
        Colors color = null;
        Long key = Long.valueOf(1);
        System.out.println("write your kotik name");
        String name = in.nextLine();
        System.out.println("write your kotik date birthday");
        LocalDateTime date = LocalDateTime.parse(in.nextLine());
        System.out.println("write your kotik breed");
        String breed = in.nextLine();
        System.out.println("write your name");
        String nameOwner = in.nextLine();

        for(Owner owner : owners) {
            if (owner.getName().equals(nameOwner))
            {
                owner1 = owner;
            }
        }
        System.out.println("write your kotik color");
        System.out.println("1 - white");
        System.out.println("2 - black");
        String str = in.nextLine();
        int number = Integer.parseInt(str);
        switch (number){
            case 1:
                color = Colors.WHITE;
                break;
            case 2:
                color = Colors.BLACK;
                break;
            default:
                System.out.println("wrong number entered");
                break;
        }
        Kotiki kotiki = new Kotiki(key, name, date, breed, owner1, color);
        owner1.getKotiks().add(kotiki);



    }

    @Override
    public Kotiki read() {
        System.out.println("write your id kotik");
        Long idkotik = Long.parseLong(in.nextLine());
        System.out.println("write your id");
        Long idowner = Long.parseLong(in.nextLine());
        for(Owner owner : owners)
        {
            if(owner.getId().equals(idowner))
            {
                List<Kotiki> kotiki = owner.getKotiks();
                for(Kotiki kotik : kotiki)
                {
                    if(kotik.getId().equals(idkotik))
                    {
                        return kotik;
                    }
                }
            }
        }
        return null;
    }
}
