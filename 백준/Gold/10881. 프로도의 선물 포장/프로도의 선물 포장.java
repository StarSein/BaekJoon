import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {

    static class Box {
        int width;
        int height;

        Box(int width, int height) {
            this.width = width;
            this.height = height;
        }

        int getSize() {
            return width * height;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; t++) {
            Box[] boxes = new Box[3];
            for (int i = 0; i < 3; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                boxes[i] = new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            int minSize = Integer.MAX_VALUE;
            for (int i = 0; i < 2; i++) {
                Box boxA = boxes[i];
                for (int j = i + 1; j < 3; j++) {
                    Box boxB = boxes[j];
                    Box[] mergedBoxes = getListOfMergedBox(boxA, boxB);

                    final int fi = i;
                    final int fj = j;
                    int k = IntStream.range(0, 3).filter(e -> e != fi && e != fj).findAny().getAsInt();
                    Box boxC = boxes[k];
                    for (Box mergedBox : mergedBoxes) {
                        Box[] wrappedBoxes = getListOfMergedBox(mergedBox, boxC);

                        int curMinSize = Stream.of(wrappedBoxes).mapToInt(Box::getSize).min().getAsInt();
                        minSize = Math.min(minSize, curMinSize);
                    }
                }
            }

            answer.append(minSize).append('\n');
        }

        System.out.println(answer);
    }

    static Box[] getListOfMergedBox(Box boxA, Box boxB) {
        return new Box[] {
                new Box(boxA.width + boxB.width, Math.max(boxA.height, boxB.height)),
                new Box(boxA.width + boxB.height, Math.max(boxA.height, boxB.width)),
                new Box(boxA.height + boxB.width, Math.max(boxA.width, boxB.height)),
                new Box(boxA.height + boxB.height, Math.max(boxA.width, boxB.width))
        };
    }
}
