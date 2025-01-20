package org.example.visrec;

import java.io.IOException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ml.classification.NeuralNetImageClassifier;
import java.awt.image.BufferedImage;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Training example of a Hot Dog image classifier in Java using JSR381 - VisRec API
 * <p>
 * <a href="https://www.youtube.com/watch?v=vIci3C4JkL0">Silicon Valley TV show Scene</a>
 * <p>
 * <a href="https://jcp.org/en/jsr/detail?id=381">Java Visual Recognition API JSR381</a>
 * <p>
 * <a href="https://github.com/JavaVisRec">The JSR project on GitHub</a>
 */
public class HotDogOrNotVisRec {

    public static void main(String[] args) {
        train();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HotDogOrNotVisRec.class);

    public static void train() {

        try {

            ImageClassifier<BufferedImage> classifier =
                    NeuralNetImageClassifier.builder()
                            .inputClass(BufferedImage.class) // input class for classifier
                            .imageWidth(128) // width of the input image | was 64
                            .imageHeight(128) // height of the input image | was 64
                            .labelsFile(Paths.get("src/main/resources/visrec/labels.txt"))// list of image labels
                            .trainingFile(Paths.get("src/main/resources/dataset/index.txt")) // index of images with corresponding labels
                            .networkArchitecture(Paths.get(
                                    "src/main/resources/visrec/hotdog.json"))// architecture of the convolutional neural network in json
                            .maxError(0.03f) // error level to stop the training (maximum acceptable error)
                            .maxEpochs(1000) // maximum number of training iterations (epochs)
                            .learningRate(0.01f)// amount of error to use for adjusting internal parameters in each training iteration
                            .exportModel(Paths.get("hotdog.dnet")) // name of the file to save trained model
                            .build();

//            evaluateModel(classifier);

        } catch (Exception exception) { // if something goes wrong, an exception is thrown
            LOGGER.error("Model creation failed! " + exception.getMessage());
        }
    }

//    public static void evaluateModel(ImageClassifier<BufferedImage> classifier) {
//
//        try {
//            LOGGER.info("Evaluating the model...");
//
//            EvaluationMetrics metrics = classifier.evaluate(Paths.get("src/main/resources/dataset/test_index.txt"));
//
//            LOGGER.info("Model Evaluation:");
//            LOGGER.info("Accuracy: " + metrics.getAccuracy());
//            LOGGER.info("Precision: " + metrics.getPrecision());
//            LOGGER.info("Recall: " + metrics.getRecall());
//            LOGGER.info("F1 Score: " + metrics.getF1Score());
//
//        } catch (IOException e) {
//            LOGGER.error("Model evaluation failed! IOException: " + e.getMessage());
//        } catch (Exception e) {
//            LOGGER.error("Model evaluation failed! Exception: " + e.getMessage());
//        }
//    }
}
