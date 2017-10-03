public class NeuralNetwork {
    private Perceptron[] perceptrons;

    public NeuralNetwork(int population, int inputs) {

        this.perceptrons = new Perceptron[population];

        for(int i = 0 ; i < population ; i++){
            perceptrons[i] = new Perceptron(inputs,0);
        }
    }

    public void TrainNetwork(){

    }

}
