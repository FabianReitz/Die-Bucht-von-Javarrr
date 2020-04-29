package units;
import java.awt.Graphics;
import game.*;
import graphics.Animation;
import graphics.Assets;

public class Player extends Unit{

    private Game game;
    private int damage, kanonen, maxLeben;
    
    //Animation
    private Animation shoot;

    public Player(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        
        //Animation
//        shoot = new Animation(500, Assets.shoot);
    }

    private void getInput() {
        xMove = 0;

        if(game.getKeyManager().left && x > 1) {
            xMove = -movespeed;

        }

        if(game.getKeyManager().right && x < 436) {
            xMove = movespeed;
        }
    }

    @Override
    public void update() {

        getInput();
        move();

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player, (int) x, (int) y, width, height, null);

    }
}