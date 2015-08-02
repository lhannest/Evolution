package genome;

import helpers.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mutator {
	private double jiggleAmount = 0.01;
	private double jiggleChance = 0.3;
	private double addNodeChance = 0.1;
	private double addArcChance = 0.1;
	private double splitAnArcChance = 0.2;
	
	private void jiggleWeight(Genome genome) {
		ArcGene arcGene = Random.grab(genome.arcGenes);
		
		NodeGene parent = arcGene.getParent();
		NodeGene child = arcGene.getChild();
		Inov inov = arcGene.getInov();
		boolean enabled = arcGene.isEnabled();
		double weight = arcGene.getWeight();

		weight += Random.randomDouble(-jiggleAmount, jiggleAmount);
		
		ArcGene modifiedArc = new ArcGene(inov, parent, child, weight, enabled);
		
		genome.arcGenes.add(modifiedArc);
	}
	
	private void addNewNode(Genome genome) {
		NodeGene nodeGene = new NodeGene(Inov.makeNext(), NodeType.HIDDEN);
		
		NodeGene parent = Random.grab(genome.nodeGenes);
		NodeGene child = Random.grab(genome.nodeGenes);
		
		ArcGene arcGene1 = new ArcGene(Inov.makeNext(), parent, nodeGene);
		ArcGene arcGene2 = new ArcGene(Inov.makeNext(), nodeGene, child);
		
		genome.nodeGenes.add(nodeGene);
		genome.arcGenes.add(arcGene1);
		genome.arcGenes.add(arcGene2);
	}
	
	private void addNewArc(Genome genome) {
		NodeGene parent = Random.grab(genome.nodeGenes);
		NodeGene child = Random.grab(genome.nodeGenes);
		
		genome.arcGenes.add(new ArcGene(Inov.makeNext(), parent, child));
	}
	
	private void splitAnArc(Genome genome) {
		ArcGene arcGene = Random.grab(genome.arcGenes);
		
		NodeGene parent = arcGene.getParent();
		NodeGene child = arcGene.getChild();
		double weight = arcGene.getWeight();
		boolean enabled = arcGene.isEnabled();
		
		genome.arcGenes.remove(arcGene);
		
		NodeGene nodeGene = new NodeGene(Inov.makeNext(), NodeType.HIDDEN);

		ArcGene arcGene1 = new ArcGene(Inov.makeNext(), parent, nodeGene, weight, enabled);
		ArcGene arcGene2 = new ArcGene(Inov.makeNext(), nodeGene, child, 1, true);
		
		genome.arcGenes.add(arcGene1);
		genome.arcGenes.add(arcGene2);
	}
	
	public void mutate(Genome genome) {
		if (Random.randomBoolean(jiggleChance)) {
			jiggleWeight(genome);
		}
		
		if (Random.randomBoolean(addNodeChance)) {
			addNewNode(genome);
		}
		
		if (Random.randomBoolean(addArcChance)) {
			addNewArc(genome);
		}
		
		if (Random.randomBoolean(splitAnArcChance)) {
			splitAnArc(genome);
		}
	}
	
	public Genome copy(Genome genome) {
		Genome child = new Genome();
		
		child.arcGenes.addAll(genome.arcGenes);
		child.nodeGenes.addAll(genome.nodeGenes);
		
		return child;
	}
	
	public Genome cross(Genome a, Genome b) {
		Genome child = new Genome();
		
		Collections.sort(a.nodeGenes);
		Collections.sort(a.arcGenes);
		Collections.sort(b.nodeGenes);
		Collections.sort(b.arcGenes);
		
		Genome dom = Random.grab(a, b);
		Genome sub = a.equals(dom) ? b : a;
		
		int p = pointOfDivergence(dom.nodeGenes, sub.nodeGenes);
		child.nodeGenes = splice(dom.nodeGenes, sub.nodeGenes, 0, p);
		List<NodeGene> extraNodeGenes = dom.nodeGenes.subList(p, dom.nodeGenes.size());
		child.nodeGenes.addAll(extraNodeGenes);
		
		p = pointOfDivergence(dom.arcGenes, sub.arcGenes);
		child.arcGenes = splice(dom.arcGenes, sub.arcGenes, 0, p);
		List<ArcGene> extraArcGenes = dom.arcGenes.subList(p, dom.arcGenes.size());
		child.arcGenes.addAll(extraArcGenes);
		
		return child;
	}
	
	private <E> List<E> splice(List<E> dom, List<E> sub, int start, int end) {
		List<E> child = new ArrayList<E>();
		
		int x = Random.randomInteger(start, end);
		int y = Random.randomInteger(start, end);
		
		int p1 = Math.min(x, y);
		int p2 = Math.max(x, y);
		
		List<E> slice1 = dom.subList(start, p1);
		List<E> slice2 = sub.subList(p1, p2);
		List<E> slice3 = dom.subList(p2, end);
		
		child.addAll(slice1);
		child.addAll(slice2);
		child.addAll(slice3);
		
		return child;
	}
	
	public static <E> int pointOfDivergence(List<E> a, List<E> b) {
		int max = Math.min(a.size(), b.size());
		
		for (int i = 0; i < max; i++) {
			if (!a.equals(b)) {
				return i;
			}
		}
		
		return max;
	}
}
