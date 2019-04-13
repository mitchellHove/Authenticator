package com.yes.authenticator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DiceRollerApp extends AppCompatActivity
{
    public int diceMaxValue;
    public Dice[] dice;
    public int amountOfDice;
    public int totalRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller_app);

        Button btn3 = (Button)findViewById(R.id.Roll_Button);

        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                RollDice();
            }
        });

        Button btn4 = (Button)findViewById(R.id.Settings_Button);

        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(DiceRollerApp.this, Account_Screen.class));
            }
        });

    }

    public void RollDice()
    {
        EditText amountOfDiceText = (EditText)findViewById(R.id.Dice_amount);
        amountOfDice = Integer.parseInt(amountOfDiceText.getText().toString());

        EditText amountOfSidesText = (EditText)findViewById(R.id.Dice_sides);
        diceMaxValue = Integer.parseInt(amountOfSidesText.getText().toString());

        dice = new Dice[amountOfDice];
        for(int i = 0; i < dice.length; i++)
        {
            dice[i] = new  Dice();
            System.out.println("***1: " + dice);
            System.out.println("***2: " + dice[i]);
            dice[i].sides = diceMaxValue;
            totalRoll += dice[i].Roll();
        }
        TextView totalRollText = (TextView) findViewById(R.id.Total_Roll);
        totalRollText.setText("" + totalRoll);
        totalRoll = 0;

    }

}
