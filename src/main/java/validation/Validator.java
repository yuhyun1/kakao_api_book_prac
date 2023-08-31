package validation;

import api.KakaoApi;
import dao.BookDao;
import service.SaveBooks;
import view.InputReader;
import view.OutputPrinter;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.List;

public class Validator {
    OutputPrinter outputPrinter = new OutputPrinter();
    KakaoApi kakaoApi = new KakaoApi();
    BookDao bookDao = new BookDao();
    InputReader inputReader = new InputReader();

    public String modify(String book) {
        return book.replaceAll("\\s", "");
    }

    public void checkSave(int cnt) {
        if (cnt > 0) {
            outputPrinter.printSaveSuccessfully();
        } else {
            outputPrinter.printFailToSave();
        }
    }

    public void checkAnswer(String saveAnswer, List<JsonObject> list) throws IOException {
        if (isValidAnswer(saveAnswer)) {
            ifYSaveBooks(saveAnswer, list);
        } else {
            repeatUntilValidAnswer(saveAnswer, list);
        }
    }

    public void repeatUntilValidAnswer(String saveAnswer, List<JsonObject> list) throws IOException {

        String answer = saveAnswer;

        while (true) {

            if (isValidAnswer(answer)) {
                ifYSaveBooks(answer, list);
                break;
            }

            outputPrinter.printNotCorrectAnswer();
            outputPrinter.printAskSave();
            answer = inputReader.readLine();

        }

    }

    public boolean isValidAnswer(String answer) {
        return answer.equals("Y") || answer.equals("N");
    }

    public void ifYSaveBooks(String answer, List<JsonObject> list) {
        if (answer.equals("Y")) {
            SaveBooks.setBookList(list);
        }
        outputPrinter.printTableList(bookDao.bookList());
    }

}