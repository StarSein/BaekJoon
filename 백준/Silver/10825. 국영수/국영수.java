import java.util.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Student> stdArr = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            stdArr.add(new Student(name, k, e, m));
        }

        Collections.sort(stdArr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Student s: stdArr) {
            bw.write(s.toString());
            bw.newLine();
        }
        bw.flush();
        return;
    }
}

class Student implements Comparable<Student> {
    String name;
    int k;
    int e;
    int m;

    Student(String name, int k, int e, int m) {
        this.name = name;
        this.k = k;
        this.e = e;
        this.m = m;
    }

    @Override
    public int compareTo(Student o) {
        if (this.k != o.k) {
            return this.k < o.k ? 1 : -1;
        }
        if (this.e != o.e) {
            return this.e > o.e ? 1 : -1;
        }
        if (this.m != o.m) {
            return this.m < o.m ? 1 : -1;
        }
        if (!(this.name).equals(o.name)) {
            return (this.name).compareTo(o.name) > 0 ? 1 : -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return this.name;
    }
}