package ua.rstkhldntsk.servlet.service;

import ua.rstkhldntsk.servlet.model.User;

import javax.servlet.http.HttpServletRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static ua.rstkhldntsk.servlet.constants.SQLQueries.COUNT_INVOICES_FY_USER_ID;
import static ua.rstkhldntsk.servlet.constants.TextConstants.*;

public class ReportService {

    private static volatile ReportService instance;

    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new ReportService();
                }
            }
        }
        return instance;
    }

    public void makeReport(int countOfAllChecks, double totalMoney, HttpServletRequest request) {
        request.setAttribute(COUNT_INVOICES_FY_USER_ID, countOfAllChecks);
        request.setAttribute(TOTAL_SUM, totalMoney);
    }

    public void writeZReport(User user, int countOfAllChecks, double totalMoney) {
        try (FileWriter fileWriter = new FileWriter(REPORT_FILE_PATH + REPORT_TYPE + LocalDate.now() + TXT)) {
            fileWriter.write(REPORT_FROM + LocalTime.now().toString() + LINE_SEPARATOR);
            fileWriter.write(REPORT_CASHIER + user.getUsername() + LINE_SEPARATOR);
            fileWriter.write(REPORT_COUNT_OF_CHECKS + countOfAllChecks + LINE_SEPARATOR);
            fileWriter.write(REPORT_TOTAL_MONEY + totalMoney);
        } catch (IOException e) {
        }
    }

}
