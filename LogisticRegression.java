//Marianthi Mindrinou 3150110
//Michalis Roussos 3150148
//Christos Tasiopoulos 3150170

import java.io.*;
import java.util.*;

    public class LogisticRegression {

    private static int size;
    /**
     * the learning step
     */
    private double step;

    /**
     * the weight to learn
     */
    private double[] weights;

    /**
     * the number of iterations
     */
    private int repeat = 1000;

    //constructor
    public LogisticRegression(int n) {
        this.step = 0.0001;
        weights = new double[n];
        this.size = n;
    }

    //the sigmoid function method. Calculated according to the types of the lecture.
    private static double sigmoidFunction(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    //the method for training the algorithm.
    public void train(List<RegressionInstance> instances) {
        for (int n = 0; n < repeat; n++) {
            for (int i = 0; i < instances.size(); i++) {
                int[] x = instances.get(i).x;
                double predicted = test(x);
                int label = instances.get(i).label;
                for (int j = 0; j < weights.length; j++) {
                    weights[j] = weights[j] + step * (label - predicted) * x[j];
                }
                step *= 0.9;
            }
        }
    }

    //checks a specific example to see if its spam or ham.
    public double test(int[] x) {
        double logit = .0;
        for (int i = 0; i < weights.length; i++) {
            logit += weights[i] * x[i];
        }
        return sigmoidFunction(logit);
    }

    //reads all the data from the file and stores them in order to be used for training.
    public static List<RegressionInstance> read(String file) {
        List<RegressionInstance> instances = new ArrayList<RegressionInstance>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            StringTokenizer tok;
            StringTokenizer tok1;
            while (line != null) {
                tok = new StringTokenizer(line, ",");

                int[] x;
                while (tok.hasMoreTokens()) {
                    x = new int[size];
                    int i = 0;
                    tok1 = new StringTokenizer(tok.nextToken(), " ");
                    while (tok1.hasMoreTokens() && i < size) {
                        x[i] = Integer.parseInt(tok1.nextToken());
                        i++;
                    }
                    RegressionInstance ri = new RegressionInstance(Integer.parseInt(tok1.nextToken()), x);
                    instances.add(ri);
                }

                line = reader.readLine();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instances;
    }

}
