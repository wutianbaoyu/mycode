package edu.packt.neuralnet;

import java.util.ArrayList;
import java.util.Arrays;

public class HiddenLayer extends Layer {//���ز�

	public ArrayList<HiddenLayer> initLayer(HiddenLayer hiddenLayer,
			ArrayList<HiddenLayer> listOfHiddenLayer, InputLayer inputLayer,
			OutputLayer outputLayer) {//��ʼ��
		//���ز㣬���ز��б�����㣬�����
		//��α���ʵ����ʼ�����ز�
		ArrayList<Double> listOfWeightIn = new ArrayList<Double>();
		//������Ȩ��
		ArrayList<Double> listOfWeightOut = new ArrayList<Double>();
		//������Ȩ��
		ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
		//��Ԫ
		int numberOfHiddenLayers = listOfHiddenLayer.size();
		//�õ����ز������
		for (int hdn_i = 0; hdn_i < numberOfHiddenLayers; hdn_i++) {
			for (int neuron_i = 0; neuron_i < hiddenLayer.getNumberOfNeuronsInLayer(); neuron_i++) {
				Neuron neuron = new Neuron();
				//�õ���Ԫ
				int limitIn  = 0;
				int limitOut = 0;

				if (hdn_i == 0) { // first
					limitIn = inputLayer.getNumberOfNeuronsInLayer();
					//�õ���������Ԫ����
					if (numberOfHiddenLayers > 1) {//�����������Ԫ��������1
						limitOut = listOfHiddenLayer.get(hdn_i + 1).getNumberOfNeuronsInLayer();
						//�õ���hdn_i+1����1�������ز�ĵ���Ԫ����
						//hdn_i��������Ĳ�����������Ϊ�˵õ����ز����Ԫ�������ӵ�һ�㿪ʼ
					} else if(numberOfHiddenLayers == 1){//�����������Ԫ��������1
						limitOut = outputLayer.getNumberOfNeuronsInLayer();
						//�õ���������Ԫ����
						//����ֻ��һ�������£��������������������������Ԫ����
					}
				} else if (hdn_i == numberOfHiddenLayers - 1) { // last//���Ĳ����Ԫ����
					limitIn = listOfHiddenLayer.get(hdn_i - 1).getNumberOfNeuronsInLayer();
					limitOut = outputLayer.getNumberOfNeuronsInLayer();
				} else { // middle//�м�����ز����Ԫ����
					limitIn = listOfHiddenLayer.get(hdn_i - 1).getNumberOfNeuronsInLayer();
					limitOut = listOfHiddenLayer.get(hdn_i + 1).getNumberOfNeuronsInLayer();
				}
				
				limitIn  = limitIn  - 1;  //bias is not connected//������ƫ����Ԫ
				limitOut = limitOut - 1;  //bias is not connected//������ƫ����Ԫ

				if (neuron_i >= 1) { //bias has no input//ƫ����Ԫ����������
					for (int k = 0; k <= limitIn; k++) {
						listOfWeightIn.add(neuron.initNeuron());
						//initNeuron()�������������doubleȨֵ
						//listOfWeightIn.add(neuron.initNeuron(k, neuron_i, 1));
						//�������û�����ڳ�ʼ����
					}
				}
				for (int k = 0; k <= limitOut; k++) {
					listOfWeightOut.add(neuron.initNeuron());
					//initNeuron()�������������doubleȨֵ
					//listOfWeightOut.add(neuron.initNeuron(k, neuron_i, 2));
					//�������û�����ڳ�ʼ����
				}

				neuron.setListOfWeightIn(listOfWeightIn);//����������Ȩ��
				neuron.setListOfWeightOut(listOfWeightOut);//����������Ȩ��
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
		//�ڿ���̨��������ز㣬��Ԫ�������Ȩֵ�������Ȩֵ
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
