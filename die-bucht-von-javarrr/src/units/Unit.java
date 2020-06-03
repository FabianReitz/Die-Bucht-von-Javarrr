package units;
import java.awt.Graphics;

public abstract class Unit {

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

            health = 100;
            movespeed = 3.0f;
            damage = 5;
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
		
	


        // Getter und Setter, um von außen drauf zugreifen zu können

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
    
        public static int getRichtung() {
    		return richtung;
    	
        }
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
