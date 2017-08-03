package kg.kloop.rinat.newcarel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private CarelGrid grid;
    private GameCanvas canvas;
    private Carel carel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        //Здесь пишем, что Карел должен делать.********************
        clearAllDesk();
        //chess();
        //progressiveDesk();





        //Дальше ничего не меняем.*********************************




    }//Здесь пишем новые методы.***********************************

    private void progressiveDesk() {
        while(true) {
            progressiveRow();
            turnRight();
            if (!isFrontClear()) {
                returnHome();
                break;
            }
            copyCell();
            turnRight();
            dropBeeper();
            progressiveRow();
            turnLeft();
            if (!isFrontClear()) {
                returnHome();
                break;
            }
            copyCell();
            turnLeft();
            dropBeeper();
        }
    }

    private void copyCell() {
        while (isBeeper()){
            collectBeeper();
            move();
            dropBeeper();
            move();
            dropBeeper();
            uTurn();
            move();
            move();
            uTurn();
        }
        move();
        move();
        uTurn();
        while (isBeeper()) {
            collectBeeper();
            move();
            move();
            dropBeeper();
            uTurn();
            move();
            move();
            uTurn();
        }
        move();
        uTurn();


    }



    private void progressiveRow() {
        while(isFrontClear()){
            copyCell();
            dropBeeper();
        }
    }

    /*
    private void chess() {
        while(true) {
            dropBeeperRowChet();
            turnRight();
            if(!isFrontClear()) {
                returnHome();
                break;
            }
            dropBeeperRowNechet();
            turnRight();
            if(!isFrontClear()) {
                returnHome();
                break;
            }
            goToNextPosition();
        }


    }


    private void dropBeeperRowNechet() {
        move();
        turnRight();
        moveToWall();
        uTurn();
        move();
        while(true){
            dropBeeper();
            if (isFrontClear()) move();
            else break;
            if (isFrontClear()) move();
            else break;

        }
    }

    private void dropBeeperRowChet() {
        while(true) {
            dropBeeper();
            if (isFrontClear()) move();
            else break;
            if (isFrontClear()) move();
            else break;
        }

    }
    */



    private void clearAllDesk() {
        while (true) {
            clearRow();
            turnRight();
            if (!isFrontClear()) {
                returnHome();
                break;
            }
            goToNextPosition();
        }
    }

    private void returnHome() {
        turnRight();
        moveToWall();
        turnRight();
        moveToWall();
        turnRight();
    }

    private void moveToWall() {
        while(isFrontClear()) move();
    }

    private void goToNextPosition() {
        move();
        turnRight();
        moveToWall();
        uTurn();
    }

    private void uTurn() {
        turnLeft();
        turnLeft();
    }

    private void turnRight() {
        for (int i = 0; i < 3; i++){
            turnLeft();
        }
    }

    private void clearRow() {
        while(true){
            clearCell();
            if (isFrontClear()) move();
            else break;

        }
    }

    private void clearCell() {
        while (isBeeper()) collectBeeper();
    }


    //Дальше ничего не меняем.*************************************





    private void move(){
        carel.move();
    }

    private void turnLeft(){
        carel.turnLeft();
    }

    private void collectBeeper(){
        carel.collectBeeper();
    }

    private void dropBeeper(){
        carel.dropBeeper();
    }

    private boolean isFrontClear(){
        return carel.isFrontClear();
    }

    private boolean isBeeper(){
        return carel.isBeeper();
    }


    private void init() {
        textView = (TextView)findViewById(R.id.textView);
        grid = new CarelGrid();
        canvas = new GameCanvas(textView);
        carel = new Carel(canvas, grid);
    }
}