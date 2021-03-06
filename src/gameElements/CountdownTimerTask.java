package gameElements;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Has the countdown timer for the game
 */


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
      if (myCount == 0) {
         cancel();
         //doWhenDone.run();
      }
   }

}