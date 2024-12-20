package command;

import java.util.HashMap;
import java.util.Map;

// Command Interface
interface Command {
    void execute();
}

// Receiver: Generic Device Interface
interface Device {
    void turnOn();
    void turnOff();
}

// Concrete Receiver: Light
class Light implements Device {
    private final String name;

    public Light(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Light is ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Light is OFF");
    }
}

// Concrete Receiver: Fan
class Fan implements Device {
    private final String name;

    public Fan(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + " Fan is ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + " Fan is OFF");
    }
}

// Concrete Command: TurnOnCommand (Singleton per Device)
class TurnOnCommand implements Command {
    private static final Map<Device, TurnOnCommand> instances = new HashMap<>();
    private final Device device;

    private TurnOnCommand(Device device) {
        this.device = device;
    }

    public static TurnOnCommand getInstance(Device device) {
        return instances.computeIfAbsent(device, TurnOnCommand::new);
    }

    @Override
    public void execute() {
        device.turnOn();
    }
}

// Concrete Command: TurnOffCommand (Singleton per Device)
class TurnOffCommand implements Command {
    private static final Map<Device, TurnOffCommand> instances = new HashMap<>();
    private final Device device;

    private TurnOffCommand(Device device) {
        this.device = device;
    }

    public static TurnOffCommand getInstance(Device device) {
        return instances.computeIfAbsent(device, TurnOffCommand::new);
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}

// Invoker: RemoteControl (Singleton)
class RemoteControl {
    private static RemoteControl instance;
    private final Map<String, Command> commandSlots = new HashMap<>();

    private RemoteControl() {
        // Private Constructor for Singleton
    }
    
    // Singleton, without synchronize here
    public static RemoteControl getInstance() {
        if (instance == null) {
            instance = new RemoteControl();
        }
        return instance;
    }

    public void setCommand(String slotName, Command command) {
        commandSlots.put(slotName, command);
    }

    public void pressButton(String slotName) {
        System.out.println("Executing command on slot: " + slotName);
        Command command = commandSlots.get(slotName);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command assigned to slot: " + slotName);
        }
    }
}

// Client: Configures and uses the command pattern
public class CommandApp {
	
	private static final Map<String, Device> devices = new HashMap<>();
	// initialize devices
	static {
		// map keys and values like "Living Room Light"/"Living Room" could be stored additionally in a separate file..
	    devices.put("Living Room Light", new Light("Living Room"));
	    devices.put("Kitchen Light", new Light("Kitchen"));
	    devices.put("Bedroom Fan", new Fan("Bedroom"));
	}

    public static void main(String[] args) {
        
        // Get Singleton Command Instances
        Command livingRoomLightOn = TurnOnCommand.getInstance(devices.get("Living Room Light"));
        Command livingRoomLightOff = TurnOffCommand.getInstance(devices.get("Living Room Light"));

        Command kitchenLightOn = TurnOnCommand.getInstance(devices.get("Kitchen Light"));
        Command kitchenLightOff = TurnOffCommand.getInstance(devices.get("Kitchen Light"));

        Command bedroomFanOn = TurnOnCommand.getInstance(devices.get("Bedroom Fan"));
        Command bedroomFanOff = TurnOffCommand.getInstance(devices.get("Bedroom Fan"));

        // Get the Singleton Invoker Instance
        RemoteControl remote = RemoteControl.getInstance();

        // Assign Commands to Slots
        remote.setCommand("Living Room Light ON", livingRoomLightOn);
        remote.setCommand("Living Room Light OFF", livingRoomLightOff);

        remote.setCommand("Kitchen Light ON", kitchenLightOn);
        remote.setCommand("Kitchen Light OFF", kitchenLightOff);

        remote.setCommand("Bedroom Fan ON", bedroomFanOn);
        remote.setCommand("Bedroom Fan OFF", bedroomFanOff);

        // Simulate Button Presses
        remote.pressButton("Living Room Light ON");
        remote.pressButton("Kitchen Light ON");
        remote.pressButton("Bedroom Fan ON");

        remote.pressButton("Living Room Light OFF");
        remote.pressButton("Kitchen Light OFF");
        remote.pressButton("Bedroom Fan OFF");

        // Attempting to press an unassigned button
        remote.pressButton("Garage Light ON");
    }
}

// When to use it?
// Another point is that all the action happens in a single place, which makes it easier to write log/trace code etc
