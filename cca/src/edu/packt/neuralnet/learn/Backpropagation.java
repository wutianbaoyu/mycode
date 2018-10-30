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
		//设置mse
		while(getMse() > n.getTargetError()) {
			//如果mse大于目标的错误
			if ( epoch >= n.getMaxEpochs() ) break;
			//如果循环数大于所定下的循环数，便不再循环
			int rows = n.getTrainSet().length;
			//rows为学习集的数量
			double sumErrors = 0.0;
			
			for (int rows_i = 0; rows_i < rows; rows_i++) {
				
				n = forward(n, rows_i);
				//n是神经网络的
				n = backpropagation(n, rows_i);
				
				sumErrors = sumErrors + n.getErrorMean();
				
			}
			
			setMse( sumErrors / rows );
			//mse为总错误除以行数
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
		//新建一个隐藏层
		listOfHiddenLayer = n.getListOfHiddenLayer();
		//获得神经网络中的隐藏层
		double estimatedOutput = 0.0;
		double realOutput = 0.0;//真正的输出
		double sumError = 0.0; //总错误
		
		if (listOfHiddenLayer.size() > 0) {
			
			int hiddenLayer_i = 0;
			
			for (HiddenLayer hiddenLayer : listOfHiddenLayer) {
				//这个for得到所有hidden layer对象，数量为listOfHiddenLayer
				int numberOfNeuronsInLayer = hiddenLayer.getNumberOfNeuronsInLayer();
				//神经元在隐藏层的数目
				for (Neuron neuron : hiddenLayer.getListOfNeurons()) {
					//获得所有的神经元对象，数量为hiddenLayer.getListOfNeurons()
					double netValueOut = 0.0;
					
					if(neuron.getListOfWeightIn().size() > 0) { //exclude bias 包括偏差值
						//该神经元权值大于0
						double netValue = 0.0;
						
						for (int layer_j = 0; layer_j < numberOfNeuronsInLayer - 1/*7*/; layer_j++) { //exclude bias
							double hiddenWeightIn = neuron.getListOfWeightIn().get(layer_j);
							//获取神经元权值
							netValue = netValue + hiddenWeightIn * n.getTrainSet()[row][layer_j];
							//把神经员权值和学习后的数相乘后，再相加。
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
						//得到神经元，数量为hiddenLayer.getListOfNeurons()
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
			//得到所有outputlayer的神经元
			error = neuron.getError();
			netValue = neuron.getOutputValue();
			sensibility = derivativeActivationFnc(n.getActivationFncOutputLayer(), netValue) * error;
			//derivative 派生的 Activation 激活 n. 
			neuron.setSensibility(sensibility);
		}
		
		n.getOutputLayer().setListOfNeurons(outputLayer);
		
		
		//sensibility hidden layer
		for (Neuron neuron : hiddenLayer) {
			
			sensibility = 0.0;
			//sensibility 情感；敏感性 感觉 识别力，灵敏器
			if(neuron.getListOfWeightIn().size() > 0) { //exclude bias 包括偏置
				ArrayList<Double> listOfWeightsOut = new ArrayList<Double>();
				
				listOfWeightsOut = neuron.getListOfWeightOut();
				
				double tempSensibility = 0.0;
				//temp 温度
				int weight_i = 0;
				for (Double weight : listOfWeightsOut) {
					//得到权值的集合
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
