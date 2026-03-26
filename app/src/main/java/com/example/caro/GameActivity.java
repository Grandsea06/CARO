package com.example.caro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    GridLayout grid;
    Button btnNew, btnExit;

    int SIZE = 17;
    String[][] board = new String[SIZE][SIZE];
    String currentPlayer = "X";
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid = findViewById(R.id.board);
        btnNew = findViewById(R.id.btnNew);
        btnExit = findViewById(R.id.btnExit);

        grid.post(this::createBoard); // 🔥 bắt buộc

        btnNew.setOnClickListener(v -> recreate());

        btnExit.setOnClickListener(v -> finish());
    }

    void createBoard(){
        grid.removeAllViews();

        int width = grid.getWidth();
        int cellSize = width / SIZE;

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){

                Button btn = new Button(this);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = cellSize;
                params.height = cellSize;

                btn.setLayoutParams(params);
                btn.setTextSize(16);

                int x=i,y=j;

                btn.setOnClickListener(v -> {
                    if(gameOver || !btn.getText().toString().equals("")) return;

                    btn.setText(currentPlayer);
                    board[x][y] = currentPlayer;

                    if(checkWin(x,y)){
                        gameOver = true;
                        showWin(currentPlayer);
                        return;
                    }

                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                });

                grid.addView(btn);
            }
        }
    }

    boolean checkWin(int x,int y){
        String p = board[x][y];

        return count(x,y,1,0,p)+count(x,y,-1,0,p)>=4 ||
                count(x,y,0,1,p)+count(x,y,0,-1,p)>=4 ||
                count(x,y,1,1,p)+count(x,y,-1,-1,p)>=4 ||
                count(x,y,1,-1,p)+count(x,y,-1,1,p)>=4;
    }

    int count(int x,int y,int dx,int dy,String p){
        int c=0;
        for(int i=1;i<5;i++){
            int nx=x+dx*i, ny=y+dy*i;
            if(nx<0||ny<0||nx>=SIZE||ny>=SIZE) break;
            if(p.equals(board[nx][ny])) c++;
            else break;
        }
        return c;
    }

    void showWin(String player){
        new AlertDialog.Builder(this)
                .setTitle("Kết thúc")
                .setMessage(player + " thắng!")
                .setPositiveButton("Chơi lại",(d,w)-> recreate())
                .setNegativeButton("Menu",(d,w)-> finish())
                .show();
    }
}