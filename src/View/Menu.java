package view;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {
    protected String title;
    protected ArrayList<T> mChon;
    
    public Menu(){}
    
    public Menu(String td, String[] mc){
        title=td;
        mChon= new ArrayList<>();
        for(String s:mc) mChon.add((T) s);
    }
//-------------------------------------------
    public void display(){
        System.out.println(title);
        System.out.println("--------------------------------");
        for(int i=0; i<mChon.size();i++)
            System.out.println((i+1)+"."+mChon.get(i));
        System.out.println("--------------------------------");
    }
//-------------------------------------------
    public int getSelected(){
        display();
        Scanner sc= new Scanner(System.in);
        System.out.print("[+]Enter your option: ");
        return sc.nextInt();
    }
//-------------------------------------------
    public abstract void execute(int n);
//-------------------------------------------
    public void run(){
        while(true){
            int n=getSelected();
            if(n<=mChon.size())execute(n);
            else break;
        }
    }
//-------------------------------------------    
}
