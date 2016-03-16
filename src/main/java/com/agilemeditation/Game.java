package com.agilemeditation;

public class Game {
  private static final int MAX_PINS = 10;

  private int score;
  private Roll previousScores[] = new Roll[2];
  private int rollCount;
  private boolean rollCompletesFrame = false;

  public int getScore() {
    return score;
  }

  public void roll(int currentRollScore) {
    //add bonuses
    if (getRoll(rollCount - 2).isStrike()) {
      score += currentRollScore;
    }
    if (getRoll(rollCount - 1).isSpareOrStrike()) {
      score += currentRollScore;
    }

    if (rollCompletesFrame) {
      //Spare or 'normal' frame scenarios
      if (getRoll(rollCount - 1).getScore() + currentRollScore == MAX_PINS) {
        storeCurrentRoll(new Roll(currentRollScore, Bonus.SPARE));
      } else {
        storeCurrentRoll(new Roll(currentRollScore, Bonus.NONE));
      }
      rollCompletesFrame = false;
    } else {
      if (currentRollScore == MAX_PINS) {
        // Strike scenario
        storeCurrentRoll(new Roll(currentRollScore, Bonus.STRIKE));
        rollCompletesFrame = false;
      } else {
        // First roll 'normal' scenario
        storeCurrentRoll(new Roll(currentRollScore, Bonus.NONE));
        rollCompletesFrame = true;
      }
    }

    score += currentRollScore;
    rollCount++;
  }

  private void storeCurrentRoll(Roll roll) {
    previousScores[rollCount % 2] = roll;
  }

  private Roll getRoll(int rollNumber) {
    if (rollNumber < 0) {
      return new Roll(0, Bonus.NONE);
    }
    return previousScores[rollNumber % 2];
  }


  private static class Roll {
    private final int score;
    private final Bonus bonus;

    public Roll(int score, Bonus bonus) {
      this.score = score;
      this.bonus = bonus;
    }

    public int getScore() {
      return score;
    }

    public boolean isStrike() {
      return Bonus.STRIKE == bonus;
    }

    public boolean isSpareOrStrike() {
      return Bonus.SPARE == bonus || Bonus.STRIKE == bonus; // Could use != Bonus.NONE, but it's ugly
    }
  }

  private enum Bonus {
    STRIKE,
    SPARE,
    NONE
  }
}
