import com.group7.asd.dao.EvaluationDao;
import com.group7.asd.model.Evaluation;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EvaluationTest {

    //Test add evaluation
    @Test
    public void test1() throws Exception{
        Evaluation evaluation = new Evaluation();
        evaluation.setId("a6e06601-5953-4bfb-ad81-f61917f19558");
        evaluation.setPlatformRating(7);
        evaluation.setFeedback("Test");
        evaluation.setDeliveryPersonFeedback("Excellent");
        evaluation.setAttraction(Arrays.asList(new String[]{"Recommended by others","Attractive advertising"}));
        evaluation.setFoodOverallRating(3);
        evaluation.setFoodPackingRating(4);
        evaluation.setFoodTasteRating(5);
        EvaluationDao evaluationDao = new EvaluationDao();
        evaluationDao.createEvaluation(evaluation);

        Evaluation evaluationTest = evaluationDao.getEvaluationById("a6e06601-5953-4bfb-ad81-f61917f19558");
        assertEquals(7, evaluationTest.getPlatformRating());
    }

    //Test delete evaluation.
    @Test
    public void test2() throws Exception{
        EvaluationDao evaluationDao = new EvaluationDao();
        evaluationDao.deleteEvaluationById("a6e06601-5953-4bfb-ad81-f61917f19558");

        Evaluation evaluationTest = evaluationDao.getEvaluationById("a6e06601-5953-4bfb-ad81-f61917f19558");
        assertNull(evaluationTest.getId());
    }
}
