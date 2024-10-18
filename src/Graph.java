import java.io.FileWriter;
import java.io.IOException;

public class Graph {
    FileWriter writer;

    public Graph(String fileName) {
        //Create the fileWriter
        try {
            writer = new FileWriter("../graph/"+fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeData(double dataX, double dataY){
        try {
            writer.write(dataX + "\t" + dataY +"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
