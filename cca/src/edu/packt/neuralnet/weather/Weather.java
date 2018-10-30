package edu.packt.neuralnet.weather;

import java.io.IOException;
import java.util.ArrayList;

import edu.packt.neuralnet.NeuralNet;
import edu.packt.neuralnet.learn.Training.ActivationFncENUM;
import edu.packt.neuralnet.learn.Training.TrainingTypesENUM;
import edu.packt.neuralnet.util.Chart;
import edu.packt.neuralnet.util.Data;
import edu.packt.neuralnet.util.Data.NormalizationTypesENUM;

public class Weather {

	public static void main(String args[]){
		
		Data weatherDataInput  = new Data("data", "inmet_13_14_input.csv");
		Data weatherDataOutput = new Data("data", "inmet_13_14_output.csv");
		
		Data weatherDataInputTestRNA  = new Data("data", "inmet_13_14_input_test.csv");
		Data weatherDataOutputTestRNA = new Data("data", "inmet_13_14_output_test.csv");
		//四个数据来源
		NormalizationTypesENUM NORMALIZATION_TYPE = Data.NormalizationTypesENUM.MAX_MIN_EQUALIZED;
		
		try {
			double[][] matrixInput   = weatherDataInput.rawData2Matrix( weatherDataInput );
			double[][] matrixOutput  = weatherDataOutput.rawData2Matrix( weatherDataOutput );
			
			double[][] matrixInputTestRNA  = weatherDataOutput.rawData2Matrix( weatherDataInputTestRNA );
			double[][] matrixOutputTestRNA = weatherDataOutput.rawData2Matrix( weatherDataOutputTestRNA );
			//将四个数据源中的数据化为数组
			double[][] matrixInputNorm  = weatherDataInput.normalize(matrixInput, NORMALIZATION_TYPE);
			double[][] matrixOutputNorm = weatherDataOutput.normalize(matrixOutput, NORMALIZATION_TYPE);
			
			double[][] matrixInputTestRNANorm = weatherDataOutput.normalize(matrixInputTestRNA, NORMALIZATION_TYPE);
			double[][] matrixOutputTestRNANorm = weatherDataOutput.normalize(matrixOutputTestRNA, NORMALIZATION_TYPE);
			
			NeuralNet n1 = new NeuralNet();
			n1 = n1.initNet(6, 2, 7, 1);//用伪随机实数初始化listofweightIn和listOfWeightOut
			//（输入神经元的数量， int numberOfHiddenLayers（隐层数）
			//, int numberOfNeuronsInHiddenLayer(隐层中神经元的数量), int numberOfOutputNeurons（输出层的神经元数量））
			n1.setTrainSet( matrixInputNorm );
			//设置训练集为输入的数据
			n1.setRealMatrixOutputSet( matrixOutputNorm );
			//设置训练集为真实输出的数据，这里为有导师训练
			n1.setMaxEpochs(5000);
			//设置最大循环数
			n1.setTargetError(0.1);
			//设置目标误差
			n1.setLearningRate(0.1);
			//设置学习率，学习率过大，步长就容易过大
			n1.setTrainType(TrainingTypesENUM.BACKPROPAGATION);
			//设置学习算法为BP算法
			n1.setActivationFnc(ActivationFncENUM.SIGLOG/*SIGLOG*/);
			//激活函数
			//SIGLOG S函数
			n1.setActivationFncOutputLayer(ActivationFncENUM.SIGLOG);
			//ActivationFncENUM.LINEAR
			NeuralNet n1Trained = new NeuralNet();
			
			n1Trained = n1.trainNet(n1);
			
			System.out.println();
			n1Trained.printNet(n1Trained);
			//ERROR:
			Chart c1 = new Chart();//图表输出，图表为误差随循环次数的变化
			c1.plotXYData(n1.getListOfMSE().toArray(), "MSE Error", "Epochs", "MSE Value");
			//图表输出的参数设置
			//TRAINING:
//			double[][] matrixOutputRNA  	  = n1Trained.getNetOutputValues(n1Trained);
//			double[][] matrixOutputRNADenorm  = new Data().denormalize(matrixOutput, matrixOutputRNA, NORMALIZATION_TYPE);
//			
//			ArrayList<double[][]> listOfArraysToJoin = new ArrayList<double[][]>();
//			listOfArraysToJoin.add( matrixOutput );//用训练的学习集输出
//			listOfArraysToJoin.add( matrixOutputRNADenorm );
//			
//			double[][] matrixOutputsJoined = new Data().joinArrays(listOfArraysToJoin);
//			
//			Chart c2 = new Chart();//第二个图表，为学习集输入和实际输出的对比，x为第几个数据
//			c2.plotXYData(matrixOutputsJoined, "Real x Estimated - Training Data", "Weather Data", "Temperature (Celsius)", Chart.ChartPlotTypeENUM.COMPARISON);
//			n1Trained.printNet(n1Trained);
			//TEST:
			n1Trained.setTrainSet( matrixInputTestRNANorm );
			n1Trained.setRealMatrixOutputSet( matrixOutputTestRNANorm );;
			
			double[][] matrixOutputRNATest  	  = n1Trained.getNetOutputValues(n1Trained);
			double[][] matrixOutputRNADenormTest  = new Data().denormalize(matrixOutputTestRNA, matrixOutputRNATest, NORMALIZATION_TYPE);
			
			ArrayList<double[][]> listOfArraysToJoinTest = new ArrayList<double[][]>();
			listOfArraysToJoinTest.add( matrixOutputTestRNA );
			listOfArraysToJoinTest.add( matrixOutputRNADenormTest );
			
			double[][] matrixOutputsJoinedTest = new Data().joinArrays(listOfArraysToJoinTest);
			n1Trained.printNet(n1Trained);
			Chart c3 = new Chart();//第三个图表，为测试输入与实际输出的对比，x为第几个数据
			c3.plotXYData(matrixOutputsJoinedTest, "Real x Estimated - Test Data", "Weather Data", "Temperature (Celsius)", Chart.ChartPlotTypeENUM.COMPARISON);
			//
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void learningrate(){
		
	}
	
}
