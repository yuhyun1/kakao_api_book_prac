package application;

import api.KakaoApi;
import validation.Validator;
import view.InputReader;
import view.OutputPrinter;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public class BookSearchApp {
    OutputPrinter outputPrinter = new OutputPrinter();
    InputReader inputReader = new InputReader();
    KakaoApi kakaoApi = new KakaoApi();
    Validator validator = new Validator();
    public void run() throws IOException, InterruptedException {

        outputPrinter.bookName();
        String bookName = inputReader.readLine();

        String revisedBookTitle = validator.modify(bookName);

        String responseBody = kakaoApi.searchBookInfo(revisedBookTitle);

        kakaoApi.extractInfo(responseBody);

        List<JsonObject> list = kakaoApi.getExtractedInfoList();
        outputPrinter.printBookInfo(list);

        outputPrinter.printAskSave();
        String saveAnswer = inputReader.readLine();

        validator.checkAnswer(saveAnswer, list);

    }
}
