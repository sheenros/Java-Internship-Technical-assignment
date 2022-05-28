package ex1;

import java.util.Scanner;

public class Candles {

 public static int birthdayCakeCandles(int[] candles){

        int max = 0;
        int count = 0;

        for (int i = 0; i < candles.length; ++i) {
//            max = Math.max(max, candles[i]);
            candles[i]= max;
            if(max>candles[i]) max=candles[i];
        }

        for (int i = 0; i < candles.length; ++i) {
            if (candles[i] == max) count++;
        }


        return count;

    }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] candles=new int[n];

        for(int i=0;i<n;i++)
        {
            candles[i] = s.nextInt();
        }
        System.out.println(birthdayCakeCandles(candles));
    }
}
