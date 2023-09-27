package chapter2.agent_AB.task1;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		if (p.getLocationState() == Environment.LocationState.DIRTY)
			return Environment.SUCK_DIRT;
		else
			return (p.getAgentLocation() == Environment.LOCATION_A) ? Environment.MOVE_RIGHT : Environment.MOVE_LEFT;
	}
}