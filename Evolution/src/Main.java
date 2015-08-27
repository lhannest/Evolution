import neuralnetwork.HiddenNode;
import neuralnetwork.InputNode;
import neuralnetwork.NeuralNetwork;
import neuralnetwork.Node;
import neuralnetwork.OutputNode;
import neuralnetwork.Topology;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import specialneuralnetwork.SpecialNode;

public class Main{
	public static void main(String[] args) {
		Topology top = new Topology();
		InputNode input = top.addInput(new InputNode());
		HiddenNode hidden = top.addHidden(new SpecialNode());
		OutputNode output = top.addOutput(new OutputNode());
		top.addArc(input, hidden);
		top.addArc(hidden, output);
		
		HiddenNode a = new SpecialNode();
		
		a.copy();
		
		NeuralNetwork nn = new NeuralNetwork(top);
		
		
		
	}
	
	public static Node makeNode() {
		return new InputNode();
	}

}
