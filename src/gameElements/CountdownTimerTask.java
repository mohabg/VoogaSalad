package gameElements;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimerTask extends TimerTask {
   private int myCount;
   //private Runnable doWhenDone;

   public CountdownTimerTask(int count/*, Runnable doWhenDone*/) {
      myCount = count;
      //this.doWhenDone = doWhenDone;
   }

   /**
    * Starts the time
	*/
   @Override
   public void run() {
      myCount--;
      int min = myCount/60;
      int sec = myCount%60;
      System.out.println("Time Remaining: " + min + ":" + sec);
      if (myCount == 0) {
         cancel();
         //doWhenDone.run();
      }
   }

}