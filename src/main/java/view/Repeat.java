package view;

import api.KakaoApi;
import dao.BookDao;
import save.SaveBooks;

import java.io.IOException;

public class Repeat {

    OutputPrinter outputPrinter = new OutputPrinter();
    InputReader inputReader = new InputReader();
    KakaoApi kakaoApi = new KakaoApi();
    BookDao bookDao = new BookDao();

    public void repeatUntilValidAnswer(String saveAnswer) throws IOException {

        String answer = saveAnswer;

        while (true) {

            if (answer.equals("Y") || answer.equals("N")) {
                if (answer.equals("Y")) {
                    SaveBooks.setBookList(kakaoApi.getExtractedInfoList());
                }
                outputPrinter.printTableList(bookDao.bookList());
                break;
            }

            outputPrinter.printNotCorrectAnswer();
            outputPrinter.printAskSave();
            answer = inputReader.readLine();

        }

    }

}