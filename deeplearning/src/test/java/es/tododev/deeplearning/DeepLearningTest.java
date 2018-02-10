package es.tododev.deeplearning;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.junit.Test;

public class DeepLearningTest {

	@Test
	public void createModel() {
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().iterations(1).weightInit(WeightInit.XAVIER)
				.activation("relu").optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.learningRate(0.05)
				// ... other hyperparameters
				.list().backprop(true).layer(0, new DenseLayer.Builder().nIn(784).nOut(250).build()).build();
	}

}
