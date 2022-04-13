package Controllers;

import Entitites.Owner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerOwner implements Controller{
    private static Scanner in;
    private List<Owner> owners;
    public ControllerOwner() {
        in = new Scanner(System.in);
        owners = new ArrayList<Owner>();
    }
    @Override
    public void create() {
        Long key = Long.valueOf(1);
        System.out.println("write your name");
        String name = in.nextLine();
        System.out.println("write your date birthday");
        LocalDateTime date = LocalDateTime.parse(in.nextLine());
        Owner owner = new Owner(key, name, date);
    }
    @Override
    public Object read() {;
        System.out.println("write your id");
        Long idowner = Long.parseLong(in.nextLine());
        for(Owner owner : owners)
        {
            if(owner.getId().equals(idowner))
            {
                return owner;
            }
        }
        return null;
    }
}
