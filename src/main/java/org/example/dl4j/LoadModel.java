package org.example.dl4j;

import java.io.File;
import java.io.IOException;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;

public class LoadModel {

    public static MultiLayerNetwork loadModel(String modelPath) throws IOException {
        File modelFile = new File(modelPath);
        return ModelSerializer.restoreMultiLayerNetwork(modelFile);
    }
}
