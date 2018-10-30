package edu.packt.neuralnet.learn;

import java.util.ArrayList;

import edu.packt.neuralnet.InputLayer;
import edu.packt.neuralnet.NeuralNet;
import edu.packt.neuralnet.Neuron;

public abstract class Training {
	int c=0;
	private int epochs;//循环数
	private double error;//误差
	private double mse;//mse是真输出减预测输出的开方
	double []qianerror=new double[100];
	public enum TrainingTypesENUM {
		PERCEPTRON, ADALINE, BACKPROPAGATION, KOHONEN;
	}

	public NeuralNet train(NeuralNet n) {
		
		ArrayList<Double> inputWeightIn = new ArrayList<Double>();

		int rows = n.getTrainSet().length;
		int cols = n.getTrainSet()[0].length;
		
		while (this.getEpochs() < n.getMaxEpochs()) {

			double estimatedOutput = 0.0;
			double realOutput = 0.0;
			
			for (int i = 0; i < rows; i++) {

				double netValue = 0.0;

				for (int j = 0; j < cols; j++) {
					inputWeightIn = n.getInputLayer().getListOfNeurons().get(j).getListOfWeightIn();
					double inputWeight = inputWeightIn.get(0);
					netValue = netValue + inputWeight * n.getTrainSet()[i][j];
				}

				estimatedOutput = this.activationFnc(n.getActivationFnc(), netValue);
				realOutput = n.getRealOutputSet()[i];

				this.setError(realOutput - estimatedOutput);//误差等于真输出减预测输出
				for(int j=0;j<i;j++){
								if(j==0)qianerror[j]=0.1;
				else{
					if(qianerror[j]>realOutput-estimatedOutput){
						qianerror[j]=(realOutput-estimatedOutput)*1.05;
					}
					else if(qianerror[j]*1.04<realOutput-estimatedOutput){
						qianerror[j]=(realOutput-estimatedOutput)*0.7;
					}
				}	
				}

				// System.out.println("Epoch: "+this.getEpochs()+" / Error: " + this.getError());

				if (Math.abs(this.getError()) > n.getTargetError()) {
					// fix weights
					InputLayer inputLayer = new InputLayer();
					inputLayer.setListOfNeurons(this.teachNeuronsOfLayer(cols, i, n, netValue));
					n.setInputLayer(inputLayer);
				}

			}

			this.setMse(Math.pow(realOutput - estimatedOutput, 2.0));
			//mse是真输出减预测输出的开方
			n.getListOfMSE().add(this.getMse());

			this.setEpochs(this.getEpochs() + 1);

		}

		n.setTrainingError(this.getError());

		return n;
	}
	
	private ArrayList<Neuron> teachNeuronsOfLayer(int numberOfInputNeurons,
			int line, NeuralNet n, double netValue) {
		ArrayList<Neuron> listOfNeurons = new ArrayList<Neuron>();
		ArrayList<Double> inputWeightsInNew = new ArrayList<Double>();
		ArrayList<Double> inputWeightsInOld = new ArrayList<Double>();

		for (int j = 0; j < numberOfInputNeurons; j++) {
			//得到所有输入神经元
			inputWeightsInOld = n.getInputLayer().getListOfNeurons().get(j)
					.getListOfWeightIn();
			double inputWeightOld = inputWeightsInOld.get(0);

			inputWeightsInNew.add( this.calcNewWeight(n.getTrainType(),
					inputWeightOld, n, this.getError(),
					n.getTrainSet()[line][j], netValue) );

			Neuron neuron = new Neuron();
			neuron.setListOfWeightIn(inputWeightsInNew);//设置权重
			listOfNeurons.add(neuron);
			inputWeightsInNew = new ArrayList<Double>();
		}

		return listOfNeurons;

	}

	private double calcNewWeight(TrainingTypesENUM trainType,
			double inputWeightOld, NeuralNet n, double error,
			double trainSample, double netValue) {
		//计算新权值（训练类型，输入的旧权重，神经网络，误差，训练集，网络值）
		
		switch (trainType) {
		case PERCEPTRON:
			c=c+1;
			return inputWeightOld +  qianerror[c-1]* error * trainSample;
			
			//返回旧权值+学习率*误差*训练集
			//return inputWeightOld +  n.getLearningRate()* error * trainSample;
			
		case ADALINE:
			c=c+1;
			return inputWeightOld + qianerror[c-1] * error * trainSample
					* derivativeActivationFnc(n.getActivationFnc(), netValue);
			//返回旧权重+学习率*误差*训练集*激活函数
			//			return inputWeightOld + n.getLearningRate() * error * trainSample
			//* derivativeActivationFnc(n.getActivationFnc(), netValue);
		case BACKPROPAGATION:
			c=c+1;
			return inputWeightOld + qianerror[c-1] * error * trainSample
					* derivativeActivationFnc(n.getActivationFnc(), netValue);
		default:
			throw new IllegalArgumentException(trainType
					+ " does not exist in TrainingTypesENUM");
		}
	}

	public enum ActivationFncENUM {
		STEP, LINEAR, SIGLOG, HYPERTAN;
		//？，纯线性函数，S函数，双曲正切函数
	}

	protected double activationFnc(ActivationFncENUM fnc, double value) {
		switch (fnc) {
		//激活函数
		case STEP:
			return fncStep(value);
			//	if (v >= 0) {return 1.0;} else {return 0.0;}
		case LINEAR:
			return fncLinear(value);
			//return v;
		case SIGLOG:
			return fncSigLog(value);
			//return (1.0 / (1.0 + Math.exp(-v)));
		case HYPERTAN:
			return fncHyperTan(value);
			//return Math.tanh(v);
		default:
			throw new IllegalArgumentException(fnc
					+ " does not exist in ActivationFncENUM");
		}
	}

	public double derivativeActivationFnc(ActivationFncENUM fnc, double value) {
		switch (fnc) {//导数
		case LINEAR:
			return derivativeFncLinear(value);//return 1.0;
		case SIGLOG:
			return derivativeFncSigLog(value);//return (v * (1.0 - v));
		case HYPERTAN:
			return derivativeFncHyperTan(value);//return (1.0 / Math.pow(Math.cosh(v), 2.0));
		case STEP:
			return derivativeFncStep(value);
		default:
			throw new IllegalArgumentException(fnc
					+ " does not exist in ActivationFncENUM");
		}
	}

	private double fncStep(double v) {
		if (v >= 0) {
			return 1.0;
		} else {
			return 0.0;
		}
	}
	private double fncLinear(double v) {
		return v;
	}
	private double fncSigLog(double v) {
		return (1.0 / (1.0 + Math.exp(-v)));
	}
	private double fncHyperTan(double v) {
		return Math.tanh(v);
	}

	private double derivativeFncLinear(double v) {
		return 1.0;
	}
	private double derivativeFncSigLog(double v) {
		return (v * (1.0 - v)+0.1);
	}
	private double derivativeFncHyperTan(double v) {
		return (1.0 / Math.pow(Math.cosh(v), 2.0));
	}
	public double derivativeFncStep(double v){
		if (v >= 0) {return 1.0;} else {return 0.0;}
	}

	public void printTrainedNetResult(NeuralNet trainedNet) {
		//打印输出网络结果
		int rows = trainedNet.getTrainSet().length;//得到训练集的长度
		int cols = trainedNet.getTrainSet()[0].length;

		ArrayList<Double> inputWeightIn = new ArrayList<Double>();

		for (int i = 0; i < rows; i++) {
			double netValue = 0.0;
			for (int j = 0; j < cols; j++) {
				inputWeightIn = trainedNet.getInputLayer().getListOfNeurons().get(j).getListOfWeightIn();
				//得到输入层的权重
				double inputWeight = inputWeightIn.get(0);
				netValue = netValue + inputWeight * trainedNet.getTrainSet()[i][j];

				System.out.print(trainedNet.getTrainSet()[i][j] + "\t");
			}

			double estimatedOutput = this.activationFnc( trainedNet.getActivationFnc(), netValue );
			
			int colsOutput = trainedNet.getRealMatrixOutputSet()[0].length;
			
			double realOutput = 0.0;
			for (int k = 0; k < colsOutput; k++) {
				realOutput = realOutput + trainedNet.getRealMatrixOutputSet()[i][k];
			}

			System.out.print(" NET OUTPUT: "  + estimatedOutput + "\t");
			System.out.print(" REAL OUTPUT: " + realOutput + "\t");
			double error = estimatedOutput - realOutput;
			System.out.print(" ERROR: " + error + "\n");

		}

	}

	public int getEpochs() {
		return epochs;
	}

	public void setEpochs(int epochs) {
		this.epochs = epochs;
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}

	public double getMse() {
		return mse;
	}

	public void setMse(double mse) {
		this.mse = mse;
	}

}
