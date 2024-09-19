
package hw2;

/**
 * Models a simplified baseball-like game called Fuzzball.
 * 
 * @author Myles Curtis
 */
public class FuzzballGame {
  /**
   * Tells which runner is on base
   */
  private boolean[] runnerOnBase;
  /**
   * Tells top of inning
   */
  private boolean topInning;
  /**
   * Tells which inning 
   */
  private int whichInning;
  /**
   * Tells Team 0 score
   */
  private int team0Score;
  /**
   * Tells Team 1 score
   */
  private int team1Score;
  /**
   * Tells ball count
   */
  private int ballCount;
  /**
   * Tells strike count
   */
  private int calledStrikes;
  /**
   * Tells amount of outs
   */
  private int currentOuts;
  /* 
   * Tells number of inning in a game
   */
  private int givenNumInnings;
  /**
   * Tells if game has ended
   */
  private boolean gameEnded;
 
  
  /**
   * Number of strikes causing a player to be out.
   */
  public static final int MAX_STRIKES = 2;

  /**
   * Number of balls causing a player to walk.
   */
  public static final int MAX_BALLS = 5;

  /**
   * Number of outs before the teams switch.
   */
  public static final int MAX_OUTS = 3;
  /**
   * 
   * @param givenNumInnings
 * @return 
   */
  public FuzzballGame(int givenNumInnings){
	  this.givenNumInnings = givenNumInnings;
	  this.runnerOnBase = new boolean[4];
	  this.topInning = true;
	  this.whichInning = 1;
	  this.team0Score = 0;
	  this.team1Score = 0;
	  this.ballCount = 0;
	  this.calledStrikes = 0;
	  this.currentOuts = 0;
	  
  }
  /** 
   * Switches to next batter in lineup 
   */
  private void switchBatter() {
  	this.calledStrikes = 0;
  	this.ballCount = 0;
  }
  /**
   * Increase score of said team
   */
  private void increaseScore() {
	  if (topInning) {
		  team0Score++;
	  }
	  else {
		  team1Score++;
	  }
	  if(currentOuts >= MAX_OUTS) {
		  switchInning();
	  }
	  }
  
  /**
   * "helper" method that switches the inning
   */
  private void switchInning() {
	 if(!gameEnded()) {
	 if (topInning) { 
	  topInning = false;
	  currentOuts = 0;
	  calledStrikes = 0;
	  ballCount = 0;
	  resetBases();
	 }
	 else {
		 topInning = true;
		 whichInning++;
		 currentOuts = 0;
		 calledStrikes = 0;
		 ballCount = 0;
		 resetBases();
	 }
	 }
  }

  /**
   * "helper" method that resets the bases
   */
  private void resetBases() {
	  runnerOnBase[1] = false;
	  runnerOnBase[2] = false;
	  runnerOnBase[3] = false;
  }
  /**
   *  Shift Runners for ball Method
   */
  private void shiftRunnersWalk() {
	  // No Runners
	  if (!runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
		  runnerOnBase[1] = true;
	  }
	  // Runner on 1st
	  else if(runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
				  runnerOnBase[2] = true;
			  }
	  // Runner on 2nd
	  else if (runnerOnBase[2] && !runnerOnBase[1] && !runnerOnBase[3]){
				  runnerOnBase[1] = true;
			  }
	  // Runner on 3rd
	  else if(runnerOnBase[3] && !runnerOnBase[2] && !runnerOnBase[1]) {
		  		runnerOnBase[1] = true;
	  }
	  // Runner on 1st and 3rd
	  else if (runnerOnBase[3] && runnerOnBase[1] && !runnerOnBase[2]) {
				  runnerOnBase[2] = true;
				  runnerOnBase[1] = true;
			  }
	  // Runner on 1st and 2nd
	  else if(runnerOnBase[1] && runnerOnBase[2] && !runnerOnBase[3]) {
				   runnerOnBase[3] = true;
			   }
	  // Bases Loaded
	  else if(runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
				   increaseScore();
			   }
			  
  }
	 
  /**
   * "helper" method that shifts runners. hitType 0 = single, 1 = double, 2 = triple, 3 = homerun
   * @param numBases
   */
  private void shiftRunners(int hitType) {
	  // Single
	if (hitType == 0) {
		// No Bases
		 if (!runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
			  runnerOnBase[1] = true;
		  }
		// Runner on 1st
		 else if(runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
					  runnerOnBase[2] = true;
				  }
		 // Runner on 1st and 2nd
		 else if(runnerOnBase[1] && runnerOnBase[2] && !runnerOnBase[3]) {
			 runnerOnBase[1] = true;
			 runnerOnBase[2] = true;
			 runnerOnBase[3] = true;
		 }
	  // Runner on 1st and 3rd
		 else if(runnerOnBase[1] && !runnerOnBase[2] && runnerOnBase[3]) {
			 runnerOnBase[1] = true;
			 runnerOnBase[2] = true;
			 runnerOnBase[3] = false;
			 increaseScore();
		 }
		 // Runner on 2nd and 3rd
		 else if(!runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
			 runnerOnBase[1] = true;
			 runnerOnBase[2] = false;
			 increaseScore();
		 }
		 // Runner on 2nd
	     else if (runnerOnBase[2] && !runnerOnBase[1] && !runnerOnBase[3]){
					  runnerOnBase[3] = true;
					  runnerOnBase[1] = true;
					  runnerOnBase[2] = false;
				  }
		 // Runner on 3rd
	     else if(!runnerOnBase[1] && !runnerOnBase[2] && runnerOnBase[3]) {
	    	 runnerOnBase[1] = true;
	    	 increaseScore();
	     }
		 // Bases Loaded 
		  else if (runnerOnBase[3] && runnerOnBase[2] && runnerOnBase[1]) {
 					 increaseScore();
				  }
  }
	// Double
	else if (hitType == 1) {
		// No Runners
		if(!runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
			runnerOnBase[2] = true;
		}
		// Runner on 1st
		else if(runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
				runnerOnBase[1] = false;
				runnerOnBase[2] = true;
				runnerOnBase[3] = true;
			}
		// Runner On 1st and 2nd
		else if(runnerOnBase[1] && runnerOnBase[2] && !runnerOnBase[3]) {
			runnerOnBase[3] = true;
			increaseScore();
		}
		// Runner on 2nd or 3rd
		else if(runnerOnBase[2] || runnerOnBase[3]) {
				runnerOnBase[2] = true;
				runnerOnBase[3] = false;
				increaseScore();
			}
		// Runner on 2nd and 3rd
		else if(!runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
			if (topInning) {
				team0Score += 2;
			}
			else {
				team1Score += 2;
			}
		}
		// Runner on 1st and 3rd
		else if (runnerOnBase[1] && !runnerOnBase[2] && runnerOnBase[3]) {
				runnerOnBase[2] = true;
				increaseScore();
		}
		// Bases Loaded
		else if(runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
				runnerOnBase[1] = false;
				runnerOnBase[2] = true;
				runnerOnBase[3] = true;
				if(topInning) {
					team0Score += 2;
				}
				else {
					team1Score += 2;
				}
			
			}
		}
	// Triple
	else if(hitType == 2) {
		// Empty Bases
		if(!runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
			runnerOnBase[3] = true;
		}
		// Runner on 1st
		else if(runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
				runnerOnBase[3] = true;
				runnerOnBase[1] = false;
				increaseScore();
			}
		// Runner on 1st and 2nd
		else if(runnerOnBase[1] && runnerOnBase[2] && !runnerOnBase[3]) {
			runnerOnBase[1] = false;
			runnerOnBase[2] = false;
			runnerOnBase[3] = true;
			if(topInning) {
				team0Score += 2;}
				else {
					team1Score +=2;
				}
		}
		// Runner on 1st and 3rd
			else if (runnerOnBase[1] && !runnerOnBase[2] && runnerOnBase[3]) {
				runnerOnBase[1] = false;
				if(topInning) {
					team0Score += 2;}
					else {
						team1Score +=2;
					}
			}
		// Runner on 2nd
			else if(runnerOnBase[2] && !runnerOnBase[1] && !runnerOnBase[3]) {
				runnerOnBase[3] = true;
				runnerOnBase[2] = false;
				increaseScore();
			}
		// Runner on 3rd
			else if (runnerOnBase[3] && !runnerOnBase[2] && !runnerOnBase[1]) {
				increaseScore();
			}
			// Bases Loaded
			else if(runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
				runnerOnBase[1] = false;
				runnerOnBase[2] = false;
				runnerOnBase[3] = true;
				if(topInning) {
					team0Score += 3;
				}
				else {
					team1Score += 3;
				}
			}
	}
	// Home Run
	else if(hitType == 3) {
		// Bases Loaded
		if(runnerOnBase[1] && runnerOnBase[2] && runnerOnBase[3]) {
			 if(topInning) {
				 team0Score += 4;
			 }
			 else {
				 team1Score += 4;
			 }
			 }
		// Two Bases
		else if((runnerOnBase[1] && runnerOnBase[2]) || (runnerOnBase[2] && runnerOnBase[3]) || (runnerOnBase[1] && runnerOnBase[3])) {
			 if(topInning) {
				 
				 team0Score += 3;
			 }
			 else {
				 team1Score += 3;
			 }
		 }
		// One Base
		else if(runnerOnBase[1] || runnerOnBase[2] || runnerOnBase[3]) {
			 if(topInning) {
				 team0Score += 2;}
				 else {
					 team1Score +=2;
				 }
			 }
		 // No Base
		else if(!runnerOnBase[1] && !runnerOnBase[2] && !runnerOnBase[3]) {
    		 increaseScore();}
    	  
    		 }
    	 

	  
	}
	/**
	 *  Method for balls thrown in simulation
	 */
  public void ball() {
	  if(!gameEnded()) {
	 ballCount++;
	 
	 // Essentially walks the batter and resets the count for next batter
	 if(ballCount >= MAX_BALLS) {
		 switchBatter();
		 shiftRunnersWalk();
		 
		
		 }
	 if(currentOuts >= MAX_OUTS) {
		 switchInning();
	 }
	  }
	 }
  /** 
   * Instigates a caught fly and produces and out
   */
  public void caughtFly() {
       currentOuts++;
	   switchBatter();
	  if(currentOuts == MAX_OUTS) {
		  switchInning();}
	  }
  
  /**
   * Ends the game 
   * @return
   */
  public boolean gameEnded() {
	  if (whichInning > givenNumInnings) {
		  gameEnded = true;
	  }
	  return gameEnded;
  }
  /** 
   * Insitgates ball count
   * @return
   */
  public int getBallCount() {
	  return ballCount;
  }
  // The methods below are provided for you and you should not modify them.
  // The compile errors will go away after you have written stubs for the
  // rest of the API methods.
  /**
   * Returns a three-character string representing the players on base, in the
   * order first, second, and third, where 'X' indicates a player is present and
   * 'o' indicates no player. For example, the string "oXX" means that there are
   * players on second and third but not on first.
   * 
   * @return three-character string showing players on base
   */
  public String getBases()
  {
    return (runnerOnBase(1) ? "X" : "o") + (runnerOnBase(2) ? "X" : "o")
        + (runnerOnBase(3) ? "X" : "o");
  }
  /**
   * Instigates called strikes
   * @return
   */
  
  public int getCalledStrikes() {
	  return calledStrikes;
  }
  /**
   * Instigates current outs
   * @return
   */
  public int getCurrentOuts() {
	  return currentOuts;
  }
  /**
   *  Instigates team 0 score
   * @return
   */
  public int getTeam0Score() {
	  return team0Score;
  }
  /**
   * Insitgates team 1 score
   * @return
   */
  public int getTeam1Score() {
	  return team1Score;
  }
  /**
   * Method for initiating hits with different distance marks stating whether single, double, triple, or home run
   * @param distance
   */
  public void hit(int distance) {
	  if(!gameEnded()) {
	  // Sets ball and strike count to 0 
	   switchBatter();
	 // this initiates a foul ball
	  if (distance < 15) {
		  currentOuts++;
	  }
	  // this else statement creates and initializes a home run
	  else if (distance >= 250){
	   shiftRunners(3);
	   }
	  else if (distance >= 200 && distance < 250) {
		  // This would be a triple
		  shiftRunners(2);
	  }
	  else if (distance >= 150 && distance < 200) {
		  // This would be a double
		  shiftRunners(1);
	  }

	     else if (distance >= 15 && distance < 150) {
			  // This would be a single
			  shiftRunners(0);
		  }
	  
	  if (currentOuts >= MAX_OUTS) {
		  switchInning();
	  }
  }
  }
  
  /** 
   * Tells that it is the top of the inning
   * @return
   */
  public boolean isTopOfInning() {
	 return topInning;
  }
  /**
   * Allows for a runner to be on base
   * @param which
   * @return
   */
  public boolean runnerOnBase(int which) {
	  return runnerOnBase[which];
  }
  /** Checks for strikes count and if theres an out
   *  
   * @param swung (If batter swung at pitch)
   */
  public void strike(boolean swung) {
	  if(!gameEnded()) {
	  // Since swinging and missing means an automatic out, this would create an out for that scenario
	  if (swung) {
		  currentOuts++;
		  if (currentOuts >= MAX_OUTS) {
			  switchInning();
		  }
		  }
		  
		  else {
			  //simple ++ that initiates an increase in Called Strikes
			  calledStrikes++;
			  if(calledStrikes >= MAX_STRIKES) {
				  switchBatter();
				  currentOuts++;
				  // if currentOuts reaches max outs then inning switches over to the opposing team; wether that be team 1 or team 0
				  if(currentOuts >= MAX_OUTS) {
					  switchInning();
				  }
			  }
		  }
	
	  }
	  }
  /**
   * Returns a one-line string representation of the current game state. The
   * format is:
   * <pre>
   *      ooo Inning:1 [T] Score:0-0 Balls:0 Strikes:0 Outs:0
   * </pre>
   * The first three characters represent the players on base as returned by the
   * <code>getBases()</code> method. The 'T' after the inning number indicates
   * it's the top of the inning, and a 'B' would indicate the bottom. The score always
   * shows team 0 first.
   * 
   * @return a single line string representation of the state of the game
   */
  public String toString()
  {
    String bases = getBases();
    String topOrBottom = (isTopOfInning() ? "T" : "B");
    String fmt = "%s Inning:%d [%s] Score:%d-%d Balls:%d Strikes:%d Outs:%d";
    return String.format(fmt, bases, whichInning(), topOrBottom, getTeam0Score(),
        getTeam1Score(), getBallCount(), getCalledStrikes(), getCurrentOuts());
  }
  /**
   * Checks which inning it is in simulation
   * @return
   */
public int whichInning() {
	  return whichInning;
  }
}

