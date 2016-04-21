package gameElements;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.TimerTask;

/**
 * Has the countdown timer for the game
 */


public class CountdownTimerTask extends TimerTask {
   private IntegerProperty myCount;
   //private Runnable doWhenDone;

   public CountdownTimerTask(int count/*, Runnable doWhenDone*/) {
       myCount = new SimpleIntegerProperty();
      myCount.set(count);
      //this.doWhenDone = doWhenDone;
   }

   /**
    * Starts the time
	*/
   @Override
   public void run() {
      myCount.add(-1);
      int min = myCount.getValue()/60;
      int sec = myCount.getValue()%60;
      if (myCount.getValue() == 0) {
         cancel();
         //doWhenDone.run();
      }
   }

}