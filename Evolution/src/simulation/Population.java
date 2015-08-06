package simulation;

import helpers.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population implements Iterable<Agent> {
	private List<Agent> agentList = new ArrayList<Agent>();
	
	public boolean add(Agent agent) {
		return agentList.add(agent);
	}
	
	public void breed(double percent) {
		if (percent > 1 || percent < 0) {
			throw new RuntimeException("percent must be between 0 and 1");
		}
		
		int max = (int) (agentList.size() * percent);
		
		for (int i = 0; i < max; i++) {
			Agent mother = Random.grab(agentList);
			Agent father = Random.grab(agentList);
			
			Agent child = mother.mate(father);
			
			agentList.add(child);
		}
		
	}
	

	public int kill(double percent) {
		if (percent > 1 || percent < 0) {
			throw new RuntimeException("percent must be between 0 and 1");
		}
		
		Collections.sort(agentList);

		int index = (int) (agentList.size() * (1-percent));
		
		List<Agent> killList = agentList.subList(index, agentList.size());
		
		int numberRemoved = killList.size();
		
		agentList.remove(killList);
		
		return numberRemoved;
	}

	@Override
	public Iterator<Agent> iterator() {
		return Collections.unmodifiableCollection(agentList).iterator();
	}
}
