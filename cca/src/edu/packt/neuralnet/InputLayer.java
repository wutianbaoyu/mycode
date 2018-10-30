package edu.packt.neuralnet;

import java.util.ArrayList;
import java.util.Arrays;

public class InputLayer extends Layer {

	public InputLayer initLayer(InputLayer inputLayer) {
		//用伪随机数初始化输入层
		ArrayList<Double> listOfWeightInTemp = new ArrayList<Double>();
		ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
		
		for (int i = 0; i < inputLayer.getNumberOfNeuronsInLayer(); i++) {
			Neuron neuron = new Neuron();
			
			listOfWeightInTemp.add( neuron.initNeuron() );
			//listOfWeightInTemp.add( neuron.initNeuron( i ) );

			neuron.setListOfWeightIn( listOfWeightInTemp );
			listOfNeurons.add( neuron );

			listOfWeightInTemp = new ArrayList<Double>();
		}

		inputLayer.setListOfNeurons(listOfNeurons);

		return inputLayer;
	}

	public void printLayer(InputLayer inputLayer){
		//打印层的输入权值
		System.out.println("### INPUT LAYER ###");
		int n = 1;
		for (Neuron neuron : inputLayer.getListOfNeurons()) {
			System.out.println("Neuron #" + n + ":");
			System.out.println("Input Weights:");
			System.out.println(Arrays.deepToString( neuron.getListOfWeightIn().toArray() ));
			n++;
		}
	}
	
	
	public void setNumberOfNeuronsInLayer(int numberOfNeuronsInLayer) {
		this.numberOfNeuronsInLayer = numberOfNeuronsInLayer + 1; //BIAS
	}
	
	
}
