package org.example.dl4j;

import java.io.IOException;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dl4jRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dl4jRunner.class);

    public static void main(String[] args) throws IOException {

        String modelPath = "modelshotdog_10_epoch.bin";
        String imagePath = "src/main/resources/visrec/1.jpg";

//        Confidence: 0.5437511801719666 pizza e 1
//        Confidence: 0.5400965809822083 hotdog e 1
//        final int HEIGHT = 128; e 1
//        final int WIDTH = 128; e 1

//        Confidence: 0.5241222381591797 hotdog e 10
//        Confidence: 0.5530278086662292 pizza e 10
//        final int HEIGHT = 299; e 10
//        final int WIDTH = 299; e 10

        // Load the model
        MultiLayerNetwork model = LoadModel.loadModel(modelPath);

        // Preprocess the image
        final int HEIGHT = 299;
        final int WIDTH = 299;
        final int CHANNELS = 3;

        INDArray image = PreprocessImage.preprocessImage(imagePath, HEIGHT, WIDTH, CHANNELS);

        // Perform inference
        INDArray output = model.output(image);

        // Print the output probabilities
        System.out.println("Class probabilities: " + output);

        // Get the predicted class
        int predictedClass = output.argMax(1).getInt(0);
        double confidence = output.getDouble(predictedClass);

        LOGGER.info("Predicted class: {}", predictedClass);
        LOGGER.info("Confidence: {}", confidence);

        if (confidence > 0.5) {
            LOGGER.info("There is a high probability that this is a hot dog");
        } else {
            LOGGER.info("Most likely this is not a hot dog");
        }

    }

}
