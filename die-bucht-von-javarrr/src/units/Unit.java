package units;
import units.Entity;

public abstract class Unit extends Entity {

    //Legt Standardwerte für die Schiffe/Units fest, die seperat aber noch geändert werden können
        public static final int STANDARD_HP = 5;
        public static final float STANDARD_SPEED = 3.0f;
        public static final int STANDARD_DAMAGE = 1;
        public static final int STANDARD_ATTACKSPEED = 1;

        //Standardisiertes Scaling
        public static final int STANDARD_UNIT_WIDTH = 72, STANDARD_UNIT_HEIGHT = 44;

        //Attribute Gegner
    	private static	int rechts = 2;
    	private  static int links = -2;
    	private static int richtung = rechts;
        

        protected int health, damage, attackSpeed;
        protected float movespeed, xMove;
        protected boolean destroyed;

        



        public Unit(float x, float y) {
            super(x, y);
            damage = STANDARD_DAMAGE;
            attackSpeed = STANDARD_ATTACKSPEED;
            health = STANDARD_HP;
            movespeed = STANDARD_SPEED;
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
    }
