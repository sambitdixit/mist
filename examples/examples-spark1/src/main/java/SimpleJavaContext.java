import mist.api.jdsl.*;

import mist.api.JobDef;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SimpleJavaContext extends JMistJob<Integer> {

    @Override
    public JobDef<Integer> defineJob() {
        return withArgs(intArg("num")).onJSparkContext((num, sc) -> {
            List<Integer> nums = Stream.of(0, 10).collect(Collectors.toList());
            List<Integer> result = sc.parallelize(nums).map(x -> x * 2).collect();
            return intRetVal(5);
        });
    }
}
