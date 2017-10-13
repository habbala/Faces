public class NeuralNetwork {

    private Perceptron[][] perceptrons;

    public NeuralNetwork(int ySize, int xSize, int inputs) {

        this.perceptrons = new Perceptron[ySize][xSize];

        for(int y = 0 ; y < ySize ; y++) {
            for (int x = 0; x < xSize; x++) {
                perceptrons[y][x] = new Perceptron(inputs);
            }
        }
    }

    public void trainNetwork(){

    }
}
