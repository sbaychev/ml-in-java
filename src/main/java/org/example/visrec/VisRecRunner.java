package org.example.visrec;

import deepnetts.net.ConvolutionalNetwork;
import deepnetts.util.FileIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ri.ml.classification.ImageClassifierNetwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisRecRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisRecRunner.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//     HotDogOrNotVisRec.train();

        // load a trained model/neural network
//        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("trained_hotdog.dnet", ConvolutionalNetwork.class);
//        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("original_hotdog.dnet", ConvolutionalNetwork.class);
//        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("net_hotdog.dnet", ConvolutionalNetwork.class);
//        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("latest_train_hotdog.dnet", ConvolutionalNetwork.class);
//        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("train_train_train_hotdog.dnet", ConvolutionalNetwork.class);
        ConvolutionalNetwork convolutionalNetwork = FileIO.createFromFile("original_hotdog.dnet", ConvolutionalNetwork.class); //        latest_train_hotdog

        // create an image classifier using trained model
        ImageClassifier<BufferedImage> imageClassifier = new ImageClassifierNetwork(convolutionalNetwork);

        // load image to classify
        BufferedImage image = ImageIO.read(new File("src/main/resources/visrec/hotdog.jpg"));

        // feed image into a classifier to recognize it
        Map<String, Float> results = imageClassifier.classify(image);

        // interpret the classification result / class probability
        float hotDogProbability = results.get("hotdog");
//        float hotDogProbability = results.get("hot_dog"); //net_hotdog

        LOGGER.info("Class probabilities: %s".formatted(results));
        LOGGER.info("Hot dog probability: %s".formatted(hotDogProbability));

        if (hotDogProbability > 0.5) {
            LOGGER.info("There is a high probability that this is a hot dog");
        } else {
            LOGGER.info("Most likely this is not a hot dog");
        }
    }
}
