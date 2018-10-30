package edu.packt.neuralnet.learn;

import java.util.ArrayList;

import edu.packt.neuralnet.HiddenLayer;
import edu.packt.neuralnet.NeuralNet;
import edu.packt.neuralnet.Neuron;
import edu.packt.neuralnet.learn.Training;
public class Backpropagation extends Training {

	int epoch = 0;
	
	public NeuralNet train(NeuralNet n) {
		
		setMse(1.0);
		//����mse
		while(getMse() > n.getTargetError()) {
			//���mse����Ŀ��Ĵ���
			if ( epoch >= n.getMaxEpochs() ) break;
			//���ѭ�������������µ�ѭ�������㲻��ѭ��
			int rows = n.getTrainSet().length;
			//rowsΪѧϰ��������
			double sumErrors = 0.0;
			
			for (int rows_i = 0; rows_i < rows; rows_i++) {
				
				n = forward(n, rows_i);
				//n���������
				n = backpropagation(n, rows_i);
				
				sumErrors = sumErrors + n.getErrorMean();
				
			}
			
			setMse( sumErrors / rows );
			//mseΪ�ܴ����������
			n.getListOfMSE().add( getMse() );
			
			//System.out.println( getMse() );
			
			epoch++;
			
		}
		
		System.out.println( getMse() );
		System.out.println("Number of epochs: "+epoch);
		
		return n;
		
	}

	public NeuralNet forward(NeuralNet n, int row) {
		
		ArrayList<HiddenLayer> listOfHiddenLayer = new ArrayList<HiddenLayer>();
		//�½�һ�����ز�
		listOfHiddenLayer = n.getListOfHiddenLayer();
		//����������е����ز�
		double estimatedOutput = 0.0;
		double realOutput = 0.0;//���������
		double sumError = 0.0; //�ܴ���
		
		if (listOfHiddenLayer.size() > 0) {
			
			int hiddenLayer_i = 0;
			
			for (HiddenLayer hiddenLayer : listOfHiddenLayer) {
				//���for�õ�����hidden layer��������ΪlistOfHiddenLayer
				int numberOfNeuronsInLayer = hiddenLayer.getNumberOfNeuronsInLayer();
				//��Ԫ�����ز����Ŀ
				for (Neuron neuron : hiddenLayer.getListOfNeurons()) {
					//������е���Ԫ��������ΪhiddenLayer.getListOfNeurons()
					double netValueOut = 0.0;
					
					if(neuron.getListOfWeightIn().size() > 0) { //exclude bias ����ƫ��ֵ
						//����ԪȨֵ����0
						double netValue = 0.0;
						
						for (int layer_j = 0; layer_j < numberOfNeuronsInLayer - 1/*7*/; layer_j++) { //exclude bias
							double hiddenWeightIn = neuron.getListOfWeightIn().get(layer_j);
							//��ȡ��ԪȨֵ
							netValue = netValue + hiddenWeightIn * n.getTrainSet()[row][layer_j];
							//����ԱȨֵ��ѧϰ�������˺�����ӡ�
						}
						
						//output hidden layer (1)
						netValueOut = super.activationFnc(n.getActivationFnc(), netValue);
						neuron.setOutputValue( netValueOut );
					} else {
						neuron.setOutputValue( 1.0 );
					}
					
				}
				
				
				//output hidden layer (2)
				double netValue = 0.0;
				double netValueOut = 0.0;
				for (int outLayer_i = 0; outLayer_i < n.getOutputLayer().getNumberOfNeuronsInLayer(); outLayer_i++){
					
					for (Neuron neuron : hiddenLayer.getListOfNeurons()) {
						//�õ���Ԫ������ΪhiddenLayer.getListOfNeurons()
						double hiddenWeightOut = neuron.getListOfWeightOut().get(outLayer_i);
						netValue = netValue + hiddenWeightOut * neuron.getOutputValue();
					}
					
					netValueOut = activationFnc(n.getActivationFncOutputLayer(), netValue);
					
					n.getOutputLayer().getListOfNeurons().get(outLayer_i).setOutputValue( netValueOut );
					
					//error
					estimatedOutput = netValueOut;
					realOutput = n.getRealMatrixOutputSet()[row][outLayer_i];
					double error = realOutput - estimatedOutput;
					n.getOutputLayer().getListOfNeurons().get(outLayer_i).setError(error);
					sumError = sumError + Math.pow(error, 2.0);
					
					/*
					if ( epoch == n.getMaxEpochs()-1 ) {
						System.out.println("netValueOut: " + netValueOut);
					}
					*/
					
				}
				
				
				
				//error mean
				double errorMean = sumError / n.getOutputLayer().getNumberOfNeuronsInLayer();
				n.setErrorMean(errorMean);
				
				n.getListOfHiddenLayer().get(hiddenLayer_i).setListOfNeurons( hiddenLayer.getListOfNeurons() );
			
				hiddenLayer_i++;
				
			}
			
		}

		return n;
		
	}

	private NeuralNet backpropagation(NeuralNet n, int row) {

		ArrayList<Neuron> outputLayer = new ArrayList<Neuron>();
		outputLayer = n.getOutputLayer().getListOfNeurons();
		
		ArrayList<Neuron> hiddenLayer = new ArrayList<Neuron>();
		hiddenLayer = n.getListOfHiddenLayer().get(0).getListOfNeurons();
		
		double error = 0.0;
		double netValue = 0.0;
		double sensibility = 0.0;
		
		//sensibility output layer
		for (Neuron neuron : outputLayer) {
			//�õ�����outputlayer����Ԫ
			error = neuron.getError();
			netValue = neuron.getOutputValue();
			sensibility = derivativeActivationFnc(n.getActivationFncOutputLayer(), netValue) * error;
			//derivative ������ Activation ���� n. 
			neuron.setSensibility(sensibility);
		}
		
		n.getOutputLayer().setListOfNeurons(outputLayer);
		
		
		//sensibility hidden layer
		for (Neuron neuron : hiddenLayer) {
			
			sensibility = 0.0;
			//sensibility ��У������� �о� ʶ������������
			if(neuron.getListOfWeightIn().size() > 0) { //exclude bias ����ƫ��
				ArrayList<Double> listOfWeightsOut = new ArrayList<Double>();
				
				listOfWeightsOut = neuron.getListOfWeightOut();
				
				double tempSensibility = 0.0;
				//temp �¶�
				int weight_i = 0;
				for (Double weight : listOfWeightsOut) {
					//�õ�Ȩֵ�ļ���
					tempSensibility = tempSensibility + (weight * outputLayer.get(weight_i).getSensibility());

						
				}
				
				sensibility = derivativeActivationFnc(n.getActivationFnc(), neuron.getOutputValue()) * tempSensibility;
				
				neuron.setSensibility(sensibility);
				
			}
			
		}
		
		//fix weights (teach) [output layer to hidden layer]
		for (int outLayer_i = 0; outLayer_i < n.getOutputLayer().getNumberOfNeuronsInLayer(); outLayer_i++) {
			
			for (int i=0;i<hiddenLayer.size();i++) {
				Neuron neuron =hiddenLayer.get(i);
				double newWeight = neuron.getListOfWeightOut().get( outLayer_i ) + 
								( qianerror[i] * 
								  outputLayer.get( outLayer_i ).getSensibility() * 
								  neuron.getOutputValue() );
				
				neuron.getListOfWeightOut().set(outLayer_i, newWeight);
			}
			
		}
		
		//fix weights (teach) [hidden layer to input layer]
		for (Neuron neuron : hiddenLayer) {
			
			ArrayList<Double> hiddenLayerInputWeights = new ArrayList<Double>();
			hiddenLayerInputWeights = neuron.getListOfWeightIn();
			
			if(hiddenLayerInputWeights.size() > 0) { //exclude bias
				int bba=0;
				int hidden_i = 0;
				double newWeight = 0.0;
				for (int i = 0; i < n.getInputLayer().getNumberOfNeuronsInLayer(); i++) {
					
					newWeight = hiddenLayerInputWeights.get(hidden_i) +
							( qianerror[i] *
							  neuron.getSensibility() * 
							  n.getTrainSet()[row][i]); 
					
					neuron.getListOfWeightIn().set(hidden_i, newWeight);
					
					hidden_i++;
				}
				
			}
			
		}
		
		n.getListOfHiddenLayer().get(0).setListOfNeurons(hiddenLayer);

		return n;
		
	}

	

}
