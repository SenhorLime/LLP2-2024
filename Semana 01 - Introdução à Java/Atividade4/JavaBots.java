import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.applet.*;
import java.lang.*;
import java.util.*;

import javax.swing.JFrame;

public class JavaBots extends Applet implements Runnable {
	
	
    final int PAUSE_LENGTH = 10; // millisecs to pause between ticks

    final boolean SHOW_TRAIL = false; // do sprites show trail?

    Dimension ecoSystemSize = new Dimension();

    //variables for double-buffered painting...

    Image offScreenImage = null;

	Graphics offScreenGraphics = null;

    Dimension offScreenSize = null;



    //user interface objects

    Panel controlPanel = new Panel();

    Panel ecoSystem = new Panel();

    CheckboxGroup creatureSize = new CheckboxGroup();

    Checkbox sizes[] = new Checkbox[Creature.MAXSIZE];

    Choice creatureColor = new Choice();

    Button startStop = new Button("START / STOP");

    Button addButton = new Button("add");

    Button removeButton = new Button("remove");

    Choice addChoice = new Choice();

    Choice removeChoice = new Choice();

    TextField mfRatField = new TextField(" " + Creature.MFRAT + " ");

    TextField bRatField = new TextField(" " + Creature.BIRTH_RATE + " ");



    //array of color choices

    String stringColors[] = {"red", "green", "blue", "yellow", "black"};

    Color colorColors[] = {Color.red, Color.green, Color.blue, Color.yellow, Color.black};



    Vector creatureList = new Vector(); //a dynamic array of creatures

    Vector sysEventList = new Vector(); //a dynamic array of future events

    Thread mainThread; //main thread

    int males = 0;

    int females = 0;

    boolean isRunning = false;



    public void init() {

        //create user interface

		setLayout(new BorderLayout());



      Panel newCreature = new Panel();

		newCreature.add(new Label("Click for new creature:"));

    	newCreature.add(new Label("Size"));

		for (int i = 0; i < Creature.MAXSIZE; i++)

		    if (i == 0)

                newCreature.add(new Checkbox ((i+1) + "", creatureSize, true));

            else

                newCreature.add(new Checkbox ((i+1) + "", creatureSize, false));

      for (int i = 0; i < stringColors.length; i++)

          creatureColor.addItem(stringColors[i]);

    	newCreature.add(new Label("Color"));

      newCreature.add(creatureColor);



      Panel moreControls = new Panel();

		moreControls.add(new Label("Randomly"));

  	   moreControls.add(addButton);

     	for (int i = 1; i <= 10; i++)

        	addChoice.addItem("" + i);

		moreControls.add(addChoice);

      moreControls.add(new Label("       Randomly"));

      moreControls.add(removeButton);

      for (int i = 1; i <= 10; i++)

      	removeChoice.addItem("" + i);

      moreControls.add(removeChoice);



      mfRatField.setEditable(true);

      bRatField.setEditable(true);

      Panel lifeControls = new Panel();

      lifeControls.add(new Label("Male/Female Ratio:"));

      lifeControls.add(mfRatField);

      lifeControls.add(new Label("   Birth Rate:"));

      lifeControls.add(bRatField);

	  ecoSystem.setLayout(new GridLayout(1, 1));
	  ecoSystem.add(startStop);
      controlPanel.setLayout(new GridLayout(4, 1));
	
      controlPanel.add(startStop);

      controlPanel.add(newCreature);

      controlPanel.add(moreControls);

      controlPanel.add(lifeControls);

      ecoSystem.addComponentListener(new ComponentAdapter() {

		@Override
		public void componentResized(ComponentEvent e) {			
			super.componentResized(e);
			System.out.println(e.getComponent().getWidth());
			ecoSystemSize.setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
		}
    	  
	});

      add("South", controlPanel);

      add("Center", ecoSystem);

      Creature.sysEventList = sysEventList;		

      update(ecoSystem.getGraphics()); //update screen
      
      

    }



    public void run() {
		
        while (true) {

            handleSysEvents(); //process all events

            for (Enumeration e = creatureList.elements(); e.hasMoreElements() ;)

                ((Creature)(e.nextElement())).tick(); //let creature know that clock had ticked

 				//check for collisions

             for (int i = 0; i < creatureList.size() ; i++)

                for (int j = i + 1; j < creatureList.size() ; j++)

                    checkCollision((Creature) creatureList.elementAt(i), (Creature) creatureList.elementAt(j));

            update(ecoSystem.getGraphics()); //update screen

            try {

                Thread.sleep(PAUSE_LENGTH); //wait for the next clock tick

            } catch (InterruptedException e) {

                break;

            }

        }

    }



    public void start() {

//        mainThread = new Thread(this);

//        mainThread.start();

//        isRunning = true;

    }



    public void stop() {

        if (mainThread != null) {

	        mainThread.stop();

   	     isRunning = false;

      	  mainThread = null;

        }

    }



    public final void update(Graphics g){

        //implements no-flicker graphis using double buffering

		Dimension dim = size();

		if((offScreenImage == null) || (dim.width != offScreenSize.width) || (dim.height != offScreenSize.height)){

			offScreenImage = createImage(dim.width, dim.height);

			offScreenSize = dim;

			offScreenGraphics = offScreenImage.getGraphics();

			if (SHOW_TRAIL)

           		offScreenGraphics.clearRect(0, 0, offScreenSize.width, offScreenSize.height);

		}

		if (!SHOW_TRAIL)

       		offScreenGraphics.clearRect(0, 0, offScreenSize.width, offScreenSize.height);

		paint(offScreenGraphics);

		g.drawImage(offScreenImage, 0, 0, null);

	}



	public void paint(Graphics g) {

 		   //ask creature to paint itself on screen

         for (Enumeration e = creatureList.elements(); e.hasMoreElements() ;)

            ((Creature)(e.nextElement())).paint(g);
        String mensagem = ("Number of creatures: "+(males+females)+"  Males: "+males+"  Females: "+females+"  Status: "+(isRunning?"running":"stopped"));
        try {
        	showStatus(mensagem);	
		} catch (NullPointerException e) {			
			((Frame)(this.getParent())).setTitle("JavaBots - "+ mensagem);
		}

	}



    public boolean handleEvent(Event event) {

        //handle java events

        switch(event.id) {

                case Event.MOUSE_DOWN:

                    if (ecoSystem.inside(event.x, event.y)) {

                        int size = Integer.parseInt(creatureSize.getCurrent().getLabel(), 10);

                        Color col = colorColors[creatureColor.getSelectedIndex()];

                        sysEventList.addElement(new sysEvent(sysEvent.GIVE_BIRTH, new Creature(ecoSystem, size, col, event.x - ecoSystem.location().x, event.y - ecoSystem.location().y)));

						      handleSysEvents();

						      update(ecoSystem.getGraphics()); //update screen

                    }

				        return true;

                case Event.ACTION_EVENT:

				        if (event.target == startStop)

        						startStop();

                    else if (event.target == addButton)

                    		addCreatures(Integer.parseInt(addChoice.getSelectedItem()));

                    else if (event.target == removeButton)

                    		removeCreatures(Integer.parseInt(removeChoice.getSelectedItem()));

				        return true;

        }

        return false;

    }



    void handleSysEvents() {

        // handle ecoSystem events

        sysEvent evt = null;



        for (Enumeration e = sysEventList.elements(); e.hasMoreElements() ;) {

            evt = (sysEvent) e.nextElement();

            switch (evt.action) {

                case sysEvent.KILL:

                    //remove creature from the list

                    creatureList.removeElement(evt.subject);

                    if (evt.subject.gender == Creature.MALE)

                    		males--;

                    else

                    		females--;

                    break;

                case sysEvent.GIVE_BIRTH:

                    //add creature to the list

                    creatureList.addElement(evt.subject);

                    if (evt.subject.gender == Creature.MALE)

                    		males++;

                    else

                    		females++;

                    break;

                case sysEvent.GROW:

                    //increase creature size

                    evt.subject.grow();

                    break;

                case sysEvent.BOUNCE:

                	  //bounce creatures off each other

                	  bounce(evt.subject, evt.subject2);

                    break;

                case sysEvent.EAT:

                	  //satisfy the hunger

                    evt.subject.eat(evt.subject2);

                    break;

                /* handle new actions here */

            }

        }

        sysEventList.removeAllElements();

    }



    void addCreatures(int num) {

      //populate our ecoSystem

      for (int i = 0; i < num; i++)

          sysEventList.addElement(new sysEvent(sysEvent.GIVE_BIRTH, new Creature(ecoSystem, (int)(Math.random() * (Creature.MAXSIZE - 1) + 1), new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255)), (int)(Math.random() * ecoSystemSize.getWidth()), (int)(Math.random() * ecoSystemSize.getHeight()))));

      handleSysEvents();

      update(ecoSystem.getGraphics()); //update screen

    }



    void removeCreatures(int num) {

      for (int i = 0; i < num; i++)

      	if (creatureList.size()> 0) {

         	sysEventList.addElement(new sysEvent(sysEvent.KILL, (Creature) creatureList.elementAt((int)(Math.random()*creatureList.size()))));

		      handleSysEvents();

         }

      update(ecoSystem.getGraphics()); //update screen

    }



    void startStop() {

    	if (isRunning) {

         isRunning = false;

      	mainThread.stop();

	      mfRatField.setEditable(true);

   	   bRatField.setEditable(true);

         mainThread = null;

   	}

      else {

         Creature.MFRAT = (new Double(mfRatField.getText())).doubleValue();

         Creature.BIRTH_RATE = (new Double(bRatField.getText())).doubleValue();

	      mfRatField.setEditable(false);

   	   bRatField.setEditable(false);

         mainThread = new Thread(this);

      	mainThread.start();

         isRunning = true;

      }

      update(ecoSystem.getGraphics()); //update screen

    }



    void checkCollision(Creature firstCreature, Creature secondCreature) {

        double  d = dist(secondCreature.positionX, firstCreature.positionX, secondCreature.positionY, firstCreature.positionY);

        boolean allIsWell = true;



        if (d <= (secondCreature.radius + firstCreature.radius)) {

            if (secondCreature.size == firstCreature.size)

              	sysEventList.addElement(new sysEvent(sysEvent.BOUNCE, firstCreature, secondCreature));

            else { //is anybody hungry?

                if (secondCreature.size> firstCreature.size) { //secondCreature wins

                    if (secondCreature.isHungry()) {

			              	sysEventList.addElement(new sysEvent(sysEvent.EAT, secondCreature, firstCreature));

                        allIsWell = false;

                    }

                    else

			              	sysEventList.addElement(new sysEvent(sysEvent.BOUNCE, firstCreature, secondCreature));

                }

                else { //firstCreature wins

                    if (firstCreature.isHungry()) {

			              	sysEventList.addElement(new sysEvent(sysEvent.EAT, firstCreature, secondCreature));

                        allIsWell = false;

                    }

                    else

			              	sysEventList.addElement(new sysEvent(sysEvent.BOUNCE, firstCreature, secondCreature));

                }

            }

            //give birth?

            if ((secondCreature.gender != firstCreature.gender) &&

              	 (Math.random() < Creature.BIRTH_RATE) &&

                (secondCreature.isAdult()) && (firstCreature.isAdult()) && allIsWell) {

                	Color col = new Color((int)((secondCreature.color.getRed() + firstCreature.color.getRed()) / 2),

                  	                    (int)((secondCreature.color.getGreen() + firstCreature.color.getGreen()) / 2),

                     	                 (int)((secondCreature.color.getBlue() + firstCreature.color.getBlue()) / 2));

                	sysEventList.addElement(new sysEvent(sysEvent.GIVE_BIRTH, new Creature(ecoSystem, 1, col, (int)(secondCreature.positionX + secondCreature.radius), (int)(secondCreature.positionY + secondCreature.radius))));



                	secondCreature.justGaveBirth();

                	firstCreature.justGaveBirth();

            }

        }

    }



   void bounce(Creature firstCreature, Creature secondCreature) {

        double massFirst = firstCreature.radius * firstCreature.radius;

        double massSecond = secondCreature.radius * secondCreature.radius;

        double speedFirst = (2 * massSecond * secondCreature.speed + (massFirst - massSecond) * firstCreature.speed) / (massSecond + massFirst);

        double speedSecond = (2 * massFirst * firstCreature.speed + (massSecond - massFirst) * secondCreature.speed) / (massSecond + massFirst);

        int combDir = (secondCreature.direction + firstCreature.direction) / 2;

        double dist = dist(secondCreature.positionX, firstCreature.positionX, secondCreature.positionY, firstCreature.positionY);



        firstCreature.setSpeed(speedFirst);

        secondCreature.setSpeed(speedSecond);

        firstCreature.heading((combDir - (firstCreature.direction - combDir)) % 360);

        secondCreature.heading((combDir - (secondCreature.direction - combDir)) % 360);



        //remove firstCreature from zone of interference with secondCreature

        firstCreature.positionX = (float)(secondCreature.positionX + ((secondCreature.radius + firstCreature.radius) * (firstCreature.positionX - secondCreature.positionX) / dist));

        firstCreature.positionY = (float)(secondCreature.positionY + ((secondCreature.radius + firstCreature.radius) * (firstCreature.positionY - secondCreature.positionY) / dist));

   }



	double dist(float x1, float x2, float y1, float y2) {

	    return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));

	}

	
	public static void main(String[] args) {
		  Frame f = new Frame("JavaBots");
		  f.addWindowListener(new java.awt.event.WindowAdapter() {
		       public void windowClosing(java.awt.event.WindowEvent e) {
		       System.exit(0);
		       };
		     });

		  JavaBots ut = new JavaBots();
		  ut.setSize(640,480); // same size as defined in the HTML APPLET
		  f.add(ut);
		  f.pack();
		  ut.init();
		  f.setSize(640,480 + 20); // add 20, seems enough for the Frame title,
		  f.setVisible(true);
		  }
}



/** Sprite class allows to create moving objects on the screen. */

class Sprite extends Observable {

    //constants that can be used outside the class

    final static int NORTH = 180;

    final static int SOUTH = 0;

    final static int WEST = 270;

    final static int EAST = 90;

    final static int MAXSPEED = 12;



    protected final static int DELTA = 4;

    protected final static int DEFAULTSIZE = 10;

    private final static SpriteView view = new SpriteView();



    //some geometrical information

    float positionX = DEFAULTSIZE;

    float positionY = DEFAULTSIZE;

    protected double speed = 0;

    protected float radius = 0;

    protected int direction = SOUTH; //in degrees from SOUTH

	 Color color = null;

    Panel environment = null;



    protected Sprite(Panel panel) {

    	  environment = panel;

        addObserver(view);

    }



    /** Sets the direction of sprite */

    public void heading (int dir) {

        direction = dir;

    }



    /** Sets the speed between 1 and MAXSPEED */

    public void setSpeed (double sp) {

        if ((sp>= 1) && (sp <= MAXSPEED))

            speed = sp;

    }



    /** The tick method is called by our parent on every time tick.

        Here's where we it moves the sprite along its direction if necessary. */

    public void tick () {

        if (speed> 0){

            positionX += ((float)speed / MAXSPEED) * DELTA * Math.sin((2 * Math.PI * direction) / 360);

            positionY += ((float)speed / MAXSPEED) * DELTA * Math.cos((2 * Math.PI * direction) / 360);

        }



        // check for boundary bounce conditions

        if ((positionX - radius) <= 0) {

            bounceVertical();

            positionX = radius;

        }

        if ((positionX + radius)>= environment.size().width) {

            bounceVertical();

            positionX = environment.size().width - radius;

        }

        if ((positionY - radius) <= 0) {

            bounceHorizontal();

            positionY = radius;

        }

        if ((positionY + radius)>= environment.size().height) {

            bounceHorizontal();

            positionY = environment.size().height - radius;

        }

    }



    /** Causes the sprite to bounce off the vertical wall */

    public void bounceVertical() {

        direction = (0 - direction) % 360;

    }



    /** Causes the sprite to bounce off the horizontal wall */

    public void bounceHorizontal() {

        direction = (180 - direction) % 360;

    }



    public void paint(Graphics g) {

         setChanged();

		 notifyObservers(g);

    }

}



/** a view class for sprite */

class SpriteView implements Observer {



   public void update(Observable o, Object arg) {

   	Graphics g = (Graphics) arg;

      Sprite s = (Sprite) o;



      g.setColor(s.color);
      

      g.fillOval((int)(s.positionX - s.radius), (int)(s.positionY - s.radius), (int)(s.radius*2), (int)(s.radius*2));

   }

}



/** Creature class adds living organism properties to sprite. */

class Creature extends Sprite {

    final static int 	MAXSIZE = 5;

    final static int 	MALE = 2;

    final static int 	FEMALE = 3;

    final static int 	DEFAULTDIAMETER = 10;

    static double BIRTH_RATE = 0.2;

    static double MFRAT = 0.5;

    static int PUBERTY_AGE = 100;

    static int HAPPY_PERIOD = 200; // won't eat other creatures if happy

    int 	GROWTH_PERIOD = 2000;

    int 	LIFESPAN = 9999;



    static Vector	sysEventList = null;

	 int				size = 0;

    int 				gender = (Math.random()> MFRAT) ? FEMALE : MALE;

    int 				age = 0;

	 int 				last_happy = 0;



    Creature(Panel ecoSystem, int sz, Color col, int posX, int posY) {

        super(ecoSystem);

        if ((sz>= 1) && (sz <= MAXSIZE))

	        size = sz;

        else

	        size = MAXSIZE;

        age = (size - 1) * GROWTH_PERIOD;

        feelingGood();

        radius = (DEFAULTDIAMETER + size*2) / 2;

        color = col;

        positionX = posX;

        positionY = posY;

        heading((int)(Math.random() * 359 + 1));

        setSpeed(MAXSPEED/size);

    }



    public void tick() {

        age++;



        //let sprite do whatever it finds useful first

        super.tick();



        //old creatures have to expire

        if (age> LIFESPAN) {

            sysEventList.addElement(new sysEvent(sysEvent.KILL, this));

        }



        //is it time to grow?

        if ((age % GROWTH_PERIOD) == 0)

            sysEventList.addElement(new sysEvent(sysEvent.GROW, this));

    }


    public void paint(Graphics g) {

        super.paint(g);
        if(this.gender == MALE)
        	g.fillRect((int)(this.positionX - this.radius), (int)(this.positionY - this.radius), (int)(this.radius*2), (int)(this.radius*2));
        
    }



    public void grow() {

        Image img;



        if (size < MAXSIZE) {

            size++;

	         radius = (DEFAULTDIAMETER + size*2) / 2;

 //	         setSpeed(MAXSPEED/size);

        }

    }



    public void eat(Creature victim) {

        color = new Color((int)((color.getRed() + victim.color.getRed()) / 2),

                          (int)((color.getGreen() + victim.color.getGreen()) / 2),

                          (int)((color.getBlue() + victim.color.getBlue()) / 2));

        sysEventList.addElement(new sysEvent(sysEvent.KILL, victim));

        feelingGood(); //food is good

    }



    public boolean isHungry() {

    	 //bigger creatures get hungry faster

		 return ((age - last_happy)> (HAPPY_PERIOD * (MAXSIZE - size + 1)));

    }



    public boolean isAdult() {

	 	 return (age> PUBERTY_AGE);

    }



    public void justGaveBirth() {

//    	 feelingGood(); //giving birth is good

    }



    public void feelingGood() {

			last_happy = age;

    }

}



/** sysEvent class, just a package to hold an creature-relevant event */

class sysEvent {

    final static int NOACTION 	= 0;

    final static int KILL 			= 1;

    final static int GIVE_BIRTH 	= 2;

    final static int GROW 			= 3;

    final static int BOUNCE 		= 4;

    final static int EAT 			= 5;

    /* add new actions here */



    Creature subject = null;

    Creature subject2 = null;

    int action = 0;



    sysEvent(int a) {

        action = a;

    }



    sysEvent(int a, Creature firstCreature) {

        subject = firstCreature;

        action = a;

    }



    sysEvent(int a, Creature firstCreature, Creature secondCreature) {

        subject = firstCreature;

        subject2 = secondCreature;

        action = a;

    }

}