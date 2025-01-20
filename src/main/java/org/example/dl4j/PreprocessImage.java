package org.example.dl4j;

import org.datavec.image.loader.NativeImageLoader;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.preprocessor.ImagePreProcessingScaler;

import java.io.File;
import java.io.IOException;

public class PreprocessImage {

    public static INDArray preprocessImage(String imagePath, int height, int width, int channels) throws IOException {

        File imageFile = new File(imagePath);
        NativeImageLoader loader = new NativeImageLoader(height, width, channels);
        INDArray image = loader.asMatrix(imageFile);

        // Normalize the image
        ImagePreProcessingScaler scaler = new ImagePreProcessingScaler(0, 1);
        scaler.transform(image);

        return image;
    }
}
