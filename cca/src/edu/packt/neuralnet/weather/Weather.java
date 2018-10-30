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
		//�ĸ�������Դ
		NormalizationTypesENUM NORMALIZATION_TYPE = Data.NormalizationTypesENUM.MAX_MIN_EQUALIZED;
		
		try {
			double[][] matrixInput   = weatherDataInput.rawData2Matrix( weatherDataInput );
			double[][] matrixOutput  = weatherDataOutput.rawData2Matrix( weatherDataOutput );
			
			double[][] matrixInputTestRNA  = weatherDataOutput.rawData2Matrix( weatherDataInputTestRNA );
			double[][] matrixOutputTestRNA = weatherDataOutput.rawData2Matrix( weatherDataOutputTestRNA );
			//���ĸ�����Դ�е����ݻ�Ϊ����
			double[][] matrixInputNorm  = weatherDataInput.normalize(matrixInput, NORMALIZATION_TYPE);
			double[][] matrixOutputNorm = weatherDataOutput.normalize(matrixOutput, NORMALIZATION_TYPE);
			
			double[][] matrixInputTestRNANorm = weatherDataOutput.normalize(matrixInputTestRNA, NORMALIZATION_TYPE);
			double[][] matrixOutputTestRNANorm = weatherDataOutput.normalize(matrixOutputTestRNA, NORMALIZATION_TYPE);
			
			NeuralNet n1 = new NeuralNet();
			n1 = n1.initNet(6, 2, 7, 1);//��α���ʵ����ʼ��listofweightIn��listOfWeightOut
			//��������Ԫ�������� int numberOfHiddenLayers����������
			//, int numberOfNeuronsInHiddenLayer(��������Ԫ������), int numberOfOutputNeurons����������Ԫ��������
			n1.setTrainSet( matrixInputNorm );
			//����ѵ����Ϊ���������
			n1.setRealMatrixOutputSet( matrixOutputNorm );
			//����ѵ����Ϊ��ʵ��������ݣ�����Ϊ�е�ʦѵ��
			n1.setMaxEpochs(5000);
			//�������ѭ����
			n1.setTargetError(0.1);
			//����Ŀ�����
			n1.setLearningRate(0.1);
			//����ѧϰ�ʣ�ѧϰ�ʹ��󣬲��������׹���
			n1.setTrainType(TrainingTypesENUM.BACKPROPAGATION);
			//����ѧϰ�㷨ΪBP�㷨
			n1.setActivationFnc(ActivationFncENUM.SIGLOG/*SIGLOG*/);
			//�����
			//SIGLOG S����
			n1.setActivationFncOutputLayer(ActivationFncENUM.SIGLOG);
			//ActivationFncENUM.LINEAR
			NeuralNet n1Trained = new NeuralNet();
			
			n1Trained = n1.trainNet(n1);
			
			System.out.println();
			n1Trained.printNet(n1Trained);
			//ERROR:
			Chart c1 = new Chart();//ͼ�������ͼ��Ϊ�����ѭ�������ı仯
			c1.plotXYData(n1.getListOfMSE().toArray(), "MSE Error", "Epochs", "MSE Value");
			//ͼ������Ĳ�������
			//TRAINING:
//			double[][] matrixOutputRNA  	  = n1Trained.getNetOutputValues(n1Trained);
//			double[][] matrixOutputRNADenorm  = new Data().denormalize(matrixOutput, matrixOutputRNA, NORMALIZATION_TYPE);
//			
//			ArrayList<double[][]> listOfArraysToJoin = new ArrayList<double[][]>();
//			listOfArraysToJoin.add( matrixOutput );//��ѵ����ѧϰ�����
//			listOfArraysToJoin.add( matrixOutputRNADenorm );
//			
//			double[][] matrixOutputsJoined = new Data().joinArrays(listOfArraysToJoin);
//			
//			Chart c2 = new Chart();//�ڶ���ͼ��Ϊѧϰ�������ʵ������ĶԱȣ�xΪ�ڼ�������
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
			Chart c3 = new Chart();//������ͼ��Ϊ����������ʵ������ĶԱȣ�xΪ�ڼ�������
			c3.plotXYData(matrixOutputsJoinedTest, "Real x Estimated - Test Data", "Weather Data", "Temperature (Celsius)", Chart.ChartPlotTypeENUM.COMPARISON);
			//
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void learningrate(){
		
	}
	
}
