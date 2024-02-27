package base;

import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.Scanner;

public class tictactoe {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        tictactoe obj=new tictactoe();
        System.out.println("enter 1 to play PVP \nenter 2 to play vs AI ");
        int a=sc.nextInt();
        sc.nextLine();
        System.out.println(obj.ttt(3,a));
    }
    public String ttt(int a,int b){
        Random obj=new Random();
        String player1;
        String player2;
        if (b==1){
            System.out.println("player 1 Name");
             player1=sc.nextLine();
            System.out.println("player 2 Name");
             player2=sc.nextLine();
        }
        else {
            System.out.println("player 1 Name");
            player1=sc.nextLine();
            player2="AI";
        }
        int[][] tarray=new int[a][a];
        arp(tarray);
        String vi;
        int i= 0;
        int c=obj.nextInt(2);
        i=c;
        int fin=0;
        while(true){
            if(i-c==a*a)return "DRAW";
            i++;
            if ((i % 2 != 0)) {
                System.out.println(player1);
                int loc=sc.nextInt();
                int uni=loc%10-1;
                loc=loc/10-1;
                if (!logic(loc,uni,tarray)){
                    System.out.println("enter a valid position");
                    i--;
                    continue;
                }
                tarray[loc][uni]=1;
                fin=che(tarray,loc,uni,a);
                if (fin!=0){
                    arp(tarray);
                    return player1+" won the game";
                }
            }
            else {
                System.out.println(player2);
                int loc=0;
                if (b==1)loc=sc.nextInt();
                else loc=ai(tarray,-1);
                int uni=loc%10-1;
                loc=loc/10-1;
                if (!logic(loc,uni,tarray)){
                    System.out.println("enter a valid position");
                    i--;
                    continue;
                }
                tarray[loc][uni]=-1;
                fin=che(tarray,loc,uni,a);
                if (fin!=0){
                    arp(tarray);
                    return player2+" won the game";
                }
            }
            arp(tarray);
        }
    }
    public int che(int[][] a,int ... b){
        int loc=a[b[0]][b[1]];
        int c=b[2];
        for (int i=0;i<c;i++){
            if (a[b[0]][i]!=loc)break;
            if (i==c-1)return loc;
        }
        for (int i=0;i<c;i++){
            if (a[i][b[1]]!=loc)break;
            if (i==c-1)return loc;
        }
        if (b[0]==b[1]){
            for (int i=0;i<c;i++){
                if (a[i][i]!=loc)break;
                if (i==c-1)return loc;
            }
        }
        if (b[0]+b[1]==c-1){
            for(int i=0;i<c;i++){
                if (a[i][c-i-1]!=loc)break;
                if (i==2)return loc;
            }
        }
        return 0;
    }
    public boolean logic(int a,int b,int[][] c){
        if(a>=c.length||b>=c.length)return false;
        if(c[a][b]==0)return true;
        return false;
    }
    public int ai(int[][] a,int b){
        if (a[1][1]==0)return 22;
        int size=a.length;
        int opp=0;
        int f=0;
        int fav=0;
        int op=0;
        int freeposi=0;
        for (int i=0;i<size;i++){
            if (a[i][i]==b)fav++;
            else if (a[i][i]==0){
                f++;
                freeposi=(i+1)*10+i+1;
            }
            else op++;
        }
        if(fav==size-1&&f==1){
            return freeposi;
        }
        else if (op==size-1&&f==1) {
            opp=freeposi;
        }
        f=fav=op=0;
        for (int i=0;i<size;i++){
            if(a[size-1-i][i]==b)fav++;
            else if(a[size-1-i][i]==0){
                f++;
                freeposi=(size-i)*10+i+1;
            }
            else op++;
        }
        if(fav==size-1&&f==1){
            return freeposi;
        }
        else if (op==size-1&&f==1) {
            opp=freeposi;
        }
        for(int i=0;i<size;i++){
            f=0;
            fav=0;
            op=0;
            for(int j=0;j<size;j++){
                if(a[i][j] ==0){
                    f++;
                    freeposi=(i+1)*10+j+1;
                }
                else if (a[i][j]==b){
                    fav++;
                }
                else {
                    op++;
                }
            }
            if (fav==2&&f==1){
                return freeposi;
            }
            else if(op==2&&f==1&&opp==0){
                opp=freeposi;
            }
            f=fav=op=0;
            for(int j=0;j<size;j++){
                if(a[j][i] ==0){
                    f++;
                    freeposi=(j+1)*10+i+1;
                }
                else if (a[j][i]==b){
                    fav++;
                }
                else {
                    op++;
                }
            }
            if (fav==2&&f==1){
                return freeposi;
            }
            else if (f==1&&op==2){
                opp=freeposi;
            }
        }
        if (opp==0){
            int[] edge=new int[]{0,0,size-1,size-1,0};
            for (int i=0;i<4;i++){
                int q=edge[i];
                int w=edge[i+1];
                if (a[q][w]==0)return (q+1)*10+w+1;
            }
            return freeposi;
        }
        return opp;
    }
    public void arp(int[][] a){
        for (int z=0;z<a.length;z++){
            System.out.print("+---");
        }
        System.out.println("+");
        for(int o=0;o<a.length;o++){
            for(int j=0;j<a.length;j++){
                if(a[o][j]==0) System.out.print("| - ");
                else if ((a[o][j] == -1)) {
                    System.out.print("| O ");
                } else {
                    System.out.print("| X ");
                }
            }
            System.out.println("|");
            for (int z=0;z<a.length;z++){
                System.out.print("+---");
            }
            System.out.println("+");
        }
    }
}
