import java.io.*;
import java.util.*;


public class Main {

    static class SearchOption {
        ArrayList<Integer>[] locs;
        int d;
        boolean isHorizontal;

        SearchOption(ArrayList<Integer>[] locs, int d, boolean isHorizontal) {
            this.locs = locs;
            this.d = d;
            this.isHorizontal = isHorizontal;
        }
    }

    static final int AXIS = 100_000, INIT_X = 0, INIT_Y = 0;
    static int N, M, cx, cy;
    static ArrayList<Integer>[] xLocs, yLocs;
    static char[] cmd;
    static Map<Character, SearchOption> searchOptionMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        xLocs = new ArrayList[2 * AXIS + 1];
        yLocs = new ArrayList[2 * AXIS + 1];
        for (int i = 0; i < xLocs.length; i++) {
            xLocs[i] = new ArrayList<>();
            yLocs[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            xLocs[X + AXIS].add(Y);
            yLocs[Y + AXIS].add(X);
        }
        cmd = br.readLine().toCharArray();

        for (int i = 0; i < xLocs.length; i++) {
            if (!xLocs[i].isEmpty()) {
                xLocs[i].sort(Comparator.naturalOrder());
            }
            if (!yLocs[i].isEmpty()) {
                yLocs[i].sort(Comparator.naturalOrder());
            }
        }

        searchOptionMap = new HashMap<>();
        searchOptionMap.put('L', new SearchOption(yLocs, -1, true));
        searchOptionMap.put('R', new SearchOption(yLocs, 1, true));
        searchOptionMap.put('U', new SearchOption(xLocs, 1, false));
        searchOptionMap.put('D', new SearchOption(xLocs, -1, false));

        cx = INIT_X;
        cy = INIT_Y;

        for (char c : cmd) {
            moveToNextLoc(searchOptionMap.get(c));
        }

        System.out.printf("%d %d", cx, cy);
    }

    static void moveToNextLoc(SearchOption option) {
        if (option.isHorizontal) {
            cx = searchNextLoc(option.locs[cy + AXIS], cx, option.d);
        } else {
            cy = searchNextLoc(option.locs[cx + AXIS], cy, option.d);
        }
    }

    static int searchNextLoc(ArrayList<Integer> loc, int curVal, int d) {
        int curIdx = Collections.binarySearch(loc, curVal);
        return loc.get(curIdx + d);
    }
}
