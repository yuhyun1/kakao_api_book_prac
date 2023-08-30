package application;

import api.KakaoApi;
import dao.BookDao;
import save.SaveBooks;
import validation.Validator;
import view.InputReader;
import view.OutputPrinter;
import view.Repeat;

import java.io.IOException;

public class BookSearchApp {
    OutputPrinter outputPrinter = new OutputPrinter();
    Repeat repeat = new Repeat();
    InputReader inputReader = new InputReader();
    KakaoApi kakaoApi = new KakaoApi();
    Validator validator = new Validator();
    BookDao bookDao = new BookDao();
    public void run() throws IOException, InterruptedException {

        outputPrinter.bookName();
        String bookName = inputReader.readLine();

        String revisedBookTitle = validator.modify(bookName);

        String responseBody = kakaoApi.searchBookInfo(revisedBookTitle);

        kakaoApi.extractInfo(responseBody);

        outputPrinter.printBookInfo(kakaoApi.getExtractedInfoList());

        outputPrinter.printAskSave();
        String saveAnswer = inputReader.readLine();

        // Y나 N까지 받았어
        // y든 n이든 테이블리스트는 출력
        // y라면 저장하고 저장성공 출력 후 테이블리스트 출력
        // n이라면 그냥 테이블리스트 출력
        // 둘다 아니라면 다시 y나 n일 때까지 계속 입력받기

        if (validator.checkCorrectAnswer(saveAnswer)) {
            if (saveAnswer.equals("Y")) {
                SaveBooks.setBookList(kakaoApi.getExtractedInfoList());
            }
            outputPrinter.printTableList(bookDao.bookList());
        } else {
            repeat.repeatUntilValidAnswer(saveAnswer);
        }

    }
}
