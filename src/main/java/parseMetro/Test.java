package parseMetro;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        info<String> inf = new info<>("hello");
        Map<String , Integer> wordsCount = new HashMap<>();
        wordsCount.merge("Java",1,Integer::sum);
        wordsCount.merge("pok",1,Integer::sum);
        wordsCount.merge("Java",1,Integer::sum);
        wordsCount.forEach((word,count)-> System.out.println(word+" "+count));
    }

    static class info<T>{
        private T value;
        public info(T value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return "info{" +
                    "value=" + value +
                    '}';
        }
    }
}
