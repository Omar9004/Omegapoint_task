import java.io.FileNotFoundException;
import java.util.HashMap;

class uniTest1 {

    @org.junit.jupiter.api.Test
    void specsCheck() throws FileNotFoundException {
        winnerFinder wf = new winnerFinder("test.txt");

        HashMap<String,player> hashMap = wf.fileScanner();

        wf.errorLogPrinter();
    }
}