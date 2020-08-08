package output;

import java.util.Date;

public class WaitingTimeLine {
    private static final String QUERY_LINE_MODEL = "D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]";
    private static final String WAITING_TIME_LINE_MODEL = "C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time";
    private String serviceId;
    private String questionTypeId;
    private String responseType;
    private Date date;
    private int count;

    public WaitingTimeLine(String serviceId, String questionTypeId, String responseType, Date date, int count) {
        this.serviceId = serviceId;
        this.questionTypeId = questionTypeId;
        this.responseType = responseType;
        this.date = date;
        this.count = count;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public String getResponseType() {
        return responseType;
    }

    public Date getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "WaitingTimeLine{" +
                "serviceId='" + serviceId + '\'' +
                ", questionTypeId='" + questionTypeId + '\'' +
                ", date=" + date +
                ", count=" + count +
                '}';
    }
}
