import java.io.IOException;
import java.util.List;

import com.codeminders.labs.timeextractor.entities.Confidence;
import com.codeminders.labs.timeextractor.service.ConfidenceLevelService;
import com.codeminders.labs.timeextractor.service.SUTimeService;

import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.SUTime.Temporal;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

public class TrainingMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        SUTimeService service = new SUTimeService();
        ConfidenceLevelService confService = new ConfidenceLevelService();

        String date = "2014-07-30";
        String toPredict = "This section";
        System.out.println("To predict: " + toPredict);

        List<CoreMap> predicted = service.extractDatesAndTimeFromText(toPredict, date);
        confService.getConfidenceLevel(predicted);
        System.out.println(predicted);

        for (CoreMap cm : predicted) {
            TimeExpression timeExpr = cm.get(TimeExpression.Annotation.class);
            Temporal temporal = timeExpr.getTemporal();

            System.out.println("\n **********SuTime************ ");
            System.out.println("TimeLabel:" + temporal.getTimeLabel());
            System.out.println("TimexValue:" + temporal.getTimexValue());
            System.out.println("Duration:" + temporal.getDuration());
            System.out.println("Period:" + temporal.getPeriod());
            System.out.println("Range:" + temporal.getRange());
            System.out.println("Time:" + temporal.getTime());
            System.out.println("Type:" + temporal.getTimexType());
            System.out.println("confidence level: " + cm.get(Confidence.class));

            SUTime.Range range = temporal.getRange();
            // System.out.println(range.getJodaTimeInterval());
        }
    }
}