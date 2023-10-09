package chapter2.agent_AB.task1;

public class AgentProgram {
	/*
	 * A B 
	 * D C
	 */
	public Action execute(Percept p) {// location, status
		if (p.getLocationState() == Environment.LocationState.DIRTY)
			return Environment.SUCK_DIRT;
		else {
			if (p.getAgentLocation() == Environment.LOCATION_A)
				return Environment.MOVE_RIGHT;
			else if (p.getAgentLocation() == Environment.LOCATION_B)
				return Environment.MOVE_DOWN;
			else if (p.getAgentLocation() == Environment.LOCATION_C)
				return Environment.MOVE_LEFT;
			else
				return Environment.MOVE_UP;

		}
	}
}