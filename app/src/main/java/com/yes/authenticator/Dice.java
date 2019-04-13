package com.yes.authenticator;

import java.util.Random;

public class Dice

{
    public int sides;
    public int roll;

    public int Roll()
    {
        Random rand = new Random();
        int rolledAmount = rand.nextInt(sides);
        return rolledAmount;
    }

}