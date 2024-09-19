
package hw1;

/** 
 * Hot Air Balloon Representation 
 * @author Myles Curtis
 *  Accessor = Returns information without modifying it 
 *  Mutator =  Modifies the object, usually returning void
 */
public class Balloon {
	private static double heat = 0.1; 
	private static double volume = 61234; // Volume of air in balloon
	private static double acceleration = 9.81;
	private static double gasConstant = 287.05; // Units = J/KgK
	private static double pressure = 1013.25; // Units = hPa
	private static double kelvin = 273.15; // K at 0 degrees C
	
	private double fuelRemaining;
	private double balloonMass;
	private double outsideBalloonTemp;
	private double fuelBurnRate;
	private double balloonTemp;
	private double velocity;
	private double balloonAltitude;
	private double tetherLength;
	private double windDirection;
	private double initialOutsideTemp;
	private double initialWindDirection;
	private long time;
	/** (Accessor)
	 * Constructs a Balloon object with given airTemp and Wind Direction
	 * @param airTemp
	 * @param windDirection
	 */
	public Balloon(double airTemp, double windDirection) {
		this.outsideBalloonTemp = airTemp;
		this.balloonTemp = airTemp;
		this.windDirection = windDirection;
		
		this.time = 0;
		this.balloonAltitude = 0;
		this.fuelRemaining = 0;
		this.fuelBurnRate = 0;
		this.balloonMass = 0;
		this.velocity = 0;
		this.tetherLength = 0;
		
		this.initialOutsideTemp = airTemp;
		this.initialWindDirection = windDirection;
	
	}
	/** (Accessor)
	 * Retrieves remaining fuel
	 * @return the remaining fuel amount
	 */
	
	public double getFuelRemaining() {
		return fuelRemaining;
	}
	/** (Mutator)
	 *  Sets remaining fuel amount
	 * @param fuel remaining
	 */
	public void setFuelRemaning(double fuelRemaining) {
		this.fuelRemaining = fuelRemaining;
	}
	/** (Accessor)
	 * Gets Balloon Mass
	 * @return Balloon mass
	 */
	public double getBalloonMass() {
		return balloonMass;
	}
	/** (Mutator)
	 * Sets Balloon Mass
	 * @param mass
	 */
	public void setBalloonMass(double mass) {	
		this.balloonMass = mass;
	}
	/** (Accessor)
	 * Gets the Outside Air Temp
	 * @return outside air temp
	 */
	public double getOutsideAirTemp() {
		return outsideBalloonTemp;
	}
	/** (Mutator)
	 * Sets Outside Air Temp
	 * @param temp of the new outside air.
	 */
	public void setOutsideAirTemp(double outsideAirTemp) {
		this.outsideBalloonTemp = outsideAirTemp;
	}
	/**
	 * (Accessor)
	 * Retrieves fuel burn rate from balloon 
	 * @return fuel burn rate 
	 */
	public double getFuelBurnRate() {
		return fuelBurnRate;
	}
	/** (Mutator)
	 * Sets fuel burn rate of balloon 
	 * @param rate of fuel burning is set
	 */
	public void setFuelBurnRate(double rate) {
		this.fuelBurnRate = rate;
	}
	/** (Accessor)
	 * Retrieves Temp
	 * 
	 * @return Balloon Temp
	 */
	public double getBalloonTemp() {
		return balloonTemp;
	}
	/** (Mutator)
	 * Sets Balloon Temp
	 * @param Temp of ballon set
	 */
	public void setBalloonTemp(double balloonTemp) {
		this.balloonTemp = balloonTemp;
	}
	/** (Accessor)
	 * Gets velocity of balloon 
	 *
	 * @return velocity
	 */
	public double getVelocity() {
		return velocity;		
	}
	/** (Accessor)
	 * Gets balloon altitude
	 * @return altitude
	 */
	public double getAltitude() {
		return balloonAltitude;
	}
	/** (Accessor)
	 * Gets length of tether
	 * @return tether length
	 */
	public double getTetherLength() {
		return tetherLength;
	}
	/** (Accessor)
	 *  Gets remaining tether
	 * @return tether
	 */
	public double getTetherRemaining() {
		return this.tetherLength - this.balloonAltitude;
	}
	/** (Mutator)
	 * Sets tether length
	 * @param length
	 */
	public void setTetherLength(double length) {
		this.tetherLength = length;
	}
	/** (Accessor)
	 * Gets wind direction
	 * 
	 * @return wind direction
	 */
	public double getWindDirection() {
		return windDirection;
	}
	/** (Mutator)
	 * Changes wind direction
	 * 
	 * 
	 * 
	 * 
	 * @param deg change amount by which the wind direction changes
	 */
	public void changeWindDirection(double deg) {
		windDirection = (windDirection + deg) % 360;
		windDirection = (windDirection + 360) % 360;
	
	}
	/** (Accessor)
	 *  Gets minutes elapsed
	 * @return minutes elapsed
	 */
	public long getMinutes() {
		return time/60;
	}
	/** (Accessor)
	 * Gets seconds elapsed
	 * 
	 * @return seconds elapsed 
	 */
	public long getSeconds() {
		return time%60;
	}
	/*
	 * this.time,temp,fuelRemaining focused on the change in time, temp, and fuel
	 * doubles until this.velocity are focused on change in velocity 
	 * 
	 */
	public void update() {
		time += 1;
		double fuelBurned = Math.min(fuelBurnRate, fuelRemaining);
		double tempChange = outsideBalloonTemp - balloonTemp;
		balloonTemp += fuelBurned + (tempChange)*heat;
		fuelRemaining = Math.max(fuelRemaining - fuelBurnRate, 0);
		
		
		double outside = pressure/(gasConstant*(outsideBalloonTemp + kelvin));
		double inside = pressure/(gasConstant*(balloonTemp + kelvin));
		double liftForce = volume*acceleration*(outside- inside);
		double gravityForce = balloonMass*acceleration;
		double netForce = liftForce - gravityForce;
		double netAcceleration = netForce/balloonMass;
		velocity += netAcceleration;
		
		balloonAltitude += velocity;
		
		balloonAltitude = Math.max((Math.min(tetherLength, balloonAltitude)), 0);
	}
	/**
	 * Resets Simulation 
	 */
	public void reset() {
		outsideBalloonTemp = initialOutsideTemp;
		balloonTemp = initialOutsideTemp;
		windDirection = initialWindDirection;
		time = 0;
		balloonAltitude = 0;
		velocity = 0;
		tetherLength = 0;
		balloonMass = 0;
		fuelRemaining = 0;
		fuelBurnRate = 0;
		
	}
}
