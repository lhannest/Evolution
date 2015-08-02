package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population {
	List<Agent> agentList = new ArrayList<Agent>();
	
	public boolean add(Agent agent) {
		return agentList.add(agent);
	}
	
	public void kill(double percent) {
		Collections.sort(agentList);
	}
}
