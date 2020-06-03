package units;
import java.awt.Graphics;

public abstract class Unit {

    //Legt Standardwerte f√ºr die Schiffe/Units fest, die seperat aber noch ge√§ndert werden k√∂nnen
        public static final int STANDARD_HP = 100;
        public static final float STANDARD_SPEED = 3.0f;

        //Standardisiertes Scaling
        public static final int STANDARD_UNIT_WIDTH = 72, STANDARD_UNIT_HEIGHT = 44, STANDARD_UNIT_DMG = 5;

        //Attribute Gegner
    	private static int rechts = 1;
    	private static int links = -1;
    	private static int richtung = rechts;
        

        protected int health, damage;
        protected float movespeed, xMove;
        private boolean destroyed;
        
        protected float x, y;
        
        public Unit(float x, float y) {
        	this.x = x;
            this.y = y;

            health = STANDARD_HP;
            movespeed = STANDARD_SPEED;
            damage = STANDARD_UNIT_DMG;
            xMove = 0;
  
            destroyed = false;
        
        }

        public boolean isDestroyed() {
			return destroyed;
		}

		public void setDestroyed(boolean destroyed) {
			this.destroyed = destroyed;
		}

		public void move() {
            x += xMove;
        }
		
	


        // Getter und Setter, um von au√üen drauf zugreifen zu k√∂nnen

        public float getXmove() {
            return xMove;
        }



        public void setXmove(float xmove) {
            this.xMove = xmove;
        }
       
        public int getHealth() {
            return health;
        }


        public void setHealth(int health) {
            this.health = health;
        }


        public float getMovespeed() {
            return movespeed;
        }


        public void setMovespeed(float movespeed) {
            this.movespeed = movespeed;
        }
        /*Dadurch, dass die Richtung f¸r alle Gegner ge‰ndert werden soll,
         muss die variable Statisch sein
         */
        
        public static int getRichtung() {
    		return richtung;
    	
        }
        //Richtung des Gegners wird ge‰ndert
    	public void richtungLinks() {
    		richtung = links;
    	}
    	public void richtungRechts() {
    		richtung = rechts;
    }
        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public abstract void update();

        public abstract void render(Graphics graphics);
    	
    }
