package ua.rstkhldntsk.servlet.services;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class XReportService {

//    private static final Logger LOGGER = Logger.getLogger(XReportService.class);

    private static volatile XReportService instance;

    public static XReportService getInstance() {
        if (instance == null) {
            synchronized (XReportService.class) {
                if (instance == null) {
                    instance = new XReportService();
                }
            }
        }
        return instance;
    }

    public void makeReport(int countOfInvoices, double totalMoney, HttpServletRequest request) {
        request.setAttribute("COUNT", countOfInvoices);
        request.setAttribute("TOTAL_SUM", totalMoney);
    }

}
