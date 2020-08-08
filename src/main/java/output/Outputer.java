package output;

import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Outputer {
    //models
    private static final int TYPE_INDEX = 0;
    private static final int SERVICE_ID_INDEX = 1;
    private static final int QUESTION_TYPE_ID_INDEX = 2;
    private static final int RESPONSE_TYPE_INDEX = 3;
    private static final int DTE_INDEX = 4;
    private static final int COUNT_INDEX = 5;
    private static final String QUERY = "D";
    private static final String WAITING_TIME_LINE = "C";
    //condition
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String EMPTY_ANSWER = "-";
    private static final String ALL_QUESTIONS = "*";
    //fields
    private DateFormat simpleDateFormat;
    private List<WaitingTimeLine> timeLines;

    public List<String> output(List<String> inputs) {
        List<String> output = new ArrayList<String>();

        if (inputs != null && inputs.size() > 0) {
            for (int i = 0; i < inputs.size(); i++) {
                String[] model = inputs.get(i).split(" ");

                if (model[TYPE_INDEX].equals(WAITING_TIME_LINE)) {
                    timeLines.add(new WaitingTimeLine(model[SERVICE_ID_INDEX],
                            model[QUESTION_TYPE_ID_INDEX],
                            model[RESPONSE_TYPE_INDEX],
                            getFirstDate(model[DTE_INDEX]),
                            Integer.parseInt(model[COUNT_INDEX])));
                } else if (model[TYPE_INDEX].equals(QUERY)) {
                    Date first = getFirstDate(model[DTE_INDEX]);
                    Date last = getSecondDate(model[DTE_INDEX]);

                    if (first != null && last != null) {
                        int count = 0;
                        int amount = 0;

                        for (WaitingTimeLine timeLine : timeLines) {
                            if (dateIsBetween(timeLine.getDate(), first, last)
                                    && checkQuestion(timeLine.getQuestionTypeId(), model[QUESTION_TYPE_ID_INDEX])
                                    && checkService(timeLine.getServiceId(), model[SERVICE_ID_INDEX])
                                    && timeLine.getResponseType().equals(model[RESPONSE_TYPE_INDEX])) {
                                amount++;
                                count += timeLine.getCount();
                            }
                        }

                        if (amount == 0) {
                            output.add(EMPTY_ANSWER);
                        } else {
                            output.add((count / amount) + "");
                        }
                    } else {
                        output.add(EMPTY_ANSWER);
                    }
                }
            }
        }

        return output;
    }

    private Date getFirstDate(String value) {
        try {
            return simpleDateFormat.parse(value.substring(0, DATE_FORMAT.length()));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Date getSecondDate(String value) {
        try {
            if (value.length() > DATE_FORMAT.length()) {
                return simpleDateFormat.parse(value.substring(value.length() - DATE_FORMAT.length()));
            } else {
                return null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean dateIsBetween(Date date, Date min, Date max) {
        return date.after(min) && date.before(max);
    }

    private boolean checkService(String serviceWaitingTimeLine, String serviceQuery) {
        return serviceQuery.equals(serviceWaitingTimeLine.substring(0, serviceQuery.length()));
    }

    private boolean checkQuestion(String questionWaitingTimeLine, String questionQuery) {
        if (questionQuery.equals(ALL_QUESTIONS)) {
            return true;
        }

        return questionQuery.equals(questionWaitingTimeLine.substring(0, questionQuery.length()));
    }

    public Outputer() {
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        timeLines = new ArrayList<WaitingTimeLine>();
    }
}
