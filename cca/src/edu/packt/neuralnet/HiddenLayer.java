package edu.packt.neuralnet;

import java.util.ArrayList;
import java.util.Arrays;

public class HiddenLayer extends Layer {//隐藏层

	public ArrayList<HiddenLayer> initLayer(HiddenLayer hiddenLayer,
			ArrayList<HiddenLayer> listOfHiddenLayer, InputLayer inputLayer,
			OutputLayer outputLayer) {//初始化
		//隐藏层，隐藏层列表，输入层，输出层
		//用伪随机实数初始化隐藏层
		ArrayList<Double> listOfWeightIn = new ArrayList<Double>();
		//输入层的权重
		ArrayList<Double> listOfWeightOut = new ArrayList<Double>();
		//输出层的权重
		ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
		//神经元
		int numberOfHiddenLayers = listOfHiddenLayer.size();
		//得到隐藏层的数量
		for (int hdn_i = 0; hdn_i < numberOfHiddenLayers; hdn_i++) {
			for (int neuron_i = 0; neuron_i < hiddenLayer.getNumberOfNeuronsInLayer(); neuron_i++) {
				Neuron neuron = new Neuron();
				//得到神经元
				int limitIn  = 0;
				int limitOut = 0;

				if (hdn_i == 0) { // first
					limitIn = inputLayer.getNumberOfNeuronsInLayer();
					//得到输入层的神经元数量
					if (numberOfHiddenLayers > 1) {//如果输入层的神经元数量大于1
						limitOut = listOfHiddenLayer.get(hdn_i + 1).getNumberOfNeuronsInLayer();
						//得到第hdn_i+1（即1）层隐藏层的的神经元数量
						//hdn_i就是隐层的层数，这里是为了得到隐藏层的神经元数量，从第一层开始
					} else if(numberOfHiddenLayers == 1){//如果输入层的神经元数量等于1
						limitOut = outputLayer.getNumberOfNeuronsInLayer();
						//得到输出层的神经元数量
						//隐层只有一层的情况下，隐层输出的数量就是输出层的神经元数量
					}
				} else if (hdn_i == numberOfHiddenLayers - 1) { // last//最后的层的神经元数量
					limitIn = listOfHiddenLayer.get(hdn_i - 1).getNumberOfNeuronsInLayer();
					limitOut = outputLayer.getNumberOfNeuronsInLayer();
				} else { // middle//中间的隐藏层的神经元数量
					limitIn = listOfHiddenLayer.get(hdn_i - 1).getNumberOfNeuronsInLayer();
					limitOut = listOfHiddenLayer.get(hdn_i + 1).getNumberOfNeuronsInLayer();
				}
				
				limitIn  = limitIn  - 1;  //bias is not connected//不计算偏置神经元
				limitOut = limitOut - 1;  //bias is not connected//不计算偏置神经元

				if (neuron_i >= 1) { //bias has no input//偏置神经元不计入输入
					for (int k = 0; k <= limitIn; k++) {
						listOfWeightIn.add(neuron.initNeuron());
						//initNeuron()方法这里是随机double权值
						//listOfWeightIn.add(neuron.initNeuron(k, neuron_i, 1));
						//这个方法没定义在初始代码
					}
				}
				for (int k = 0; k <= limitOut; k++) {
					listOfWeightOut.add(neuron.initNeuron());
					//initNeuron()方法这里是随机double权值
					//listOfWeightOut.add(neuron.initNeuron(k, neuron_i, 2));
					//这个方法没定义在初始代码
				}

				neuron.setListOfWeightIn(listOfWeightIn);//设置输入层的权重
				neuron.setListOfWeightOut(listOfWeightOut);//设置输出层的权重
				listOfNeurons.add(neuron);

				listOfWeightIn = new ArrayList<Double>();
				listOfWeightOut = new ArrayList<Double>();

			}

			listOfHiddenLayer.get(hdn_i).setListOfNeurons(listOfNeurons);

			listOfNeurons = new ArrayList<Neuron>();

		}

		return listOfHiddenLayer;

	}

	public void printLayer(ArrayList<HiddenLayer> listOfHiddenLayer) {
		//在控制台上输出隐藏层，神经元，输入层权值，输出层权值
		if (listOfHiddenLayer.size() > 0) {
			System.out.println("### HIDDEN LAYER ###");
			int h = 1;
			for (HiddenLayer hiddenLayer : listOfHiddenLayer) {
				System.out.println("Hidden Layer #" + h);
				int n = 1;
				for (Neuron neuron : hiddenLayer.getListOfNeurons()) {
					System.out.println("Neuron #" + n);
					System.out.println("Input Weights:");
					System.out.println(Arrays.deepToString(neuron
							.getListOfWeightIn().toArray()));
					System.out.println("Output Weights:");
					System.out.println(Arrays.deepToString(neuron
							.getListOfWeightOut().toArray()));
					n++;
				}
				h++;
			}
		}
	}
	
	public void setNumberOfNeuronsInLayer(int numberOfNeuronsInLayer) {
		this.numberOfNeuronsInLayer = numberOfNeuronsInLayer + 1; //BIAS
	}
	
}
